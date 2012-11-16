package com.aimprosoft.portlet.login.social.twitter;

import com.aimprosoft.portlet.login.model.LoginPreferences;
import com.aimprosoft.portlet.login.util.LoginPreferencesUtil;
import com.liferay.portal.kernel.exception.SystemException;

import javax.portlet.PortletRequest;

/**
 * @author V. Koshelenko
 */
public class TwitterConnectImpl implements TwitterConnect {

    @Override
    public String getAppId(PortletRequest request) throws SystemException {
        LoginPreferences preferences = LoginPreferencesUtil.getPreferences(request);
        return preferences.getTwitterAppId();
    }

    @Override
    public String getAppSecret(PortletRequest request) throws SystemException {
        LoginPreferences preferences = LoginPreferencesUtil.getPreferences(request);
        return preferences.getTwitterAppSecret();
    }

    @Override
    public String getRedirectURL(PortletRequest request) throws SystemException {
        LoginPreferences preferences = LoginPreferencesUtil.getPreferences(request);
        return preferences.getTwitterRedirectURL();
    }

    @Override
    public boolean isEnabled(PortletRequest request) throws SystemException {
        LoginPreferences preferences = LoginPreferencesUtil.getPreferences(request);
        return preferences.getTwitterIsEnabled();
    }
}
