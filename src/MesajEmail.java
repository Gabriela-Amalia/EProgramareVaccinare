import javax.mail.*;
import java.util.*;
import javax.mail.internet.*;
public class MesajEmail {
    
    private  Session session;
    private  String host;
    private String emailAddress, messageEmail,  subject,  username,  password;
    
      public boolean trimiteEmail() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        if(username.contains("@gmail.")){
            host = "smtp.gmail.com";
        }else if(username.contains("@yahoo.")){
            host = "smtp.mail.yahoo.com";
        }
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
       

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
            message.setSubject(subject);
            message.setText(messageEmail);

            Transport.send(message);
            System.out.println("Mesajul a fost trimis!");
            return true;
        } catch (MessagingException e) {
            System.out.println("Operatia de trimitere email a esuat!"+ e.getMessage());
            return false;
        }
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setMessageEmail(String messageEmail) {
        this.messageEmail = messageEmail;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

     
}
