package com.mingyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动类
 *
 * @author jmm
 */
@EnableAsync
@SpringBootApplication
public class JavaAsyncProgramApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaAsyncProgramApplication.class, args);
    }

}

