package org.iglooproject.wicket.more.util.binding;

import org.bindgen.java.util.ListBinding;
import org.iglooproject.commons.util.mime.MediaTypeBinding;
import org.iglooproject.infinispan.model.IAttributionBinding;
import org.iglooproject.infinispan.model.ILockAttributionBinding;
import org.iglooproject.infinispan.model.ILockBinding;
import org.iglooproject.infinispan.model.INodeBinding;
import org.iglooproject.infinispan.model.IRoleAttributionBinding;
import org.iglooproject.infinispan.model.IRoleBinding;
import org.iglooproject.jpa.more.infinispan.model.TaskQueueStatusBinding;
import org.iglooproject.jpa.security.business.person.model.GenericUserBinding;
import org.iglooproject.wicket.more.console.maintenance.ehcache.model.EhCacheCacheInformationBinding;
import org.iglooproject.wicket.more.model.IBindableDataProviderBinding;

public final class CoreWicketMoreBindings {

	@SuppressWarnings("rawtypes")
	public static final GenericUserBinding GENERIC_USER = new GenericUserBinding<>();

	private static final EhCacheCacheInformationBinding EH_CACHE_CACHE_INFORMATION = new EhCacheCacheInformationBinding();

	private static final IBindableDataProviderBinding IBINDABLE_DATA_PROVIDER = new IBindableDataProviderBinding();

	private static final ListBinding<?> LIST = new ListBinding<Void>();

	private static final MediaTypeBinding MEDIA_TYPE = new MediaTypeBinding();

	private static final INodeBinding I_NODE = new INodeBinding();

	private static final ILockBinding I_LOCK = new ILockBinding();
	private static final ILockAttributionBinding I_LOCK_ATTRIBUTION = new ILockAttributionBinding();

	private static final IRoleBinding I_ROLE = new IRoleBinding();
	private static final IRoleAttributionBinding I_ROLE_ATTRIBUTION = new IRoleAttributionBinding();

	private static final IAttributionBinding I_ATTRIBUTION = new IAttributionBinding();

	private static final TaskQueueStatusBinding TASK_QUEUE_STATUS = new TaskQueueStatusBinding(); 

	public static EhCacheCacheInformationBinding ehCacheCacheInformation() {
		return EH_CACHE_CACHE_INFORMATION;
	}

	public static IBindableDataProviderBinding iBindableDataProvider() {
		return IBINDABLE_DATA_PROVIDER;
	}

	public static ListBinding<?> list() {
		return LIST;
	}

	public static MediaTypeBinding mediaType() {
		return MEDIA_TYPE;
	}

	public static INodeBinding iNode() {
		return I_NODE;
	}

	public static ILockBinding iLock() {
		return I_LOCK;
	}

	public static ILockAttributionBinding iLockAttribution() {
		return I_LOCK_ATTRIBUTION;
	}

	public static IRoleBinding iRole() {
		return I_ROLE;
	}

	public static IRoleAttributionBinding iRoleAttribution() {
		return I_ROLE_ATTRIBUTION;
	}

	public static IAttributionBinding iAttribution(){
		return I_ATTRIBUTION;
	}

	public static TaskQueueStatusBinding taskQueueStatus(){
		return TASK_QUEUE_STATUS;
	}

	private CoreWicketMoreBindings() {
	}

}
