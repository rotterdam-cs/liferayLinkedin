package com.aimprosoft.portlet.login.social.linkedin;

import com.aimprosoft.portlet.login.model.LoginPreferences;
import com.aimprosoft.portlet.login.util.LoginPreferencesUtil;
import com.liferay.portal.kernel.exception.SystemException;

import javax.portlet.PortletRequest;

/**
 * @author V. Koshelenko
 */
public class LinkedInConnectImpl implements LinkedInConnect {

    @Override
    public String getAppId(PortletRequest request) throws SystemException {
        LoginPreferences preferences = LoginPreferencesUtil.getPreferences(request);
        return preferences.getLinkedInAppId();
    }

    @Override
    public String getAppSecret(PortletRequest request) throws SystemException {
        LoginPreferences preferences = LoginPreferencesUtil.getPreferences(request);
        return preferences.getLinkedInAppSecret();
    }

    @Override
    public String getRedirectURL(PortletRequest request) throws SystemException {
        LoginPreferences preferences = LoginPreferencesUtil.getPreferences(request);
        return preferences.getLinkedInRedirectURL();
    }

    @Override
    public boolean isEnabled(PortletRequest request) throws SystemException {
        LoginPreferences preferences = LoginPreferencesUtil.getPreferences(request);
        return preferences.getLinkedInIsEnabled();
    }
}
