package com.mikewoo.study.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.mikewoo.study.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * <p>不可变对象-Collections.unmodifiableXXX()实例</p>
 * <p>引用变量被Collections.unmodifiableXXX()方法处理后，变量所指对象的内容将不能改变。</p>
 * @author Eric Gui
 * @date 2018/7/21
 */
@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer, String> map = Maps.newHashMap();

    static {
        map.put(1, "java");
        map.put(2, "php");
        map.put(3, "go");
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(2, "python"); // 抛异常，Exception in thread "main" java.lang.UnsupportedOperationException
        log.info("{}", map.get(2));
    }

}
