package com.liferay.portal.kernel.twitter;

import com.liferay.portal.kernel.exception.SystemException;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.RequestToken;

import java.util.HashMap;
import java.util.Map;

/**
 * @author V. Koshelenko
 */
public class TwitterConnectUtil {

    private static Map<Long,TwitterConnect> twitterConnects;
    private static Map<Long,User> twitterUsers;

    public static Twitter getTwitter(long companyId) throws SystemException {
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(
                getAppId(companyId),
                getAppSecret(companyId)
        );
        return twitter;
    }

    public static RequestToken getTwitterRequestToken(long companyId, String url) throws SystemException, TwitterException {
        return getTwitter(companyId).getOAuthRequestToken(url);
    }

    public static RequestToken getTwitterRequestToken(Twitter twitter, String url) throws SystemException, TwitterException {
        return twitter.getOAuthRequestToken(url);
    }

    public static String getAppId(long companyId) throws SystemException {
        return getTwitterConnect(companyId).getAppId(companyId);
    }

    public static String getAppSecret(long companyId) throws SystemException {
        return getTwitterConnect(companyId).getAppSecret(companyId);
    }

    public static String getRedirectURL(long companyId) throws SystemException {
        return getTwitterConnect(companyId).getRedirectURL(companyId);
    }

    public static boolean isEnabled(long companyId) throws SystemException {
        return getTwitterConnect(companyId).isEnabled(companyId);
    }

    public static User getLoggedUser(long companyId) {
        if (twitterUsers == null || !twitterUsers.containsKey(companyId)) {
            return null;
        } else {
            return twitterUsers.get(companyId);
        }
    }

    public static void addLoggedUser(long companyId, User user) {
        if (twitterUsers == null) {
            twitterUsers = new HashMap<Long, User>();
        }
        if (twitterUsers.containsKey(companyId)) {
            twitterUsers.remove(companyId);
        }
        twitterUsers.put(companyId, user);
    }

    private static TwitterConnect getTwitterConnect(long companyId){
        if (twitterConnects == null || !twitterConnects.containsKey(companyId)) {
            if (twitterConnects == null) {
                twitterConnects = new HashMap<Long, TwitterConnect>();
            }
            TwitterConnect twitterConnect = new TwitterConnectImpl();
            twitterConnects.put(companyId, twitterConnect);
            return twitterConnect;
        } else {
            return twitterConnects.get(companyId);
        }
    }
}
