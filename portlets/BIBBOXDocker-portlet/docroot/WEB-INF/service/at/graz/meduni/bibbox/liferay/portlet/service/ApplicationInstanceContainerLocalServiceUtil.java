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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for ApplicationInstanceContainer. This utility wraps
 * {@link at.graz.meduni.bibbox.liferay.portlet.service.impl.ApplicationInstanceContainerLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Robert Reihs
 * @see ApplicationInstanceContainerLocalService
 * @see at.graz.meduni.bibbox.liferay.portlet.service.base.ApplicationInstanceContainerLocalServiceBaseImpl
 * @see at.graz.meduni.bibbox.liferay.portlet.service.impl.ApplicationInstanceContainerLocalServiceImpl
 * @generated
 */
@ProviderType
public class ApplicationInstanceContainerLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link at.graz.meduni.bibbox.liferay.portlet.service.impl.ApplicationInstanceContainerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the application instance container to the database. Also notifies the appropriate model listeners.
	*
	* @param applicationInstanceContainer the application instance container
	* @return the application instance container that was added
	*/
	public static at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstanceContainer addApplicationInstanceContainer(
		at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstanceContainer applicationInstanceContainer) {
		return getService()
				   .addApplicationInstanceContainer(applicationInstanceContainer);
	}

	/**
	* Creates a new application instance container with the primary key. Does not add the application instance container to the database.
	*
	* @param applicationInstanceContainerId the primary key for the new application instance container
	* @return the new application instance container
	*/
	public static at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstanceContainer createApplicationInstanceContainer(
		long applicationInstanceContainerId) {
		return getService()
				   .createApplicationInstanceContainer(applicationInstanceContainerId);
	}

	/**
	* Deletes the application instance container from the database. Also notifies the appropriate model listeners.
	*
	* @param applicationInstanceContainer the application instance container
	* @return the application instance container that was removed
	*/
	public static at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstanceContainer deleteApplicationInstanceContainer(
		at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstanceContainer applicationInstanceContainer) {
		return getService()
				   .deleteApplicationInstanceContainer(applicationInstanceContainer);
	}

	/**
	* Deletes the application instance container with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param applicationInstanceContainerId the primary key of the application instance container
	* @return the application instance container that was removed
	* @throws PortalException if a application instance container with the primary key could not be found
	*/
	public static at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstanceContainer deleteApplicationInstanceContainer(
		long applicationInstanceContainerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteApplicationInstanceContainer(applicationInstanceContainerId);
	}

	public static at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstanceContainer fetchApplicationInstanceContainer(
		long applicationInstanceContainerId) {
		return getService()
				   .fetchApplicationInstanceContainer(applicationInstanceContainerId);
	}

	/**
	* Returns the application instance container with the primary key.
	*
	* @param applicationInstanceContainerId the primary key of the application instance container
	* @return the application instance container
	* @throws PortalException if a application instance container with the primary key could not be found
	*/
	public static at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstanceContainer getApplicationInstanceContainer(
		long applicationInstanceContainerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getApplicationInstanceContainer(applicationInstanceContainerId);
	}

	/**
	* Updates the application instance container in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param applicationInstanceContainer the application instance container
	* @return the application instance container that was updated
	*/
	public static at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstanceContainer updateApplicationInstanceContainer(
		at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstanceContainer applicationInstanceContainer) {
		return getService()
				   .updateApplicationInstanceContainer(applicationInstanceContainer);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of application instance containers.
	*
	* @return the number of application instance containers
	*/
	public static int getApplicationInstanceContainersCount() {
		return getService().getApplicationInstanceContainersCount();
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link at.graz.meduni.bibbox.liferay.portlet.model.impl.ApplicationInstanceContainerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link at.graz.meduni.bibbox.liferay.portlet.model.impl.ApplicationInstanceContainerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static java.util.List<at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstanceContainer> getApplicationInstanceContainerForInstance(
		long applicationinstanceId) {
		return getService()
				   .getApplicationInstanceContainerForInstance(applicationinstanceId);
	}

	public static java.util.List<at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstanceContainer> getApplicationInstanceContainerNeedToRun(
		long applicationinstanceId) {
		return getService()
				   .getApplicationInstanceContainerNeedToRun(applicationinstanceId);
	}

	/**
	* Returns a range of all the application instance containers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link at.graz.meduni.bibbox.liferay.portlet.model.impl.ApplicationInstanceContainerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of application instance containers
	* @param end the upper bound of the range of application instance containers (not inclusive)
	* @return the range of application instance containers
	*/
	public static java.util.List<at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstanceContainer> getApplicationInstanceContainers(
		int start, int end) {
		return getService().getApplicationInstanceContainers(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static void clearService() {
		_service = null;
	}

	public static ApplicationInstanceContainerLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ApplicationInstanceContainerLocalService.class.getName());

			if (invokableLocalService instanceof ApplicationInstanceContainerLocalService) {
				_service = (ApplicationInstanceContainerLocalService)invokableLocalService;
			}
			else {
				_service = new ApplicationInstanceContainerLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ApplicationInstanceContainerLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static ApplicationInstanceContainerLocalService _service;
}