package com.github.ghthou.startexceptionnotifications.samples.listener.test;

import java.text.MessageFormat;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        throw new RuntimeException(MessageFormat.format("模拟 {0} 异常", event.getClass().getSimpleName()));

    }
}
