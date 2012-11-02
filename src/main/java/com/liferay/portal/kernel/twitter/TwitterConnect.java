package com.liferay.portal.kernel.twitter;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author V. Koshelenko
 */
public interface TwitterConnect {

    public String getAppId(long companyId) throws SystemException;

    public String getAppSecret(long companyId) throws SystemException;

    public String getRedirectURL(long companyId) throws SystemException;

    public boolean isEnabled(long companyId) throws SystemException;

}
