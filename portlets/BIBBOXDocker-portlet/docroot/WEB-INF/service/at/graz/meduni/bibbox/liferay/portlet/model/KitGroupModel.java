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

package at.graz.meduni.bibbox.liferay.portlet.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the KitGroup service. Represents a row in the &quot;bibboxdocker_KitGroup&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link at.graz.meduni.bibbox.liferay.portlet.model.impl.KitGroupModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link at.graz.meduni.bibbox.liferay.portlet.model.impl.KitGroupImpl}.
 * </p>
 *
 * @author Robert Reihs
 * @see KitGroup
 * @see at.graz.meduni.bibbox.liferay.portlet.model.impl.KitGroupImpl
 * @see at.graz.meduni.bibbox.liferay.portlet.model.impl.KitGroupModelImpl
 * @generated
 */
@ProviderType
public interface KitGroupModel extends BaseModel<KitGroup>, GroupedModel,
	ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a kit group model instance should use the {@link KitGroup} interface instead.
	 */

	/**
	 * Returns the primary key of this kit group.
	 *
	 * @return the primary key of this kit group
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this kit group.
	 *
	 * @param primaryKey the primary key of this kit group
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the kit group ID of this kit group.
	 *
	 * @return the kit group ID of this kit group
	 */
	public long getKitGroupId();

	/**
	 * Sets the kit group ID of this kit group.
	 *
	 * @param kitGroupId the kit group ID of this kit group
	 */
	public void setKitGroupId(long kitGroupId);

	/**
	 * Returns the group ID of this kit group.
	 *
	 * @return the group ID of this kit group
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this kit group.
	 *
	 * @param groupId the group ID of this kit group
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this kit group.
	 *
	 * @return the company ID of this kit group
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this kit group.
	 *
	 * @param companyId the company ID of this kit group
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this kit group.
	 *
	 * @return the user ID of this kit group
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this kit group.
	 *
	 * @param userId the user ID of this kit group
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this kit group.
	 *
	 * @return the user uuid of this kit group
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this kit group.
	 *
	 * @param userUuid the user uuid of this kit group
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this kit group.
	 *
	 * @return the user name of this kit group
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this kit group.
	 *
	 * @param userName the user name of this kit group
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this kit group.
	 *
	 * @return the create date of this kit group
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this kit group.
	 *
	 * @param createDate the create date of this kit group
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this kit group.
	 *
	 * @return the modified date of this kit group
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this kit group.
	 *
	 * @param modifiedDate the modified date of this kit group
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the group of this kit group.
	 *
	 * @return the group of this kit group
	 */
	@AutoEscape
	public String getGroup();

	/**
	 * Sets the group of this kit group.
	 *
	 * @param group the group of this kit group
	 */
	public void setGroup(String group);

	/**
	 * Returns the application store item ID of this kit group.
	 *
	 * @return the application store item ID of this kit group
	 */
	public long getApplicationStoreItemId();

	/**
	 * Sets the application store item ID of this kit group.
	 *
	 * @param applicationStoreItemId the application store item ID of this kit group
	 */
	public void setApplicationStoreItemId(long applicationStoreItemId);

	/**
	 * Returns the bibbox kit ID of this kit group.
	 *
	 * @return the bibbox kit ID of this kit group
	 */
	public long getBibboxKitId();

	/**
	 * Sets the bibbox kit ID of this kit group.
	 *
	 * @param bibboxKitId the bibbox kit ID of this kit group
	 */
	public void setBibboxKitId(long bibboxKitId);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(
		at.graz.meduni.bibbox.liferay.portlet.model.KitGroup kitGroup);

	@Override
	public int hashCode();

	@Override
	public CacheModel<at.graz.meduni.bibbox.liferay.portlet.model.KitGroup> toCacheModel();

	@Override
	public at.graz.meduni.bibbox.liferay.portlet.model.KitGroup toEscapedModel();

	@Override
	public at.graz.meduni.bibbox.liferay.portlet.model.KitGroup toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}