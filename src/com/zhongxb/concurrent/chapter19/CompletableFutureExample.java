package com.zhongxb.concurrent.chapter19;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CompletableFuture Java8提供的异步编程执行任务
 *
 * @author Mr.zxb
 * @date 2018-10-30 17:42
 */
public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 使用ForkJoinPool.commonPool()作为它的线程池执行异步代码
        // 无返回值的异步任务
//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("The task is running.");
//        });
        // 会阻塞，等待异步任务结束
//        future.get();
//        System.out.println("The task is finish.");
        // 有返回值的异步任务
//        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "hello");
        // future.get()在等待执行结果时，程序会一直阻塞，如果此时调用complete方法会立即执行
        // complete方法只能调用一次，重复调用无效
        // 如果future已经执行完毕能够返回结果，此时再调用complete则会无效
//        TimeUnit.SECONDS.sleep(5);
//        future1.complete(" world");
        // 如果使用completeExceptionally则抛出一个异常，而不是一个成功的结果
//        future1.completeExceptionally(new RuntimeException("出错啦."));
//        System.out.println("future1:" + future1.get());

        // thenApply接受一个function参数用来转换CompletableFuture
//        future1 = future1.thenApply(s -> s + " world").thenApply(String::toUpperCase);
//        System.out.println(future1.get());

        // 数据流的类型转换：String -> Integer -> Double
//        CompletableFuture<Double> future2 = CompletableFuture.supplyAsync(() -> "10")
//                .thenApply(Integer::parseInt).thenApply(i -> i * 10.0);
//        System.out.println("future2:" + future2.get());

        // 在异步操作完成的时候对异步操作的结果进行一些操作，仍然返回CompletableFuture
//        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "Hello")
//                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
//        System.out.println("future3:" + future3.get());

//        CompletableFuture<Double> future4 = CompletableFuture.supplyAsync(() -> "100")
//                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "100"))
//                .thenCompose(s -> CompletableFuture.supplyAsync(() -> Double.parseDouble(s)));
//        System.out.println("future4:" + future4.get());

        //当两个CompletableFuture都正常完成后，执行提供的fn，用它来组合另外一个CompletableFuture的结果
//        CompletableFuture<String> future5 = CompletableFuture.supplyAsync(() -> "100");
//        CompletableFuture<Integer> future6 = CompletableFuture.supplyAsync(() -> 100);
//        CompletableFuture<String> future7 = future5.thenCombine(future6, (i, j) -> i + j);
//        System.out.println("future7:" + future7.get());

        //当两个CompletableFuture都正常完成后，执行提供的action，用它来组合另外一个CompletableFuture的结果
//        CompletableFuture<String> future8 = CompletableFuture.supplyAsync(() -> "10");
//        CompletableFuture<Integer> future9 = CompletableFuture.supplyAsync(() -> 10);
//        CompletableFuture<Void> future10 = future8.thenAcceptBoth(future9, (s, i) -> System.out.println("future10:"+ s + i));
//        future10.get();

        //当CompletableFuture完成计算结果时对结果进行处理，或者当CompletableFuture产生异常的时候对异常进行处理
//        CompletableFuture.supplyAsync(() -> "Hello").thenApply(s -> s + " World")
//                        .thenApply(s -> s + "\nThis is CompletableFuture demo.")
//                        .thenApply(String::toLowerCase)
//                        .whenComplete((result, throwable) -> System.out.println(result));

        // 当CompletableFuture完成计算结果或者抛出异常的时候，执行提供的fn
//        CompletableFuture<Double> future11 = CompletableFuture.supplyAsync(() -> "100")
//                .thenApply(s -> s + 100).handle((s, t) -> s != null ? Double.parseDouble(s) : 0);
//        System.out.println("future11:" + future11.get());

        // 当CompletableFuture完成计算结果，只对结果执行Action，而不返回新的计算值
//        CompletableFuture.supplyAsync(() -> "Hello").thenApply(s -> s + " world")
//                .thenApply(s -> s + "\nThis is demo").thenApply(String::toUpperCase)
//                .thenAccept(System.out::println);


//        ThreadLocalRandom random = ThreadLocalRandom.current();
//        CompletableFuture<String> future12 = CompletableFuture.supplyAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(random.nextInt(10));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "from future12";
//        });

//        CompletableFuture<String> future13 = CompletableFuture.supplyAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(random.nextInt(10));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "from future13";
//        });
        //当任意一个CompletableFuture完成的时候，action这个消费者就会被执行
//        CompletableFuture<Void> future14 = future12.acceptEither(future13, s -> System.out.println("The future is " + s));
//        future14.get();
        // 当任意一个CompletableFuture完成的时候，fn会被执行，它的返回值会当作新的CompletableFuture<U>的计算结果
//        CompletableFuture<String> future15 = future12.applyToEither(future13, s -> "The future is " + s);
//        System.out.println("future15:" + future15.get());

        // allOf在所有Future对象完成后结束，并返回一个future
//        CompletableFuture<String> future16 = CompletableFuture.supplyAsync(() -> "tony");
//        CompletableFuture<String> future17 = CompletableFuture.supplyAsync(() -> "allen");
//        CompletableFuture<String> future18 = CompletableFuture.supplyAsync(() -> "caitlin");
//        CompletableFuture.allOf(future16, future17, future18).thenApply(v -> Stream.of(future16, future17, future18)
//                .map(CompletableFuture::join).collect(Collectors.joining(" "))).thenAccept(System.out::println);

        // anyOf在任意一个Future对象结束后结束，并返回一个future
//        CompletableFuture<Object> future19 = CompletableFuture.anyOf(future12, future13);
//        System.out.println(future19.get());

        // 只有当CompletableFuture抛出异常的时候，才会触发这个exceptionally的计算，调用function计算值
//        CompletableFuture.supplyAsync(() -> "hello world")
//                .thenApply(s -> {
////                    s = null;
//                    int length = s.length();
//                    return length;
//                }).thenAccept(i -> System.out.println(i))
//                .exceptionally(throwable -> {
//                    System.out.println("error:" + throwable);
//                    return null;
//                });
    }
}
