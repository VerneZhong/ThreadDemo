package com.zhongxb.concurrent.chapter29.example04;

/**
 * User对象，代表聊天室的参与者
 * @author Mr.zxb
 * @date 2018-11-07 17:28
 */
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
