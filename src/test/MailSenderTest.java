package test;

import javax.mail.MessagingException;

import org.junit.Test;

import com.cineplex.tools.MailFactory;
import com.cineplex.tools.MailSender;
import com.cineplex.tools.MailSenderType;

public class MailSenderTest {

	@Test
	public void test() {
//		fail("Not yet implemented");
		MailSender sender = MailFactory.getSender(MailSenderType.SERVICE);
		String recipient = "lwt394000785@sina.com";
		String subject = "mail test";
		String content = "this is a content";
		try {
			sender.send(recipient, subject, content);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
