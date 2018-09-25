package com.github.ghthou.startexceptionnotifications.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationTools implements ApplicationContextAware, BeanFactoryAware,
        EnvironmentAware {

    private static volatile Environment environment;
    private static volatile ApplicationContext applicationContext;
    private static volatile BeanFactory beanFactory;

    public static Environment getEnvironment() {
        return environment;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationTools.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        ApplicationTools.environment = environment;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        ApplicationTools.beanFactory = beanFactory;

    }
}
