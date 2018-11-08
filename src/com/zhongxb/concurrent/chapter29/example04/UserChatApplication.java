package com.zhongxb.concurrent.chapter29.example04;

import com.zhongxb.concurrent.chapter29.example03.AsyncEventDispatcher;

/**
 * @author Mr.zxb
 * @date 2018-11-07 17:58
 */
public class UserChatApplication {

    public static void main(String[] args) {

        // 定义异步的router
        AsyncEventDispatcher dispatcher = new AsyncEventDispatcher();

        // 为Router注册Channel和Event之间的关系
        dispatcher.registerChannel(UserOnlineEvent.class, new UserOnlineEventChannel());

        dispatcher.registerChannel(UserOfflineEvent.class, new UserOfflineEventChannel());

        dispatcher.registerChannel(UserChatEvent.class, new UserChatEventChannel());

        // 启动登陆聊天室的User
        new UserChatThread(new User("zxb"), dispatcher).start();
        new UserChatThread(new User("Leo"), dispatcher).start();
        new UserChatThread(new User("Alex"), dispatcher).start();
        new UserChatThread(new User("Tina"), dispatcher).start();
    }
}
