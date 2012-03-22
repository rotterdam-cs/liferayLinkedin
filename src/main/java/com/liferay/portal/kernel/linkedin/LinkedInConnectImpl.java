package com.liferay.portal.kernel.linkedin;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PropsKeysExt;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValuesExt;

/**
 * @author V. Koshelenko
 */
public class LinkedInConnectImpl implements LinkedInConnect {

    @Override
    public String getAppId(long companyId) throws SystemException {
        return PrefsPropsUtil.getString(companyId,
                PropsKeysExt.LINKEDIN_CONNECT_APP_ID,
                PropsValuesExt.LINKEDIN_CONNECT_APP_ID);
    }

    @Override
    public String getAppSecret(long companyId) throws SystemException {
        return PrefsPropsUtil.getString(companyId,
                PropsKeysExt.LINKEDIN_CONNECT_APP_SECRET,
                PropsValuesExt.LINKEDIN_CONNECT_APP_SECRET);
    }

    @Override
    public String getRedirectURL(long companyId) throws SystemException {
        return PrefsPropsUtil.getString(companyId,
                PropsKeysExt.LINKEDIN_REDIRECT_URL,
                PropsValuesExt.LINKEDIN_REDIRECT_URL);
    }

    @Override
    public boolean isEnabled(long companyId) throws SystemException {
        return PrefsPropsUtil.getBoolean(companyId,
                PropsKeysExt.LINKEDIN_CONNECT_AUTH_ENABLED,
                PropsValuesExt.LINKEDIN_CONNECT_AUTH_ENABLED);
    }
}
