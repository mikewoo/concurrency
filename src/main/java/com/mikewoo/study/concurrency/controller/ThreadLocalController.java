package com.mikewoo.study.concurrency.controller;

import com.mikewoo.study.concurrency.example.threadlocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eric Gui
 * @date 2018/7/21
 */
@RestController
@RequestMapping("/threadLocal")
@Slf4j
public class ThreadLocalController {

    @RequestMapping("/test")
    public Long threadLocalTest() {
        return RequestHolder.getId();
    }
}
