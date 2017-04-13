package fr.openwide.core.wicket.more.console.maintenance.infinispan.component;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import fr.openwide.core.infinispan.model.INode;
import fr.openwide.core.infinispan.service.IInfinispanClusterService;
import fr.openwide.core.wicket.more.markup.repeater.collection.CollectionView;
import fr.openwide.core.wicket.more.util.model.Detachables;
import fr.openwide.core.wicket.more.util.model.Models;

public class ConsoleMaintenanceInfinispanClusterPanel extends Panel {

	private static final long serialVersionUID = -18845870460913498L;

	@SpringBean
	private IInfinispanClusterService infinispanClusterService;

	private final IModel<List<INode>> nodesModel;

	public ConsoleMaintenanceInfinispanClusterPanel(String id) {
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
						
//						add(
//								
//						);
					}
				}
		);
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		Detachables.detach(nodesModel);
	}

}
