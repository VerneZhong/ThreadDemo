package com.zhongxb.concurrent.chapter29.example04;

/**
 * 代表用户聊天的Event
 * @author Mr.zxb
 * @date 2018-11-07 17:32
 */
public class UserChatEvent extends UserOnlineEvent {

    /**
     * 聊天信息
     */
    private String message;

    public UserChatEvent(User user, String message) {
        super(user);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
