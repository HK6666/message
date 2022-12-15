package com.hk.message;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
// 日志测试类
@SpringBootTest
@Slf4j
public class SLF4JTest {

    private static final String TAG = "message";

    @Test
    void testSlf4j() {
        log.info(System.getProperty("user.home"));
        log.info("test");
    }
}
