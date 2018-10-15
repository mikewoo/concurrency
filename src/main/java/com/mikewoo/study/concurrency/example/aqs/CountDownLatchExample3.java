package com.mikewoo.study.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 自定义容器，提供新增元素（add）和获取元素数量（size）方法。
 * 启动两个线程。线程1向容器中新增10个数据。线程2监听容器元素数量，当容器元素数量为5时，线程2输出信息并终止。
 * @author Eric Gui
 * @date 2018/10/15
 */
@Slf4j
public class CountDownLatchExample3 {
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        Container container = new Container();
        new Thread(() -> {
            if (container.size() != 5) {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("container size is {}", container.size());
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                log.info("add object[{}] to container", i);
                container.add(new Object());
                if (container.size() == 5) {
                    countDownLatch.countDown();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

class Container {
    private List<Object> container = new ArrayList<>();

    public void add(Object obj) {
        this.container.add(obj);
    }

    public int size() {
        return this.container.size();
    }

    public Object take() {
        if (this.container.isEmpty()) {
            return null;
        }
        return this.container.get(0);
    }

}
