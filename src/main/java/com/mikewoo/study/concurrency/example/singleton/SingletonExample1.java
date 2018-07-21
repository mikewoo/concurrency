package com.mikewoo.study.concurrency.example.singleton;

import com.mikewoo.study.concurrency.annotations.NotThreadSafe;

/**
 * <p>饿汉模式</p>
 * <p>加载时机：单例实例在第一次使用时创建，线程不安全</p>
 * <p>不足：由于单例实例实在使用时创建，所以示例中写法在多线程环境中会出现线程安全问题。</p>
 * @author Eric Gui
 * @date 2018/7/21
 */
@NotThreadSafe
public class SingletonExample1 {

    // 私有构造函数
    private SingletonExample1(){

    }

    // 单例对象
    private static SingletonExample1 instance = null;

    // 静态工厂方法
    public static SingletonExample1 getInstance() {
        if (null == instance) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
