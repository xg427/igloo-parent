package org.iglooproject.basicapp.core.business.user.model.embeddable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.OrderColumn;

import org.bindgen.Bindable;
import org.hibernate.annotations.Type;
import org.iglooproject.basicapp.core.config.hibernate.TypeDefinitions;
import org.iglooproject.commons.util.CloneUtils;
import org.iglooproject.commons.util.collections.CollectionUtils;

import com.google.common.collect.Lists;

@Embeddable
@Bindable
public class UserPasswordInformation implements Serializable {

	private static final long serialVersionUID = -5388035775227696038L;

	@Column
	private LocalDateTime lastUpdateDate;

	@ElementCollection
	@OrderColumn
	@Type(type = TypeDefinitions.STRING_CLOB)
	private List<String> history = Lists.newArrayList();

	public LocalDateTime getLastUpdateDate() {
		return CloneUtils.clone(lastUpdateDate);
	}

	public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
		this.lastUpdateDate = CloneUtils.clone(lastUpdateDate);
	}

	public List<String> getHistory() {
		return history;
	}

	public void setHistory(List<String> history) {
		CollectionUtils.replaceAll(this.history, history);
	}

}
