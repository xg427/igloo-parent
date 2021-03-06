/*
 * Copyright (C) 2008-2011 Open Wide
 * Contact: contact@openwide.fr
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.iglooproject.jpa.more.business.audit.model.util;

import javax.persistence.MappedSuperclass;

import org.iglooproject.jpa.more.business.referencedata.model.GenericBasicReferenceData;

/**
 * <p>Les actions référencées dans le journal.</p>
 * 
 * <p>Il est recommandé de faire hériter un enum de cette interface mais on peut aussi envisager
 * de stocker ces éléments dans la base de données.</p>
 * 
 * <p><strong>NOTE :</strong> il vaut mieux utiliser un enum pour modéliser les actions, sauf raison particulière
 * (les anciens projets utilisent toujours cette classe pour ne pas avoir à tout refactorer).</p>
 */
@MappedSuperclass
public abstract class AbstractAuditAction extends GenericBasicReferenceData<AbstractAuditAction> {
	private static final long serialVersionUID = 3770925165610240322L;
	
	protected AbstractAuditAction() {
		super();
	}
	
	public AbstractAuditAction(String label, Integer position) {
		super(label, position);
	}
}
