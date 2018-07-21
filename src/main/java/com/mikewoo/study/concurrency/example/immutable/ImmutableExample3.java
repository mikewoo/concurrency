package com.mikewoo.study.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mikewoo.study.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>不可变对象-ImmutableCollection实例</p>
 * <p>如果引用变量是使用Guava中的ImmutableCollection构造，变量所指对象的内容将不能改变。</p>
 *
 * @author Eric Gui
 * @date 2018/7/21
 */
@Slf4j
@ThreadSafe
public class ImmutableExample3 {

    private static final ImmutableList<Integer> list = ImmutableList.of(1, 2, 3, 4, 5);

    private static final ImmutableSet<Integer> set = ImmutableSet.copyOf(list);

    private static final ImmutableMap<Integer, String> map1 = ImmutableMap.of(1, "java", 2, "python", 3, "go");

    private static final ImmutableMap<Integer, String> map2 = ImmutableMap
            .<Integer, String>builder()
            .put(1, "java")
            .put(2, "python")
            .put(3, "go").build();

    public static void main(String[] args) {
        // list.add(6); // 抛异常，Exception in thread "main" java.lang.UnsupportedOperationException
        log.info("list[1]: {}", list.get(1));
        // set.add(6); // 抛异常，Exception in thread "main" java.lang.UnsupportedOperationException
        // map1.put(4, "php"); // 抛异常，Exception in thread "main" java.lang.UnsupportedOperationException
        log.info("map1: key:1, value={}", map1.get(1));
        // map2.put(4, "php"); // 抛异常，Exception in thread "main" java.lang.UnsupportedOperationException
        log.info("map2: key:3, value={}", map2.get(3));

    }
}
