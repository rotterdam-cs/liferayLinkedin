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

package com.liferay.portal.linkedin.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.linkedin.model.UserLinkedIn;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing UserLinkedIn in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UserLinkedIn
 * @generated
 */
public class UserLinkedInCacheModel implements CacheModel<UserLinkedIn>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{id=");
		sb.append(id);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", linkedId=");
		sb.append(linkedId);
		sb.append("}");

		return sb.toString();
	}

	public UserLinkedIn toEntityModel() {
		UserLinkedInImpl userLinkedInImpl = new UserLinkedInImpl();

		userLinkedInImpl.setId(id);
		userLinkedInImpl.setCompanyId(companyId);
		userLinkedInImpl.setUserId(userId);

		if (linkedId == null) {
			userLinkedInImpl.setLinkedId(StringPool.BLANK);
		}
		else {
			userLinkedInImpl.setLinkedId(linkedId);
		}

		userLinkedInImpl.resetOriginalValues();

		return userLinkedInImpl;
	}

	public long id;
	public long companyId;
	public long userId;
	public String linkedId;
}