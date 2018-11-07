package com.zhongxb.concurrent.chapter28.example02;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * 对WatchEvent.Kind和Path的包装，一旦目录发生任何变化，都会提交FileChangeEvent事件
 * @author Mr.zxb
 * @date 2018-11-07 14:53
 */
public class FileChangeEvent {

    private final Path path;

    private final WatchEvent.Kind<?> kind;

    public FileChangeEvent(Path child, WatchEvent.Kind<?> kind) {
        this.path = child;
        this.kind = kind;
    }

    public Path getPath() {
        return path;
    }

    public WatchEvent.Kind<?> getKind() {
        return kind;
    }
}
