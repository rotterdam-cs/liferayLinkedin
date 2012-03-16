/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.linkedin.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the UserLinkedIn service. Represents a row in the &quot;Portal_UserLinkedIn&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.linkedin.model.impl.UserLinkedInModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.linkedin.model.impl.UserLinkedInImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserLinkedIn
 * @see com.liferay.portal.linkedin.model.impl.UserLinkedInImpl
 * @see com.liferay.portal.linkedin.model.impl.UserLinkedInModelImpl
 * @generated
 */
public interface UserLinkedInModel extends BaseModel<UserLinkedIn> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a user linked in model instance should use the {@link UserLinkedIn} interface instead.
	 */

	/**
	 * Returns the primary key of this user linked in.
	 *
	 * @return the primary key of this user linked in
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this user linked in.
	 *
	 * @param primaryKey the primary key of this user linked in
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the ID of this user linked in.
	 *
	 * @return the ID of this user linked in
	 */
	public long getId();

	/**
	 * Sets the ID of this user linked in.
	 *
	 * @param id the ID of this user linked in
	 */
	public void setId(long id);

	/**
	 * Returns the company ID of this user linked in.
	 *
	 * @return the company ID of this user linked in
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this user linked in.
	 *
	 * @param companyId the company ID of this user linked in
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this user linked in.
	 *
	 * @return the user ID of this user linked in
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this user linked in.
	 *
	 * @param userId the user ID of this user linked in
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this user linked in.
	 *
	 * @return the user uuid of this user linked in
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this user linked in.
	 *
	 * @param userUuid the user uuid of this user linked in
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the linked ID of this user linked in.
	 *
	 * @return the linked ID of this user linked in
	 */
	@AutoEscape
	public String getLinkedId();

	/**
	 * Sets the linked ID of this user linked in.
	 *
	 * @param linkedId the linked ID of this user linked in
	 */
	public void setLinkedId(String linkedId);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(UserLinkedIn userLinkedIn);

	public int hashCode();

	public CacheModel<UserLinkedIn> toCacheModel();

	public UserLinkedIn toEscapedModel();

	public String toString();

	public String toXmlString();
}