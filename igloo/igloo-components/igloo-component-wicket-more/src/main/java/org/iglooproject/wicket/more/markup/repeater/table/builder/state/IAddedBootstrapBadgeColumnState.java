package org.iglooproject.wicket.more.markup.repeater.table.builder.state;

import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.model.IModel;

import com.google.common.base.Function;

import org.iglooproject.commons.util.binding.AbstractCoreBinding;
import org.iglooproject.jpa.more.business.sort.ISort;
import org.iglooproject.wicket.more.condition.Condition;
import org.iglooproject.wicket.more.link.descriptor.generator.ILinkGenerator;
import org.iglooproject.wicket.more.link.descriptor.mapper.ILinkDescriptorMapper;
import org.iglooproject.wicket.more.markup.html.sort.ISortIconStyle;
import org.iglooproject.wicket.more.markup.html.sort.TableSortLink.CycleMode;

public interface IAddedBootstrapBadgeColumnState<T, S extends ISort<?>, C> extends IAddedCoreColumnState<T, S> {

	@Override
	IAddedBootstrapBadgeColumnState<T, S, C> when(Condition condition);
	
	@Override
	IAddedBootstrapBadgeColumnState<T, S, C> withClass(String cssClass);
	
	@Override
	IAddedBootstrapBadgeColumnState<T, S, C> withSort(S sort);

	@Override
	IAddedBootstrapBadgeColumnState<T, S, C> withSort(S sort, ISortIconStyle sortIconStyle);

	@Override
	IAddedBootstrapBadgeColumnState<T, S, C> withSort(S sort, ISortIconStyle sortIconStyle, CycleMode cycleMode);

	IAddedBootstrapBadgeColumnState<T, S, C> withLink(ILinkDescriptorMapper<? extends ILinkGenerator, ? super IModel<T>> linkGeneratorMapper);
	
	<E> IAddedBootstrapBadgeColumnState<T, S, C> withLink(Function<? super T, E> binding, ILinkDescriptorMapper<? extends ILinkGenerator, ? super IModel<E>> linkGeneratorMapper);

	<E> IAddedBootstrapBadgeColumnState<T, S, C> withLink(AbstractCoreBinding<? super T, E> binding, ILinkDescriptorMapper<? extends ILinkGenerator, ? super IModel<E>> linkGeneratorMapper);

	IAddedBootstrapBadgeColumnState<T, S, C> withSideLink(ILinkDescriptorMapper<? extends ILinkGenerator, ? super IModel<T>> linkGeneratorMapper);
	
	<E> IAddedBootstrapBadgeColumnState<T, S, C> withSideLink(Function<? super T, E> binding, ILinkDescriptorMapper<? extends ILinkGenerator, ? super IModel<E>> linkGeneratorMapper);

	<E> IAddedBootstrapBadgeColumnState<T, S, C> withSideLink(AbstractCoreBinding<? super T, E> binding, ILinkDescriptorMapper<? extends ILinkGenerator, ? super IModel<E>> linkGeneratorMapper);

	/**
	 * @deprecated This is the default behavior, so calling this method is generally useless. The method is here for
	 * compatibility reasons.
	 */
	@Deprecated
	IAddedBootstrapBadgeColumnState<T, S, C> disableIfInvalid();

	IAddedBootstrapBadgeColumnState<T, S, C> hideIfInvalid();

	IAddedBootstrapBadgeColumnState<T, S, C> throwExceptionIfInvalid();

	IAddedBootstrapBadgeColumnState<T, S, C> linkBehavior(Behavior linkBehavior);

	IAddedBootstrapBadgeColumnState<T, S, C> targetBlank();

}