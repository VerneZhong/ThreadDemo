package com.zhongxb.concurrent.chapter27.example01;

import com.zhongxb.concurrent.chapter19.FutureTask;

/**
 * @author Mr.zxb
 * @date 2018-11-01 16:14
 */
public class ActiveFuture<T> extends FutureTask<T> {

    @Override
    public void finish(T result) {
        super.finish(result);
    }
}
