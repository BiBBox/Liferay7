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

import aQute.bnd.annotation.ProviderType;

import at.graz.meduni.bibbox.liferay.portlet.model.DockerContainer;
import at.graz.meduni.bibbox.liferay.portlet.service.DockerContainerLocalService;
import at.graz.meduni.bibbox.liferay.portlet.service.persistence.ApplicationStoreItemPersistence;
import at.graz.meduni.bibbox.liferay.portlet.service.persistence.ApplicationTagPersistence;
import at.graz.meduni.bibbox.liferay.portlet.service.persistence.BibboxKitPersistence;
import at.graz.meduni.bibbox.liferay.portlet.service.persistence.DockerContainerPersistence;
import at.graz.meduni.bibbox.liferay.portlet.service.persistence.KitGroupPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the docker container local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link at.graz.meduni.bibbox.liferay.portlet.service.impl.DockerContainerLocalServiceImpl}.
 * </p>
 *
 * @author Robert Reihs
 * @see at.graz.meduni.bibbox.liferay.portlet.service.impl.DockerContainerLocalServiceImpl
 * @see at.graz.meduni.bibbox.liferay.portlet.service.DockerContainerLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class DockerContainerLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements DockerContainerLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link at.graz.meduni.bibbox.liferay.portlet.service.DockerContainerLocalServiceUtil} to access the docker container local service.
	 */

	/**
	 * Adds the docker container to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dockerContainer the docker container
	 * @return the docker container that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DockerContainer addDockerContainer(DockerContainer dockerContainer) {
		dockerContainer.setNew(true);

		return dockerContainerPersistence.update(dockerContainer);
	}

	/**
	 * Creates a new docker container with the primary key. Does not add the docker container to the database.
	 *
	 * @param dockerContainerId the primary key for the new docker container
	 * @return the new docker container
	 */
	@Override
	public DockerContainer createDockerContainer(long dockerContainerId) {
		return dockerContainerPersistence.create(dockerContainerId);
	}

	/**
	 * Deletes the docker container with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dockerContainerId the primary key of the docker container
	 * @return the docker container that was removed
	 * @throws PortalException if a docker container with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DockerContainer deleteDockerContainer(long dockerContainerId)
		throws PortalException {
		return dockerContainerPersistence.remove(dockerContainerId);
	}

	/**
	 * Deletes the docker container from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dockerContainer the docker container
	 * @return the docker container that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DockerContainer deleteDockerContainer(
		DockerContainer dockerContainer) {
		return dockerContainerPersistence.remove(dockerContainer);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(DockerContainer.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return dockerContainerPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link at.graz.meduni.bibbox.liferay.portlet.model.impl.DockerContainerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return dockerContainerPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link at.graz.meduni.bibbox.liferay.portlet.model.impl.DockerContainerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return dockerContainerPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return dockerContainerPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return dockerContainerPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public DockerContainer fetchDockerContainer(long dockerContainerId) {
		return dockerContainerPersistence.fetchByPrimaryKey(dockerContainerId);
	}

	/**
	 * Returns the docker container with the primary key.
	 *
	 * @param dockerContainerId the primary key of the docker container
	 * @return the docker container
	 * @throws PortalException if a docker container with the primary key could not be found
	 */
	@Override
	public DockerContainer getDockerContainer(long dockerContainerId)
		throws PortalException {
		return dockerContainerPersistence.findByPrimaryKey(dockerContainerId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(dockerContainerLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DockerContainer.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("dockerContainerId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(dockerContainerLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(DockerContainer.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"dockerContainerId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(dockerContainerLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DockerContainer.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("dockerContainerId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return dockerContainerLocalService.deleteDockerContainer((DockerContainer)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return dockerContainerPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the docker containers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link at.graz.meduni.bibbox.liferay.portlet.model.impl.DockerContainerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of docker containers
	 * @param end the upper bound of the range of docker containers (not inclusive)
	 * @return the range of docker containers
	 */
	@Override
	public List<DockerContainer> getDockerContainers(int start, int end) {
		return dockerContainerPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of docker containers.
	 *
	 * @return the number of docker containers
	 */
	@Override
	public int getDockerContainersCount() {
		return dockerContainerPersistence.countAll();
	}

	/**
	 * Updates the docker container in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dockerContainer the docker container
	 * @return the docker container that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DockerContainer updateDockerContainer(
		DockerContainer dockerContainer) {
		return dockerContainerPersistence.update(dockerContainer);
	}

	/**
	 * Returns the application store item local service.
	 *
	 * @return the application store item local service
	 */
	public at.graz.meduni.bibbox.liferay.portlet.service.ApplicationStoreItemLocalService getApplicationStoreItemLocalService() {
		return applicationStoreItemLocalService;
	}

	/**
	 * Sets the application store item local service.
	 *
	 * @param applicationStoreItemLocalService the application store item local service
	 */
	public void setApplicationStoreItemLocalService(
		at.graz.meduni.bibbox.liferay.portlet.service.ApplicationStoreItemLocalService applicationStoreItemLocalService) {
		this.applicationStoreItemLocalService = applicationStoreItemLocalService;
	}

	/**
	 * Returns the application store item persistence.
	 *
	 * @return the application store item persistence
	 */
	public ApplicationStoreItemPersistence getApplicationStoreItemPersistence() {
		return applicationStoreItemPersistence;
	}

	/**
	 * Sets the application store item persistence.
	 *
	 * @param applicationStoreItemPersistence the application store item persistence
	 */
	public void setApplicationStoreItemPersistence(
		ApplicationStoreItemPersistence applicationStoreItemPersistence) {
		this.applicationStoreItemPersistence = applicationStoreItemPersistence;
	}

	/**
	 * Returns the application tag local service.
	 *
	 * @return the application tag local service
	 */
	public at.graz.meduni.bibbox.liferay.portlet.service.ApplicationTagLocalService getApplicationTagLocalService() {
		return applicationTagLocalService;
	}

	/**
	 * Sets the application tag local service.
	 *
	 * @param applicationTagLocalService the application tag local service
	 */
	public void setApplicationTagLocalService(
		at.graz.meduni.bibbox.liferay.portlet.service.ApplicationTagLocalService applicationTagLocalService) {
		this.applicationTagLocalService = applicationTagLocalService;
	}

	/**
	 * Returns the application tag persistence.
	 *
	 * @return the application tag persistence
	 */
	public ApplicationTagPersistence getApplicationTagPersistence() {
		return applicationTagPersistence;
	}

	/**
	 * Sets the application tag persistence.
	 *
	 * @param applicationTagPersistence the application tag persistence
	 */
	public void setApplicationTagPersistence(
		ApplicationTagPersistence applicationTagPersistence) {
		this.applicationTagPersistence = applicationTagPersistence;
	}

	/**
	 * Returns the bibbox kit local service.
	 *
	 * @return the bibbox kit local service
	 */
	public at.graz.meduni.bibbox.liferay.portlet.service.BibboxKitLocalService getBibboxKitLocalService() {
		return bibboxKitLocalService;
	}

	/**
	 * Sets the bibbox kit local service.
	 *
	 * @param bibboxKitLocalService the bibbox kit local service
	 */
	public void setBibboxKitLocalService(
		at.graz.meduni.bibbox.liferay.portlet.service.BibboxKitLocalService bibboxKitLocalService) {
		this.bibboxKitLocalService = bibboxKitLocalService;
	}

	/**
	 * Returns the bibbox kit persistence.
	 *
	 * @return the bibbox kit persistence
	 */
	public BibboxKitPersistence getBibboxKitPersistence() {
		return bibboxKitPersistence;
	}

	/**
	 * Sets the bibbox kit persistence.
	 *
	 * @param bibboxKitPersistence the bibbox kit persistence
	 */
	public void setBibboxKitPersistence(
		BibboxKitPersistence bibboxKitPersistence) {
		this.bibboxKitPersistence = bibboxKitPersistence;
	}

	/**
	 * Returns the docker container local service.
	 *
	 * @return the docker container local service
	 */
	public DockerContainerLocalService getDockerContainerLocalService() {
		return dockerContainerLocalService;
	}

	/**
	 * Sets the docker container local service.
	 *
	 * @param dockerContainerLocalService the docker container local service
	 */
	public void setDockerContainerLocalService(
		DockerContainerLocalService dockerContainerLocalService) {
		this.dockerContainerLocalService = dockerContainerLocalService;
	}

	/**
	 * Returns the docker container persistence.
	 *
	 * @return the docker container persistence
	 */
	public DockerContainerPersistence getDockerContainerPersistence() {
		return dockerContainerPersistence;
	}

	/**
	 * Sets the docker container persistence.
	 *
	 * @param dockerContainerPersistence the docker container persistence
	 */
	public void setDockerContainerPersistence(
		DockerContainerPersistence dockerContainerPersistence) {
		this.dockerContainerPersistence = dockerContainerPersistence;
	}

	/**
	 * Returns the kit group local service.
	 *
	 * @return the kit group local service
	 */
	public at.graz.meduni.bibbox.liferay.portlet.service.KitGroupLocalService getKitGroupLocalService() {
		return kitGroupLocalService;
	}

	/**
	 * Sets the kit group local service.
	 *
	 * @param kitGroupLocalService the kit group local service
	 */
	public void setKitGroupLocalService(
		at.graz.meduni.bibbox.liferay.portlet.service.KitGroupLocalService kitGroupLocalService) {
		this.kitGroupLocalService = kitGroupLocalService;
	}

	/**
	 * Returns the kit group persistence.
	 *
	 * @return the kit group persistence
	 */
	public KitGroupPersistence getKitGroupPersistence() {
		return kitGroupPersistence;
	}

	/**
	 * Sets the kit group persistence.
	 *
	 * @param kitGroupPersistence the kit group persistence
	 */
	public void setKitGroupPersistence(KitGroupPersistence kitGroupPersistence) {
		this.kitGroupPersistence = kitGroupPersistence;
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

		PersistedModelLocalServiceRegistryUtil.register("at.graz.meduni.bibbox.liferay.portlet.model.DockerContainer",
			dockerContainerLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"at.graz.meduni.bibbox.liferay.portlet.model.DockerContainer");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DockerContainerLocalService.class.getName();
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
		return DockerContainer.class;
	}

	protected String getModelClassName() {
		return DockerContainer.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = dockerContainerPersistence.getDataSource();

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

	@BeanReference(type = at.graz.meduni.bibbox.liferay.portlet.service.ApplicationStoreItemLocalService.class)
	protected at.graz.meduni.bibbox.liferay.portlet.service.ApplicationStoreItemLocalService applicationStoreItemLocalService;
	@BeanReference(type = ApplicationStoreItemPersistence.class)
	protected ApplicationStoreItemPersistence applicationStoreItemPersistence;
	@BeanReference(type = at.graz.meduni.bibbox.liferay.portlet.service.ApplicationTagLocalService.class)
	protected at.graz.meduni.bibbox.liferay.portlet.service.ApplicationTagLocalService applicationTagLocalService;
	@BeanReference(type = ApplicationTagPersistence.class)
	protected ApplicationTagPersistence applicationTagPersistence;
	@BeanReference(type = at.graz.meduni.bibbox.liferay.portlet.service.BibboxKitLocalService.class)
	protected at.graz.meduni.bibbox.liferay.portlet.service.BibboxKitLocalService bibboxKitLocalService;
	@BeanReference(type = BibboxKitPersistence.class)
	protected BibboxKitPersistence bibboxKitPersistence;
	@BeanReference(type = DockerContainerLocalService.class)
	protected DockerContainerLocalService dockerContainerLocalService;
	@BeanReference(type = DockerContainerPersistence.class)
	protected DockerContainerPersistence dockerContainerPersistence;
	@BeanReference(type = at.graz.meduni.bibbox.liferay.portlet.service.KitGroupLocalService.class)
	protected at.graz.meduni.bibbox.liferay.portlet.service.KitGroupLocalService kitGroupLocalService;
	@BeanReference(type = KitGroupPersistence.class)
	protected KitGroupPersistence kitGroupPersistence;
	@BeanReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private ClassLoader _classLoader;
	private DockerContainerLocalServiceClpInvoker _clpInvoker = new DockerContainerLocalServiceClpInvoker();
}