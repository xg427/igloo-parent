package fr.openwide.core.showcase.web.application.util.template.styles;

import java.util.List;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.retzlaff.select2.resource.Select2CssResourceReference;

import com.google.common.collect.Lists;

import fr.openwide.core.wicket.more.lesscss.LessCssResourceReference;
import fr.openwide.core.wicket.more.markup.html.template.css.bootstrap2.jqueryui.JQueryUiCssResourceReference;

public class StylesLessCssResourceReference extends LessCssResourceReference {

	private static final long serialVersionUID = 4656765761895221782L;

	private static final StylesLessCssResourceReference INSTANCE = new StylesLessCssResourceReference();

	private StylesLessCssResourceReference() {
		super(StylesLessCssResourceReference.class, "styles.less");
	}
	
	@Override
	public Iterable<? extends HeaderItem> getDependencies() {
		List<HeaderItem> dependencies = Lists.newArrayListWithExpectedSize(2);
		dependencies.add(CssHeaderItem.forReference(JQueryUiCssResourceReference.get()));
		dependencies.add(CssHeaderItem.forReference(Select2CssResourceReference.get()));
		return dependencies;
	}
	
	public static StylesLessCssResourceReference get() {
		return INSTANCE;
	}
}