package com.zhongxb.concurrent.chapter29.example04;

/**
 * 代表用户下线的Event
 * @author Mr.zxb
 * @date 2018-11-07 17:31
 */
public class UserOfflineEvent extends UserOnlineEvent {

    public UserOfflineEvent(User user) {
        super(user);
    }

}
