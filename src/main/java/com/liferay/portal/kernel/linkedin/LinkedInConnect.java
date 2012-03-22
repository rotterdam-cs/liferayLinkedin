package com.liferay.portal.kernel.linkedin;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author V. Koshelenko
 */
public interface LinkedInConnect {

	public String getAppId(long companyId) throws SystemException;

	public String getAppSecret(long companyId) throws SystemException;

    public String getRedirectURL(long companyId) throws SystemException;

    public boolean isEnabled(long companyId) throws SystemException;


}

