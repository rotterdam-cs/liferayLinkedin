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

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link UserLinkedIn}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       UserLinkedIn
 * @generated
 */
public class UserLinkedInWrapper implements UserLinkedIn,
	ModelWrapper<UserLinkedIn> {
	public UserLinkedInWrapper(UserLinkedIn userLinkedIn) {
		_userLinkedIn = userLinkedIn;
	}

	public Class<?> getModelClass() {
		return UserLinkedIn.class;
	}

	public String getModelClassName() {
		return UserLinkedIn.class.getName();
	}

	/**
	* Returns the primary key of this user linked in.
	*
	* @return the primary key of this user linked in
	*/
	public long getPrimaryKey() {
		return _userLinkedIn.getPrimaryKey();
	}

	/**
	* Sets the primary key of this user linked in.
	*
	* @param primaryKey the primary key of this user linked in
	*/
	public void setPrimaryKey(long primaryKey) {
		_userLinkedIn.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ID of this user linked in.
	*
	* @return the ID of this user linked in
	*/
	public long getId() {
		return _userLinkedIn.getId();
	}

	/**
	* Sets the ID of this user linked in.
	*
	* @param id the ID of this user linked in
	*/
	public void setId(long id) {
		_userLinkedIn.setId(id);
	}

	/**
	* Returns the company ID of this user linked in.
	*
	* @return the company ID of this user linked in
	*/
	public long getCompanyId() {
		return _userLinkedIn.getCompanyId();
	}

	/**
	* Sets the company ID of this user linked in.
	*
	* @param companyId the company ID of this user linked in
	*/
	public void setCompanyId(long companyId) {
		_userLinkedIn.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this user linked in.
	*
	* @return the user ID of this user linked in
	*/
	public long getUserId() {
		return _userLinkedIn.getUserId();
	}

	/**
	* Sets the user ID of this user linked in.
	*
	* @param userId the user ID of this user linked in
	*/
	public void setUserId(long userId) {
		_userLinkedIn.setUserId(userId);
	}

	/**
	* Returns the user uuid of this user linked in.
	*
	* @return the user uuid of this user linked in
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userLinkedIn.getUserUuid();
	}

	/**
	* Sets the user uuid of this user linked in.
	*
	* @param userUuid the user uuid of this user linked in
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_userLinkedIn.setUserUuid(userUuid);
	}

	/**
	* Returns the linked ID of this user linked in.
	*
	* @return the linked ID of this user linked in
	*/
	public java.lang.String getLinkedId() {
		return _userLinkedIn.getLinkedId();
	}

	/**
	* Sets the linked ID of this user linked in.
	*
	* @param linkedId the linked ID of this user linked in
	*/
	public void setLinkedId(java.lang.String linkedId) {
		_userLinkedIn.setLinkedId(linkedId);
	}

	public boolean isNew() {
		return _userLinkedIn.isNew();
	}

	public void setNew(boolean n) {
		_userLinkedIn.setNew(n);
	}

	public boolean isCachedModel() {
		return _userLinkedIn.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_userLinkedIn.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _userLinkedIn.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _userLinkedIn.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_userLinkedIn.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _userLinkedIn.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_userLinkedIn.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new UserLinkedInWrapper((UserLinkedIn)_userLinkedIn.clone());
	}

	public int compareTo(
		com.liferay.portal.linkedin.model.UserLinkedIn userLinkedIn) {
		return _userLinkedIn.compareTo(userLinkedIn);
	}

	@Override
	public int hashCode() {
		return _userLinkedIn.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.linkedin.model.UserLinkedIn> toCacheModel() {
		return _userLinkedIn.toCacheModel();
	}

	public com.liferay.portal.linkedin.model.UserLinkedIn toEscapedModel() {
		return new UserLinkedInWrapper(_userLinkedIn.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _userLinkedIn.toString();
	}

	public java.lang.String toXmlString() {
		return _userLinkedIn.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_userLinkedIn.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public UserLinkedIn getWrappedUserLinkedIn() {
		return _userLinkedIn;
	}

	public UserLinkedIn getWrappedModel() {
		return _userLinkedIn;
	}

	public void resetOriginalValues() {
		_userLinkedIn.resetOriginalValues();
	}

	private UserLinkedIn _userLinkedIn;
}