package org.iglooproject.functional.builder.function.generic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;

import org.iglooproject.functional.Function2;
import org.iglooproject.functional.builder.function.BigDecimalFunctionBuildState;
import org.iglooproject.functional.builder.function.BooleanFunctionBuildState;
import org.iglooproject.functional.builder.function.DateFunctionBuildState;
import org.iglooproject.functional.builder.function.DoubleFunctionBuildState;
import org.iglooproject.functional.builder.function.FunctionBuildState;
import org.iglooproject.functional.builder.function.IntegerFunctionBuildState;
import org.iglooproject.functional.builder.function.LocalDateFunctionBuildState;
import org.iglooproject.functional.builder.function.LocalDateTimeFunctionBuildState;
import org.iglooproject.functional.builder.function.LocalTimeFunctionBuildState;
import org.iglooproject.functional.builder.function.LongFunctionBuildState;
import org.iglooproject.functional.builder.function.StringFunctionBuildState;
import org.iglooproject.functional.builder.function.ZonedDateTimeFunctionBuildState;

public abstract class GenericFunctionBuildStateImpl
		<
		TBuildResult,
		TCurrentType,
		TStateSwitcher extends FunctionBuildStateSwitcher<TBuildResult, TCurrentType, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState>,
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
		implements FunctionBuildState<TBuildResult, TCurrentType, TBooleanState, TDateState, TLocalDateTimeState, TLocalDateState, TLocalTimeState, TZonedDateTimeState, TIntegerState, TLongState, TDoubleState, TBigDecimalState, TStringState> {
	
	protected abstract TStateSwitcher getStateSwitcher();

	@Override
	public TStringState toString(Function2<? super TCurrentType, String> function) {
		return getStateSwitcher().toString(function);
	}

	@Override
	public TIntegerState toInteger(Function2<? super TCurrentType, Integer> function) {
		return getStateSwitcher().toInteger(function);
	}

	@Override
	public TLongState toLong(Function2<? super TCurrentType, Long> function) {
		return getStateSwitcher().toLong(function);
	}

	@Override
	public TDoubleState toDouble(Function2<? super TCurrentType, Double> function) {
		return getStateSwitcher().toDouble(function);
	}
	
	@Override
	public TBigDecimalState toBigDecimal(Function2<? super TCurrentType, BigDecimal> function) {
		return getStateSwitcher().toBigDecimal(function);
	}

	@Override
	public TDateState toDate(Function2<? super TCurrentType, ? extends Date> function) {
		return getStateSwitcher().toDate(function);
	}

	@Override
	public TLocalDateTimeState toLocalDateTime(Function2<? super TCurrentType, ? extends LocalDateTime> function) {
		return getStateSwitcher().toLocalDateTime(function);
	}

	@Override
	public TLocalDateState toLocalDate(Function2<? super TCurrentType, ? extends LocalDate> function) {
		return getStateSwitcher().toLocalDate(function);
	}

	@Override
	public TLocalTimeState toLocalTime(Function2<? super TCurrentType, ? extends LocalTime> function) {
		return getStateSwitcher().toLocalTime(function);
	}

	@Override
	public TZonedDateTimeState toZonedDateTime(Function2<? super TCurrentType, ? extends ZonedDateTime> function) {
		return getStateSwitcher().toZonedDateTime(function);
	}

	@Override
	public TBooleanState toBoolean(Function2<? super TCurrentType, Boolean> function) {
		return getStateSwitcher().toBoolean(function);
	}

	@Override
	public TBuildResult build() {
		return getStateSwitcher().build();
	}

}