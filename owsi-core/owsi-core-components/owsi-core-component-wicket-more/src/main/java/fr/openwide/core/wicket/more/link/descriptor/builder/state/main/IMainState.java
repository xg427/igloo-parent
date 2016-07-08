package fr.openwide.core.wicket.more.link.descriptor.builder.state.main;

import fr.openwide.core.wicket.more.link.descriptor.builder.state.parameter.mapping.IParameterMappingState;
import fr.openwide.core.wicket.more.link.descriptor.builder.state.validator.IValidatorState;

/**
 * One of the main states, from which one may:
 * <ul>
 *  <li>map already-defined model parameters, by calling methods from {@link IParameterMappingState}.
 *  <li>add link validators, by calling methods from {@link IValidatorState}.
 *  <li>and depending on the actual state ({@link INoMappableParameterMainState}, {@link IOneMappableParameterMainState}, ...), add new
 *  model parameters.
 * </ul>
 */
public interface IMainState
		<
		TSelf extends IMainState<TSelf>
		>
		extends IParameterMappingState<TSelf>, IValidatorState<TSelf> {

}
