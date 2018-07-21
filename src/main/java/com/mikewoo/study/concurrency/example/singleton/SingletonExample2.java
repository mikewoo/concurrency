package com.mikewoo.study.concurrency.example.singleton;

import com.mikewoo.study.concurrency.annotations.NotRecommand;
import com.mikewoo.study.concurrency.annotations.ThreadSafe;

/**
 * <p>饿汉模式 - synchronized锁模式/p>
 * <p>加载时机：单例实例在第一次使用时创建，线程安全</p>
 * <p>说明：在静态工厂方法加synchronized同步锁，解决了线程安全问题，但此时是静态方法加锁，所以性能很差，不推荐使用。</p>
 * @author Eric Gui
 * @date 2018/7/21
 */
@ThreadSafe
@NotRecommand
public class SingletonExample2 {

    // 私有构造函数
    private SingletonExample2(){

    }

    // 单例对象
    private static SingletonExample2 instance = null;

    // 静态工厂方法
    public synchronized static SingletonExample2 getInstance() {
        if (null == instance) {
            instance = new SingletonExample2();
        }
        return instance;
    }
}
