package com.aimprosoft.portlet.login.util;

import com.aimprosoft.common.config.ApplicationPropsBean;
import com.aimprosoft.common.spring.ObjectFactory;
import com.aimprosoft.portlet.login.model.LoginConstants;
import com.aimprosoft.portlet.login.model.LoginPreferences;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

public class LoginPreferencesUtil {

    private static final Logger _logger = Logger.getLogger(LoginPreferencesUtil.class);
    private static ApplicationPropsBean props = ObjectFactory.getBean(ApplicationPropsBean.class);

    public static LoginPreferences getPreferences(PortletRequest request){

        PortletPreferences preferences = request.getPreferences();

        LoginPreferences loginPreferences = new LoginPreferences();

        loginPreferences.setLinkedInIsEnabled(Boolean.parseBoolean(preferences.getValue(LoginConstants.LINKED_IN_IS_ENABLED, props.getLinkedInDefaultAuthEnabled())));
        loginPreferences.setLinkedInAppId(preferences.getValue(LoginConstants.LINKED_IN_APP_ID, props.getLinkedInDefaultAppId()));
        loginPreferences.setLinkedInAppSecret(preferences.getValue(LoginConstants.LINKED_IN_APP_SECRET, props.getLinkedInDefaultAppSecret()));
        loginPreferences.setLinkedInRedirectURL(preferences.getValue(LoginConstants.LINKED_IN_REDIRECT_URL, props.getLinkedInDefaultRedirectUrl()));

        loginPreferences.setTwitterIsEnabled(Boolean.parseBoolean(preferences.getValue(LoginConstants.TWITTER_IS_ENABLED, props.getTwitterDefaultAuthEnabled())));
        loginPreferences.setTwitterAppId(preferences.getValue(LoginConstants.TWITTER_APP_ID, props.getTwitterDefaultAppId()));
        loginPreferences.setTwitterAppSecret(preferences.getValue(LoginConstants.TWITTER_APP_SECRET, props.getTwitterDefaultAppSecret()));
        loginPreferences.setTwitterRedirectURL(preferences.getValue(LoginConstants.TWITTER_REDIRECT_URL, props.getTwitterDefaultRedirectUrl()));

        loginPreferences.setEmailFromName(preferences.getValue(LoginConstants.EMAIL_FROM_NAME, props.getEmailFromNameDefault()));
        loginPreferences.setEmailFromAddress(preferences.getValue(LoginConstants.EMAIL_FROM_ADDRESS, props.getEmailFromAddressDefault()));

        return loginPreferences;
    }

    public static void storePreferences(PortletRequest request, LoginPreferences loginPreferences) {

        try {
            PortletPreferences preferences = request.getPreferences();

            preferences.setValue(LoginConstants.LINKED_IN_IS_ENABLED, String.valueOf(loginPreferences.getLinkedInIsEnabled()));
            preferences.setValue(LoginConstants.LINKED_IN_APP_ID, loginPreferences.getLinkedInAppId());
            preferences.setValue(LoginConstants.LINKED_IN_APP_SECRET, loginPreferences.getLinkedInAppSecret());
            preferences.setValue(LoginConstants.LINKED_IN_REDIRECT_URL, loginPreferences.getLinkedInRedirectURL());

            preferences.setValue(LoginConstants.TWITTER_IS_ENABLED, String.valueOf(loginPreferences.getTwitterIsEnabled()));
            preferences.setValue(LoginConstants.TWITTER_APP_ID, loginPreferences.getTwitterAppId());
            preferences.setValue(LoginConstants.TWITTER_APP_SECRET, loginPreferences.getTwitterAppSecret());
            preferences.setValue(LoginConstants.TWITTER_REDIRECT_URL, loginPreferences.getTwitterRedirectURL());

            preferences.setValue(LoginConstants.EMAIL_FROM_NAME, loginPreferences.getEmailFromName());
            preferences.setValue(LoginConstants.EMAIL_FROM_ADDRESS, loginPreferences.getEmailFromAddress());

            preferences.store();

        } catch (Exception e) {
            _logger.error("Can not store loginPreferences : " + e.getMessage());
        }
    }

    public static String getPreference(PortletRequest request, String preferenceName, String defaultValue) {
        PortletPreferences preferences = request.getPreferences();
        return preferences.getValue(preferenceName, defaultValue);
    }

    public static String getPreference(PortletRequest request, String preferenceName) {
        return getPreference(request, preferenceName, StringUtils.EMPTY);
    }
}
