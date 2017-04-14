package fr.openwide.core.wicket.more.console.maintenance.infinispan.component;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import fr.openwide.core.infinispan.model.INode;
import fr.openwide.core.infinispan.model.INodeBinding;
import fr.openwide.core.infinispan.service.IInfinispanClusterService;
import fr.openwide.core.wicket.markup.html.basic.CoreLabel;
import fr.openwide.core.wicket.more.condition.Condition;
import fr.openwide.core.wicket.more.console.maintenance.infinispan.renderer.INodeRenderer;
import fr.openwide.core.wicket.more.markup.html.basic.DateLabel;
import fr.openwide.core.wicket.more.markup.html.basic.PlaceholderContainer;
import fr.openwide.core.wicket.more.markup.html.bootstrap.label.component.BootstrapBadge;
import fr.openwide.core.wicket.more.markup.repeater.collection.CollectionView;
import fr.openwide.core.wicket.more.model.BindingModel;
import fr.openwide.core.wicket.more.util.DatePattern;
import fr.openwide.core.wicket.more.util.binding.CoreWicketMoreBindings;
import fr.openwide.core.wicket.more.util.model.Detachables;
import fr.openwide.core.wicket.more.util.model.Models;

public class ConsoleMaintenanceInfinispanClusterPanelOld extends Panel {

	private static final long serialVersionUID = -18845870460913498L;

	@SpringBean
	private IInfinispanClusterService infinispanClusterService;

	private final IModel<List<INode>> nodesModel;

	public ConsoleMaintenanceInfinispanClusterPanelOld(String id) {
		super(id);
		
		nodesModel = new LoadableDetachableModel<List<INode>>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected List<INode> load() {
				return infinispanClusterService.getNodes();
			}
		};
		
		add(
				new CollectionView<INode>("nodes", nodesModel, Models.<INode>serializableModelFactory()) {
					private static final long serialVersionUID = 1L;
					@Override
					protected void populateItem(Item<INode> item) {
						IModel<INode> nodeModel = item.getModel();
						
						item.add(
								new CoreLabel("address", BindingModel.of(nodeModel, CoreWicketMoreBindings.iNode().address()))
										.showPlaceholder(),
								new CoreLabel("name", BindingModel.of(nodeModel, CoreWicketMoreBindings.iNode().name()))
										.showPlaceholder(),
								new DateLabel("creationDate", BindingModel.of(nodeModel, CoreWicketMoreBindings.iNode().creationDate()), DatePattern.REALLY_SHORT_DATETIME)
										.showPlaceholder(),
								new BootstrapBadge<>("anonymous", nodeModel, INodeRenderer.anonymous())
						);
					}
				},
				new PlaceholderContainer("nodesPlaceholder")
						.condition(Condition.collectionModelNotEmpty(nodesModel))
		);
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		Detachables.detach(nodesModel);
	}

}
