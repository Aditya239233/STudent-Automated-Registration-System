package controller;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.Student;
import model.Index;

public class SendEmail implements Notification {

	/**
	 * This function is used to send a message through Email
	 * 
	 * @param student - refers to the student to whom the email is sent
	 * @param index   - refers to the Index that the student is added to
	 */
	public static void sendMessage(Student student, Index index) {
		final String username = "donotreplyblackboard5@gmail.com";
		final String password = "Pokemon@1234";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("aditya.chandrasekhar2001@gmail.com"));
			message.setSubject("STARS Planner");
			message.setText("Registered Courses: \nCourse" + index.getCourse().getID() + " "
					+ index.getCourse().getName() + "\nIndex: " + index.getID());

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
}
