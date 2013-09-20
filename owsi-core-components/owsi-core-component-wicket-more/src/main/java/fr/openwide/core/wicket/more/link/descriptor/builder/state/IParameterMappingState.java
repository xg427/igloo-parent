package fr.openwide.core.wicket.more.link.descriptor.builder.state;

import org.apache.wicket.model.IModel;

import fr.openwide.core.wicket.more.link.descriptor.ILinkDescriptor;
import fr.openwide.core.wicket.more.link.descriptor.parameter.mapping.ILinkParameterMappingEntry;

public interface IParameterMappingState<L extends ILinkDescriptor> extends IValidatorState<L> {

	<T> IAddedParameterMappingState<L> map(String parameterName, IModel<T> valueModel, Class<T> valueType);
	
	IAddedParameterMappingState<L> map(ILinkParameterMappingEntry parameterMappingEntry);

}
