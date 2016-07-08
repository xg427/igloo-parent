package fr.openwide.core.wicket.more.link.descriptor.builder.state.parameter.chosen;

import fr.openwide.core.wicket.more.link.descriptor.builder.state.parameter.chosen.common.IThreeChosenParameterState;


public interface IFourMappableParameterThreeChosenParameterState
		<
		InitialState,
		TParam1, TParam2, TParam3, TParam4,
		TChosenParam1, TChosenParam2, TChosenParam3,
		TLateTargetDefinitionPageResult,
		TLateTargetDefinitionResourceResult,
		TLateTargetDefinitionImageResourceResult
		>
		extends IThreeChosenParameterState
						<
						InitialState,
						TChosenParam1, TChosenParam2, TChosenParam3,
						TLateTargetDefinitionPageResult,
						TLateTargetDefinitionResourceResult,
						TLateTargetDefinitionImageResourceResult
						> {

	IFourMappableParameterFourChosenParameterState<
			InitialState,
			TParam1, TParam2, TParam3, TParam4,
			TChosenParam1, TChosenParam2, TChosenParam3, TParam1,
			TLateTargetDefinitionPageResult,
			TLateTargetDefinitionResourceResult,
			TLateTargetDefinitionImageResourceResult
			> andFirst();

	IFourMappableParameterFourChosenParameterState<
			InitialState,
			TParam1, TParam2, TParam3, TParam4,
			TChosenParam1, TChosenParam2, TChosenParam3, TParam2,
			TLateTargetDefinitionPageResult,
			TLateTargetDefinitionResourceResult,
			TLateTargetDefinitionImageResourceResult
			> andSecond();

	IFourMappableParameterFourChosenParameterState<
			InitialState,
			TParam1, TParam2, TParam3, TParam4,
			TChosenParam1, TChosenParam2, TChosenParam3, TParam3,
			TLateTargetDefinitionPageResult,
			TLateTargetDefinitionResourceResult,
			TLateTargetDefinitionImageResourceResult
			> andThird();

	IFourMappableParameterFourChosenParameterState<
			InitialState,
			TParam1, TParam2, TParam3, TParam4,
			TChosenParam1, TChosenParam2, TChosenParam3, TParam4,
			TLateTargetDefinitionPageResult,
			TLateTargetDefinitionResourceResult,
			TLateTargetDefinitionImageResourceResult
			> andFourth();

}
