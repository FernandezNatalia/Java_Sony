package logica;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Emailer {
public static Emailer instance;
	
	private Properties props;
	
	public static Emailer getInstance(){
		if (instance==null){
			instance=new Emailer();
		}
		return instance;
	}
	
	public Emailer() {

		 props = new Properties();		
		 props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.starttls.enable", "true");
		 props.put("mail.smtp.host", "smtp.gmail.com");
		 props.put("mail.smtp.port", "587");
		 props.put("mail.username", "turnoswebjava@gmail.com");
		 props.put("mail.password","turnosjava");
		
	}
	
	public void send(String to, String subject, String body){

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props.getProperty("mail.username"), props.getProperty("mail.password"));
			}
		  });

		try {
			System.out.println("Enviando mail a "+ to +" ...");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("mail.username")));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to)); 
			message.setSubject(subject); 
			message.setText(body); 

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
