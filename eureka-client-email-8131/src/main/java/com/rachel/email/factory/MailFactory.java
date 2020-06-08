package com.rachel.email.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Component
public class MailFactory {

    private static final Logger LOG = LoggerFactory.getLogger(MailFactory.class);

    @Value("${email.username}")
    private String emailACCount;

    @Value("${email.password}")
    private String emailPassword;

    @Value("${email.host}")
    private String emailSMTHost;

    public boolean sendEmail(String receiveEmailAccount, String content){
        // 1,创建参数配置，用于连接邮件服务器的参数配置
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol","smtp");
        props.setProperty("mail.smtp.host", emailSMTHost);
        props.setProperty("mail.smtp.auth", "true");

        // 根据配置创建会话对象，用于和邮件服务器交互
        Session session = Session.getInstance(props);
        // 设置为debug模式，可以查看详细的发送log
        session.setDebug(true);

        try {
            // 创建一封邮件
            MimeMessage message = createMessage(session, emailACCount, receiveEmailAccount, content);
            // 4. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();
            transport.connect(emailACCount, emailPassword);
            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());
            // 7. 关闭连接
            transport.close();
            return true;
        }catch (Exception e){
            LOG.error("send main failed.....");
            e.printStackTrace();
        }
        return false;

    }

    private MimeMessage createMessage(Session session, String sendMail, String receiveMail, String content) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "杭州小若琪", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "Github", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject("Please verify your email address", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent("您当前的注册验证码为：" + content + "，有效期为10分钟", "text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

}
