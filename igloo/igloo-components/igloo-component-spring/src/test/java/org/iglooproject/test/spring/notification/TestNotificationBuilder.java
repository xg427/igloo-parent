package org.iglooproject.test.spring.notification;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.assertj.core.api.Assertions;
import org.iglooproject.config.bootstrap.spring.ExtendedApplicationContextInitializer;
import org.iglooproject.jpa.exception.ServiceException;
import org.iglooproject.spring.notification.exception.InvalidNotificationTargetException;
import org.iglooproject.spring.notification.service.INotificationBuilderBaseState;
import org.iglooproject.test.spring.notification.spring.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(
		classes = { TestConfig.class },
		initializers = { ExtendedApplicationContextInitializer.class }
)
public class TestNotificationBuilder extends AbstractTestNotification {

	@Test
	public void testToCcBccExcept() throws ServiceException {
		INotificationBuilderBaseState builder = createNotificationBuilder();
		
		builder.toAddress("test-to-1@example.com", "test-to-2@example.com", "mail@îdn.fr", "test-to-ignore@example.com")
				.ccAddress("test-to-2@example.com", "test-cc-1@example.com", "test-cc-2@example.com", "test-cc-ignore@example.com")
				.bccAddress("test-to-2@example.com", "test-cc-1@example.com", "test-bcc-1@example.com", "test-bcc-2@example.com", "test-bcc-ignore@example.com")
				.exceptAddress("test-to-ignore@example.com", "test-cc-ignore@example.com", "test-bcc-ignore@example.com")
				.subject("Test notification builder")
				.textBody("Test notification builder")
				.send();
	}

	@Test
	public void testInvalidEmail() throws ServiceException {
		INotificationBuilderBaseState builder = createNotificationBuilder();
		Assertions.assertThatThrownBy(() ->
			builder.toAddress("pas un mail valide")
					.subject("Test notification builder")
					.textBody("Test notification builder")
					.send()
		).isInstanceOf(InvalidNotificationTargetException.class);
	}

	@Test
	public void testSendGroup() throws ServiceException, MessagingException {
		INotificationBuilderBaseState builder = createNotificationBuilder();
		String address1 = "test-to-1@example.com";
		String address2 = "test-to-2@example.com";
		builder.toAddress(address1, address2).subject("subject").textBody("body").send();
		ArgumentCaptor<MimeMessage> argument = mockitoSend(Mockito.times(1));
		MimeMessage mimeMessage = argument.getValue();
		Assertions.assertThat(mimeMessage.getRecipients(RecipientType.TO)).hasSize(2)
			.anyMatch((a) -> ((InternetAddress) a).getAddress().equals(address1))
			.anyMatch((a) -> ((InternetAddress) a).getAddress().equals(address2));
	}

}
