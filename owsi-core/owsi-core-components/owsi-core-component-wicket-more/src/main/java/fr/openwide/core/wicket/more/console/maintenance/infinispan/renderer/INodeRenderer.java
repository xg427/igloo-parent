package fr.openwide.core.wicket.more.console.maintenance.infinispan.renderer;

import java.util.Locale;

import fr.openwide.core.infinispan.model.INode;
import fr.openwide.core.wicket.more.markup.html.bootstrap.label.model.BootstrapColor;
import fr.openwide.core.wicket.more.markup.html.bootstrap.label.renderer.BootstrapRenderer;
import fr.openwide.core.wicket.more.markup.html.bootstrap.label.renderer.BootstrapRendererInformation;

public class INodeRenderer {

	private static final INodeAnonymousRenderer ANONYMOUS = new INodeAnonymousRenderer();

	public static INodeAnonymousRenderer anonymous() {
		return ANONYMOUS;
	}

	private static class INodeAnonymousRenderer extends BootstrapRenderer<INode> {
		private static final long serialVersionUID = 1L;

		@Override
		protected BootstrapRendererInformation doRender(INode value, Locale locale) {
			if (!value.isAnonymous()) {
				return BootstrapRendererInformation.builder().build();
			}
			return BootstrapRendererInformation.builder()
					.icon("fa fa-exclamation")
					.color(BootstrapColor.DANGER)
					.label(getString("business.infinispan.node.anonymous", locale))
					.build();
		}
	}

}

