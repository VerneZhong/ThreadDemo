package com.zhongxb.concurrent.chapter29.example04;

import com.zhongxb.concurrent.chapter29.example02.Event;

/**
 * 代表用户上线的Event
 * @author Mr.zxb
 * @date 2018-11-07 17:29
 */
public class UserOnlineEvent extends Event {

    private User user;

    public UserOnlineEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
