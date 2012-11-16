package com.aimprosoft.portlet.login.social.linkedin;


import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Person;
import com.liferay.portal.kernel.exception.SystemException;

import javax.portlet.PortletRequest;

/**
 * @author V. Koshelenko
 */
public class LinkedInConnectUtil {

    private static LinkedInOAuthService oAuthService;

    private static LinkedInAccessToken accessToken;

    private static LinkedInConnect linkedInConnect;

    private static Person linkedInPerson;

    public static LinkedInAccessToken getAccessToken() throws SystemException {
        return accessToken;
    }

    public static LinkedInAccessToken getAccessToken(LinkedInRequestToken requestToken, String authVerifier, PortletRequest request) throws SystemException {
        if (LinkedInConnectUtil.accessToken == null) {
            LinkedInConnectUtil.accessToken = getLinkedInOAuthService(request).getOAuthAccessToken(requestToken,authVerifier);
        }
        return LinkedInConnectUtil.accessToken;
    }

    public static void setAccessToken(LinkedInAccessToken accessToken) {
        LinkedInConnectUtil.accessToken = accessToken;
    }

    public static LinkedInOAuthService getLinkedInOAuthService(PortletRequest request) throws SystemException {
        if (LinkedInConnectUtil.oAuthService == null) {
            LinkedInConnectUtil.oAuthService = LinkedInOAuthServiceFactory.getInstance().
                    createLinkedInOAuthService(getAppId(request), getAppSecret(request));
        }
        return LinkedInConnectUtil.oAuthService;
    }

    public static Person getLinkedInPerson() {
        return LinkedInConnectUtil.linkedInPerson;
    }

    public static void setLinkedInPerson(Person person) {
        LinkedInConnectUtil.linkedInPerson = person;
    }

    public static LinkedInRequestToken getLinkedInRequestToken(PortletRequest request) throws SystemException {
        return getLinkedInOAuthService(request).getOAuthRequestToken(getRedirectURL(request));
    }

    public static LinkedInRequestToken getLinkedInRequestToken(String redirectURL, PortletRequest request) throws SystemException {
        return getLinkedInOAuthService(request).getOAuthRequestToken(redirectURL);
    }

	public static String getAppId(PortletRequest request) throws SystemException {
		return getLinkedInConnect().getAppId(request);
	}

	public static String getAppSecret(PortletRequest request) throws SystemException {
		return getLinkedInConnect().getAppSecret(request);
	}

	public static String getRedirectURL(PortletRequest request) throws SystemException {
		return getLinkedInConnect().getRedirectURL(request);
	}

    public static boolean isEnabled(PortletRequest request) throws SystemException {
        return getLinkedInConnect().isEnabled(request);
    }

    public static LinkedInConnect getLinkedInConnect() {
        if (LinkedInConnectUtil.linkedInConnect == null) {
            LinkedInConnectUtil.linkedInConnect = new LinkedInConnectImpl();
        }
        return LinkedInConnectUtil.linkedInConnect;
    }

    public static void setLinkedInConnect(LinkedInConnect linkedInConnect) {
        LinkedInConnectUtil.linkedInConnect = linkedInConnect;
    }

}
