package com.zhongxb.concurrent.chapter29.example04;

import com.zhongxb.concurrent.chapter29.example02.Event;
import com.zhongxb.concurrent.chapter29.example03.AsyncChannel;

/**
 * 用户聊天的Event，简单输出用户上线即可
 * @author Mr.zxb
 * @date 2018-11-07 17:33
 */
public class UserChatEventChannel extends AsyncChannel {

    @Override
    protected void handle(Event message) {
        UserChatEvent event = (UserChatEvent) message;
        System.out.println("The User [" + event.getUser().getName() + "] say:" + event.getMessage());
    }
}
