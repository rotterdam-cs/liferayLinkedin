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

package com.liferay.portal.linkedin.service.persistence;

import com.liferay.portal.linkedin.model.UserLinkedIn;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the user linked in service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserLinkedInPersistenceImpl
 * @see UserLinkedInUtil
 * @generated
 */
public interface UserLinkedInPersistence extends BasePersistence<UserLinkedIn> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserLinkedInUtil} to access the user linked in persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the user linked in in the entity cache if it is enabled.
	*
	* @param userLinkedIn the user linked in
	*/
	public void cacheResult(
		com.liferay.portal.linkedin.model.UserLinkedIn userLinkedIn);

	/**
	* Caches the user linked ins in the entity cache if it is enabled.
	*
	* @param userLinkedIns the user linked ins
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.linkedin.model.UserLinkedIn> userLinkedIns);

	/**
	* Creates a new user linked in with the primary key. Does not add the user linked in to the database.
	*
	* @param id the primary key for the new user linked in
	* @return the new user linked in
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn create(long id);

	/**
	* Removes the user linked in with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the user linked in
	* @return the user linked in that was removed
	* @throws com.liferay.portal.linkedin.NoSuchUserLinkedInException if a user linked in with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn remove(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException;

	public com.liferay.portal.linkedin.model.UserLinkedIn updateImpl(
		com.liferay.portal.linkedin.model.UserLinkedIn userLinkedIn,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user linked in with the primary key or throws a {@link com.liferay.portal.linkedin.NoSuchUserLinkedInException} if it could not be found.
	*
	* @param id the primary key of the user linked in
	* @return the user linked in
	* @throws com.liferay.portal.linkedin.NoSuchUserLinkedInException if a user linked in with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn findByPrimaryKey(
		long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException;

	/**
	* Returns the user linked in with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the user linked in
	* @return the user linked in, or <code>null</code> if a user linked in with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn fetchByPrimaryKey(
		long id) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user linked in where userId = &#63; or throws a {@link com.liferay.portal.linkedin.NoSuchUserLinkedInException} if it could not be found.
	*
	* @param userId the user ID
	* @return the matching user linked in
	* @throws com.liferay.portal.linkedin.NoSuchUserLinkedInException if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn findByUserId(
		long userId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException;

	/**
	* Returns the user linked in where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn fetchByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user linked in where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn fetchByUserId(
		long userId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user linked in where linkedId = &#63; or throws a {@link com.liferay.portal.linkedin.NoSuchUserLinkedInException} if it could not be found.
	*
	* @param linkedId the linked ID
	* @return the matching user linked in
	* @throws com.liferay.portal.linkedin.NoSuchUserLinkedInException if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn findByLinkedId(
		java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException;

	/**
	* Returns the user linked in where linkedId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param linkedId the linked ID
	* @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn fetchByLinkedId(
		java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user linked in where linkedId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param linkedId the linked ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn fetchByLinkedId(
		java.lang.String linkedId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user linked in where companyId = &#63; and linkedId = &#63; or throws a {@link com.liferay.portal.linkedin.NoSuchUserLinkedInException} if it could not be found.
	*
	* @param companyId the company ID
	* @param linkedId the linked ID
	* @return the matching user linked in
	* @throws com.liferay.portal.linkedin.NoSuchUserLinkedInException if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn findByL_C_Id(
		long companyId, java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException;

	/**
	* Returns the user linked in where companyId = &#63; and linkedId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param linkedId the linked ID
	* @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn fetchByL_C_Id(
		long companyId, java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user linked in where companyId = &#63; and linkedId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param linkedId the linked ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn fetchByL_C_Id(
		long companyId, java.lang.String linkedId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user linked in where companyId = &#63; and userId = &#63; and linkedId = &#63; or throws a {@link com.liferay.portal.linkedin.NoSuchUserLinkedInException} if it could not be found.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param linkedId the linked ID
	* @return the matching user linked in
	* @throws com.liferay.portal.linkedin.NoSuchUserLinkedInException if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn findByU_L(
		long companyId, long userId, java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException;

	/**
	* Returns the user linked in where companyId = &#63; and userId = &#63; and linkedId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param linkedId the linked ID
	* @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn fetchByU_L(
		long companyId, long userId, java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user linked in where companyId = &#63; and userId = &#63; and linkedId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param linkedId the linked ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.linkedin.model.UserLinkedIn fetchByU_L(
		long companyId, long userId, java.lang.String linkedId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the user linked ins.
	*
	* @return the user linked ins
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.linkedin.model.UserLinkedIn> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.portal.linkedin.model.UserLinkedIn> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the user linked ins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user linked ins
	* @param end the upper bound of the range of user linked ins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user linked ins
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.linkedin.model.UserLinkedIn> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the user linked in where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException;

	/**
	* Removes the user linked in where linkedId = &#63; from the database.
	*
	* @param linkedId the linked ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByLinkedId(java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException;

	/**
	* Removes the user linked in where companyId = &#63; and linkedId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param linkedId the linked ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByL_C_Id(long companyId, java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException;

	/**
	* Removes the user linked in where companyId = &#63; and userId = &#63; and linkedId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param linkedId the linked ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_L(long companyId, long userId,
		java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException;

	/**
	* Removes all the user linked ins from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user linked ins where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching user linked ins
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user linked ins where linkedId = &#63;.
	*
	* @param linkedId the linked ID
	* @return the number of matching user linked ins
	* @throws SystemException if a system exception occurred
	*/
	public int countByLinkedId(java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user linked ins where companyId = &#63; and linkedId = &#63;.
	*
	* @param companyId the company ID
	* @param linkedId the linked ID
	* @return the number of matching user linked ins
	* @throws SystemException if a system exception occurred
	*/
	public int countByL_C_Id(long companyId, java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user linked ins where companyId = &#63; and userId = &#63; and linkedId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param linkedId the linked ID
	* @return the number of matching user linked ins
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_L(long companyId, long userId, java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user linked ins.
	*
	* @return the number of user linked ins
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}