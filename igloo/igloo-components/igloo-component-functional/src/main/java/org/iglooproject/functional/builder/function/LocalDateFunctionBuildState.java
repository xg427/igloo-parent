package org.iglooproject.functional.builder.function;

import java.time.LocalDate;

public interface LocalDateFunctionBuildState
		<
		TBuildResult,
		TBooleanState extends BooleanFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TDateState extends DateFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TLocalDateTimeState extends LocalDateTimeFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TLocalDateState extends LocalDateFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TLocalTimeState extends LocalTimeFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TZonedDateTimeState extends ZonedDateTimeFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TIntegerState extends IntegerFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TLongState extends LongFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TDoubleState extends DoubleFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TBigDecimalState extends BigDecimalFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
		TStringState extends StringFunctionBuildState<?, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>
		>
		extends FunctionBuildState<TBuildResult, LocalDate, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState> {

}
