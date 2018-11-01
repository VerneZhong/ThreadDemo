package com.zhongxb.concurrent.chapter24.example02;

import java.io.*;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.net.Socket;

/**
 * 响应客户端连接的Handler：主要负责和客户端进行通信交互
 *
 * @author Mr.zxb
 * @date 2018-11-01 09:19
 */
public class ClientHandler implements Runnable {

    /**
     * 客户端的socket连接
     */
    private final Socket socket;

    /**
     * 客户端的鉴定
     */
    private final String clientIdentify;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.clientIdentify = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
    }

    @Override
    public void run() {
        try {
            this.chat();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.release();
        }
    }

    private void chat() throws IOException {
        BufferedReader bufferedReader = wrap2PrintReader(this.socket.getInputStream());
        PrintStream printStream = wrap2Print(this.socket.getOutputStream());
        String received;
        while ((received = bufferedReader.readLine()) != null) {
            // 将客户端发送的消息输出到控制台
            System.out.printf("client:%s-message:%s\n", clientIdentify, received);
            if (received.equals("quit")) {
                // 如果客户端发送了quit指令，则断开与客户端的连接
                write2Client(printStream, " client will close.");
                this.socket.close();
                break;
            }
            // 向客户端发送消息
            write2Client(printStream, "Server:" + received);
        }
    }

    /**
     * 将输入字节流封装成BufferedReader缓存字符流
     *
     * @param inputStream
     * @return
     */
    private BufferedReader wrap2PrintReader(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * 将输出字节流封装成PrintStream
     *
     * @param outputStream
     * @return
     */
    private PrintStream wrap2Print(OutputStream outputStream) {
        return new PrintStream(outputStream);
    }

    /**
     * 该方法主要用于向客户端发送消息
     *
     * @param printStream
     * @param message
     */
    private void write2Client(PrintStream printStream, String message) {
        printStream.println(message);
        printStream.flush();
    }

    /**
     * 释放资源
     */
    private void release() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            if (socket != null) {
                // 将socket实例加入Tracker中
                SocketCleaningTracker.tracker(socket);
            }
        }
    }

    /**
     * 当关闭socket失败时，交给SocketCleaningTracker处理
     * 当JVM 对socket对象进行垃圾回收时可尝试再次进行资源释放
     */
    private static class SocketCleaningTracker {

        /**
         * 定义ReferenceQueue
         */
        private static final ReferenceQueue<Socket> queue = new ReferenceQueue<>();

        static {
            // 启动Cleaner线程
            new Cleaner().start();
        }

        private static void tracker(Socket socket) {
            new Tracker(socket, queue);
        }

        private static class Cleaner extends Thread {

            private Cleaner() {
                super("SocketCleaningTracker");
                setDaemon(true);
            }

            @Override
            public void run() {
                while (true) {
                    try {
                        // 当Tracker被垃圾回收器回收时会加入Queue中
                        Tracker tracker = (Tracker) queue.remove();
                        tracker.close();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * Tracker是一个PhantomReference的子类
         */
        private static class Tracker extends PhantomReference<Socket> {

            private final Socket socket;

            public Tracker(Socket socket, ReferenceQueue<? super Socket> queue) {
                super(socket, queue);
                this.socket = socket;
            }

            public void close() {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
