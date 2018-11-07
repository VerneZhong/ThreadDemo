package com.zhongxb.concurrent.chapter28.example02;

import com.zhongxb.concurrent.chapter28.example01.Subscribe;

/**
 * 监控目录变化
 * @author Mr.zxb
 * @date 2018-11-07 15:01
 */
public class FileChangeListener {

    /**
     * 当有事件发生到了默认的topic上后，该方法将被执行，
     * @param event
     */
    @Subscribe
    public void onChange(FileChangeEvent event) {
        System.out.printf("%s----%s\n", event.getPath(), event.getKind());
    }
}
