package fr.openwide.core.wicket.more.link.descriptor.builder.state;

import org.apache.wicket.Page;
import org.apache.wicket.model.IModel;

import fr.openwide.core.wicket.more.link.descriptor.IPageLinkDescriptor;

public interface IBasePageState {
	
	<P extends Page> IParameterMappingState<? extends IPageLinkDescriptor> page(Class<P> pageClass);
	
	IParameterMappingState<? extends IPageLinkDescriptor> page(IModel<? extends Class<? extends Page>> pageClassModel);

}