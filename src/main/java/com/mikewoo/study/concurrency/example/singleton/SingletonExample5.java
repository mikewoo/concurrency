package com.mikewoo.study.concurrency.example.singleton;

import com.mikewoo.study.concurrency.annotations.ThreadSafe;

/**
 * <p>饿汉模式-静态域模式</p>
 * <p>加载时机：单例实例在类加载时创建，线程安全</p>
 * <p>不足：
 * <blockquote>
 * 1. 在单例类的构造方法中包含过多的处理，会出现类加载的过程非常慢，引发性能问题；
 * </blockquote>
 * <blockquote>
 * 2. 另外如果饿汉模式只进行了加载而没有被实际使用，就会造成资源的浪费。
 * </blockquote>
 * </p>
 * @author Eric Gui
 * @date 2018/7/21
 */
@ThreadSafe
public class SingletonExample5 {

    // 私有构造函数
    private SingletonExample5(){

    }

    // 单例对象
    private static SingletonExample5 instance = new SingletonExample5();

    // 静态工厂方法
    public static SingletonExample5 getInstance() {
        return instance;
    }
}
