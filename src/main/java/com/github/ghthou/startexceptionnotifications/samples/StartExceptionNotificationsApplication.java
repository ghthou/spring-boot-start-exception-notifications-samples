package com.github.ghthou.startexceptionnotifications.samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartExceptionNotificationsApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(StartExceptionNotificationsApplication.class);
        // 不触发
        // springApplication.addListeners(new ApplicationStartingEventListener());
        // 触发
        // springApplication.addListeners(new ApplicationEnvironmentPreparedEventListener());
        // 触发
        // springApplication.addListeners(new ApplicationPreparedEventListener());
        // 触发
        // springApplication.addListeners(new ApplicationStartedEventListener());
        // 不触发
        // springApplication.addListeners(new ApplicationReadyEventListener());
        springApplication.run(args);
    }
}
