package com.mikewoo.study.concurrency.example.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * <p>不可变对象-final关键字实例</p>
 * <p>final修饰变量：可用来修饰基本数据类型变量和引用数据类型变量</p>
 * <p>
 *    <blockquote>1. 修饰基本数据类型变量，变量被赋值后值不能再改变;</blockquote>
 *    <blockquote>2. 修饰引用数据类型变量，变量不可再指向其他引用，但被指向地引用对象的内容是可以改变。</blockquote>
 * </p>
 * @author Eric Gui
 * @date 2018/7/21
 */
@Slf4j
public class ImmutableExample1 {

    private final static Integer a = 1;

    private final static String b = "b";

    private final static Map<Integer, String> map = Maps.newHashMap();

    static {
        map.put(1, "java");
        map.put(2, "php");
        map.put(3, "go");
    }

    public static void main(String[] args) {
//        a = 2;
//        b = "haha";
//        map = Maps.newHashMap();
        map.put(2, "python");
        log.info("{}", map.get(2));
    }

    public void test(final int a, final Map<String, String> map) {
//        a = 1; // final修饰的基本数据类型参数，值不能改变
//        map = Maps.newHashMap(); // final修饰的引用数据类型参数，引用不能改变
        map.put("name", "eric"); // final修饰的引用数据类型参数，引用不能改变，但引用所指对象的内容是可以改变的
    }
}
