package com.mikewoo.study.concurrency.example.publish;

import com.mikewoo.study.concurrency.annotations.NotRecommand;
import com.mikewoo.study.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Eric Gui
 * @date 2018/7/21
 */
@Slf4j
@NotThreadSafe
@NotRecommand
public class ObjectEscape {

    private int num = 0;

    public ObjectEscape() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("ObjectEscape num: {}", ObjectEscape.this.num);
        }
    }

    public static void main(String[] args) {
        new ObjectEscape();
    }
}
