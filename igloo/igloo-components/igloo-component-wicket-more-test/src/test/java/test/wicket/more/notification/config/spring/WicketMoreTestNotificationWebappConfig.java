package test.wicket.more.notification.config.spring;

import org.apache.wicket.protocol.http.WebApplication;
import org.iglooproject.wicket.more.config.spring.AbstractWebappConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import test.wicket.more.config.spring.WicketMoreTestCoreCommonConfig;
import test.wicket.more.notification.application.WicketMoreTestNotificationApplication;

/**
 * Stub.
 */
@Configuration
@Import({
	WicketMoreTestCoreCommonConfig.class,
	NotificationTestConfig.class
})
public class WicketMoreTestNotificationWebappConfig extends AbstractWebappConfig {

	@Override
	@Bean
	public WebApplication application() {
		return new WicketMoreTestNotificationApplication();
	}

}
