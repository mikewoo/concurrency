package com.mikewoo.study.concurrency.example.singleton;

import com.mikewoo.study.concurrency.annotations.ThreadSafe;

/**
 * <p>饿汉模式 - 双重检查锁（double checked locking）- volatile模式</p>
 * <p>加载时机：单例实例在第一次使用时创建，线程不安全</p>
 * <p>说明：使用双重检查锁加上volatile关键字模式的单例模型，由于禁止了指令重排，所以不会重现线程安全问题。</p>
 * @author Eric Gui
 * @date 2018/7/21
 */
@ThreadSafe
public class SingletonExample4 {

    // 私有构造函数
    private SingletonExample4() {

    }

    // 单例对象
    private volatile static SingletonExample4 instance = null;

    // 静态工厂方法
    public static SingletonExample4 getInstance() {
        if (null == instance) {
            synchronized (SingletonExample4.class) {
                if (null == instance) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
