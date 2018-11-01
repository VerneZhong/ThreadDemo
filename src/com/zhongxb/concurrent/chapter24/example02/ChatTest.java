package com.zhongxb.concurrent.chapter24.example02;

import java.io.IOException;

/**
 * @author Mr.zxb
 * @date 2018-11-01 09:30
 */
public class ChatTest {

    public static void main(String[] args) throws IOException {

        new ChatServer().startServer();
    }
}
