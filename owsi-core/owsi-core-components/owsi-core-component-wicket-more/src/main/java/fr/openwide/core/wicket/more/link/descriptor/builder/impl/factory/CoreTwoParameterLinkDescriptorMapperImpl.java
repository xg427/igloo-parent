package fr.openwide.core.wicket.more.link.descriptor.builder.impl.factory;

import org.apache.wicket.model.IModel;
import org.javatuples.Pair;

import fr.openwide.core.wicket.more.link.descriptor.ILinkDescriptor;
import fr.openwide.core.wicket.more.link.descriptor.mapper.AbstractTwoParameterLinkDescriptorMapper;
import fr.openwide.core.wicket.more.link.descriptor.mapper.ITwoParameterLinkDescriptorMapper;

public class CoreTwoParameterLinkDescriptorMapperImpl<TLinkDescriptor extends ILinkDescriptor, TParam1, TParam2>
		extends AbstractTwoParameterLinkDescriptorMapper<TLinkDescriptor, TParam1, TParam2>
		implements ITwoParameterLinkDescriptorMapper<TLinkDescriptor, TParam1, TParam2> {
	private static final long serialVersionUID = -4881770003726056213L;
	
	private final CoreLinkDescriptorMapperLinkDescriptorFactory<TLinkDescriptor> factory;

	public CoreTwoParameterLinkDescriptorMapperImpl(CoreLinkDescriptorMapperLinkDescriptorFactory<TLinkDescriptor> factory) {
		this.factory = factory;
	}

	@Override
	public TLinkDescriptor map(IModel<TParam1> model1, IModel<TParam2> model2) {
		return factory.create(Pair.with(model1, model2));
	}
	
	@Override
	public void detach() {
		super.detach();
		factory.detach();
	}

}
