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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class UserLinkedInSoap implements Serializable {
	public static UserLinkedInSoap toSoapModel(UserLinkedIn model) {
		UserLinkedInSoap soapModel = new UserLinkedInSoap();

		soapModel.setId(model.getId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setLinkedId(model.getLinkedId());

		return soapModel;
	}

	public static UserLinkedInSoap[] toSoapModels(UserLinkedIn[] models) {
		UserLinkedInSoap[] soapModels = new UserLinkedInSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserLinkedInSoap[][] toSoapModels(UserLinkedIn[][] models) {
		UserLinkedInSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserLinkedInSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserLinkedInSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserLinkedInSoap[] toSoapModels(List<UserLinkedIn> models) {
		List<UserLinkedInSoap> soapModels = new ArrayList<UserLinkedInSoap>(models.size());

		for (UserLinkedIn model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserLinkedInSoap[soapModels.size()]);
	}

	public UserLinkedInSoap() {
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long pk) {
		setId(pk);
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getLinkedId() {
		return _linkedId;
	}

	public void setLinkedId(String linkedId) {
		_linkedId = linkedId;
	}

	private long _id;
	private long _companyId;
	private long _userId;
	private String _linkedId;
}