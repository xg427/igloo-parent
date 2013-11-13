package fr.openwide.core.wicket.more.notification.service;

import org.apache.wicket.Page;
import org.apache.wicket.core.request.handler.BookmarkablePageRequestHandler;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.lang.Args;

import fr.openwide.core.spring.util.StringUtils;
import fr.openwide.core.wicket.more.link.descriptor.IPageLinkDescriptor;

public abstract class AbstractNotificationUrlBuilderServiceImpl extends AbstractBackgroundWicketThreadContextBuilder {
	
	private static final String ANCHOR_ROOT = "#";
	
	protected String buildUrl(IPageLinkDescriptor pageLinkDescriptor) {
		return buildUrl(pageLinkDescriptor);
	}
	
	protected String buildUrl(IPageLinkDescriptor pageLinkDescriptor, String anchor) {
		Args.notNull(pageLinkDescriptor, "pageLinkDescriptor");
		
		RequestCycleThreadAttachmentStatus requestCycleStatus = null;
		
		try {
			requestCycleStatus = attachRequestCycleIfNeeded(getApplicationName());
			
			StringBuilder url = new StringBuilder();
			url.append(pageLinkDescriptor.fullUrl());
			if (StringUtils.hasText(anchor)) {
				url.append(ANCHOR_ROOT).append(anchor);
			}
			
			return url.toString();
		} finally {
			if (requestCycleStatus != null) {
				detachRequestCycleIfNeeded(requestCycleStatus);
			}
		}
	}
	
	@Deprecated
	protected String buildUrl(Class<? extends Page> pageClass, PageParameters parameters) {
		return buildUrl(pageClass, parameters, null);
	}
	
	@Deprecated
	protected String buildUrl(Class<? extends Page> pageClass, PageParameters parameters, String anchor) {
		return buildUrl(getApplicationName(), new BookmarkablePageRequestHandler(new PageProvider(pageClass, parameters)), anchor);
	}
	
	@Deprecated
	protected String buildUrl(String applicationName, IRequestHandler requestHandler) {
		return buildUrl(applicationName, requestHandler, null);
	}
	
	@Deprecated
	protected String buildUrl(String applicationName, IRequestHandler requestHandler, String anchor) {
		Args.notNull(applicationName, "applicationName");
		Args.notNull(requestHandler, "requestHandler");
		
		RequestCycleThreadAttachmentStatus requestCycleStatus = null;
		
		try {
			requestCycleStatus = attachRequestCycleIfNeeded(getApplicationName());
			
			StringBuilder url = new StringBuilder();
			url.append(RequestCycle.get().getUrlRenderer().renderFullUrl(Url.parse(RequestCycle.get().urlFor(requestHandler))));
			if (StringUtils.hasText(anchor)) {
				url.append(ANCHOR_ROOT).append(anchor);
			}
			
			return url.toString();
		} finally {
			if (requestCycleStatus != null) {
				detachRequestCycleIfNeeded(requestCycleStatus);
			}
		}
	}

}
