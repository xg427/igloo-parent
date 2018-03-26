package org.iglooproject.basicapp.core.business.notification.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.iglooproject.basicapp.core.business.user.model.User;
import org.iglooproject.spring.notification.model.INotificationContentDescriptor;
import org.iglooproject.spring.notification.util.NotificationContentDescriptors;


/**
 * Implémentation bouche-trou, uniquement pour combler la dépendance.
 */
public class EmptyNotificationContentDescriptorFactoryImpl implements IBasicApplicationNotificationContentDescriptorFactory {
	
	private static final INotificationContentDescriptor DEFAULT_DESCRIPTOR =
			NotificationContentDescriptors.explicit("defaultSubject", "defaultTextBody", "defaultHtmlBody");

	/**
	 * @deprecated Use new API date from java.time.
	 */
	@Deprecated
	@Override
	public INotificationContentDescriptor example(User user, Date date) {
		return DEFAULT_DESCRIPTOR;
	}

	@Override
	public INotificationContentDescriptor example(User user, LocalDateTime date) {
		return DEFAULT_DESCRIPTOR;
	}

	@Override
	public INotificationContentDescriptor userPasswordRecoveryRequest(User user) {
		return DEFAULT_DESCRIPTOR;
	}

}
