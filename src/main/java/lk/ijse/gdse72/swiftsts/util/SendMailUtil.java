package lk.ijse.gdse72.swiftsts.util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class SendMailUtil extends Thread {

    String recipient;
    String subject;
    String messageBody;

    public SendMailUtil(String recipient, String subject, String messageBody) {
        this.recipient = recipient;
        this.subject = subject;
        this.messageBody = messageBody;
    }

    @Override
    public void run() {
        sendEmail(recipient, subject, messageBody);
    }

    public static void sendEmail(String recipient, String subject, String messageBody) {
        if (recipient == null || recipient.isEmpty()) {
            System.out.println("Recipient email address is null or empty.");
            return;
        }

        String host = "smtp.gmail.com";
        String port = "587";
        String username = "kariyawasamlithira2@gmail.com";
        String password = "gnzz aanf hgxu dmwy";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(messageBody);

            Transport.send(message);
            System.out.println("Email successfully sent to " + recipient);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email to " + recipient);
        }
    }
}