package com.ing.mobile.mailer;

import java.util.Properties;


public class Main {
	private static void sendEmail() {
		  String host = "mail.tzr-019.co.il";
		  Properties props = System.getProperties();
		  props.setProperty("mail.smtps.host", host);
		  props.setProperty("mail.smtp.port", "25");
		  props.setProperty("mail.smtps.auth", "true");
		  Session session = Session.getDefaultInstance(props);
		  try{
		     MimeMessage message = new MimeMessage(session);
		     message.setFrom(new InternetAddress,user));
		     message.addRecipient(Message.RecipientType.TO,new InternetAddress(TO));
		     message.setSubject("This is the Subject Line!");
		     message.setText("This is actual message");
		     Transport.send(message);

		     System.out.println("Sent message successfully....");
		  }catch (Exception mex) {
		     mex.printStackTrace();
		  }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}