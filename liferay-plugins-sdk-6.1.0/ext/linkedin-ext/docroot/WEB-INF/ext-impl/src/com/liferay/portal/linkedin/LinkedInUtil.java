/**
 * Copyright (c) 2012 Rotterdam CS, Inc. All rights reserved.
 */

package com.liferay.portal.linkedin;

import javax.portlet.PortletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Response;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.model.Verb;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.linkedin.api.LinkedInOAuthRequest;
import com.liferay.portal.linkedin.api.LinkedInOauthService;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PrefsPropsUtil;

/**
 * @author Rajesh
 * 
 */
public class LinkedInUtil {

	public static final String LINKED_CONNECT_APP_ID = "linkedin.connect.app.id";
	public static final String LINKED_CONNECT_APP_SECRET = "linkedin.connect.app.secret";
	public static final String LINKED_CONNECT_AUTH_ENABLED = "linkedin.connect.auth.enabled";
	public static final String LINKED_CONNECT_GRAPH_URL = "linkedin.connect.graph.url";
	public static final String LINKED_CONNECT_OAUTH_AUTH_URL = "linkedin.connect.oauth.auth.url";
	public static final String LINKED_CONNECT_OAUTH_REDIRECT_URL = "linkedin.connect.oauth.redirect.url";
	public static final String LINKED_CONNECT_OAUTH_INVALIDATE_URL = "linkedin.connect.oauth.invalidate.url";
	public static final String LINKED_CONNECT_OAUTH_TOKEN_URL = "linkedin.connect.oauth.token.url";
	public static final String LINKED_CONNECT_VERIFIED_ACCOUNT_REQUIRED = "linkedin.connect.verified.account.required";

	/**
	 * This method read xoauth linkedin token from secure (https) cookie
	 * 
	 * @param request
	 * @param companyId
	 * @return
	 * @throws SystemException
	 */
	public static Token getAccessToken(long companyId,
			HttpServletRequest request) throws SystemException {
		// read oauth2.0 token from secure https cookie
		JSONObject auth2Token = getAuth2Token(request);
		if (auth2Token != null) {
			String xoauth_oauth2_access_token = auth2Token
					.getString("access_token");

			return LinkedInUtil.getAccessToken(companyId,
					xoauth_oauth2_access_token);
		}
		return null;
	}

	public static Token getAccessToken(long companyId,
			String xoauth_oauth2_access_token) throws SystemException {

		Token accessToken = getAuthService(companyId).getAccessToken(
				xoauth_oauth2_access_token);

		return accessToken;
	}

	public static void invalidateOauthToken(long companyId, Token accessToken) {
		try {
			Token _accessToken = new Token(accessToken.getToken(),
					accessToken.getSecret());

			String url = getInvalidateURL(companyId);

			LinkedInOAuthRequest request = new LinkedInOAuthRequest(Verb.GET,
					url);

			if (_log.isDebugEnabled())
				_log.debug("invalidateOauthToken url - " + url);

			getAuthService(companyId).signRequest(_accessToken, request);

			Response response = request.send();

			if (_log.isDebugEnabled())
				_log.debug("invalidateOauthToken json - " + response.getCode());

		} catch (Exception e) {
			_log.error(e);
		}
	}

	public static LinkedInOauthService getAuthService(long companyId)
			throws SystemException {

		String appSecret = getAppSecret(companyId);
		OAuthConfig config = new OAuthConfig(getAppId(companyId), appSecret,
				null, SignatureType.QueryString, null, null);

		DefaultApi10a api = new LinkedInApi();
		LinkedInOauthService auth10aRestService = new LinkedInOauthService(api,
				config);

		return auth10aRestService;
	}

	public static JSONObject getGraphResources(long companyId,
			Token accessToken, String fields) {

		try {
			Token _accessToken = new Token(accessToken.getToken(),
					accessToken.getSecret());

			String url = getGraphURL(companyId);

			if (Validator.isNotNull(fields)) {
				url = url + ":(" + fields + ")?format=json";
			}

			LinkedInOAuthRequest request = new LinkedInOAuthRequest(Verb.GET,
					url);

			if (_log.isDebugEnabled())
				_log.debug("getGraphResources url=" + url);

			getAuthService(companyId).signRequest(_accessToken, request);

			Response response = request.send();

			String json = response.getBody();

			if (_log.isDebugEnabled())
				_log.debug("getGraphResources json=" + json);

			return JSONFactoryUtil.createJSONObject(json);

		} catch (Exception e) {
			_log.error(e);
		}

		return null;
	}

	public static String getProfileImageURL(PortletRequest portletRequest) {
		HttpServletRequest request = PortalUtil
				.getHttpServletRequest(portletRequest);

		request = PortalUtil.getOriginalServletRequest(request);

		HttpSession session = request.getSession();

		String linkedInId = (String) session
				.getAttribute(LinkedInWebKeys.LINKEDIN_USER_ID);

		if (Validator.isNull(linkedInId)) {
			return null;
		}

		/*JSONObject jsonObject = getGraphResources(companyId, token,
				"id,picture-url");*/

		return "";
	}

	public static JSONObject getAuth2Token(HttpServletRequest request)
			throws SystemException {

		long companyId = PortalUtil.getCompanyId(request);
		Cookie[] cookies = request.getCookies();

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(
					"linkedin_oauth_" + LinkedInUtil.getAppId(companyId))) {

				System.out.println("cookies = cookie=" + cookie.getName()
						+ ", value=" + cookie.getValue());

				try {
					String json = HttpUtil.decodeURL(cookie.getValue());
					System.out.println("cookies = json=" + json);

					JSONObject jsonObject = JSONFactoryUtil
							.createJSONObject(json);
					return jsonObject;

				} catch (Exception e) {
					System.out.println(e);
				}
				return null;
			}
		}

		return null;

	}

	public static String getAccessTokenURL(long companyId)
			throws SystemException {
		return PrefsPropsUtil.getString(companyId,
				LINKED_CONNECT_OAUTH_TOKEN_URL);
	}

	public static String getAppId(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(companyId, LINKED_CONNECT_APP_ID);
	}

	public static String getAppSecret(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(companyId, LINKED_CONNECT_APP_SECRET);
	}

	public static String getAuthURL(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(companyId,
				LINKED_CONNECT_OAUTH_AUTH_URL);
	}

	public static String getGraphURL(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(companyId, LINKED_CONNECT_GRAPH_URL);
	}

	public static String getRedirectURL(long companyId) throws SystemException {
		return PrefsPropsUtil.getString(companyId,
				LINKED_CONNECT_OAUTH_REDIRECT_URL);
	}

	public static String getInvalidateURL(long companyId)
			throws SystemException {
		return PrefsPropsUtil.getString(companyId,
				LINKED_CONNECT_OAUTH_INVALIDATE_URL);
	}

	public static boolean isEnabled(long companyId) throws SystemException {
		return PrefsPropsUtil
				.getBoolean(companyId, LINKED_CONNECT_AUTH_ENABLED);
	}

	public static boolean isVerifiedAccountRequired(long companyId)
			throws SystemException {
		return PrefsPropsUtil.getBoolean(companyId,
				LINKED_CONNECT_VERIFIED_ACCOUNT_REQUIRED);
	}

	private static Log _log = LogFactoryUtil.getLog(LinkedInUtil.class);

}
