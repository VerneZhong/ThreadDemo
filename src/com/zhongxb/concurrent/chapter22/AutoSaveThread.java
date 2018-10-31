package com.zhongxb.concurrent.chapter22;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 文档自动保存线程
 *
 * @author Mr.zxb
 * @date 2018-10-31 11:25
 */
public class AutoSaveThread extends Thread {

    private final Document document;

    public AutoSaveThread(Document document) {
        super("DocumentAutoSaveThread");
        this.document = document;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 每隔一秒自动保存一次文档
                document.save();
                TimeUnit.SECONDS.sleep(1);
            } catch (IOException e) {
                break;
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
