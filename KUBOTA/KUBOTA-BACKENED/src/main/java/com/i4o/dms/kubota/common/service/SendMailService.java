package com.i4o.dms.kubota.common.service;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.i4o.dms.kubota.common.model.MailEntity;
import com.sun.mail.util.MailSSLSocketFactory;

@Component
public class SendMailService {

	@Value("${mail.smtp.host}")
	private String host;
	@Value("${mail.smtp.port}")
	private String port;
	@Value("${mail.smtp.username}")
	private String username;
	@Value("${mail.smtp.password}")
	private String password;
	@Value("${mail.smtp.from}")
	private String from;
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public boolean sendMail(MailEntity mailEntity, File attachment){
		Properties props = new Properties();
		props.put("mail.smtp.host", getHost());
        props.put("mail.transport.protocol", "smtp" );
		props.put("mail.smtp.starttls.enable", "false"); 
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", getPort());
       

		try{
			/*MailSSLSocketFactory sf = new MailSSLSocketFactory();
	        sf.setTrustAllHosts(true);
	        props.put("mail.smtps.ssl.enable", "false");
	        props.put("mail.smtps.ssl.checkserveridentity", "true");
	        props.put("mail.smtp.socketFactory.port", getPort());
	        props.put("mail.smtps.ssl.socketFactory", sf);*/
			// Get the Session object.
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(getUsername(), getPassword());
				}
			});
				
		

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			
			if(mailEntity.getTomailid()!=null && !mailEntity.getTomailid().equals("")){
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailEntity.getTomailid()));
			}
			
			if(mailEntity.getCcmailid()!=null && !mailEntity.getCcmailid().equals("")){
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(mailEntity.getCcmailid()));
			}
			
			if(mailEntity.getBccmailid()!=null && !mailEntity.getBccmailid().equals("")){
				message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(mailEntity.getBccmailid()));
			}
			
			message.setSubject(mailEntity.getMailsubject());
			
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(mailEntity.getMailbodytxt(),"text/html; charset=utf-8");
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			
			if(attachment != null) {

				messageBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(attachment);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(attachment.getName());
				
				multipart.addBodyPart(messageBodyPart);
			}
			
			message.setContent(multipart);
			
			if(message.getRecipients(Message.RecipientType.TO)!=null || message.getRecipients(Message.RecipientType.CC)!=null || message.getRecipients(Message.RecipientType.BCC)!=null){
			    
				Transport.send(message);

				System.out.println("Mail Sent Successfully....");
	
				return true;
			}
		}
		catch (MessagingException e)
		{
			// throw new RuntimeException(e);
			System.out.println("Could not connect to SMTP host");
			e.printStackTrace();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return false;
	}
	
	
}
