package com.github.ghthou.startexceptionnotifications.samples.listener.test;

import java.text.MessageFormat;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        throw new RuntimeException(MessageFormat.format("模拟 {0} 异常", event.getClass().getSimpleName()));
    }
}
