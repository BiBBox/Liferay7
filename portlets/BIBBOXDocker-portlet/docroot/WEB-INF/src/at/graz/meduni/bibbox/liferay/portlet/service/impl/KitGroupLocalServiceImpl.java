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

package at.graz.meduni.bibbox.liferay.portlet.service.impl;

import java.util.List;

import aQute.bnd.annotation.ProviderType;
import at.graz.meduni.bibbox.liferay.portlet.exception.NoSuchKitGroupException;
import at.graz.meduni.bibbox.liferay.portlet.model.KitGroup;
import at.graz.meduni.bibbox.liferay.portlet.service.base.KitGroupLocalServiceBaseImpl;

/**
 * The implementation of the kit group local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link at.graz.meduni.bibbox.liferay.portlet.service.KitGroupLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Robert Reihs
 * @see KitGroupLocalServiceBaseImpl
 * @see at.graz.meduni.bibbox.liferay.portlet.service.KitGroupLocalServiceUtil
 */
@ProviderType
public class KitGroupLocalServiceImpl extends KitGroupLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link at.graz.meduni.bibbox.liferay.portlet.service.KitGroupLocalServiceUtil} to access the kit group local service.
	 */
	public List<KitGroup> getKitGroups(long applicationStoreItemId) {
		return kitGroupPersistence.findByKitGroups(applicationStoreItemId);
	}
	
	public KitGroup getKitGroup(long applicationStoreItemId, long bibboxKitId) {
		try {
			return kitGroupPersistence.findByKitGroup(applicationStoreItemId, bibboxKitId);
		} catch (NoSuchKitGroupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}