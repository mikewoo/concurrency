package com.mikewoo.study.concurrency.example.synccontainer;

import com.mikewoo.study.concurrency.annotations.NotThreadSafe;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author Eric Gui
 * @date 2018/7/22
 */
@NotThreadSafe
public class VectorExample3 {

    public static void test1(Vector<Integer> v) { // foreach
        for (Integer i : v) {
            if (i.equals(3)) {
                v.remove(i);
            }
        }
    }

    public static void test2(Vector<Integer> v) { // iterator
        Iterator<Integer> iterator = v.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                v.remove(i);
            }
        }
    }

    public static void test3(Vector<Integer> v) {
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).equals(3)) {
                v.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        // test1(vector); // Exception in thread "main" java.util.ConcurrentModificationException
        // test2(vector); // Exception in thread "main" java.util.ConcurrentModificationException
        test3(vector);
    }
}
