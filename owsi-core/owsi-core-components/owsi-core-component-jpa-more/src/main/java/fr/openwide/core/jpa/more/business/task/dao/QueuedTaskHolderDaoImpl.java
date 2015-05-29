package fr.openwide.core.jpa.more.business.task.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

import fr.openwide.core.jpa.business.generic.dao.GenericEntityDaoImpl;
import fr.openwide.core.jpa.more.business.task.model.QQueuedTaskHolder;
import fr.openwide.core.jpa.more.business.task.model.QueuedTaskHolder;
import fr.openwide.core.jpa.more.business.task.util.TaskStatus;

public class QueuedTaskHolderDaoImpl extends GenericEntityDaoImpl<Long, QueuedTaskHolder>
		implements IQueuedTaskHolderDao {

	private static final QQueuedTaskHolder qQueuedTaskHolder = QQueuedTaskHolder.queuedTaskHolder; // NOSONAR
	
	@Override
	public Long count(Date since, TaskStatus... statuses) {
		JPQLQuery query = new JPAQuery(getEntityManager());

		query.from(qQueuedTaskHolder).where(
				qQueuedTaskHolder.status.in(statuses).and(qQueuedTaskHolder.creationDate.after(since)));

		return query.count();
	}
	
	@Override
	public Long count(TaskStatus... statuses) {
		JPQLQuery query = new JPAQuery(getEntityManager());

		query.from(qQueuedTaskHolder).where(qQueuedTaskHolder.status.in(statuses));

		return query.count();
	}
	
	@Override
	public QueuedTaskHolder getNextTaskForExecution(String taskType) {
		Date now = new Date();
		
		QQueuedTaskHolder qQueuedTask = QQueuedTaskHolder.queuedTaskHolder;
		
		JPQLQuery query = new JPAQuery(getEntityManager());
		
		query.from(qQueuedTask)
				.where(qQueuedTask.taskType.eq(taskType)
						.and(qQueuedTask.startDate.isNull())
						.and(qQueuedTask.triggeringDate.isNull()
								.or(qQueuedTask.triggeringDate.before(now))))
				.orderBy(qQueuedTask.version.asc(), qQueuedTask.creationDate.asc());
		
		List<QueuedTaskHolder> tasksForExecution = query.list(qQueuedTask);
		
		if (tasksForExecution.isEmpty()) {
			return null;
		} else {
			return tasksForExecution.get(0);
		}
	}

	@Override
	public QueuedTaskHolder getStalledTask(String taskType, int executionTimeLimitInSeconds) {
		Calendar timeLimit = Calendar.getInstance();
		timeLimit.add(Calendar.SECOND, -executionTimeLimitInSeconds);
		
		QQueuedTaskHolder qQueuedTask = QQueuedTaskHolder.queuedTaskHolder;
		
		JPQLQuery query = new JPAQuery(getEntityManager());
		
		query.from(qQueuedTask)
				.where(qQueuedTask.startDate.isNotNull()
						.and(qQueuedTask.taskType.eq(taskType))
						.and(qQueuedTask.startDate.before(timeLimit.getTime())));
		
		List<QueuedTaskHolder> stalledTasks = query.list(qQueuedTask);
		
		if (stalledTasks.isEmpty()) {
			return null;
		} else {
			return stalledTasks.get(0);
		}
	}
	
	@Override
	public List<QueuedTaskHolder> listConsumable() {
		JPQLQuery query = new JPAQuery(getEntityManager());
		
		query.from(qQueuedTaskHolder)
				.where(qQueuedTaskHolder.status.in(TaskStatus.CONSUMABLE_TASK_STATUS))
				.orderBy(qQueuedTaskHolder.id.asc());
		
		return query.list(qQueuedTaskHolder);
	}
	
	@Override
	public List<QueuedTaskHolder> listConsumable(String queueId) {
		JPQLQuery query = new JPAQuery(getEntityManager());
		
		query.from(qQueuedTaskHolder)
				.where(qQueuedTaskHolder.status.in(TaskStatus.CONSUMABLE_TASK_STATUS))
				.orderBy(qQueuedTaskHolder.id.asc());
		
		if (queueId != null) {
			query.where(qQueuedTaskHolder.queueId.eq(queueId));
		} else {
			query.where(qQueuedTaskHolder.queueId.isNull());
		}
		
		return query.list(qQueuedTaskHolder);
	}

	@Override
	public List<String> listTypes() {
		JPQLQuery query = new JPAQuery(getEntityManager());

		query.from(qQueuedTaskHolder).orderBy(qQueuedTaskHolder.taskType.asc()).distinct();

		return query.list(qQueuedTaskHolder.taskType);
	}
}
