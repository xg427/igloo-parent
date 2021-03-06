package org.iglooproject.wicket.more.markup.html.form;

import java.util.Collection;

import org.apache.wicket.model.IModel;
import org.iglooproject.jpa.more.business.generic.model.search.EnabledFilter;
import org.iglooproject.jpa.more.business.referencedata.model.GenericBasicReferenceData;
import org.iglooproject.wicket.more.markup.html.model.GenericReferenceDataModel;
import org.iglooproject.wicket.more.markup.html.select2.GenericSelect2DropDownSingleChoice;
import org.iglooproject.wicket.more.rendering.GenericBasicReferenceDataRenderer;

public class GenericBasicReferenceDataDropDownSingleChoice<T extends GenericBasicReferenceData<? super T>> extends GenericSelect2DropDownSingleChoice<T> {

	private static final long serialVersionUID = 899355920433466940L;

	public GenericBasicReferenceDataDropDownSingleChoice(String id, IModel<T> model, Class<T> clazz) {
		this(
				id,
				model,
				new GenericReferenceDataModel<T>(clazz, EnabledFilter.ENABLED_ONLY)
		);
	}

	public GenericBasicReferenceDataDropDownSingleChoice(String id, IModel<T> model, IModel<? extends Collection<? extends T>> choicesModel) {
		super(
				id,
				model,
				choicesModel,
				GenericEntityRendererToChoiceRenderer.of(GenericBasicReferenceDataRenderer.get())
		);
		setNullValid(true);
	}

}
