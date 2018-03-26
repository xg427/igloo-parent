package org.iglooproject.jpa.more.business.history.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.iglooproject.jpa.more.business.history.model.AbstractHistoryLog;
import org.iglooproject.jpa.more.business.history.model.embeddable.HistoryEventSummary;

public interface IGenericHistoryEventSummaryService<U> {

	void refresh(HistoryEventSummary event);

	void refresh(HistoryEventSummary event, LocalDateTime date);

	/**
	 * @deprecated Use new API date from java.time.
	 */
	@Deprecated
	void refresh(HistoryEventSummary event, Date date);

	void refresh(HistoryEventSummary event, LocalDateTime date, U subject);

	/**
	 * @deprecated Use new API date from java.time.
	 */
	@Deprecated
	void refresh(HistoryEventSummary event, Date date, U subject);

	void refresh(HistoryEventSummary event, AbstractHistoryLog<?, ?, ?> historyLog);

	void clear(HistoryEventSummary event);

	boolean isSubject(HistoryEventSummary event, U subject);

}
