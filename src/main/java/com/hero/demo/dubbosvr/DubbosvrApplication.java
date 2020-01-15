package com.hero.demo.dubbosvr;

import com.hero.demo.dubbosvr.threadpool.CommonTaskManager;
import com.hero.demo.dubbosvr.threadpool.InitTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DubbosvrApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubbosvrApplication.class, args);

        InitTask initTask = new InitTask();
        CommonTaskManager.getInstance().addTask(initTask);
    }

}
