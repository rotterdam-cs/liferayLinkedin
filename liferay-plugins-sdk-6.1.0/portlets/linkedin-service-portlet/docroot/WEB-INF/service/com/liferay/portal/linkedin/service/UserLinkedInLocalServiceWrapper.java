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

package com.liferay.portal.linkedin.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link UserLinkedInLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       UserLinkedInLocalService
 * @generated
 */
public class UserLinkedInLocalServiceWrapper implements UserLinkedInLocalService,
	ServiceWrapper<UserLinkedInLocalService> {
	public UserLinkedInLocalServiceWrapper(
		UserLinkedInLocalService userLinkedInLocalService) {
		_userLinkedInLocalService = userLinkedInLocalService;
	}

	/**
	* Adds the user linked in to the database. Also notifies the appropriate model listeners.
	*
	* @param userLinkedIn the user linked in
	* @return the user linked in that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn addUserLinkedIn(
		com.liferay.portal.linkedin.model.UserLinkedIn userLinkedIn)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userLinkedInLocalService.addUserLinkedIn(userLinkedIn);
	}

	/**
	* Creates a new user linked in with the primary key. Does not add the user linked in to the database.
	*
	* @param id the primary key for the new user linked in
	* @return the new user linked in
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn createUserLinkedIn(
		long id) {
		return _userLinkedInLocalService.createUserLinkedIn(id);
	}

	/**
	* Deletes the user linked in with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the user linked in
	* @throws PortalException if a user linked in with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteUserLinkedIn(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_userLinkedInLocalService.deleteUserLinkedIn(id);
	}

	/**
	* Deletes the user linked in from the database. Also notifies the appropriate model listeners.
	*
	* @param userLinkedIn the user linked in
	* @throws SystemException if a system exception occurred
	*/
	public void deleteUserLinkedIn(
		com.liferay.portal.linkedin.model.UserLinkedIn userLinkedIn)
		throws com.liferay.portal.kernel.exception.SystemException {
		_userLinkedInLocalService.deleteUserLinkedIn(userLinkedIn);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userLinkedInLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _userLinkedInLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userLinkedInLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userLinkedInLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.linkedin.model.UserLinkedIn fetchUserLinkedIn(
		long id) throws com.liferay.portal.kernel.exception.SystemException {
		return _userLinkedInLocalService.fetchUserLinkedIn(id);
	}

	/**
	* Returns the user linked in with the primary key.
	*
	* @param id the primary key of the user linked in
	* @return the user linked in
	* @throws PortalException if a user linked in with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn getUserLinkedIn(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userLinkedInLocalService.getUserLinkedIn(id);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userLinkedInLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the user linked ins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user linked ins
	* @param end the upper bound of the range of user linked ins (not inclusive)
	* @return the range of user linked ins
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.linkedin.model.UserLinkedIn> getUserLinkedIns(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userLinkedInLocalService.getUserLinkedIns(start, end);
	}

	/**
	* Returns the number of user linked ins.
	*
	* @return the number of user linked ins
	* @throws SystemException if a system exception occurred
	*/
	public int getUserLinkedInsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userLinkedInLocalService.getUserLinkedInsCount();
	}

	/**
	* Updates the user linked in in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userLinkedIn the user linked in
	* @return the user linked in that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn updateUserLinkedIn(
		com.liferay.portal.linkedin.model.UserLinkedIn userLinkedIn)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userLinkedInLocalService.updateUserLinkedIn(userLinkedIn);
	}

	/**
	* Updates the user linked in in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userLinkedIn the user linked in
	* @param merge whether to merge the user linked in with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the user linked in that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn updateUserLinkedIn(
		com.liferay.portal.linkedin.model.UserLinkedIn userLinkedIn,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userLinkedInLocalService.updateUserLinkedIn(userLinkedIn, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _userLinkedInLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_userLinkedInLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.linkedin.model.UserLinkedIn findByL_C_Id(
		long companyId, java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException {
		return _userLinkedInLocalService.findByL_C_Id(companyId, linkedId);
	}

	public com.liferay.portal.linkedin.model.UserLinkedIn findByLinkedId(
		java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException {
		return _userLinkedInLocalService.findByLinkedId(linkedId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public UserLinkedInLocalService getWrappedUserLinkedInLocalService() {
		return _userLinkedInLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedUserLinkedInLocalService(
		UserLinkedInLocalService userLinkedInLocalService) {
		_userLinkedInLocalService = userLinkedInLocalService;
	}

	public UserLinkedInLocalService getWrappedService() {
		return _userLinkedInLocalService;
	}

	public void setWrappedService(
		UserLinkedInLocalService userLinkedInLocalService) {
		_userLinkedInLocalService = userLinkedInLocalService;
	}

	private UserLinkedInLocalService _userLinkedInLocalService;
}