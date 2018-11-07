package com.zhongxb.concurrent.chapter28.example02;

import com.zhongxb.concurrent.chapter28.example01.EventBus;

import java.nio.file.*;

/**
 * 监控目录文件的变化，采用Jdk7提供的WatchService和EventBus，可以基于事件通知的方式监控文件或者目录的变化
 * @author Mr.zxb
 * @date 2018-11-07 14:41
 */
public class DirectoryTargetMonitor {

    private WatchService watchService;

    private final EventBus eventBus;

    private final Path path;

    private volatile boolean start = false;

    public DirectoryTargetMonitor(EventBus eventBus, String targetPath) {
        this(eventBus, targetPath, "");
    }

    /**
     * 构造Monitor的时候传入EventBus以及需要监控的目录
     * @param eventBus
     * @param targetPath
     * @param morePaths
     */
    public DirectoryTargetMonitor(EventBus eventBus, String targetPath, String...morePaths) {
        this.eventBus = eventBus;
        this.path = Paths.get(targetPath, morePaths);
    }

    /**
     * 开始监控目录
     * @throws Exception
     */
    public void startMonitor() throws Exception {
        this.watchService = FileSystems.getDefault().newWatchService();
        // 为路径注册感兴趣的事件，创建，更新，删除
        this.path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_CREATE);
        System.out.printf("The directory [%s] is monitoring... \n", path);

        this.start = true;
        while (start) {
            WatchKey watchKey = null;
            try {
                // 当有事件发生时会返回对应的WatchKey
                watchKey = watchService.take();
                watchKey.pollEvents().forEach(event -> {
                    WatchEvent.Kind<?> kind = event.kind();
                    Path path = (Path) event.context();
                    Path child = DirectoryTargetMonitor.this.path.resolve(path);
                    // 提交FileChangeEvent到EventBus
                    eventBus.post(new FileChangeEvent(child, kind));
                });
            } catch (Exception e) {
                this.start = false;
            } finally {
                if (watchKey != null) {
                    watchKey.reset();
                }
            }
        }
    }

    /**
     * 停止监控
     * @throws Exception
     */
    public void stopMonitor() throws Exception {
        System.out.printf("The directory [%s] monitor will be stop...\n", path);
        Thread.currentThread().interrupt();
        this.start = false;
        this.watchService.close();
        System.out.printf("The directory [%s] monitor will be stop done.\n", path);
    }
}
