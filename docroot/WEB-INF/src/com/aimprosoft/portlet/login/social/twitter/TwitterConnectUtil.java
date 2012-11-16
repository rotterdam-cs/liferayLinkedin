package com.aimprosoft.portlet.login.social.twitter;

import com.liferay.portal.kernel.exception.SystemException;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.RequestToken;

import javax.portlet.PortletRequest;

/**
 * @author V. Koshelenko
 */
public class TwitterConnectUtil {

    private static TwitterConnect twitterConnect;
    private static User twitterUser;

    public static Twitter getTwitter(PortletRequest portletRequest) throws SystemException {
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(
                getAppId(portletRequest),
                getAppSecret(portletRequest)
        );
        return twitter;
    }

    public static RequestToken getTwitterRequestToken(PortletRequest portletRequest, String url) throws SystemException, TwitterException {
        return getTwitter(portletRequest).getOAuthRequestToken(url);
    }

    public static RequestToken getTwitterRequestToken(Twitter twitter, String url) throws SystemException, TwitterException {
        return twitter.getOAuthRequestToken(url);
    }

    public static String getAppId(PortletRequest portletRequest) throws SystemException {
        return getTwitterConnect().getAppId(portletRequest);
    }

    public static String getAppSecret(PortletRequest portletRequest) throws SystemException {
        return getTwitterConnect().getAppSecret(portletRequest);
    }

    public static String getRedirectURL(PortletRequest portletRequest) throws SystemException {
        return getTwitterConnect().getRedirectURL(portletRequest);
    }

    public static boolean isEnabled(PortletRequest portletRequest) throws SystemException {
        return getTwitterConnect().isEnabled(portletRequest);
    }

    public static void setTwitterUser(User user){
        TwitterConnectUtil.twitterUser = user;
    }

    public static User getTwitterUser() {
        return TwitterConnectUtil.twitterUser;
    }

    private static TwitterConnect getTwitterConnect(){
        if (TwitterConnectUtil.twitterConnect == null) {
            TwitterConnectUtil.twitterConnect = new TwitterConnectImpl();
        }
        return TwitterConnectUtil.twitterConnect;
    }
}
