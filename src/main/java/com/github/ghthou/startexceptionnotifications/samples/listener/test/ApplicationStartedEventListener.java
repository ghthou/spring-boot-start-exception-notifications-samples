package com.github.ghthou.startexceptionnotifications.samples.listener.test;

import java.text.MessageFormat;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        throw new RuntimeException(MessageFormat.format("模拟 {0} 异常", event.getClass().getSimpleName()));
    }
}
