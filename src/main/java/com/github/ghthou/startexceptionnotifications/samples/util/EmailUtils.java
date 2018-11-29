package com.github.ghthou.startexceptionnotifications.samples.util;

import java.text.MessageFormat;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.github.ghthou.startexceptionnotifications.samples.properties.NotificationsProperties;

import lombok.SneakyThrows;

public class EmailUtils {

    public static final NotificationsProperties NOTIFICATIONS_PROPERTIES;
    public static final JavaMailSender JAVA_MAIL_SENDER;

    static {
        NOTIFICATIONS_PROPERTIES = initNotificationsProperties();
        JAVA_MAIL_SENDER = initJavaMailSender();
    }

    private static JavaMailSender initJavaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        applyProperties(sender);
        return sender;
    }
    private static void applyProperties(JavaMailSenderImpl sender) {
        NotificationsProperties properties = NOTIFICATIONS_PROPERTIES;

        sender.setHost(properties.getHost());
        if (properties.getPort() != null) {
            sender.setPort(properties.getPort());
        }
        sender.setUsername(properties.getUsername());
        sender.setPassword(properties.getPassword());
        sender.setProtocol(properties.getProtocol());
        if (properties.getDefaultEncoding() != null) {
            sender.setDefaultEncoding(properties.getDefaultEncoding());
        }
        if (!properties.getProperties().isEmpty()) {
            sender.setJavaMailProperties(asProperties(properties.getProperties()));
        }
    }

    private static Properties asProperties(String source) {
        Properties properties = new Properties();
        for (String pro : StringUtils.split(source, ",")) {
            String[] split = StringUtils.split(pro, "=");
            if (split.length == 2) {
                properties.put(split[0], split[1]);
            }
        }
        return properties;
    }

    @SneakyThrows
    private static NotificationsProperties initNotificationsProperties() {
        Properties p = PropertiesLoaderUtils.loadProperties(new ClassPathResource("notifications.properties"));
        NotificationsProperties notificationsProperties = new NotificationsProperties();
        notificationsProperties.setAppName(p.getProperty("notifications.appName"));
        notificationsProperties.setTo(p.getProperty("notifications.to"));
        notificationsProperties.setHost(p.getProperty("notifications.host"));
        notificationsProperties.setPort(Integer.valueOf(p.getProperty("notifications.port")));
        notificationsProperties.setProtocol(p.getProperty("notifications.protocol"));
        notificationsProperties.setUsername(p.getProperty("notifications.username"));
        notificationsProperties.setPassword(p.getProperty("notifications.password"));
        notificationsProperties.setDefaultEncoding(p.getProperty("notifications.defaultEncoding"));
        notificationsProperties.setProperties(p.getProperty("notifications.properties"));
        return notificationsProperties;
    }

    private static JavaMailSender getJavaMailSender() {
        return JAVA_MAIL_SENDER;
    }

    public static void send(SimpleMailMessage simpleMessage) {
        getJavaMailSender().send(simpleMessage);
    }

    public static SimpleMailMessage createSimpleMailMessage(ApplicationFailedEvent event) {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        // 发件人
        simpleMessage.setFrom(MessageFormat.format("Spring Boot 启动异常 <{0}>", NOTIFICATIONS_PROPERTIES.getUsername()));
        // 收件人
        simpleMessage.setTo(NOTIFICATIONS_PROPERTIES.getTo());
        // 主题
        simpleMessage.setSubject(MessageFormat.format("{0} 启动异常", NOTIFICATIONS_PROPERTIES.getAppName()));
        // 内容
        simpleMessage.setText(ExceptionUtils.getStackTrace(event.getException()));
        return simpleMessage;
    }

}
