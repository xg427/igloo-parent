package fr.openwide.core.wicket.more.markup.html.template.component;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

import fr.openwide.core.wicket.more.markup.html.basic.EnclosureBehavior;
import fr.openwide.core.wicket.more.markup.html.template.model.BreadCrumbElement;

public class BreadCrumbListView extends ListView<BreadCrumbElement> {

	private static final long serialVersionUID = -6354654324654295238L;

	public BreadCrumbListView(String id, IModel<List<BreadCrumbElement>> breadCrumb) {
		super(id, breadCrumb);
	}

	@Override
	protected void populateItem(ListItem<BreadCrumbElement> item) {
		WebMarkupContainer separator = new WebMarkupContainer("divider");
		separator.setVisible(item.getIndex() > 0);
		item.add(separator);
		
		Component breadCrumbLink = item.getModelObject().component("breadCrumbElement");
		item.add(breadCrumbLink);
		
		item.add(new EnclosureBehavior().component(breadCrumbLink));
	}
	
}
