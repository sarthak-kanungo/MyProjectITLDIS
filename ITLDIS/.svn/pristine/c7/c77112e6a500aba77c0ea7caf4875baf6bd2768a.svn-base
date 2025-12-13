package com.common;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
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

/**
 *
 * @author pramod.vishwakarma
 */
class JAuthenticate extends Authenticator {

    String sUsername = null;
    String sPassword = null;

    public JAuthenticate(String username, String password) {
// Assign username and password values passed in data from calling mail connection
        sUsername = username;
        sPassword = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
// username/password get authenticated next line
        return new PasswordAuthentication(sUsername, sPassword);
    }
}

public class SendMailUtility {

    static String from = "";
    static String path = "";
    static String To_id = null;
    static String BCC_id = null;
    static String CC_id = null;

    public static boolean send_mail(String messageText, String subject, String fromAddress, String destArr[], String destCCArr[], String destBCCArr[], Vector filesVec) throws MessagingException, IOException {
        boolean flag = false;
        
        try {
            Locale locale = new Locale("en", "US");
            ResourceBundle labels = ResourceBundle.getBundle("com.myapp.struts.ApplicationResource", locale);
            String smtpHostServer = labels.getString("smtpHostServer");
            final String fromEmail = labels.getString("fromEmail"); //requires valid gmail id
            final String password = labels.getString("email_password"); //
            final String smtp_port = labels.getString("smtp_port"); //
            final String ntlm_domain = labels.getString("ntlm_domain"); //

            // System.setProperty("java.net.preferIPv4Stack" , "false");
            // Get system properties
            Properties props = System.getProperties();
            // Setup mail server
            props.put("mail.smtp.host", smtpHostServer);//mailhost);
            props.put("mail.smtp.auth", "true");         
            props.put("mail.smtp.port", smtp_port);
            props.put("mail.smtp.auth.ntlm.domain",ntlm_domain);

            //props.put("mail.debug", "true");
            //props.put("mail.smtp.starttls.enable", "false");

          //  System.out.println("smtpHostServer : "+smtpHostServer);
          //  System.out.println("smtp_port : "+smtp_port);
          //  System.out.println("User ID : "+fromEmail);
           // System.out.println("Password : "+password);

            JAuthenticate pAuth = new JAuthenticate(fromEmail, password);

            // Get session
            Session session = Session.getInstance(props, pAuth);


            // Define message
            MimeMessage message = new MimeMessage(session);
          //  message.setDataHandler(new DataHandler(new ByteArrayDataSource(messageText, "html")));
            message.setFrom(new InternetAddress(fromAddress));
            message.setSubject(subject);
            To_id = "";
            BCC_id = "";
            CC_id = "";
            if (destArr != null) {

                InternetAddress[] addressTo = new InternetAddress[destArr.length];
                for (int i = 0; i < destArr.length; i++) {
                    if (!destArr[i].equals("")) {
                        if (i == 0) {
                            To_id += destArr[i];
                        } else {
                            To_id += ";" + destArr[i];
                        }
                        addressTo[i] = new InternetAddress(destArr[i]);
                    }
                }
                if (destArr.length != 0) {
                    message.setRecipients(Message.RecipientType.TO, addressTo);
                }
            }

            if (destBCCArr != null) {
                InternetAddress[] addressBCC = new InternetAddress[destBCCArr.length];
                for (int i = 0; i < destBCCArr.length; i++) {

                    if (!destBCCArr[i].equals("")) {

                        if (i == 0) {
                            BCC_id += destBCCArr[i];
                        } else {
                            BCC_id += ";" + destBCCArr[i];
                        }
                        addressBCC[i] = new InternetAddress(destBCCArr[i]);
                    }
                }
                if (destBCCArr.length != 0) {
                    message.setRecipients(Message.RecipientType.BCC, addressBCC);
                }
            }

            if (destCCArr != null) {

                CC_id = destCCArr.toString();
                InternetAddress[] addressCC = new InternetAddress[destCCArr.length];
                for (int i = 0; i < destCCArr.length; i++) {
                    if (!destCCArr[i].equals("")) {
                        if (i == 0) {
                            CC_id += destCCArr[i];
                        } else {
                            CC_id += ";" + destCCArr[i];
                        }
                        addressCC[i] = new InternetAddress(destCCArr[i]);
                    }
                }
                if (destCCArr.length != 0) {
                    message.setRecipients(Message.RecipientType.CC, addressCC);
                }
            }
            message.setSentDate(new java.util.Date());
            message.setContent(messageText, "text/html");

           // create the message part
//          MimeBodyPart messageBodyPart = new MimeBodyPart();
//          messageBodyPart.setContent(messageText, "text/html");
//          messageBodyPart.setText(messageText);

            MimeBodyPart mbp[] = null;
            if (filesVec != null && filesVec.size() > 0) {
                mbp = new MimeBodyPart[filesVec.size()];
                FileDataSource fds[] = new FileDataSource[filesVec.size()];
                for (int i = 0; i < filesVec.size(); i++) {
                    mbp[i] = new MimeBodyPart();
                    fds[i] = new FileDataSource("" + filesVec.elementAt(i));
                    mbp[i].setDataHandler(new DataHandler(fds[i]));
                    mbp[i].setFileName(fds[i].getName());
                }
            }
 //         Multipart multipart = null;
 //         multipart.addBodyPart(messageBodyPart);

            if (filesVec != null && filesVec.size() > 0) {
                Multipart multipart = new MimeMultipart();
                for (int i = 0; i < filesVec.size(); i++) {
                    multipart.addBodyPart(mbp[i]);
                }
                message.setContent(multipart);
            }
          Transport.send(message);
          flag = true;

   /*      Transport transport = null;


         try {
             transport = session.getTransport("smtp");
         } catch (NoSuchProviderException e) {
             System.out.println("No such Provider Exception");
         }

         try {
             transport.connect(smtpHostServer, fromEmail, password);
             //transport.sendMessage(message, message.getAllRecipients());
             message.saveChanges();

             transport.send(message);
             transport.close();


             System.out.println("Email sent successfully.");
             flag = true;
         } catch (MessagingException e) {
             e.printStackTrace();

             System.out.println("Messaging Exception" + e);
               flag = false;
         }
*/
       

        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
