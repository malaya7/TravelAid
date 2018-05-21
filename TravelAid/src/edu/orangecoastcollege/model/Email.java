package edu.orangecoastcollege.model;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/*
 * Gmail SMTP server address: smtp.gmail.com
Gmail SMTP username: Your Gmail address (e.g. example@gmail.com)
Gmail SMTP password: Your Gmail password
Gmail SMTP port (TLS): 587
Gmail SMTP port (SSL): 465
 */
public class Email {
	
	private String text;
	private String subject;
	private String from;
	private String recipient;
	private String sender;
	private String messageContent;

	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public void setText(String msg)
	{
		this.text=msg;
	}
	public void setSubject(String sb){
		this.subject=sb;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getText() {
		return text;
	}
	public String getSubject() {
		return subject;
	}

	
	//Encapsulate our own email and password even though it's going to be publicly viewed on github...
	private class SMTPAuthenticator extends Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {										//username                    password
            return new PasswordAuthentication("asapsearchcs272@gmail.com", "Albert!123");
        }
    }
	/**
	 * 
	 * @param messageContents ArrayList[0] contains the Recipient, the send is set before hand, at [1] it sets the Subject of the email, at [2] it sets the content
	 * @throws MessagingException Forces caller to catch this exception in case something went wrong
	 */
	public  void createAndSendEmailMessage(ArrayList<?> messageContents) throws MessagingException {
	       Email email = new Email();
	       email.setRecipient(messageContents.get(0) + "");
	       email.setSubject(messageContents.get(1) + "");
	       email.setMessageContent(messageContents.get(2)+"");
	       email.setSender("asapsearchcs272@gmail.com");
	       sendEmailMessage(email);
	   }
	
	public  void sendEmailMessage(Email email) throws MessagingException {

        // Get system properties preparing the package where we would send the email on
		//In order to use Gmail we need to set the user,the host,port used by the email, enable Time To Live 
		//set debug to true as well as Authority, use SSL or since Gmail enforces it, and etc into the system properties.
		Properties props = System.getProperties();
		props = new Properties();
		props.put("mail.smtp.user", "asapsearchcs272@gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		//use the SMPT Authenticator in order to put our credentials(our email and password) securely
		SMTPAuthenticator auth = new SMTPAuthenticator();
		//create and open a session in order to send the email 
		Session session = Session.getInstance(props, auth);
		session.setDebug(false);
		//create message and add on parameters to send it
		MimeMessage msg = new MimeMessage(session);
		msg.setText(email.getMessageContent());
		msg.setSubject(email.getSubject());
		msg.setFrom(new InternetAddress(email.getSender()));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getRecipient()));
		//connect the session to the transport class using Simple Mail Transfer Protocol
		Transport transport = session.getTransport("smtps");
		transport.connect("smtp.gmail.com", 465, "username", "password");
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
	
}
}