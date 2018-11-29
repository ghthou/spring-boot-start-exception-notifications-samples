package com.github.ghthou.startexceptionnotifications.samples.listener;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import com.github.ghthou.startexceptionnotifications.samples.util.EmailUtils;

public class StartExceptionNotificationsListener implements ApplicationListener<ApplicationFailedEvent> {

    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        // 如果不是 dev 环境,因为 dev 环境会查看控制台
        if (applicationContext == null || applicationContext.getEnvironment().acceptsProfiles("!dev")) {
            // 进行异常通知
            EmailUtils.send(EmailUtils.createSimpleMailMessage(event));
        }
    }
}
