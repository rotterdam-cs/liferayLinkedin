package com.liferay.portal.kernel.twitter;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PropsKeysExt;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValuesExt;

/**
 * @author V. Koshelenko
 */
public class TwitterConnectImpl implements TwitterConnect {

    @Override
    public String getAppId(long companyId) throws SystemException {
        return PrefsPropsUtil.getString(companyId,
                PropsKeysExt.TWITTER_CONNECT_APP_ID,
                PropsValuesExt.TWITTER_CONNECT_APP_ID);
    }

    @Override
    public String getAppSecret(long companyId) throws SystemException {
        return PrefsPropsUtil.getString(companyId,
                PropsKeysExt.TWITTER_CONNECT_APP_SECRET,
                PropsValuesExt.TWITTER_CONNECT_APP_SECRET);
    }

    @Override
    public String getRedirectURL(long companyId) throws SystemException {
        return PrefsPropsUtil.getString(companyId,
                PropsKeysExt.TWITTER_REDIRECT_URL,
                PropsValuesExt.TWITTER_REDIRECT_URL);
    }

    @Override
    public boolean isEnabled(long companyId) throws SystemException {
        return PrefsPropsUtil.getBoolean(companyId,
                PropsKeysExt.TWITTER_CONNECT_AUTH_ENABLED,
                PropsValuesExt.TWITTER_CONNECT_AUTH_ENABLED);
    }
}
