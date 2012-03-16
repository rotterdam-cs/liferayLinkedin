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

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.linkedin.NoSuchUserLinkedInException;
import com.liferay.portal.linkedin.model.UserLinkedIn;
import com.liferay.portal.linkedin.model.impl.UserLinkedInImpl;
import com.liferay.portal.linkedin.model.impl.UserLinkedInModelImpl;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the user linked in service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserLinkedInPersistence
 * @see UserLinkedInUtil
 * @generated
 */
public class UserLinkedInPersistenceImpl extends BasePersistenceImpl<UserLinkedIn>
	implements UserLinkedInPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UserLinkedInUtil} to access the user linked in persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UserLinkedInImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_USERID = new FinderPath(UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
			UserLinkedInModelImpl.FINDER_CACHE_ENABLED, UserLinkedInImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUserId",
			new String[] { Long.class.getName() },
			UserLinkedInModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
			UserLinkedInModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_LINKEDID = new FinderPath(UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
			UserLinkedInModelImpl.FINDER_CACHE_ENABLED, UserLinkedInImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByLinkedId",
			new String[] { String.class.getName() },
			UserLinkedInModelImpl.LINKEDID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LINKEDID = new FinderPath(UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
			UserLinkedInModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLinkedId",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_L_C_ID = new FinderPath(UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
			UserLinkedInModelImpl.FINDER_CACHE_ENABLED, UserLinkedInImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByL_C_Id",
			new String[] { Long.class.getName(), String.class.getName() },
			UserLinkedInModelImpl.COMPANYID_COLUMN_BITMASK |
			UserLinkedInModelImpl.LINKEDID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_L_C_ID = new FinderPath(UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
			UserLinkedInModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByL_C_Id",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_U_L = new FinderPath(UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
			UserLinkedInModelImpl.FINDER_CACHE_ENABLED, UserLinkedInImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			UserLinkedInModelImpl.COMPANYID_COLUMN_BITMASK |
			UserLinkedInModelImpl.USERID_COLUMN_BITMASK |
			UserLinkedInModelImpl.LINKEDID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_L = new FinderPath(UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
			UserLinkedInModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_L",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
			UserLinkedInModelImpl.FINDER_CACHE_ENABLED, UserLinkedInImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
			UserLinkedInModelImpl.FINDER_CACHE_ENABLED, UserLinkedInImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
			UserLinkedInModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the user linked in in the entity cache if it is enabled.
	 *
	 * @param userLinkedIn the user linked in
	 */
	public void cacheResult(UserLinkedIn userLinkedIn) {
		EntityCacheUtil.putResult(UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
			UserLinkedInImpl.class, userLinkedIn.getPrimaryKey(), userLinkedIn);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID,
			new Object[] { Long.valueOf(userLinkedIn.getUserId()) },
			userLinkedIn);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LINKEDID,
			new Object[] { userLinkedIn.getLinkedId() }, userLinkedIn);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_L_C_ID,
			new Object[] {
				Long.valueOf(userLinkedIn.getCompanyId()),
				
			userLinkedIn.getLinkedId()
			}, userLinkedIn);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_L,
			new Object[] {
				Long.valueOf(userLinkedIn.getCompanyId()),
				Long.valueOf(userLinkedIn.getUserId()),
				
			userLinkedIn.getLinkedId()
			}, userLinkedIn);

		userLinkedIn.resetOriginalValues();
	}

	/**
	 * Caches the user linked ins in the entity cache if it is enabled.
	 *
	 * @param userLinkedIns the user linked ins
	 */
	public void cacheResult(List<UserLinkedIn> userLinkedIns) {
		for (UserLinkedIn userLinkedIn : userLinkedIns) {
			if (EntityCacheUtil.getResult(
						UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
						UserLinkedInImpl.class, userLinkedIn.getPrimaryKey()) == null) {
				cacheResult(userLinkedIn);
			}
			else {
				userLinkedIn.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all user linked ins.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(UserLinkedInImpl.class.getName());
		}

		EntityCacheUtil.clearCache(UserLinkedInImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the user linked in.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(UserLinkedIn userLinkedIn) {
		EntityCacheUtil.removeResult(UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
			UserLinkedInImpl.class, userLinkedIn.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(userLinkedIn);
	}

	@Override
	public void clearCache(List<UserLinkedIn> userLinkedIns) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (UserLinkedIn userLinkedIn : userLinkedIns) {
			EntityCacheUtil.removeResult(UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
				UserLinkedInImpl.class, userLinkedIn.getPrimaryKey());

			clearUniqueFindersCache(userLinkedIn);
		}
	}

	protected void clearUniqueFindersCache(UserLinkedIn userLinkedIn) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERID,
			new Object[] { Long.valueOf(userLinkedIn.getUserId()) });

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_LINKEDID,
			new Object[] { userLinkedIn.getLinkedId() });

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_L_C_ID,
			new Object[] {
				Long.valueOf(userLinkedIn.getCompanyId()),
				
			userLinkedIn.getLinkedId()
			});

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_L,
			new Object[] {
				Long.valueOf(userLinkedIn.getCompanyId()),
				Long.valueOf(userLinkedIn.getUserId()),
				
			userLinkedIn.getLinkedId()
			});
	}

	/**
	 * Creates a new user linked in with the primary key. Does not add the user linked in to the database.
	 *
	 * @param id the primary key for the new user linked in
	 * @return the new user linked in
	 */
	public UserLinkedIn create(long id) {
		UserLinkedIn userLinkedIn = new UserLinkedInImpl();

		userLinkedIn.setNew(true);
		userLinkedIn.setPrimaryKey(id);

		return userLinkedIn;
	}

	/**
	 * Removes the user linked in with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the user linked in
	 * @return the user linked in that was removed
	 * @throws com.liferay.portal.linkedin.NoSuchUserLinkedInException if a user linked in with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserLinkedIn remove(long id)
		throws NoSuchUserLinkedInException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the user linked in with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user linked in
	 * @return the user linked in that was removed
	 * @throws com.liferay.portal.linkedin.NoSuchUserLinkedInException if a user linked in with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserLinkedIn remove(Serializable primaryKey)
		throws NoSuchUserLinkedInException, SystemException {
		Session session = null;

		try {
			session = openSession();

			UserLinkedIn userLinkedIn = (UserLinkedIn)session.get(UserLinkedInImpl.class,
					primaryKey);

			if (userLinkedIn == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserLinkedInException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(userLinkedIn);
		}
		catch (NoSuchUserLinkedInException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected UserLinkedIn removeImpl(UserLinkedIn userLinkedIn)
		throws SystemException {
		userLinkedIn = toUnwrappedModel(userLinkedIn);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, userLinkedIn);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(userLinkedIn);

		return userLinkedIn;
	}

	@Override
	public UserLinkedIn updateImpl(
		com.liferay.portal.linkedin.model.UserLinkedIn userLinkedIn,
		boolean merge) throws SystemException {
		userLinkedIn = toUnwrappedModel(userLinkedIn);

		boolean isNew = userLinkedIn.isNew();

		UserLinkedInModelImpl userLinkedInModelImpl = (UserLinkedInModelImpl)userLinkedIn;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, userLinkedIn, merge);

			userLinkedIn.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !UserLinkedInModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
			UserLinkedInImpl.class, userLinkedIn.getPrimaryKey(), userLinkedIn);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID,
				new Object[] { Long.valueOf(userLinkedIn.getUserId()) },
				userLinkedIn);

			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LINKEDID,
				new Object[] { userLinkedIn.getLinkedId() }, userLinkedIn);

			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_L_C_ID,
				new Object[] {
					Long.valueOf(userLinkedIn.getCompanyId()),
					
				userLinkedIn.getLinkedId()
				}, userLinkedIn);

			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_L,
				new Object[] {
					Long.valueOf(userLinkedIn.getCompanyId()),
					Long.valueOf(userLinkedIn.getUserId()),
					
				userLinkedIn.getLinkedId()
				}, userLinkedIn);
		}
		else {
			if ((userLinkedInModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(userLinkedInModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERID, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID,
					new Object[] { Long.valueOf(userLinkedIn.getUserId()) },
					userLinkedIn);
			}

			if ((userLinkedInModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_LINKEDID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						userLinkedInModelImpl.getOriginalLinkedId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LINKEDID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_LINKEDID, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LINKEDID,
					new Object[] { userLinkedIn.getLinkedId() }, userLinkedIn);
			}

			if ((userLinkedInModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_L_C_ID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(userLinkedInModelImpl.getOriginalCompanyId()),
						
						userLinkedInModelImpl.getOriginalLinkedId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_L_C_ID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_L_C_ID, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_L_C_ID,
					new Object[] {
						Long.valueOf(userLinkedIn.getCompanyId()),
						
					userLinkedIn.getLinkedId()
					}, userLinkedIn);
			}

			if ((userLinkedInModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(userLinkedInModelImpl.getOriginalCompanyId()),
						Long.valueOf(userLinkedInModelImpl.getOriginalUserId()),
						
						userLinkedInModelImpl.getOriginalLinkedId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_L, args);
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_L, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_L,
					new Object[] {
						Long.valueOf(userLinkedIn.getCompanyId()),
						Long.valueOf(userLinkedIn.getUserId()),
						
					userLinkedIn.getLinkedId()
					}, userLinkedIn);
			}
		}

		return userLinkedIn;
	}

	protected UserLinkedIn toUnwrappedModel(UserLinkedIn userLinkedIn) {
		if (userLinkedIn instanceof UserLinkedInImpl) {
			return userLinkedIn;
		}

		UserLinkedInImpl userLinkedInImpl = new UserLinkedInImpl();

		userLinkedInImpl.setNew(userLinkedIn.isNew());
		userLinkedInImpl.setPrimaryKey(userLinkedIn.getPrimaryKey());

		userLinkedInImpl.setId(userLinkedIn.getId());
		userLinkedInImpl.setCompanyId(userLinkedIn.getCompanyId());
		userLinkedInImpl.setUserId(userLinkedIn.getUserId());
		userLinkedInImpl.setLinkedId(userLinkedIn.getLinkedId());

		return userLinkedInImpl;
	}

	/**
	 * Returns the user linked in with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the user linked in
	 * @return the user linked in
	 * @throws com.liferay.portal.NoSuchModelException if a user linked in with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserLinkedIn findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the user linked in with the primary key or throws a {@link com.liferay.portal.linkedin.NoSuchUserLinkedInException} if it could not be found.
	 *
	 * @param id the primary key of the user linked in
	 * @return the user linked in
	 * @throws com.liferay.portal.linkedin.NoSuchUserLinkedInException if a user linked in with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserLinkedIn findByPrimaryKey(long id)
		throws NoSuchUserLinkedInException, SystemException {
		UserLinkedIn userLinkedIn = fetchByPrimaryKey(id);

		if (userLinkedIn == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchUserLinkedInException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return userLinkedIn;
	}

	/**
	 * Returns the user linked in with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user linked in
	 * @return the user linked in, or <code>null</code> if a user linked in with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public UserLinkedIn fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the user linked in with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the user linked in
	 * @return the user linked in, or <code>null</code> if a user linked in with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserLinkedIn fetchByPrimaryKey(long id) throws SystemException {
		UserLinkedIn userLinkedIn = (UserLinkedIn)EntityCacheUtil.getResult(UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
				UserLinkedInImpl.class, id);

		if (userLinkedIn == _nullUserLinkedIn) {
			return null;
		}

		if (userLinkedIn == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				userLinkedIn = (UserLinkedIn)session.get(UserLinkedInImpl.class,
						Long.valueOf(id));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (userLinkedIn != null) {
					cacheResult(userLinkedIn);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(UserLinkedInModelImpl.ENTITY_CACHE_ENABLED,
						UserLinkedInImpl.class, id, _nullUserLinkedIn);
				}

				closeSession(session);
			}
		}

		return userLinkedIn;
	}

	/**
	 * Returns the user linked in where userId = &#63; or throws a {@link com.liferay.portal.linkedin.NoSuchUserLinkedInException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching user linked in
	 * @throws com.liferay.portal.linkedin.NoSuchUserLinkedInException if a matching user linked in could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserLinkedIn findByUserId(long userId)
		throws NoSuchUserLinkedInException, SystemException {
		UserLinkedIn userLinkedIn = fetchByUserId(userId);

		if (userLinkedIn == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchUserLinkedInException(msg.toString());
		}

		return userLinkedIn;
	}

	/**
	 * Returns the user linked in where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserLinkedIn fetchByUserId(long userId) throws SystemException {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the user linked in where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserLinkedIn fetchByUserId(long userId, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_USERID,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_USERLINKEDIN_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				List<UserLinkedIn> list = q.list();

				result = list;

				UserLinkedIn userLinkedIn = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID,
						finderArgs, list);
				}
				else {
					userLinkedIn = list.get(0);

					cacheResult(userLinkedIn);

					if ((userLinkedIn.getUserId() != userId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERID,
							finderArgs, userLinkedIn);
					}
				}

				return userLinkedIn;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERID,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (UserLinkedIn)result;
			}
		}
	}

	/**
	 * Returns the user linked in where linkedId = &#63; or throws a {@link com.liferay.portal.linkedin.NoSuchUserLinkedInException} if it could not be found.
	 *
	 * @param linkedId the linked ID
	 * @return the matching user linked in
	 * @throws com.liferay.portal.linkedin.NoSuchUserLinkedInException if a matching user linked in could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserLinkedIn findByLinkedId(String linkedId)
		throws NoSuchUserLinkedInException, SystemException {
		UserLinkedIn userLinkedIn = fetchByLinkedId(linkedId);

		if (userLinkedIn == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("linkedId=");
			msg.append(linkedId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchUserLinkedInException(msg.toString());
		}

		return userLinkedIn;
	}

	/**
	 * Returns the user linked in where linkedId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param linkedId the linked ID
	 * @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserLinkedIn fetchByLinkedId(String linkedId)
		throws SystemException {
		return fetchByLinkedId(linkedId, true);
	}

	/**
	 * Returns the user linked in where linkedId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param linkedId the linked ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserLinkedIn fetchByLinkedId(String linkedId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { linkedId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_LINKEDID,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_USERLINKEDIN_WHERE);

			if (linkedId == null) {
				query.append(_FINDER_COLUMN_LINKEDID_LINKEDID_1);
			}
			else {
				if (linkedId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_LINKEDID_LINKEDID_3);
				}
				else {
					query.append(_FINDER_COLUMN_LINKEDID_LINKEDID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (linkedId != null) {
					qPos.add(linkedId);
				}

				List<UserLinkedIn> list = q.list();

				result = list;

				UserLinkedIn userLinkedIn = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LINKEDID,
						finderArgs, list);
				}
				else {
					userLinkedIn = list.get(0);

					cacheResult(userLinkedIn);

					if ((userLinkedIn.getLinkedId() == null) ||
							!userLinkedIn.getLinkedId().equals(linkedId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LINKEDID,
							finderArgs, userLinkedIn);
					}
				}

				return userLinkedIn;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_LINKEDID,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (UserLinkedIn)result;
			}
		}
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
	public UserLinkedIn findByL_C_Id(long companyId, String linkedId)
		throws NoSuchUserLinkedInException, SystemException {
		UserLinkedIn userLinkedIn = fetchByL_C_Id(companyId, linkedId);

		if (userLinkedIn == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", linkedId=");
			msg.append(linkedId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchUserLinkedInException(msg.toString());
		}

		return userLinkedIn;
	}

	/**
	 * Returns the user linked in where companyId = &#63; and linkedId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param linkedId the linked ID
	 * @return the matching user linked in, or <code>null</code> if a matching user linked in could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserLinkedIn fetchByL_C_Id(long companyId, String linkedId)
		throws SystemException {
		return fetchByL_C_Id(companyId, linkedId, true);
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
	public UserLinkedIn fetchByL_C_Id(long companyId, String linkedId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { companyId, linkedId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_L_C_ID,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_USERLINKEDIN_WHERE);

			query.append(_FINDER_COLUMN_L_C_ID_COMPANYID_2);

			if (linkedId == null) {
				query.append(_FINDER_COLUMN_L_C_ID_LINKEDID_1);
			}
			else {
				if (linkedId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_L_C_ID_LINKEDID_3);
				}
				else {
					query.append(_FINDER_COLUMN_L_C_ID_LINKEDID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (linkedId != null) {
					qPos.add(linkedId);
				}

				List<UserLinkedIn> list = q.list();

				result = list;

				UserLinkedIn userLinkedIn = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_L_C_ID,
						finderArgs, list);
				}
				else {
					userLinkedIn = list.get(0);

					cacheResult(userLinkedIn);

					if ((userLinkedIn.getCompanyId() != companyId) ||
							(userLinkedIn.getLinkedId() == null) ||
							!userLinkedIn.getLinkedId().equals(linkedId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_L_C_ID,
							finderArgs, userLinkedIn);
					}
				}

				return userLinkedIn;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_L_C_ID,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (UserLinkedIn)result;
			}
		}
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
	public UserLinkedIn findByU_L(long companyId, long userId, String linkedId)
		throws NoSuchUserLinkedInException, SystemException {
		UserLinkedIn userLinkedIn = fetchByU_L(companyId, userId, linkedId);

		if (userLinkedIn == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(", linkedId=");
			msg.append(linkedId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchUserLinkedInException(msg.toString());
		}

		return userLinkedIn;
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
	public UserLinkedIn fetchByU_L(long companyId, long userId, String linkedId)
		throws SystemException {
		return fetchByU_L(companyId, userId, linkedId, true);
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
	public UserLinkedIn fetchByU_L(long companyId, long userId,
		String linkedId, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { companyId, userId, linkedId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_L,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_USERLINKEDIN_WHERE);

			query.append(_FINDER_COLUMN_U_L_COMPANYID_2);

			query.append(_FINDER_COLUMN_U_L_USERID_2);

			if (linkedId == null) {
				query.append(_FINDER_COLUMN_U_L_LINKEDID_1);
			}
			else {
				if (linkedId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_U_L_LINKEDID_3);
				}
				else {
					query.append(_FINDER_COLUMN_U_L_LINKEDID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(userId);

				if (linkedId != null) {
					qPos.add(linkedId);
				}

				List<UserLinkedIn> list = q.list();

				result = list;

				UserLinkedIn userLinkedIn = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_L,
						finderArgs, list);
				}
				else {
					userLinkedIn = list.get(0);

					cacheResult(userLinkedIn);

					if ((userLinkedIn.getCompanyId() != companyId) ||
							(userLinkedIn.getUserId() != userId) ||
							(userLinkedIn.getLinkedId() == null) ||
							!userLinkedIn.getLinkedId().equals(linkedId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_L,
							finderArgs, userLinkedIn);
					}
				}

				return userLinkedIn;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_L,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (UserLinkedIn)result;
			}
		}
	}

	/**
	 * Returns all the user linked ins.
	 *
	 * @return the user linked ins
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserLinkedIn> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<UserLinkedIn> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	public List<UserLinkedIn> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<UserLinkedIn> list = (List<UserLinkedIn>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_USERLINKEDIN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_USERLINKEDIN;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<UserLinkedIn>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<UserLinkedIn>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes the user linked in where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUserId(long userId)
		throws NoSuchUserLinkedInException, SystemException {
		UserLinkedIn userLinkedIn = findByUserId(userId);

		remove(userLinkedIn);
	}

	/**
	 * Removes the user linked in where linkedId = &#63; from the database.
	 *
	 * @param linkedId the linked ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByLinkedId(String linkedId)
		throws NoSuchUserLinkedInException, SystemException {
		UserLinkedIn userLinkedIn = findByLinkedId(linkedId);

		remove(userLinkedIn);
	}

	/**
	 * Removes the user linked in where companyId = &#63; and linkedId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param linkedId the linked ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByL_C_Id(long companyId, String linkedId)
		throws NoSuchUserLinkedInException, SystemException {
		UserLinkedIn userLinkedIn = findByL_C_Id(companyId, linkedId);

		remove(userLinkedIn);
	}

	/**
	 * Removes the user linked in where companyId = &#63; and userId = &#63; and linkedId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param linkedId the linked ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByU_L(long companyId, long userId, String linkedId)
		throws NoSuchUserLinkedInException, SystemException {
		UserLinkedIn userLinkedIn = findByU_L(companyId, userId, linkedId);

		remove(userLinkedIn);
	}

	/**
	 * Removes all the user linked ins from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (UserLinkedIn userLinkedIn : findAll()) {
			remove(userLinkedIn);
		}
	}

	/**
	 * Returns the number of user linked ins where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user linked ins
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERLINKEDIN_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of user linked ins where linkedId = &#63;.
	 *
	 * @param linkedId the linked ID
	 * @return the number of matching user linked ins
	 * @throws SystemException if a system exception occurred
	 */
	public int countByLinkedId(String linkedId) throws SystemException {
		Object[] finderArgs = new Object[] { linkedId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_LINKEDID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERLINKEDIN_WHERE);

			if (linkedId == null) {
				query.append(_FINDER_COLUMN_LINKEDID_LINKEDID_1);
			}
			else {
				if (linkedId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_LINKEDID_LINKEDID_3);
				}
				else {
					query.append(_FINDER_COLUMN_LINKEDID_LINKEDID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (linkedId != null) {
					qPos.add(linkedId);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_LINKEDID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of user linked ins where companyId = &#63; and linkedId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param linkedId the linked ID
	 * @return the number of matching user linked ins
	 * @throws SystemException if a system exception occurred
	 */
	public int countByL_C_Id(long companyId, String linkedId)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, linkedId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_L_C_ID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_USERLINKEDIN_WHERE);

			query.append(_FINDER_COLUMN_L_C_ID_COMPANYID_2);

			if (linkedId == null) {
				query.append(_FINDER_COLUMN_L_C_ID_LINKEDID_1);
			}
			else {
				if (linkedId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_L_C_ID_LINKEDID_3);
				}
				else {
					query.append(_FINDER_COLUMN_L_C_ID_LINKEDID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (linkedId != null) {
					qPos.add(linkedId);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_L_C_ID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
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
	public int countByU_L(long companyId, long userId, String linkedId)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, userId, linkedId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_U_L,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_USERLINKEDIN_WHERE);

			query.append(_FINDER_COLUMN_U_L_COMPANYID_2);

			query.append(_FINDER_COLUMN_U_L_USERID_2);

			if (linkedId == null) {
				query.append(_FINDER_COLUMN_U_L_LINKEDID_1);
			}
			else {
				if (linkedId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_U_L_LINKEDID_3);
				}
				else {
					query.append(_FINDER_COLUMN_U_L_LINKEDID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(userId);

				if (linkedId != null) {
					qPos.add(linkedId);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_L, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of user linked ins.
	 *
	 * @return the number of user linked ins
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_USERLINKEDIN);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the user linked in persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.linkedin.model.UserLinkedIn")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<UserLinkedIn>> listenersList = new ArrayList<ModelListener<UserLinkedIn>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<UserLinkedIn>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(UserLinkedInImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = UserLinkedInPersistence.class)
	protected UserLinkedInPersistence userLinkedInPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_USERLINKEDIN = "SELECT userLinkedIn FROM UserLinkedIn userLinkedIn";
	private static final String _SQL_SELECT_USERLINKEDIN_WHERE = "SELECT userLinkedIn FROM UserLinkedIn userLinkedIn WHERE ";
	private static final String _SQL_COUNT_USERLINKEDIN = "SELECT COUNT(userLinkedIn) FROM UserLinkedIn userLinkedIn";
	private static final String _SQL_COUNT_USERLINKEDIN_WHERE = "SELECT COUNT(userLinkedIn) FROM UserLinkedIn userLinkedIn WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "userLinkedIn.userId = ?";
	private static final String _FINDER_COLUMN_LINKEDID_LINKEDID_1 = "userLinkedIn.linkedId IS NULL";
	private static final String _FINDER_COLUMN_LINKEDID_LINKEDID_2 = "userLinkedIn.linkedId = ?";
	private static final String _FINDER_COLUMN_LINKEDID_LINKEDID_3 = "(userLinkedIn.linkedId IS NULL OR userLinkedIn.linkedId = ?)";
	private static final String _FINDER_COLUMN_L_C_ID_COMPANYID_2 = "userLinkedIn.companyId = ? AND ";
	private static final String _FINDER_COLUMN_L_C_ID_LINKEDID_1 = "userLinkedIn.linkedId IS NULL";
	private static final String _FINDER_COLUMN_L_C_ID_LINKEDID_2 = "userLinkedIn.linkedId = ?";
	private static final String _FINDER_COLUMN_L_C_ID_LINKEDID_3 = "(userLinkedIn.linkedId IS NULL OR userLinkedIn.linkedId = ?)";
	private static final String _FINDER_COLUMN_U_L_COMPANYID_2 = "userLinkedIn.companyId = ? AND ";
	private static final String _FINDER_COLUMN_U_L_USERID_2 = "userLinkedIn.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_L_LINKEDID_1 = "userLinkedIn.linkedId IS NULL";
	private static final String _FINDER_COLUMN_U_L_LINKEDID_2 = "userLinkedIn.linkedId = ?";
	private static final String _FINDER_COLUMN_U_L_LINKEDID_3 = "(userLinkedIn.linkedId IS NULL OR userLinkedIn.linkedId = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "userLinkedIn.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UserLinkedIn exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UserLinkedIn exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(UserLinkedInPersistenceImpl.class);
	private static UserLinkedIn _nullUserLinkedIn = new UserLinkedInImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<UserLinkedIn> toCacheModel() {
				return _nullUserLinkedInCacheModel;
			}
		};

	private static CacheModel<UserLinkedIn> _nullUserLinkedInCacheModel = new CacheModel<UserLinkedIn>() {
			public UserLinkedIn toEntityModel() {
				return _nullUserLinkedIn;
			}
		};
}