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

package at.graz.meduni.bibbox.liferay.portlet.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * Provides the remote service interface for ApplicationStoreItem. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Robert Reihs
 * @see ApplicationStoreItemServiceUtil
 * @see at.graz.meduni.bibbox.liferay.portlet.service.base.ApplicationStoreItemServiceBaseImpl
 * @see at.graz.meduni.bibbox.liferay.portlet.service.impl.ApplicationStoreItemServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ApplicationStoreItemService extends BaseService,
	InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApplicationStoreItemServiceUtil} to access the application store item remote service. Add custom service methods to {@link at.graz.meduni.bibbox.liferay.portlet.service.impl.ApplicationStoreItemServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@JSONWebService(value = "/get-application-store-item")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getApplicationStoreItemAPI(long applicationstoreitemId);

	@JSONWebService(value = "/get-application-store-list")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getApplicationStoreListAPI();

	@JSONWebService(value = "/get-application-store-updated-list")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getApplicationStoreUpdatedListAPI();

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();
}