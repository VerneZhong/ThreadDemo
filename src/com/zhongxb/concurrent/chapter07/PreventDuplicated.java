package com.zhongxb.concurrent.chapter07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * hook应用：防止某个程序被重复启动，在进程启动时会创建lock文件，进程收到中断信号的时候会删除lock文件
 * 应用于Zookeeper、MySQL服务器、kafka
 */
public class PreventDuplicated {

    /**
     * lock文件路径
     */
    public static final String LOCK_PATH = "F:/home/zhongxb/locks/";
    /**
     * 文件扩展名
     */
    public static final String LOCK_FILE = ".lock";

    public static final String PERMISSIONS = "rw-------";

    public static void main(String[] args) throws IOException {

        // 1.注入hook线程，在程序退出的时候删除lock文件
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The program received kill signal.");
            // 删除lock文件
            getLockFile().toFile().delete();
        }));

        // 2.检查是否存在.lock文件
        checkRunning();

        // 3.模拟当前程序在运行
        while (true) {
            try {
                System.out.println("program is running.");
                TimeUnit.SECONDS.sleep(5);
                System.exit(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void checkRunning() throws IOException {
        Path path = getLockFile();
        if (path.toFile().exists()) {
            throw new RuntimeException("The program already running.");
        }
//        Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(PERMISSIONS);
//        Files.createFile(path, PosixFilePermissions.asFileAttribute(permissions));
        Files.createDirectories(getLockPath());
        Files.createFile(path);

    }

    private static Path getLockPath() {
        return Paths.get(LOCK_PATH);
    }

    private static Path getLockFile() {
        return Paths.get(LOCK_PATH, LOCK_FILE);
    }

}
