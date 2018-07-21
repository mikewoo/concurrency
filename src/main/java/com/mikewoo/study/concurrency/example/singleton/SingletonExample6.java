package com.mikewoo.study.concurrency.example.singleton;

import com.mikewoo.study.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>饿汉模式-静态代码块方式</p>
 * <p>加载时机：单例实例在类加载时创建，线程安全</p>
 * <p>不足：
 * <blockquote>
 * 1. 在单例类的构造方法中包含过多的处理，会出现类加载的过程非常慢，引发性能问题；
 * </blockquote>
 * <blockquote>
 * 2. 如果饿汉模式只进行了加载而没有被实际使用，就会造成资源的浪费。
 * </blockquote>
 * <blockquote>
 * 3. 另外使用静态代码块模式时要注意静态域(a)和静态代码块(b)的前后位置，如果静态代码块在静态域之前，这时候通过工厂方法获得的示例会为null。
 * </blockquote>
 * </p>
 * @author Eric Gui
 * @date 2018/7/21
 */
@ThreadSafe
@Slf4j
public class SingletonExample6 {

    // 私有构造函数
    private SingletonExample6(){

    }

    // 单例对象（a：静态域）
    private static SingletonExample6 instance = null;

    // （b：静态代码块）
    static {
        instance = new SingletonExample6();
    }

    // 静态工厂方法
    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        // 如果调换a和b的位置，会出现空指针
        log.info("instance: {}", getInstance().hashCode());
        log.info("instance: {}", getInstance().hashCode());
    }
}
