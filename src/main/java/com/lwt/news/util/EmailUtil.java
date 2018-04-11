package com.lwt.news.util;




import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * java发送电子邮件的类
 */
public class EmailUtil {


    /**
     * 当用户注册时发送验证邮件给用户
     * @param to  发送给to邮箱
     * @param code  验证码
     */
    // 发件人邮箱的 SMTP 服务器地址
    public static String myEmailSMTPHost = "smtp.163.com";

    public static void sendEmail(String to,String code,String url) throws Exception{
        //1.创建一个邮件对象
         Properties props = new Properties();   //用于指定连接邮件服务器的属性配置
         String filePath = EmailUtil.class.getResource("/email.properties").getPath();
                 //EmailUtil.class.getClassLoader().getResource("email.properties");
        InputStream in = new BufferedInputStream(
                new FileInputStream(
                        new File(filePath)));
        props.load(in);
        /*props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");*/
        Session session = Session.getInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("mail.from"),
                        props.getProperty("mail.password"));
            }
        });   //根据配置属性创建会话
        Message message = new MimeMessage(session); //根据会话对象创建邮件对象

        try {
            //2.设置发件人
            message.setFrom(new InternetAddress(props.getProperty("mail.from"),
                    "新闻发布系统","utf-8"));
            //3.设置收件人
            message.setRecipient(MimeMessage.RecipientType.TO,
                    new InternetAddress(to));
            //4.设置邮件主题
            message.setSubject("请验证邮箱激活你的账号");
            //5.邮件正文
            message.setContent("<h1>点击下面链接激活账号</h1>" +
                    "<a href='"+url+"account/code"+"?code="+code+
                            "&email="+to+"'>"+url+"?code="+code+"&email="+to+"</a>",
                    "text/html;charset=UTF-8");
            // 6. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();
            //transport.connect(myEmailAccount, myEmailPassword);
            // 7. 发送邮件, 发到所有的收件地址, message.getAllRecipients()
            // 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            //transport.sendMessage(message, message.getAllRecipients());
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] argv) throws Exception{
        /*Properties props = new Properties();   //用于指定连接邮件服务器的属性配置
        String filePath = EmailUtil.class.getResource("/email.properties").getPath();
        //EmailUtil.class.getClassLoader().getResource("email.properties");
        InputStream insream = new BufferedInputStream(new FileInputStream(new File(filePath)));
        props.load(insream);
        System.out.println(EmailUtil.class.getClassLoader().getResource(""));
        System.out.println(EmailUtil.class.getResource("/email.properties").getPath());
        System.out.println(props.getProperty("mail.from"));*/
       /* EmailUtil emailUtil = new EmailUtil();
        System.out.println(emailUtil.host);*/
        sendEmail("lwt2710@outlook.com","AD1234EwQ90Gh12345","http://localhost:8080/news/");
    }
}
