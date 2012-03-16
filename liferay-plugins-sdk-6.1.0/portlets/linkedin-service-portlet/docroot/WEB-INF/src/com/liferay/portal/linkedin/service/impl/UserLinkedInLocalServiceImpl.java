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

package com.liferay.portal.linkedin.service.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.linkedin.NoSuchUserLinkedInException;
import com.liferay.portal.linkedin.model.UserLinkedIn;
import com.liferay.portal.linkedin.service.base.UserLinkedInLocalServiceBaseImpl;

/**
 * The implementation of the user linked in local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.liferay.portal.linkedin.service.UserLinkedInLocalService}
 * interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 * 
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.linkedin.service.base.UserLinkedInLocalServiceBaseImpl
 * @see com.liferay.portal.linkedin.service.UserLinkedInLocalServiceUtil
 */
public class UserLinkedInLocalServiceImpl extends
		UserLinkedInLocalServiceBaseImpl {
	public UserLinkedIn findByL_C_Id(long companyId, String linkedId)
			throws NoSuchUserLinkedInException, SystemException {
		return userLinkedInPersistence.findByL_C_Id(companyId, linkedId);
	}

	public UserLinkedIn findByLinkedId(String linkedId)
			throws NoSuchUserLinkedInException, SystemException {
		return userLinkedInPersistence.findByLinkedId(linkedId);
	}
}