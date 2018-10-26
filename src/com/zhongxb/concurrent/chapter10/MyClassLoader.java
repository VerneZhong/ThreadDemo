package com.zhongxb.concurrent.chapter10;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 自定义类加载器，必须继承于ClassLoader
 * 双亲委托机制，会从父加载器加载
 */
public class MyClassLoader extends ClassLoader {

    private static final Path DEFAULT_CLASS_DIR = Paths.get("D:", "classloader3");

    private final Path classDir;

    /**
     * 使用默认的class路径
     */
    public MyClassLoader() {
        super();
        this.classDir = DEFAULT_CLASS_DIR;
    }

    /**
     * 允许传入指定的class路径
     * @param classDir
     */
    public MyClassLoader(String classDir) {
        super();
        this.classDir = Paths.get(classDir);
    }

    /**
     * 指定class路径的同时，指定父类加载器
     * @param classDir
     * @param parent
     */
    public MyClassLoader(String classDir, ClassLoader parent) {
        super(parent);
        this.classDir = Paths.get(classDir);
    }

    /**
     * 重写父类的findClass方法，必须
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 读取class的二进制数据
        byte[] classByte = this.readClassBytes(name);

        if (classByte == null || classByte.length == 0) {
            throw new ClassNotFoundException("Can not load the class " + name);
        }
        return this.defineClass(name, classByte, 0, classByte.length);
    }

    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        // 将包名分隔转成文件分隔符
        String classPath = name.replace(".", "/");

        Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));

        if (!classFullPath.toFile().exists()) {
            throw new ClassNotFoundException("The class " + name + " not found.");
        }
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException("load the class " + name + "occur error.", e);
        }
    }

    @Override
    public String toString() {
        return "My ClassLoader";
    }
}
