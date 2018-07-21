package com.mikewoo.study.concurrency.example.singleton;

import com.mikewoo.study.concurrency.annotations.Recommand;
import com.mikewoo.study.concurrency.annotations.ThreadSafe;

/**
 * <p>枚举模式</p>
 * <p>优点：由于使用枚举方式，JVM能保证单例实例绝对只会被创建一次，相对于懒汉模式，在线程安全方面得到了保证；</p>
 * <p>同时相对于饿汉模式，由于在使用时才会创建实例，不会造成资源的浪费。另外由于不需要额外的同步机制，所以性能上也具有优势。</p>
 * @author Eric Gui
 * @date 2018/7/21
 */
@ThreadSafe
@Recommand
public class SingletonExample7 {

    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonExample7 singleton;

        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getSingleton() {
            return singleton;
        }
    }
}
