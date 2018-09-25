package com.github.ghthou.startexceptionhandle.diagnostics;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.diagnostics.FailureAnalysisReporter;
import org.springframework.boot.diagnostics.LoggingFailureAnalysisReporter;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;

import com.github.ghthou.startexceptionhandle.constant.ProfileConstant;
import com.github.ghthou.startexceptionhandle.util.ApplicationTools;

public class NotifyFailureAnalysisReporter implements FailureAnalysisReporter {

    /** 收件人key */
    private static final String START_EXCEPTION_NOTIFY_EMAIL_KEY = "x-start-exception.mail.to";

    private static final Logger log = LoggerFactory.getLogger(NotifyFailureAnalysisReporter.class);

    @Override
    public void report(FailureAnalysis analysis) {
        // 获取 Environment 对象
        Environment environment = ApplicationTools.getEnvironment();
        // 如果当前环境不是开发环境
        if (environment != null && !environment.acceptsProfiles(ProfileConstant.DEV)) {
            BeanFactory beanFactory = ApplicationTools.getBeanFactory();
            // 邮件发送 mailSender
            JavaMailSender mailSender = beanFactory.getBean(JavaMailSender.class);
            MailProperties mailProperties = beanFactory.getBean(MailProperties.class);

            // 应用名称
            String appName = environment.getProperty("spring.application.name");
            // 收件人
            String mailTo = environment.getProperty(START_EXCEPTION_NOTIFY_EMAIL_KEY);
            if (!StringUtils.hasText(mailTo)) {
                log.error("请配置接收启动异常信息的邮箱,配置参数为 {}", START_EXCEPTION_NOTIFY_EMAIL_KEY);
                return;
            }

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            // 寄件人
            mailMessage.setFrom("Spring-Boot <" + mailProperties.getUsername() + ">");
            // 收件人
            mailMessage.setTo(mailTo);
            // 主题
            mailMessage.setSubject(appName + " 启动异常");
            // 内容
            mailMessage.setText(buildMessage(analysis));
            // 发送邮件
            mailSender.send(mailMessage);

        }

    }

    /**
     * 生成异常信息字符串
     *
     * @see LoggingFailureAnalysisReporter#buildMessage(org.springframework.boot.diagnostics.FailureAnalysis)
     */
    private String buildMessage(FailureAnalysis failureAnalysis) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("***************************%n"));
        builder.append(String.format("%s%n", ApplicationTools.getEnvironment().getProperty("spring.application.name")));
        builder.append(String.format("***************************%n%n"));
        builder.append(String.format("Description:%n%n"));
        builder.append(String.format("%s%n", failureAnalysis.getDescription()));
        if (StringUtils.hasText(failureAnalysis.getAction())) {
            builder.append(String.format("%nAction:%n%n"));
            builder.append(String.format("%s%n", failureAnalysis.getAction()));
        }
        builder.append(String.format("%nException StackTrace:%n%n"));
        builder.append(String.format("%s%n", ExceptionUtils.getStackTrace(failureAnalysis.getCause())));
        return builder.toString();
    }
}
