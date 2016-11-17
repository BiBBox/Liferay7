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

import at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstancePort;
import at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstancePortModel;
import at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstancePortSoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the ApplicationInstancePort service. Represents a row in the &quot;bibboxdocker_ApplicationInstancePort&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ApplicationInstancePortModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ApplicationInstancePortImpl}.
 * </p>
 *
 * @author Robert Reihs
 * @see ApplicationInstancePortImpl
 * @see ApplicationInstancePort
 * @see ApplicationInstancePortModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class ApplicationInstancePortModelImpl extends BaseModelImpl<ApplicationInstancePort>
	implements ApplicationInstancePortModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a application instance port model instance should use the {@link ApplicationInstancePort} interface instead.
	 */
	public static final String TABLE_NAME = "bibboxdocker_ApplicationInstancePort";
	public static final Object[][] TABLE_COLUMNS = {
			{ "applicationInstancePortId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "port", Types.BIGINT },
			{ "primary_", Types.BOOLEAN },
			{ "applicationInstanceId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("applicationInstancePortId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("port", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("primary_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("applicationInstanceId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table bibboxdocker_ApplicationInstancePort (applicationInstancePortId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,port LONG,primary_ BOOLEAN,applicationInstanceId LONG)";
	public static final String TABLE_SQL_DROP = "drop table bibboxdocker_ApplicationInstancePort";
	public static final String ORDER_BY_JPQL = " ORDER BY applicationInstancePort.port ASC";
	public static final String ORDER_BY_SQL = " ORDER BY bibboxdocker_ApplicationInstancePort.port ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstancePort"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstancePort"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstancePort"),
			true);
	public static final long APPLICATIONINSTANCEID_COLUMN_BITMASK = 1L;
	public static final long PRIMARY_COLUMN_BITMASK = 2L;
	public static final long PORT_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static ApplicationInstancePort toModel(
		ApplicationInstancePortSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ApplicationInstancePort model = new ApplicationInstancePortImpl();

		model.setApplicationInstancePortId(soapModel.getApplicationInstancePortId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setPort(soapModel.getPort());
		model.setPrimary(soapModel.getPrimary());
		model.setApplicationInstanceId(soapModel.getApplicationInstanceId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<ApplicationInstancePort> toModels(
		ApplicationInstancePortSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<ApplicationInstancePort> models = new ArrayList<ApplicationInstancePort>(soapModels.length);

		for (ApplicationInstancePortSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstancePort"));

	public ApplicationInstancePortModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _applicationInstancePortId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setApplicationInstancePortId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _applicationInstancePortId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ApplicationInstancePort.class;
	}

	@Override
	public String getModelClassName() {
		return ApplicationInstancePort.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("applicationInstancePortId",
			getApplicationInstancePortId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("port", getPort());
		attributes.put("primary", getPrimary());
		attributes.put("applicationInstanceId", getApplicationInstanceId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long applicationInstancePortId = (Long)attributes.get(
				"applicationInstancePortId");

		if (applicationInstancePortId != null) {
			setApplicationInstancePortId(applicationInstancePortId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long port = (Long)attributes.get("port");

		if (port != null) {
			setPort(port);
		}

		Boolean primary = (Boolean)attributes.get("primary");

		if (primary != null) {
			setPrimary(primary);
		}

		Long applicationInstanceId = (Long)attributes.get(
				"applicationInstanceId");

		if (applicationInstanceId != null) {
			setApplicationInstanceId(applicationInstanceId);
		}
	}

	@JSON
	@Override
	public long getApplicationInstancePortId() {
		return _applicationInstancePortId;
	}

	@Override
	public void setApplicationInstancePortId(long applicationInstancePortId) {
		_applicationInstancePortId = applicationInstancePortId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getPort() {
		return _port;
	}

	@Override
	public void setPort(long port) {
		_columnBitmask = -1L;

		_port = port;
	}

	@JSON
	@Override
	public boolean getPrimary() {
		return _primary;
	}

	@Override
	public boolean isPrimary() {
		return _primary;
	}

	@Override
	public void setPrimary(boolean primary) {
		_columnBitmask |= PRIMARY_COLUMN_BITMASK;

		if (!_setOriginalPrimary) {
			_setOriginalPrimary = true;

			_originalPrimary = _primary;
		}

		_primary = primary;
	}

	public boolean getOriginalPrimary() {
		return _originalPrimary;
	}

	@JSON
	@Override
	public long getApplicationInstanceId() {
		return _applicationInstanceId;
	}

	@Override
	public void setApplicationInstanceId(long applicationInstanceId) {
		_columnBitmask |= APPLICATIONINSTANCEID_COLUMN_BITMASK;

		if (!_setOriginalApplicationInstanceId) {
			_setOriginalApplicationInstanceId = true;

			_originalApplicationInstanceId = _applicationInstanceId;
		}

		_applicationInstanceId = applicationInstanceId;
	}

	public long getOriginalApplicationInstanceId() {
		return _originalApplicationInstanceId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			ApplicationInstancePort.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ApplicationInstancePort toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ApplicationInstancePort)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ApplicationInstancePortImpl applicationInstancePortImpl = new ApplicationInstancePortImpl();

		applicationInstancePortImpl.setApplicationInstancePortId(getApplicationInstancePortId());
		applicationInstancePortImpl.setGroupId(getGroupId());
		applicationInstancePortImpl.setCompanyId(getCompanyId());
		applicationInstancePortImpl.setUserId(getUserId());
		applicationInstancePortImpl.setUserName(getUserName());
		applicationInstancePortImpl.setCreateDate(getCreateDate());
		applicationInstancePortImpl.setModifiedDate(getModifiedDate());
		applicationInstancePortImpl.setPort(getPort());
		applicationInstancePortImpl.setPrimary(getPrimary());
		applicationInstancePortImpl.setApplicationInstanceId(getApplicationInstanceId());

		applicationInstancePortImpl.resetOriginalValues();

		return applicationInstancePortImpl;
	}

	@Override
	public int compareTo(ApplicationInstancePort applicationInstancePort) {
		int value = 0;

		if (getPort() < applicationInstancePort.getPort()) {
			value = -1;
		}
		else if (getPort() > applicationInstancePort.getPort()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ApplicationInstancePort)) {
			return false;
		}

		ApplicationInstancePort applicationInstancePort = (ApplicationInstancePort)obj;

		long primaryKey = applicationInstancePort.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		ApplicationInstancePortModelImpl applicationInstancePortModelImpl = this;

		applicationInstancePortModelImpl._setModifiedDate = false;

		applicationInstancePortModelImpl._originalPrimary = applicationInstancePortModelImpl._primary;

		applicationInstancePortModelImpl._setOriginalPrimary = false;

		applicationInstancePortModelImpl._originalApplicationInstanceId = applicationInstancePortModelImpl._applicationInstanceId;

		applicationInstancePortModelImpl._setOriginalApplicationInstanceId = false;

		applicationInstancePortModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ApplicationInstancePort> toCacheModel() {
		ApplicationInstancePortCacheModel applicationInstancePortCacheModel = new ApplicationInstancePortCacheModel();

		applicationInstancePortCacheModel.applicationInstancePortId = getApplicationInstancePortId();

		applicationInstancePortCacheModel.groupId = getGroupId();

		applicationInstancePortCacheModel.companyId = getCompanyId();

		applicationInstancePortCacheModel.userId = getUserId();

		applicationInstancePortCacheModel.userName = getUserName();

		String userName = applicationInstancePortCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			applicationInstancePortCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			applicationInstancePortCacheModel.createDate = createDate.getTime();
		}
		else {
			applicationInstancePortCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			applicationInstancePortCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			applicationInstancePortCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		applicationInstancePortCacheModel.port = getPort();

		applicationInstancePortCacheModel.primary = getPrimary();

		applicationInstancePortCacheModel.applicationInstanceId = getApplicationInstanceId();

		return applicationInstancePortCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{applicationInstancePortId=");
		sb.append(getApplicationInstancePortId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", port=");
		sb.append(getPort());
		sb.append(", primary=");
		sb.append(getPrimary());
		sb.append(", applicationInstanceId=");
		sb.append(getApplicationInstanceId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append(
			"at.graz.meduni.bibbox.liferay.portlet.model.ApplicationInstancePort");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>applicationInstancePortId</column-name><column-value><![CDATA[");
		sb.append(getApplicationInstancePortId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>port</column-name><column-value><![CDATA[");
		sb.append(getPort());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>primary</column-name><column-value><![CDATA[");
		sb.append(getPrimary());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>applicationInstanceId</column-name><column-value><![CDATA[");
		sb.append(getApplicationInstanceId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = ApplicationInstancePort.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			ApplicationInstancePort.class
		};
	private long _applicationInstancePortId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _port;
	private boolean _primary;
	private boolean _originalPrimary;
	private boolean _setOriginalPrimary;
	private long _applicationInstanceId;
	private long _originalApplicationInstanceId;
	private boolean _setOriginalApplicationInstanceId;
	private long _columnBitmask;
	private ApplicationInstancePort _escapedModel;
}