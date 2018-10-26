package com.zhongxb.concurrent.chapter10;

/**
 * 破坏双亲委托机制，加载自定义类加载器
 * 重写loadClass方法，
 *
 * @author zxb
 */
public class BrokerDelegateClassLoader extends MyClassLoader {

    @Override
    public String toString() {
        return "BrokerDelegateClassLoader";
    }

    /**
     * 破坏双亲委派机制，重写loadClass方法
     *
     * @param name
     * @param resolve
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        // 根据类的全路径名称进行加锁，确保每一个类在多线程的情况下只被加载一次
        synchronized (getClassLoadingLock(name)) {
            // 到已加载类的缓存中查看该类是否已经被加载，如果已加载直接返回
            Class<?> klass = findLoadedClass(name);
            // 若缓存中没有被加载的类，则需要对其首次加载
            if (klass == null) {
                // 如果类的全路径以java或是javax开头，则直接委托系统类加载器对其进行加载
//                if (name.startsWith("java.") || name.startsWith("javax")) {
//                    klass = getSystemClassLoader().loadClass(name);
//                } else {
                    // 否则以自定义类加载器进行加载
                    klass = this.findClass(name);
                    // 若自定义类加载器仍旧没有完成对类的加载，则委托给其父类加载器进行加载或者系统类加载器加载
                    if (klass == null) {
                        if (getParent() != null) {
                            klass = getParent().loadClass(name);
                        } else {
                            klass = getSystemClassLoader().loadClass(name);
                        }
                    }
//                }
            }
            // 若干次加载后，还是无法对类进行加载，则抛出无法找到类的异常
            if (klass == null) {
                throw new ClassNotFoundException("The class " + name + " not found.");
            }
            if (resolve) {
                resolveClass(klass);
            }
            return klass;
        }
    }

}
