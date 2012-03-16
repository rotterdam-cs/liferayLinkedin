/**
 * Copyright (c) 2012 Rotterdam CS, Inc. All rights reserved.
 */

package com.liferay.portal.security.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.linkedin.LinkedInUtil;
import com.liferay.portal.linkedin.LinkedInWebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Rajesh
 */
public class LinkedInAutoLogin implements AutoLogin {

	public String[] login(HttpServletRequest request,
			HttpServletResponse response) {

		String[] credentials = null;

		try {
			long companyId = PortalUtil.getCompanyId(request);

			if (!LinkedInUtil.isEnabled(companyId)) {
				return credentials;
			}

			HttpSession session = request.getSession();

			Token accessToken = (Token) request.getSession().getAttribute(
					LinkedInWebKeys.LINKEDIN_ACCESS_TOKEN);

			if (accessToken == null)
				return credentials;

			long linkedUserId = GetterUtil.getLong(session
					.getAttribute(LinkedInWebKeys.LINKEDIN_USER_ID));

			_log.debug("login - linkedUserId=" + linkedUserId);

			User user = null;
			if (linkedUserId > 0) {
				try {
					user = UserLocalServiceUtil.getUser(linkedUserId);
				} catch (NoSuchUserException nsue) {
					_log.info(nsue.getMessage());
					return credentials;
				}
			} else {
				return credentials;
			}

			credentials = new String[3];

			credentials[0] = String.valueOf(user.getUserId());
			credentials[1] = user.getPassword();
			credentials[2] = Boolean.FALSE.toString();

		} catch (Exception e) {
			_log.error(e, e);
		}

		return credentials;
	}

	private static Log _log = LogFactoryUtil.getLog(LinkedInAutoLogin.class);

}