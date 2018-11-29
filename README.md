### Spring Boot 项目启动异常时使用邮件通知
如需运行 demo ,请在 [src/main/resources/notifications.properties](src/main/resources/notifications.properties) 文件中配置以下参数
```properties
# 收件人
notifications.to=example@example.com
# 邮件服务器地址
notifications.host=smtp.example.com
# 邮件服务器端口
notifications.port=465
# 邮件服务器帐号
notifications.username=example@example.com
# 邮件服务器密码
notifications.password=example
# 邮件服务器协议
notifications.protocol=smtp
# 额外参数,如果是 ssl 端口,必须配置
notifications.properties=mail.smtp.ssl.enable=true
```
#### 博客地址
[Spring Boot 项目启动异常时使用邮件通知](https://ghthou.github.io/2018/09/25/Spring-Boot项目启动异常时使用邮件通知/)

