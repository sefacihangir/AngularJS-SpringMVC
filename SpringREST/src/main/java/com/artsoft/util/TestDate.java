package com.artsoft.util;

import com.artsoft.model.MailModel;
import com.artsoft.service.MailService;
import com.artsoft.service.MailServiceImpl;

public class TestDate {

	public static void main(String[] args) {

		MailModel mail = new MailModel();
		mail.setFrom("berar1994@gmail.com");
		mail.setTo("berar1994@gmail.com");
		mail.setSubject("TEST");
		mail.setContent("This is a test");

		MailServiceImpl mailService = new MailServiceImpl();
		if (mailService != null) {
			mailService.sendEmail(mail);
			System.out.println("Sent");
		}

	}

}
