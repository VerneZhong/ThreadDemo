package com.zhongxb.concurrent.chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FightQueryExample {

    /**
     * 初始化航班公司
     */
    private static List<String> fightCompany = Arrays.asList("CSA", "CEA", "HNA");

    public static void main(String[] args) {

        List<String> results = search("SH", "BJ");
        System.out.println("=========result===========");
        results.forEach(System.out::println);
    }

    private static List<String> search(String sh, String bj) {
        List<String> result = new ArrayList<>();

        // 创建查询航班信息的线程列表
        List<FightQueryTask> tasks = fightCompany.stream().map(f -> createSearchTask(f, sh, bj)).collect(Collectors.toList());

        // 启动线程
        tasks.forEach(Thread::start);

        // 调用线程join方法，阻塞当前线程
        tasks.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 当前线程阻塞，获取每个线程查询结果，返回结果
        tasks.stream().map(FightQueryTask::get).forEach(result::addAll);

        return result;
    }

    private static FightQueryTask createSearchTask(String f, String sh, String bj) {
        return new FightQueryTask(f, sh, bj);
    }

}
