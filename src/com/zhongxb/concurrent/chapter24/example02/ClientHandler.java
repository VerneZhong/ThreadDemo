package com.zhongxb.concurrent.chapter24.example02;

import java.io.*;
import java.net.Socket;

/**
 * 响应客户端连接的Handler：主要负责和客户端进行通信交互
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
     * @param inputStream
     * @return
     */
    private BufferedReader wrap2PrintReader(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * 将输出字节流封装成PrintStream
     * @param outputStream
     * @return
     */
    private PrintStream wrap2Print(OutputStream outputStream) {
        return new PrintStream(outputStream);
    }

    /**
     * 该方法主要用于向客户端发送消息
     * @param printStream
     * @param message
     */
    private void write2Client(PrintStream printStream, String message) {
        printStream.println(message);
        printStream.flush();
    }
}
