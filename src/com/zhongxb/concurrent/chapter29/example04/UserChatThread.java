package com.zhongxb.concurrent.chapter29.example04;

import com.zhongxb.concurrent.chapter29.example03.AsyncEventDispatcher;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.zxb
 * @date 2018-11-07 17:54
 */
public class UserChatThread extends Thread {

    private User user;

    private AsyncEventDispatcher dispatcher;

    public UserChatThread(User user, AsyncEventDispatcher dispatcher) {
        super(user.getName());
        this.user = user;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        try {
            // user上线，发送Online Event
            dispatcher.dispatch(new UserOnlineEvent(user));

            for (int i = 0; i < 5; i++) {
                // 发送user 聊天信息
                dispatcher.dispatch(new UserChatEvent(user, getName() + "-Hello-" + i));
                // 短暂休眠
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // user下线，发送Offline Event
            dispatcher.dispatch(new UserOfflineEvent(user));
        }
    }
}
