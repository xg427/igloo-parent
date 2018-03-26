package org.iglooproject.jpa.more.business.history.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.iglooproject.commons.util.date.Dates;
import org.iglooproject.jpa.more.business.history.model.AbstractHistoryLog;
import org.iglooproject.jpa.more.business.history.model.embeddable.HistoryEventSummary;
import org.iglooproject.jpa.util.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractHistoryEventSummaryServiceImpl<U> implements IGenericHistoryEventSummaryService<U> {
	
	@Autowired
	private IHistoryValueService valueService;

	protected abstract U getDefaultSubject();

	@Override
	public void refresh(HistoryEventSummary evenement) {
		refresh(evenement, new Date());
	}
	
	@Override
	public void refresh(HistoryEventSummary evenement, Date date) {
		refresh(evenement, Dates.toLocalDateTime(date));
	}
	
	@Override
	public void refresh(HistoryEventSummary evenement, LocalDateTime date) {
		refresh(evenement, date, getDefaultSubject());
	}
	
	@Override
	public void refresh(HistoryEventSummary evenement, Date date, U subject) {
		refresh(evenement, Dates.toLocalDateTime(date), subject);
	}
	
	@Override
	public void refresh(HistoryEventSummary evenement, LocalDateTime date, U subject) {
		evenement.setDate(date);
		evenement.setSubject(valueService.create(HibernateUtils.unwrap(subject)));
	}
	
	@Override
	public void refresh(HistoryEventSummary evenement, AbstractHistoryLog<?, ?, ?> historyLog) {
		evenement.setDate(historyLog.getDate());
		evenement.setSubject(historyLog.getSubject());
	}
	
	@Override
	public void clear(HistoryEventSummary event) {
		event.setDate((LocalDateTime) null);
		event.setSubject(null);
	}
	
	@Override
	public boolean isSubject(HistoryEventSummary event, U subject) {
		return valueService.matches(event.getSubject(), subject).or(false);
	}

}
