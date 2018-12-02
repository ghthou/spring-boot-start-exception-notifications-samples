package com.github.ghthou.startexceptionnotifications.samples.listener.test;

import java.text.MessageFormat;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        throw new RuntimeException(MessageFormat.format("模拟 {0} 异常", event.getClass().getSimpleName()));
    }
}
