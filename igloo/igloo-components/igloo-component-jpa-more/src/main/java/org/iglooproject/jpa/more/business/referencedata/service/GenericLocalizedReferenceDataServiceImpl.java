package org.iglooproject.jpa.more.business.referencedata.service;

import java.util.Comparator;
import java.util.List;

import org.iglooproject.jpa.more.business.generic.model.search.EnabledFilter;
import org.iglooproject.jpa.more.business.referencedata.dao.IGenericLocalizedReferenceDataDao;
import org.iglooproject.jpa.more.business.referencedata.model.GenericReferenceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;

@Service
public class GenericLocalizedReferenceDataServiceImpl implements IGenericLocalizedReferenceDataService {

	@Autowired
	private IGenericReferenceDataService genericReferenceDataService;

	@Autowired
	public GenericLocalizedReferenceDataServiceImpl(IGenericLocalizedReferenceDataDao referenceDataDao) {
		super();
	}

	@Override
	public <E extends GenericReferenceData<?, ?>> E getById(Class<E> clazz, Long id) {
		return genericReferenceDataService.getById(clazz, id);
	}

	@Override
	public <E extends GenericReferenceData<?, ?>> void create(E entity) {
		genericReferenceDataService.create(entity);
	}

	@Override
	public <E extends GenericReferenceData<?, ?>> void update(E entity) {
		genericReferenceDataService.update(entity);
	}

	@Override
	public <E extends GenericReferenceData<?, ?>> void delete(E entity) {
		genericReferenceDataService.delete(entity);
	}

	@Override
	public <E extends GenericReferenceData<?, ?>> long count(Class<E> clazz) {
		return genericReferenceDataService.count(clazz);
	}

	@Override
	public <E extends GenericReferenceData<?, ?>> long count(Class<E> clazz, EnabledFilter enabledFilter) {
		return genericReferenceDataService.count(clazz, enabledFilter);
	}

	@Override
	public <E extends GenericReferenceData<?, ?>> List<E> list(Class<E> clazz) {
		return genericReferenceDataService.list(clazz);
	}

	@Override
	public <E extends GenericReferenceData<?, ?>> List<E> list(Class<E> clazz, Comparator<? super E> comparator) {
		return genericReferenceDataService.list(clazz, comparator);
	}

	@Override
	public <E extends GenericReferenceData<?, ?>> List<E> list(Class<E> clazz, EnabledFilter enabledFilter, Comparator<? super E> comparator) {
		return genericReferenceDataService.list(clazz, enabledFilter, comparator);
	}

	@Override
	public <E extends GenericReferenceData<?, ?>> List<E> listEnabled(Class<E> clazz) {
		return genericReferenceDataService.listEnabled(clazz);
	}

	@Override
	public <E extends GenericReferenceData<?, ?>> List<E> listEnabled(Class<E> clazz, Comparator<? super E> comparator) {
		return genericReferenceDataService.listEnabled(clazz, comparator);
	}

	@Override
	public <E extends GenericReferenceData<?, ?>, V extends Comparable<?>> E getByField(EntityPath<E> entityPath, SimpleExpression<V> field, V fieldValue) {
		return genericReferenceDataService.getByField(entityPath, field, fieldValue);
	}

	@Override
	public <E extends GenericReferenceData<?, ?>> E getByFieldIgnoreCase(EntityPath<E> entityPath, StringExpression field, String fieldValue) {
		return genericReferenceDataService.getByFieldIgnoreCase(entityPath, field, fieldValue);
	}

}
