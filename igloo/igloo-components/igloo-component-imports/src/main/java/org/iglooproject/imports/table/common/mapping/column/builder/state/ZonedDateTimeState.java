package org.iglooproject.imports.table.common.mapping.column.builder.state;

import java.time.ZonedDateTime;

import org.iglooproject.functional.Function2;
import org.iglooproject.functional.builder.function.generic.GenericZonedDateTimeBuildStateImpl;
import org.iglooproject.imports.table.common.mapping.AbstractTableImportColumnSet;

public abstract class ZonedDateTimeState<TTable, TRow, TCell, TCellReference> extends GenericZonedDateTimeBuildStateImpl
		<
		AbstractTableImportColumnSet<TTable, TRow, TCell, TCellReference>.Column<ZonedDateTime>,
		ColumnFunctionBuildStateSwitcher<TTable, TRow, TCell, TCellReference, ZonedDateTime>,
		BooleanState<TTable, TRow, TCell, TCellReference>,
		DateState<TTable, TRow, TCell, TCellReference>,
		LocalDateTimeState<TTable, TRow, TCell, TCellReference>,
		LocalDateState<TTable, TRow, TCell, TCellReference>,
		LocalTimeState<TTable, TRow, TCell, TCellReference>,
		ZonedDateTimeState<TTable, TRow, TCell, TCellReference>,
		IntegerState<TTable, TRow, TCell, TCellReference>,
		LongState<TTable, TRow, TCell, TCellReference>,
		DoubleState<TTable, TRow, TCell, TCellReference>,
		BigDecimalState<TTable, TRow, TCell, TCellReference>,
		StringState<TTable, TRow, TCell, TCellReference>
		>
		implements ColumnFunctionBuildState<TTable, TRow, TCell, TCellReference, ZonedDateTime> {
		
	@Override
	public <TValue> GenericState<TTable, TRow, TCell, TCellReference, TValue> transform(Function2<? super ZonedDateTime, TValue> function) {
		return getStateSwitcher().toGeneric(function);
	}

}
