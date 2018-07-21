package com.mikewoo.study.concurrency.example.publish;

import com.mikewoo.study.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author Eric Gui
 * @date 2018/7/21
 */
@Slf4j
@NotThreadSafe
public class UnSafedPublish {

    private String[] strs = {"a", "b", "c"};

    public String[] getStrs() {
        return strs;
    }

    public static void main(String[] args) {
        UnSafedPublish publish = new UnSafedPublish();
        log.info("strs: {}", Arrays.toString(publish.getStrs()));

        publish.getStrs()[0] = "d";
        log.info("strs: {}", Arrays.toString(publish.getStrs()));

    }
}
