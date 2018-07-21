package com.mikewoo.study.concurrency.example.singleton;

import com.mikewoo.study.concurrency.annotations.NotThreadSafe;

/**
 * <p>饿汉模式 - 双重检查锁（double checked locking）模式</p>
 * <p>加载时机：单例实例在第一次使用时创建，线程不安全</p>
 * <p>说明：使用双重检查锁模式的单例模型，性能上得到了优化提升，但在多线程环境中由于JVM和CPU优化，发生指令重排，也会出现线程安全问题。</p>
 * <p>线程不安全现象说明：</p>
 * 通常一个对象创建的过程大致经过以下三步：
 * <blockquote>
 * 1. memory = allocate()，分配对象内存空间；
 * </blockquote>
 * <blockquote>
 * 2. ctorInstance()，对象初始化；
 * </blockquote>
 * <blockquote>
 * 3. instance = memory，设置对象引用指向分配的内存空间。
 * </blockquote>
 * 由于2跟3两步没有明确的依赖关系，执行时有可能发生指令重排，如先执行3再执行2，这时一个线程就有可能返回前一个线程创建的但还未进行初始化的对象，从而出现线程安全问题。
 * </p>
 *
 * @author Eric Gui
 * @date 2018/7/21
 */
@NotThreadSafe
public class SingletonExample3 {

    // 私有构造函数
    private SingletonExample3() {

    }

    // 单例对象
    private static SingletonExample3 instance = null;

    // 静态工厂方法
    public static SingletonExample3 getInstance() {
        if (null == instance) {
            synchronized (SingletonExample3.class) {
                if (null == instance) {
                    instance = new SingletonExample3();
                }
            }
        }
        return instance;
    }
}
