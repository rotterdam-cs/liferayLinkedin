package com.liferay.portal.kernel.linkedin;


import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Person;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author V. Koshelenko
 */
public class LinkedInConnectUtil {

    private static Map<Long,LinkedInOAuthService> oAuthServices;

    private static Map<Long,LinkedInAccessToken> accessTokens;

    private static Map<Long,LinkedInConnect> linkedInConnects;

    private static Map<Long,Person> linkedInPersons;

    public static LinkedInAccessToken getAccessToken(long companyId) throws SystemException {
        return (accessTokens != null) ?
                accessTokens.get(companyId) :
                null;
    }

    public static LinkedInAccessToken getAccessToken(long companyId, LinkedInRequestToken requestToken, String authVerifier) throws SystemException {
        if (accessTokens == null || !accessTokens.containsKey(companyId))
            return getLinkedInOAuthService(companyId).getOAuthAccessToken(requestToken,authVerifier);
        else
            return accessTokens.get(companyId);
    }

    public static void setAccessToken(long companyId, LinkedInAccessToken accessToken) {
        if (accessTokens == null)
            accessTokens = new HashMap<Long, LinkedInAccessToken>();
        accessTokens.remove(companyId);
        accessTokens.put(companyId, accessToken);
    }

    public static LinkedInOAuthService getLinkedInOAuthService(long companyId) throws SystemException {
        if (oAuthServices == null || !oAuthServices.containsKey(companyId)) {
            LinkedInOAuthService oAuthService = LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(getAppId(companyId), getAppSecret(companyId));
            if (oAuthServices == null)
                oAuthServices = new HashMap<Long, LinkedInOAuthService>();
            oAuthServices.put(companyId, oAuthService);
            return oAuthService;
        } else
            return oAuthServices.get(companyId);
    }

    public static Person getLinkedInPerson(long companyId) {
        return (linkedInPersons != null) ?
                linkedInPersons.get(companyId) :
                null;
    }

    public static void setLinkedInPerson(long companyId, Person person) {
        if (linkedInPersons == null)
            linkedInPersons = new HashMap<Long, Person>();
        linkedInPersons.remove(companyId);
        linkedInPersons.put(companyId, person);
    }

    public static LinkedInRequestToken getLinkedInRequestToken(long companyId) throws SystemException {
        return getLinkedInOAuthService(companyId).getOAuthRequestToken(getRedirectURL(companyId));
    }

    public static LinkedInRequestToken getLinkedInRequestToken(long companyId, String redirectURL) throws SystemException {
        return getLinkedInOAuthService(companyId).getOAuthRequestToken(redirectURL);
    }

	public static String getAppId(long companyId) throws SystemException {
		return getLinkedInConnect(companyId).getAppId(companyId);
	}

	public static String getAppSecret(long companyId) throws SystemException {
		return getLinkedInConnect(companyId).getAppSecret(companyId);
	}

	public static String getRedirectURL(long companyId) throws SystemException {
		return getLinkedInConnect(companyId).getRedirectURL(companyId);
	}

    public static boolean isEnabled(long companyId) throws SystemException {
        return getLinkedInConnect(companyId).isEnabled(companyId);
    }

    public static LinkedInConnect getLinkedInConnect(long companyId) {
        if (linkedInConnects == null || !linkedInConnects.containsKey(companyId)) {
            LinkedInConnect linkedInConnect = new LinkedInConnectImpl();
            if (linkedInConnects == null)
                linkedInConnects = new HashMap<Long, LinkedInConnect>();
            return linkedInConnect;
        } else
            return linkedInConnects.get(companyId);
    }

    public static void setLinkedInConnect(long companyId, LinkedInConnect linkedInConnect) {
        if (linkedInConnects == null)
            linkedInConnects = new HashMap<Long, LinkedInConnect>();
        linkedInConnects.put(companyId, linkedInConnect);
    }

}
