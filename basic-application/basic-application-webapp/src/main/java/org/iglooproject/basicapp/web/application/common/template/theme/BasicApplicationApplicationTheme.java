package org.iglooproject.basicapp.web.application.common.template.theme;

import java.util.List;
import java.util.function.Supplier;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.iglooproject.basicapp.web.application.common.template.MainTemplate;
import org.iglooproject.spring.property.SpringPropertyIds;
import org.iglooproject.wicket.bootstrap4.markup.html.template.js.bootstrap.collapse.BootstrapCollapseJavaScriptResourceReference;
import org.iglooproject.wicket.markup.html.basic.CoreLabel;
import org.iglooproject.wicket.more.condition.Condition;
import org.iglooproject.wicket.more.markup.html.template.js.jquery.plugins.scrolltotop.ScrollToTopBehavior;
import org.iglooproject.wicket.more.markup.html.template.model.NavigationMenuItem;
import org.iglooproject.wicket.more.model.ApplicationPropertyModel;

public enum BasicApplicationApplicationTheme {

	BASIC {
		@Override
		public String getMarkupVariation() {
			return "basic";
		}
		
		@Override
		public void renderHead(IHeaderResponse response) {
			response.render(CssHeaderItem.forReference(org.iglooproject.basicapp.web.application.common.template.resources.styles.application.basic.StylesScssResourceReference.get()));
			response.render(JavaScriptHeaderItem.forReference(BootstrapCollapseJavaScriptResourceReference.get()));
		}
		
		@Override
		public void specificContent(
				MainTemplate mainTemplate,
				Supplier<List<NavigationMenuItem>> mainNavSupplier,
				Supplier<Class<? extends WebPage>> firstMenuPageSupplier,
				Supplier<Class<? extends WebPage>> secondMenuPageSupplier
		) {
			mainTemplate.add(
					new org.iglooproject.basicapp.web.application.common.template.theme.basic.NavbarPanel(
							"navbar",
							mainNavSupplier,
							firstMenuPageSupplier,
							secondMenuPageSupplier
					),
					
					new CoreLabel("version", new StringResourceModel("common.version", ApplicationPropertyModel.of(SpringPropertyIds.VERSION)))
							.add(new AttributeModifier("title", new StringResourceModel("common.version.full")
									.setParameters(ApplicationPropertyModel.of(SpringPropertyIds.VERSION), ApplicationPropertyModel.of(SpringPropertyIds.IGLOO_VERSION))
							)),
					
					new WebMarkupContainer("scrollToTop").add(new ScrollToTopBehavior())
			);
		}
		
		@Override
		public BasicApplicationApplicationTheme next() {
			return BasicApplicationApplicationTheme.ADVANCED;
		}
	},
	ADVANCED {
		@Override
		public String getMarkupVariation() {
			return "advanced";
		}
		
		@Override
		public void renderHead(IHeaderResponse response) {
			response.render(CssHeaderItem.forReference(org.iglooproject.basicapp.web.application.common.template.resources.styles.application.advanced.StylesScssResourceReference.get()));
			response.render(JavaScriptHeaderItem.forReference(BootstrapCollapseJavaScriptResourceReference.get()));
		}
		
		@Override
		public void specificContent(
				MainTemplate mainTemplate,
				Supplier<List<NavigationMenuItem>> mainNavSupplier,
				Supplier<Class<? extends WebPage>> firstMenuPageSupplier,
				Supplier<Class<? extends WebPage>> secondMenuPageSupplier
		) {
			mainTemplate.add(
					new org.iglooproject.basicapp.web.application.common.template.theme.advanced.NavbarPanel(
							"navbar",
							mainNavSupplier,
							firstMenuPageSupplier,
							secondMenuPageSupplier
					),
					new org.iglooproject.basicapp.web.application.common.template.theme.advanced.SidebarPanel(
							"sidenav",
							mainNavSupplier,
							firstMenuPageSupplier,
							secondMenuPageSupplier
					)
			);
			
//			mainTemplate.add(
//					new PerfectScrollbarBehavior("#sidebar-body-scrollable")
//			);
		}

		@Override
		public BasicApplicationApplicationTheme next() {
			return BasicApplicationApplicationTheme.BASIC;
		}
	};

	public abstract String getMarkupVariation();

	public abstract void renderHead(IHeaderResponse response);

	public abstract void specificContent(
			MainTemplate mainTemplate,
			Supplier<List<NavigationMenuItem>> mainNavSupplier,
			Supplier<Class<? extends WebPage>> firstMenuPageSupplier,
			Supplier<Class<? extends WebPage>> secondMenuPageSupplier
	);

	public abstract BasicApplicationApplicationTheme next();

	public Condition isEqual(BasicApplicationApplicationTheme applicationTheme) {
		if (applicationTheme == null) {
			return Condition.alwaysFalse();
		}
		return Condition.isEqual(Model.of(this), Model.of(applicationTheme));
	}

}
