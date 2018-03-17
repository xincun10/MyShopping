package com.zcy.service;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {
	private final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private final String username = "624968962";
	//������Ȩ��
	private final String password = "lqukpyjesjevbfge";
	
	public void sendToSomebody(String content, String subject, String email) {
		/*
		 * 1.�õ�session
		 */
		Properties props = new Properties();
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.host", "smtp.qq.com");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.port", "25");
		
		Authenticator auth = new Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			};
		};
		
		Session session = Session.getInstance(props, auth);
		/*
		 * 2.����MimeMessage
		 */
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress("624968962@qq.com"));
			msg.setRecipients(RecipientType.TO, email);
			msg.setSubject(subject);
			msg.setContent(content, "text/html;charset=utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * 3.�����ʼ�
		 */
		try {
			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
