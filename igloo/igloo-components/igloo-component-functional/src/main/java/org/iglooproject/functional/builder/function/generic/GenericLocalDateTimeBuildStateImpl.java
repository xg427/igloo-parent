package org.iglooproject.functional.builder.function.generic;

import java.time.LocalDateTime;

import org.iglooproject.functional.Functions2;
import org.iglooproject.functional.builder.function.BigDecimalFunctionBuildState;
import org.iglooproject.functional.builder.function.BooleanFunctionBuildState;
import org.iglooproject.functional.builder.function.DateFunctionBuildState;
import org.iglooproject.functional.builder.function.DoubleFunctionBuildState;
import org.iglooproject.functional.builder.function.IntegerFunctionBuildState;
import org.iglooproject.functional.builder.function.LocalDateFunctionBuildState;
import org.iglooproject.functional.builder.function.LocalDateTimeFunctionBuildState;
import org.iglooproject.functional.builder.function.LocalTimeFunctionBuildState;
import org.iglooproject.functional.builder.function.LongFunctionBuildState;
import org.iglooproject.functional.builder.function.StringFunctionBuildState;
import org.iglooproject.functional.builder.function.ZonedDateTimeFunctionBuildState;

public abstract class GenericLocalDateTimeBuildStateImpl
		<
		TBuildResult,
		TStateSwitcher extends FunctionBuildStateSwitcher<TBuildResult, LocalDateTime, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TBooleanState extends BooleanFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TDateState extends DateFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TLocalDateTimeState extends LocalDateTimeFunctionBuildState<TBuildResult, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TLocalDateState extends LocalDateFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TLocalTimeState extends LocalTimeFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TZonedDateTimeState extends ZonedDateTimeFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TIntegerState extends IntegerFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TLongState extends LongFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TDoubleState extends DoubleFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TBigDecimalState extends BigDecimalFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TStringState extends StringFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>
		>
		extends GenericFunctionBuildStateImpl<TBuildResult, LocalDateTime, TStateSwitcher, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>
		implements LocalDateTimeFunctionBuildState<TBuildResult, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState> {
	
	@Override
	public TBuildResult withDefault(final LocalDateTime defaultValue) {
		return toLocalDateTime(Functions2.defaultValue(defaultValue)).build();
	}

}
