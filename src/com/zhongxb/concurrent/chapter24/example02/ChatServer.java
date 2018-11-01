package com.zhongxb.concurrent.chapter24.example02;

import com.zhongxb.concurrent.chapter08.DenyPolicy;
import com.zhongxb.concurrent.chapter08.ThreadPool;
import com.zhongxb.concurrent.chapter08.ThreadPoolBuilder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * 服务端程序
 *
 * @author Mr.zxb
 * @date 2018-11-01 09:11
 */
public class ChatServer {

    /**
     * 服务端端口
     */
    private final int port;

    /**
     * 线程池
     */
    private ThreadPool threadPool;

    /**
     * 服务端Socket
     */
    private ServerSocket serverSocket;

    /**
     * 默认使用13312端口
     */
    public ChatServer() {
        this(13312);
    }

    public ChatServer(int port) {
        this.port = port;
    }

    public void startServer() throws IOException {
        // 创建线程池，初始化一个线程，核心线程2，最大线程4，阻塞队列中最大可加入1000个任务
        this.threadPool = new ThreadPoolBuilder().setInitSize(1).setCoreSize(2).setMaxSize(4)
                .setQueueMaxSize(1000).setDenyPolicy(new DenyPolicy.DiscardDenyPolicy())
                .setKeepAliveTime(10).setTimeUnit(TimeUnit.SECONDS).build();
        this.serverSocket = new ServerSocket(port);
        this.serverSocket.setReuseAddress(true);
        System.out.println("Chat server is started and listen at port:" + port);
        this.listen();
    }

    private void listen() throws IOException {
        while (true) {
            // accept方法是阻塞方法，当有新的链接进入时才会返回，并且返回的是客户端的连接
            Socket client = serverSocket.accept();
            // 将客户端连接作为一个Request封装成对应的Handler然后提交给线程池
            this.threadPool.execute(new ClientHandler(client));
        }
    }
}
