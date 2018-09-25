package com.github.ghthou.startexceptionnotifications.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

public class ThrowExceptionListener implements ApplicationListener<ApplicationStartedEvent> {

    public static final String PROFILE = "throwException";

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        if (event.getApplicationContext().getEnvironment().acceptsProfiles(PROFILE)) {
            throw new RuntimeException("启动时出现异常");
        }
    }

}
