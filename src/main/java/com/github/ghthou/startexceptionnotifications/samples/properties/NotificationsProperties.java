package com.github.ghthou.startexceptionnotifications.samples.properties;

import lombok.Data;

@Data
public class NotificationsProperties {
    /** 应用名称,用于区分不同的应用 */
    private String appName;
    /** 收件人 */
    private String to;
    /** 邮件服务器地址 */
    private String host;
    /** 邮件服务器端口 */
    private Integer port;
    /** 邮件服务器帐号 */
    private String username;
    /** 邮件服务器密码 */
    private String password;
    /** 邮件服务器协议 */
    private String protocol = "smtp";
    /** 邮件编码 */
    private String defaultEncoding = "UTF-8";
    /** 额外参数 */
    private String properties ;

}
