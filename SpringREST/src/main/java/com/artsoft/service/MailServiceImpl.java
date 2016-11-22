package com.artsoft.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.artsoft.model.MailModel;

@Service("mailService")
public class MailServiceImpl implements MailService {

	@Autowired
	JavaMailSender mailSender;
	
	
	@Override
	public void sendEmail(MailModel mail) {
		MimeMessagePreparator mimeMessage = getMessagePreparator(mail);
		try{
			mailSender.send(mimeMessage);
			System.out.println("Message sent.");
		}catch(MailException ex){
			ex.printStackTrace();
		}
	}

	private MimeMessagePreparator getMessagePreparator(MailModel mail) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setFrom(new InternetAddress(mail.getFrom()));
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getTo()));
				mimeMessage.setText(mail.getContent());
				mimeMessage.setSubject(mail.getSubject());
			}
		};
		return preparator;
	}

}
