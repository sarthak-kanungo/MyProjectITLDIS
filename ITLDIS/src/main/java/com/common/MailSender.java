package com.common;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailSender {

	public static String sendBiPartMail(String jobcardNo, String biPartComplaints, String dealerCode, String dealerName, String jobcardDate, String toList, String ccList,String stateName,String location) {
	    try {
	        System.out.println("Print before sending mail");
	        System.out.println("Job Card No   : " + jobcardNo);
	        System.out.println("Complaints    : " + biPartComplaints);
	        System.out.println("Dealer Code   : " + dealerCode);
	        System.out.println("Dealer Name   : " + dealerName);
	        System.out.println("Job Card Date : " + jobcardDate);
	        System.out.println("To List       : " + toList);
	        System.out.println("CC List       : " + ccList);

	        // Only proceed if TO email is available
	        if (toList == null || toList.trim().isEmpty()) {
	            System.out.println("No TO email provided, mail will not be sent.");
	            return "success";
	        }

	        Locale locale = new Locale("en", "US");
	        ResourceBundle bundle = ResourceBundle.getBundle("com.myapp.struts.ApplicationResource", locale);

	        boolean mailEnabled = Boolean.parseBoolean(bundle.getString("mail.enabled"));
	        if (!mailEnabled) {
	            System.out.println("Mail is disabled (mail.enabled=false)");
	            return "success";
	        }

	        // SMTP settings
	        String host = bundle.getString("mail.smtp.host");
	        String port = bundle.getString("mail.smtp.port");
	        String username = bundle.getString("mail.smtp.username");
	        String password = bundle.getString("mail.smtp.password");
	        String from = bundle.getString("mail.smtp.from");
	        String auth = bundle.getString("mail.smtp.auth");
	    
	        String starttls = bundle.getString("mail.smtp.starttls.enable");

	        // JavaMail properties
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", auth);
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.port", port);
	        
	        
	        props.put("mail.smtp.starttls.enable", starttls);
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
	        

	        Session session = Session.getInstance(props, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	        });

	        // Email body
	        StringBuilder body = new StringBuilder();
	        
	        
	        body.append("Dear Team,<br><br>");
	        body.append("This Job Card has been booked in <b>Bi Part</b>, so please look into this.<br><br>");
	        body.append("<b>Job Card No:</b> ").append(jobcardNo).append("<br>");
	        body.append("<b>Job Card Date:</b> ").append(jobcardDate).append("<br>");
	        body.append("<b>Dealer Code:</b> ").append(dealerCode).append("<br>");
	        body.append("<b>Dealer Name:</b> ").append(dealerName != null ? dealerName : "").append("<br>");
	        body.append("<b>Dealer State:</b> ").append(stateName != null ? stateName : "").append("<br>");
	        body.append("<b>Dealer Location:</b> ").append(location != null ? location : "").append("<br>");
	        body.append("<b>Complaint Nos:</b> ").append(biPartComplaints).append("<br><br>");
	        body.append("With Regards,<br>");
	        body.append("TMS Support");
	        

	        // Compose email
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toList));
	        if (ccList != null && !ccList.trim().isEmpty()) {
	            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccList));
	        }

	        message.setSubject("Bi Part Job Card:- " + jobcardNo);
	        message.setContent(body.toString(), "text/html");
	  
	        // Send
	        Transport.send(message);
	        System.out.println("Mail sent successfully "+jobcardNo);
	        return "success";

	    } catch (Exception e) {
	        System.out.println("Mail sending failed.");
	        e.printStackTrace();
	        return "failed";
	    }
	}

}
