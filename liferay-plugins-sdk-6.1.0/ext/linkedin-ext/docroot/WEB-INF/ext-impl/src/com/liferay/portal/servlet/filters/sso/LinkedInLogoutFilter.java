/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.servlet.filters.sso;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.linkedin.LinkedInUtil;
import com.liferay.portal.linkedin.LinkedInWebKeys;
import com.liferay.portal.servlet.filters.BasePortalFilter;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Rajesh
 *  
 * Custom implementation to support LR working as CAS proxy. Here we mostly
 * copied part of standard Cas20ProxyReceivingTicketValidationFilter class from
 * cas-client-core package.
 * 
 */
public class LinkedInLogoutFilter extends BasePortalFilter {
	public static String LOGIN = LinkedInLogoutFilter.class.getName() + "LOGIN";

	protected Log getLog() {
		return log;
	}

	protected void processFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws SystemException, IOException {

		long companyId = PortalUtil.getCompanyId(request);

		if (LinkedInUtil.isEnabled(companyId)) {
			HttpSession session = request.getSession();

			String pathInfo = request.getPathInfo();

			log.debug("Path: " + pathInfo);

			if (pathInfo.indexOf("/portal/logout") != -1
					|| pathInfo.indexOf("/portal/expire_session") != -1) {

				Token accessToken = (Token) request.getSession().getAttribute(
						LinkedInWebKeys.LINKEDIN_ACCESS_TOKEN);

				if (accessToken != null) {
					
					System.out.println("processFilter - accessToken="
							+ accessToken);

					LinkedInUtil.invalidateOauthToken(companyId, accessToken);

					request.getSession().removeAttribute(
							LinkedInWebKeys.LINKEDIN_ACCESS_TOKEN);
					request.getSession().removeAttribute(
							LinkedInWebKeys.LINKEDIN_USER_ID);

					Cookie killMyCookie = new Cookie("linkedin_oauth_"
							+ LinkedInUtil.getAppId(companyId), null);
					killMyCookie.setMaxAge(0);
					killMyCookie.setPath("/");
					killMyCookie.setSecure(true);

					response.addCookie(killMyCookie);

					session.invalidate();
				}
			}
		}
		try {
			processFilter(LinkedInLogoutFilter.class, request, response,
					filterChain);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	private static Log log = LogFactoryUtil.getLog(LinkedInLogoutFilter.class);

}