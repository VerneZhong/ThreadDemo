package com.zhongxb.concurrent.chapter22;

import java.io.IOException;
import java.util.Scanner;

/**
 * 主动编辑文档线程
 *
 * @author Mr.zxb
 * @date 2018-10-31 11:28
 */
public class DocumentEditThread extends Thread {

    private final String documentPath;

    private final String documentName;

    private final Scanner scanner = new Scanner(System.in);

    public DocumentEditThread(String documentPath, String documentName) {
        super("DocumentEditThread");
        this.documentPath = documentPath;
        this.documentName = documentName;
    }

    @Override
    public void run() {
        int times = 0;

        try {
            Document document = Document.create(documentPath, documentName);
            while (true) {
                // 获取用户的键盘输入
                String text = scanner.next();
                if ("quit".equals(text)) {
                    document.close();
                    break;
                }
                // 将内容编辑到document中
                document.edit(text);
                if (times == 5) {
                    // 用户输入了5次后进行文档保存
                    document.save();
                    times = 0;
                }
                times++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
