/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package at.graz.meduni.bibbox.liferay.portlet.model.impl;

import aQute.bnd.annotation.ProviderType;

import at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstanceStatus;
import at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceStatusLocalServiceUtil;

/**
 * The extended model base implementation for the ApplicationInstanceStatus service. Represents a row in the &quot;bibboxdocker_ApplicationInstanceStatus&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ApplicationInstanceStatusImpl}.
 * </p>
 *
 * @author Robert Reihs
 * @see ApplicationInstanceStatusImpl
 * @see ApplicationInstanceStatus
 * @generated
 */
@ProviderType
public abstract class ApplicationInstanceStatusBaseImpl
	extends ApplicationInstanceStatusModelImpl
	implements ApplicationInstanceStatus {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a application instance status model instance should use the {@link ApplicationInstanceStatus} interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			ApplicationInstanceStatusLocalServiceUtil.addApplicationInstanceStatus(this);
		}
		else {
			ApplicationInstanceStatusLocalServiceUtil.updateApplicationInstanceStatus(this);
		}
	}
}