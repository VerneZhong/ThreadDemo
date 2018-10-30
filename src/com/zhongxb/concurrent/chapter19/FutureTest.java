package com.zhongxb.concurrent.chapter19;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr.zxb
 * @date 2018-10-30 13:57
 */
public class FutureTest {

    public static void main(String[] args) throws InterruptedException {

        // 定义不需要返回值的FutureService
//        FutureService<Void, Void> service = FutureService.newService();
        // submit方法为立即返回的方法
//        Future<?> future = service.submit(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("I am finish done.");
//        });
        // 会进入阻塞
//        future.get();

        // 定义需要返回值的FutureService
//        FutureService<String, Integer> service = FutureService.newService();
//        // submit会立即返回
//        Future<Integer> future = service.submit(input -> {
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return input.length();
//        }, "hello");
//        // get方法使当前线程进入阻塞，最终会返回计算的结果
//        System.out.println(future.get());

        FutureService<String, Integer> service = FutureService.newService();
        service.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input.length();
        }, "hello", System.out::println);

    }
}
