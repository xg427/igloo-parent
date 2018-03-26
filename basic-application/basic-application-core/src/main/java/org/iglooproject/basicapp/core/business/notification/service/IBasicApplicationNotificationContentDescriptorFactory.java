package org.iglooproject.basicapp.core.business.notification.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.iglooproject.basicapp.core.business.user.model.User;
import org.iglooproject.spring.notification.model.INotificationContentDescriptor;

public interface IBasicApplicationNotificationContentDescriptorFactory {

	/**
	 * @deprecated Use new API date from java.time.
	 */
	@Deprecated
	INotificationContentDescriptor example(User user, Date date);

	INotificationContentDescriptor example(User user, LocalDateTime date);

	INotificationContentDescriptor userPasswordRecoveryRequest(User user);

}
