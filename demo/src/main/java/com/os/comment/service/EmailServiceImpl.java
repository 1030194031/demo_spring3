package com.os.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service("emailService")
public class EmailServiceImpl implements EmailService{
	@Autowired
	private JavaMailSenderImpl javaMailSender;
	@Override
	public void sendMail(String title,String context, String email) throws Exception {
	 	MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
	    MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

	    messageHelper.setFrom(new InternetAddress(this.javaMailSender.getUsername()));
	    messageHelper.setSubject(title);
	    messageHelper.setText(context, true);
	    messageHelper.setTo(new InternetAddress(email));
	    mimeMessage = messageHelper.getMimeMessage();

	    EmailThread et = new EmailThread(mimeMessage);
	    et.start();
	}
	
	class EmailThread extends Thread {
		private final MimeMessage mimeMessage;

		public EmailThread(MimeMessage mimeMessage) {
			this.mimeMessage = mimeMessage;
		}

		public void run() {
			EmailServiceImpl.this.javaMailSender.send(this.mimeMessage);
		}
	}

}
