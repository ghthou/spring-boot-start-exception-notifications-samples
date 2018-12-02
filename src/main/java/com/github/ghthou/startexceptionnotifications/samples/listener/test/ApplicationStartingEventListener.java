package com.github.ghthou.startexceptionnotifications.samples.listener.test;

import java.text.MessageFormat;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        throw new RuntimeException(MessageFormat.format("模拟 {0} 异常", event.getClass().getSimpleName()));
    }
}
