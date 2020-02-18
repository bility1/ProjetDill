package Global;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {
    public int sendMail(String from, String password, String to, String subject, String msg) {

        // setting gmail smtp properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // check the authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(from));
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            // recipients email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // add the Subject of email
            message.setSubject(subject);

            // message body
            message.setText(msg);

            Transport.send(message);
            return 1;
        } catch (MessagingException e) {
            e.printStackTrace();
            return 0;
        }
    }
}

