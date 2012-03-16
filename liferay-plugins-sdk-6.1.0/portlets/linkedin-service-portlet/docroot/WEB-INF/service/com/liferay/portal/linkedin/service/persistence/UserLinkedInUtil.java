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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.linkedin.model.UserLinkedIn;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the user linked in service. This utility wraps {@link UserLinkedInPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserLinkedInPersistence
 * @see UserLinkedInPersistenceImpl
 * @generated
 */
public class UserLinkedInUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(UserLinkedIn userLinkedIn) {
		getPersistence().clearCache(userLinkedIn);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<UserLinkedIn> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserLinkedIn> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserLinkedIn> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static UserLinkedIn update(UserLinkedIn userLinkedIn, boolean merge)
		throws SystemException {
		return getPersistence().update(userLinkedIn, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static UserLinkedIn update(UserLinkedIn userLinkedIn, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(userLinkedIn, merge, serviceContext);
	}

	/**
	* Caches the user linked in in the entity cache if it is enabled.
	*
	* @param userLinkedIn the user linked in
	*/
	public static void cacheResult(
		com.liferay.portal.linkedin.model.UserLinkedIn userLinkedIn) {
		getPersistence().cacheResult(userLinkedIn);
	}

	/**
	* Caches the user linked ins in the entity cache if it is enabled.
	*
	* @param userLinkedIns the user linked ins
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.linkedin.model.UserLinkedIn> userLinkedIns) {
		getPersistence().cacheResult(userLinkedIns);
	}

	/**
	* Creates a new user linked in with the primary key. Does not add the user linked in to the database.
	*
	* @param id the primary key for the new user linked in
	* @return the new user linked in
	*/
	public static com.liferay.portal.linkedin.model.UserLinkedIn create(long id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the user linked in with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the user linked in
	* @return the user linked in that was removed
	* @throws com.liferay.portal.linkedin.NoSuchUserLinkedInException if a user linked in with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.linkedin.model.UserLinkedIn remove(long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException {
		return getPersistence().remove(id);
	}

	public static com.liferay.portal.linkedin.model.UserLinkedIn updateImpl(
		com.liferay.portal.linkedin.model.UserLinkedIn userLinkedIn,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(userLinkedIn, merge);
	}

	/**
	* Returns the user linked in with the primary key or throws a {@link com.liferay.portal.linkedin.NoSuchUserLinkedInException} if it could not be found.
	*
	* @param id the primary key of the user linked in
	* @return the user linked in
	* @throws com.liferay.portal.linkedin.NoSuchUserLinkedInException if a user linked in with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.linkedin.model.UserLinkedIn findByPrimaryKey(
		long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the user linked in with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the user linked in
	* @return the user linked in, or <code>null</code> if a user linked in with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.linkedin.model.UserLinkedIn fetchByPrimaryKey(
		long id) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	* Returns the user linked in where userId = &#63; or throws a {@link com.liferay.portal.linkedin.NoSuchUserLinkedInException} if it could not be found.
	*
	* @param userId the user ID
	* @return the matching user linked in
	* @throws com.liferay.portal.linkedin.NoSuchUserLinkedInException if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.linkedin.model.UserLinkedIn findByUserId(
		long userId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns the user linked in where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.linkedin.model.UserLinkedIn fetchByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId(userId);
	}

	/**
	* Returns the user linked in where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.linkedin.model.UserLinkedIn fetchByUserId(
		long userId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId(userId, retrieveFromCache);
	}

	/**
	* Returns the user linked in where linkedId = &#63; or throws a {@link com.liferay.portal.linkedin.NoSuchUserLinkedInException} if it could not be found.
	*
	* @param linkedId the linked ID
	* @return the matching user linked in
	* @throws com.liferay.portal.linkedin.NoSuchUserLinkedInException if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.linkedin.model.UserLinkedIn findByLinkedId(
		java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException {
		return getPersistence().findByLinkedId(linkedId);
	}

	/**
	* Returns the user linked in where linkedId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param linkedId the linked ID
	* @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.linkedin.model.UserLinkedIn fetchByLinkedId(
		java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByLinkedId(linkedId);
	}

	/**
	* Returns the user linked in where linkedId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param linkedId the linked ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.linkedin.model.UserLinkedIn fetchByLinkedId(
		java.lang.String linkedId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByLinkedId(linkedId, retrieveFromCache);
	}

	/**
	* Returns the user linked in where companyId = &#63; and linkedId = &#63; or throws a {@link com.liferay.portal.linkedin.NoSuchUserLinkedInException} if it could not be found.
	*
	* @param companyId the company ID
	* @param linkedId the linked ID
	* @return the matching user linked in
	* @throws com.liferay.portal.linkedin.NoSuchUserLinkedInException if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.linkedin.model.UserLinkedIn findByL_C_Id(
		long companyId, java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException {
		return getPersistence().findByL_C_Id(companyId, linkedId);
	}

	/**
	* Returns the user linked in where companyId = &#63; and linkedId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param linkedId the linked ID
	* @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.linkedin.model.UserLinkedIn fetchByL_C_Id(
		long companyId, java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByL_C_Id(companyId, linkedId);
	}

	/**
	* Returns the user linked in where companyId = &#63; and linkedId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param linkedId the linked ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.linkedin.model.UserLinkedIn fetchByL_C_Id(
		long companyId, java.lang.String linkedId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByL_C_Id(companyId, linkedId, retrieveFromCache);
	}

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
	public static com.liferay.portal.linkedin.model.UserLinkedIn findByU_L(
		long companyId, long userId, java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException {
		return getPersistence().findByU_L(companyId, userId, linkedId);
	}

	/**
	* Returns the user linked in where companyId = &#63; and userId = &#63; and linkedId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param linkedId the linked ID
	* @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.linkedin.model.UserLinkedIn fetchByU_L(
		long companyId, long userId, java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_L(companyId, userId, linkedId);
	}

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
	public static com.liferay.portal.linkedin.model.UserLinkedIn fetchByU_L(
		long companyId, long userId, java.lang.String linkedId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_L(companyId, userId, linkedId, retrieveFromCache);
	}

	/**
	* Returns all the user linked ins.
	*
	* @return the user linked ins
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.linkedin.model.UserLinkedIn> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.liferay.portal.linkedin.model.UserLinkedIn> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.portal.linkedin.model.UserLinkedIn> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the user linked in where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Removes the user linked in where linkedId = &#63; from the database.
	*
	* @param linkedId the linked ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByLinkedId(java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException {
		getPersistence().removeByLinkedId(linkedId);
	}

	/**
	* Removes the user linked in where companyId = &#63; and linkedId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param linkedId the linked ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByL_C_Id(long companyId, java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException {
		getPersistence().removeByL_C_Id(companyId, linkedId);
	}

	/**
	* Removes the user linked in where companyId = &#63; and userId = &#63; and linkedId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param linkedId the linked ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_L(long companyId, long userId,
		java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.linkedin.NoSuchUserLinkedInException {
		getPersistence().removeByU_L(companyId, userId, linkedId);
	}

	/**
	* Removes all the user linked ins from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of user linked ins where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching user linked ins
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of user linked ins where linkedId = &#63;.
	*
	* @param linkedId the linked ID
	* @return the number of matching user linked ins
	* @throws SystemException if a system exception occurred
	*/
	public static int countByLinkedId(java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByLinkedId(linkedId);
	}

	/**
	* Returns the number of user linked ins where companyId = &#63; and linkedId = &#63;.
	*
	* @param companyId the company ID
	* @param linkedId the linked ID
	* @return the number of matching user linked ins
	* @throws SystemException if a system exception occurred
	*/
	public static int countByL_C_Id(long companyId, java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByL_C_Id(companyId, linkedId);
	}

	/**
	* Returns the number of user linked ins where companyId = &#63; and userId = &#63; and linkedId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param linkedId the linked ID
	* @return the number of matching user linked ins
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_L(long companyId, long userId,
		java.lang.String linkedId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_L(companyId, userId, linkedId);
	}

	/**
	* Returns the number of user linked ins.
	*
	* @return the number of user linked ins
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static UserLinkedInPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (UserLinkedInPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.linkedin.service.ClpSerializer.getServletContextName(),
					UserLinkedInPersistence.class.getName());

			ReferenceRegistry.registerReference(UserLinkedInUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(UserLinkedInPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(UserLinkedInUtil.class,
			"_persistence");
	}

	private static UserLinkedInPersistence _persistence;
}