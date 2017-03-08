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

package at.graz.meduni.bibbox.liferay.portlet.service.base;

import at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstanceStatus;
import at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceStatusService;
import at.graz.meduni.bibbox.liferay.portlet.service.persistence.ApplicationInstanceContainerPersistence;
import at.graz.meduni.bibbox.liferay.portlet.service.persistence.ApplicationInstancePersistence;
import at.graz.meduni.bibbox.liferay.portlet.service.persistence.ApplicationInstancePortPersistence;
import at.graz.meduni.bibbox.liferay.portlet.service.persistence.ApplicationInstanceStatusPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the application instance status remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link at.graz.meduni.bibbox.liferay.portlet.service.impl.ApplicationInstanceStatusServiceImpl}.
 * </p>
 *
 * @author Robert Reihs
 * @see at.graz.meduni.bibbox.liferay.portlet.service.impl.ApplicationInstanceStatusServiceImpl
 * @see at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceStatusServiceUtil
 * @generated
 */
public abstract class ApplicationInstanceStatusServiceBaseImpl
	extends BaseServiceImpl implements ApplicationInstanceStatusService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceStatusServiceUtil} to access the application instance status remote service.
	 */

	/**
	 * Returns the application instance local service.
	 *
	 * @return the application instance local service
	 */
	public at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceLocalService getApplicationInstanceLocalService() {
		return applicationInstanceLocalService;
	}

	/**
	 * Sets the application instance local service.
	 *
	 * @param applicationInstanceLocalService the application instance local service
	 */
	public void setApplicationInstanceLocalService(
		at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceLocalService applicationInstanceLocalService) {
		this.applicationInstanceLocalService = applicationInstanceLocalService;
	}

	/**
	 * Returns the application instance remote service.
	 *
	 * @return the application instance remote service
	 */
	public at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceService getApplicationInstanceService() {
		return applicationInstanceService;
	}

	/**
	 * Sets the application instance remote service.
	 *
	 * @param applicationInstanceService the application instance remote service
	 */
	public void setApplicationInstanceService(
		at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceService applicationInstanceService) {
		this.applicationInstanceService = applicationInstanceService;
	}

	/**
	 * Returns the application instance persistence.
	 *
	 * @return the application instance persistence
	 */
	public ApplicationInstancePersistence getApplicationInstancePersistence() {
		return applicationInstancePersistence;
	}

	/**
	 * Sets the application instance persistence.
	 *
	 * @param applicationInstancePersistence the application instance persistence
	 */
	public void setApplicationInstancePersistence(
		ApplicationInstancePersistence applicationInstancePersistence) {
		this.applicationInstancePersistence = applicationInstancePersistence;
	}

	/**
	 * Returns the application instance container local service.
	 *
	 * @return the application instance container local service
	 */
	public at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceContainerLocalService getApplicationInstanceContainerLocalService() {
		return applicationInstanceContainerLocalService;
	}

	/**
	 * Sets the application instance container local service.
	 *
	 * @param applicationInstanceContainerLocalService the application instance container local service
	 */
	public void setApplicationInstanceContainerLocalService(
		at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceContainerLocalService applicationInstanceContainerLocalService) {
		this.applicationInstanceContainerLocalService = applicationInstanceContainerLocalService;
	}

	/**
	 * Returns the application instance container remote service.
	 *
	 * @return the application instance container remote service
	 */
	public at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceContainerService getApplicationInstanceContainerService() {
		return applicationInstanceContainerService;
	}

	/**
	 * Sets the application instance container remote service.
	 *
	 * @param applicationInstanceContainerService the application instance container remote service
	 */
	public void setApplicationInstanceContainerService(
		at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceContainerService applicationInstanceContainerService) {
		this.applicationInstanceContainerService = applicationInstanceContainerService;
	}

	/**
	 * Returns the application instance container persistence.
	 *
	 * @return the application instance container persistence
	 */
	public ApplicationInstanceContainerPersistence getApplicationInstanceContainerPersistence() {
		return applicationInstanceContainerPersistence;
	}

	/**
	 * Sets the application instance container persistence.
	 *
	 * @param applicationInstanceContainerPersistence the application instance container persistence
	 */
	public void setApplicationInstanceContainerPersistence(
		ApplicationInstanceContainerPersistence applicationInstanceContainerPersistence) {
		this.applicationInstanceContainerPersistence = applicationInstanceContainerPersistence;
	}

	/**
	 * Returns the application instance port local service.
	 *
	 * @return the application instance port local service
	 */
	public at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstancePortLocalService getApplicationInstancePortLocalService() {
		return applicationInstancePortLocalService;
	}

	/**
	 * Sets the application instance port local service.
	 *
	 * @param applicationInstancePortLocalService the application instance port local service
	 */
	public void setApplicationInstancePortLocalService(
		at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstancePortLocalService applicationInstancePortLocalService) {
		this.applicationInstancePortLocalService = applicationInstancePortLocalService;
	}

	/**
	 * Returns the application instance port remote service.
	 *
	 * @return the application instance port remote service
	 */
	public at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstancePortService getApplicationInstancePortService() {
		return applicationInstancePortService;
	}

	/**
	 * Sets the application instance port remote service.
	 *
	 * @param applicationInstancePortService the application instance port remote service
	 */
	public void setApplicationInstancePortService(
		at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstancePortService applicationInstancePortService) {
		this.applicationInstancePortService = applicationInstancePortService;
	}

	/**
	 * Returns the application instance port persistence.
	 *
	 * @return the application instance port persistence
	 */
	public ApplicationInstancePortPersistence getApplicationInstancePortPersistence() {
		return applicationInstancePortPersistence;
	}

	/**
	 * Sets the application instance port persistence.
	 *
	 * @param applicationInstancePortPersistence the application instance port persistence
	 */
	public void setApplicationInstancePortPersistence(
		ApplicationInstancePortPersistence applicationInstancePortPersistence) {
		this.applicationInstancePortPersistence = applicationInstancePortPersistence;
	}

	/**
	 * Returns the application instance status local service.
	 *
	 * @return the application instance status local service
	 */
	public at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceStatusLocalService getApplicationInstanceStatusLocalService() {
		return applicationInstanceStatusLocalService;
	}

	/**
	 * Sets the application instance status local service.
	 *
	 * @param applicationInstanceStatusLocalService the application instance status local service
	 */
	public void setApplicationInstanceStatusLocalService(
		at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceStatusLocalService applicationInstanceStatusLocalService) {
		this.applicationInstanceStatusLocalService = applicationInstanceStatusLocalService;
	}

	/**
	 * Returns the application instance status remote service.
	 *
	 * @return the application instance status remote service
	 */
	public ApplicationInstanceStatusService getApplicationInstanceStatusService() {
		return applicationInstanceStatusService;
	}

	/**
	 * Sets the application instance status remote service.
	 *
	 * @param applicationInstanceStatusService the application instance status remote service
	 */
	public void setApplicationInstanceStatusService(
		ApplicationInstanceStatusService applicationInstanceStatusService) {
		this.applicationInstanceStatusService = applicationInstanceStatusService;
	}

	/**
	 * Returns the application instance status persistence.
	 *
	 * @return the application instance status persistence
	 */
	public ApplicationInstanceStatusPersistence getApplicationInstanceStatusPersistence() {
		return applicationInstanceStatusPersistence;
	}

	/**
	 * Sets the application instance status persistence.
	 *
	 * @param applicationInstanceStatusPersistence the application instance status persistence
	 */
	public void setApplicationInstanceStatusPersistence(
		ApplicationInstanceStatusPersistence applicationInstanceStatusPersistence) {
		this.applicationInstanceStatusPersistence = applicationInstanceStatusPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService getClassNameService() {
		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.kernel.service.ClassNameService classNameService) {
		this.classNameService = classNameService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ApplicationInstanceStatusService.class.getName();
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return ApplicationInstanceStatus.class;
	}

	protected String getModelClassName() {
		return ApplicationInstanceStatus.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = applicationInstanceStatusPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceLocalService.class)
	protected at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceLocalService applicationInstanceLocalService;
	@BeanReference(type = at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceService.class)
	protected at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceService applicationInstanceService;
	@BeanReference(type = ApplicationInstancePersistence.class)
	protected ApplicationInstancePersistence applicationInstancePersistence;
	@BeanReference(type = at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceContainerLocalService.class)
	protected at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceContainerLocalService applicationInstanceContainerLocalService;
	@BeanReference(type = at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceContainerService.class)
	protected at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceContainerService applicationInstanceContainerService;
	@BeanReference(type = ApplicationInstanceContainerPersistence.class)
	protected ApplicationInstanceContainerPersistence applicationInstanceContainerPersistence;
	@BeanReference(type = at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstancePortLocalService.class)
	protected at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstancePortLocalService applicationInstancePortLocalService;
	@BeanReference(type = at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstancePortService.class)
	protected at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstancePortService applicationInstancePortService;
	@BeanReference(type = ApplicationInstancePortPersistence.class)
	protected ApplicationInstancePortPersistence applicationInstancePortPersistence;
	@BeanReference(type = at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceStatusLocalService.class)
	protected at.graz.meduni.bibbox.liferay.portlet.service.ApplicationInstanceStatusLocalService applicationInstanceStatusLocalService;
	@BeanReference(type = ApplicationInstanceStatusService.class)
	protected ApplicationInstanceStatusService applicationInstanceStatusService;
	@BeanReference(type = ApplicationInstanceStatusPersistence.class)
	protected ApplicationInstanceStatusPersistence applicationInstanceStatusPersistence;
	@BeanReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.ClassNameService.class)
	protected com.liferay.portal.kernel.service.ClassNameService classNameService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.UserService.class)
	protected com.liferay.portal.kernel.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private ClassLoader _classLoader;
	private ApplicationInstanceStatusServiceClpInvoker _clpInvoker = new ApplicationInstanceStatusServiceClpInvoker();
}