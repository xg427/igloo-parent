package org.iglooproject.functional.builder.function;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;

import org.iglooproject.functional.Function2;

public interface FunctionBuildState
		<
		TBuildResult,
		TCurrentType,
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
		> {
	
	TStringState toString(Function2<? super TCurrentType, String> function);
	
	TIntegerState toInteger(Function2<? super TCurrentType, Integer> function);
	
	TLongState toLong(Function2<? super TCurrentType, Long> function);
	
	TDoubleState toDouble(Function2<? super TCurrentType, Double> function);
	
	TBigDecimalState toBigDecimal(Function2<? super TCurrentType, BigDecimal> function);
	
	TDateState toDate(Function2<? super TCurrentType, ? extends Date> function);
	
	TLocalDateTimeState toLocalDateTime(Function2<? super TCurrentType, ? extends LocalDateTime> function);
	
	TLocalDateState toLocalDate(Function2<? super TCurrentType, ? extends LocalDate> function);
	
	TLocalTimeState toLocalTime(Function2<? super TCurrentType, ? extends LocalTime> function);
	
	TZonedDateTimeState toZonedDateTime(Function2<? super TCurrentType, ? extends ZonedDateTime> function);
	
	TBooleanState toBoolean(Function2<? super TCurrentType, Boolean> function);
	
	TBuildResult build();
	
	TBuildResult withDefault(TCurrentType defaultValue);

}
