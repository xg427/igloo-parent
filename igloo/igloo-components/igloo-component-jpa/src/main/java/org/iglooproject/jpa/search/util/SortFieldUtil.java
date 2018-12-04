package org.iglooproject.jpa.search.util;

import java.util.Collection;

import javax.persistence.EntityManager;

import org.apache.lucene.search.SortField;
import org.hibernate.search.engine.search.SearchSort;
import org.hibernate.search.engine.search.dsl.sort.FieldSortContext;
import org.hibernate.search.engine.search.dsl.sort.SearchSortContainerContext;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.jpa.FullTextEntityManager;
import org.hibernate.search.mapper.orm.jpa.FullTextSearchTarget;

public final class SortFieldUtil {

	public static final SearchSort getSort(EntityManager entityManager, Class<?> entityClass, SortField... sortFields) {
		if (sortFields == null || sortFields.length == 0) {
			return null;
		} else {
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
			FullTextSearchTarget<?> queryBuilder = fullTextEntityManager.search(entityClass);
			SearchSortContainerContext context = queryBuilder.sort();
			FieldSortContext fieldContext = null;
			for (SortField sortField : sortFields) {
				if (fieldContext == null) {
					fieldContext = context.byField(sortField.getField());
				} else {
					fieldContext = fieldContext.then().byField(sortField.getField());
				}
				if ( ! sortField.getReverse()) {
					fieldContext.asc();
				} else {
					fieldContext.desc();
				}
			}
			if (fieldContext != null) {
				return fieldContext.toSort();
			} else {
				return null;
			}
		}
	}

	public static void setSort(FullTextSearchTarget<?> ftq, EntityManager entityManager, Class<?> entityClass, SortField... sortFields) {
		ftq.sort().by(getSort(entityManager, entityClass, sortFields));
	}

	public static void setSort(FullTextSearchTarget<?> ftq, EntityManager entityManager, Class<?> entityClass, Collection<SortField> sortFields) {
		ftq.sort().by(getSort(entityManager, entityClass, sortFields.toArray(new SortField[sortFields.size()])));
	}

	// hibernate 6.0.0 / removed method
	//	public static void setSort(FullTextSearchTarget<?> ftq, EntityManager entityManager, Class<?> entityClass, Sort sort) {
	//		setSort(ftq, entityManager, entityClass, sort.getSort());
	//	}

	private SortFieldUtil() {} // NOSONAR
}
