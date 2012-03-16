/**
 * Copyright (c) 2012 Rotterdam CS, Inc. All rights reserved.
 */

package com.liferay.portlet.login.action;

import java.util.Calendar;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.scribe.model.Token;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.UserEmailAddressException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.linkedin.LinkedInUtil;
import com.liferay.portal.linkedin.LinkedInWebKeys;
import com.liferay.portal.linkedin.NoSuchUserLinkedInException;
import com.liferay.portal.linkedin.model.UserLinkedIn;
import com.liferay.portal.linkedin.service.UserLinkedInLocalServiceUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.struts.ActionConstants;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Rajesh
 * 
 */
public class LinkedInConnectAction extends PortletAction {

	@Override
	public ActionForward render(ActionMapping mapping, ActionForm form,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
				.getAttribute(WebKeys.THEME_DISPLAY);

		if (!LinkedInUtil.isEnabled(themeDisplay.getCompanyId())) {
			return null;
		}

		HttpServletRequest request = PortalUtil
				.getHttpServletRequest(renderRequest);

		_log.info("render - LINKEDIN_USER_ID="
				+ request.getSession().getAttribute(
						LinkedInWebKeys.LINKEDIN_USER_ID));

		if (request.getSession().getAttribute(LinkedInWebKeys.LINKEDIN_USER_ID) != null)
			return mapping.findForward(ActionConstants.COMMON_REFERER);

		return mapping.findForward("portlet.linkedin.email");
	}

	@Override
	public void processAction(ActionMapping mapping, ActionForm form,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {

		HttpServletRequest request = PortalUtil
				.getHttpServletRequest(actionRequest);

		long companyId = PortalUtil.getCompanyId(request);
		String emailAddress = ParamUtil
				.getString(actionRequest, "emailAddress");

		_log.debug("processAction - emailAddress=" + emailAddress
				+ ", companyId=" + companyId);

		// if linkedin access token already exists in session
		Token accessToken = (Token) request.getSession().getAttribute(
				LinkedInWebKeys.LINKEDIN_ACCESS_TOKEN);
		if (accessToken == null) {
			accessToken = LinkedInUtil.getAccessToken(companyId, request);
			request.getSession().setAttribute(
					LinkedInWebKeys.LINKEDIN_ACCESS_TOKEN, accessToken);
		}

		_log.info("processAction - accessToken=" + accessToken.getToken()
				+ ", accessSecret=" + accessToken.getSecret());

		// read linkedin member id from rest json request
		JSONObject jsonObject = LinkedInUtil
				.getGraphResources(companyId, accessToken,
						"id,first-name,last-name,headline,summary,date-of-birth,picture-url");

		if (jsonObject != null) {

			String memberId = jsonObject.getString("id");
			_log.debug("processAction - memberId=" + memberId);

			if (Validator.isNotNull(emailAddress)) {
				try {
					validateEmailAddress(companyId, emailAddress);

					User addUser = addUser(companyId, emailAddress, jsonObject);
					_log.debug("processAction - addUser=" + addUser.getUserId());

					try {
						UserLinkedInLocalServiceUtil.findByL_C_Id(companyId,
								memberId);
					} catch (NoSuchUserLinkedInException e) {
						// link liferay and linked user
						addUserLinkedIn(companyId, memberId,
								addUser.getUserId());
					}
					request.getSession().setAttribute(
							LinkedInWebKeys.LINKEDIN_USER_ID,
							addUser.getUserId());

				} catch (Exception e) {
					_log.error("Email validation failed");
					if (e instanceof UserEmailAddressException) {
						SessionErrors
								.add(actionRequest, e.getClass().getName());
					} else {
						_log.error(e);
					}
				}
				if (_log.isDebugEnabled())
					_log.debug("processAction - emailValidated successfully");

			} else {

				try {
					UserLinkedIn userLinkedId = UserLinkedInLocalServiceUtil
							.findByL_C_Id(companyId, memberId);

					try {
						UserLocalServiceUtil.getUser(userLinkedId.getUserId());
					} catch (NoSuchUserException e) {
						_log.error("No such user exist in liferay - "
								+ e.getMessage());
						UserLinkedInLocalServiceUtil
								.deleteUserLinkedIn(userLinkedId);
						return;
					}

					request.getSession().setAttribute(
							LinkedInWebKeys.LINKEDIN_USER_ID,
							userLinkedId.getUserId());

					updateUser(userLinkedId.getUserId(), jsonObject);

					// redirect to autologin hook /c/portal/login
					_log.debug("redirect to autologin hook /c/portal/login");

					// update user
					request.getSession().setAttribute(
							LinkedInWebKeys.LINKEDIN_USER_ID,
							userLinkedId.getUserId());

				} catch (NoSuchUserLinkedInException e) {
					// if no user mapping exist between liferay and linkedin,
					// redirect to email address input screen
					_log.info("No such user exists in liferay - "
							+ e.getMessage());
					// continue normal flow..
				} catch (Exception e) {
					// something went wrong
					_log.error(e);
				}
			}
		}
	}

	private void addUserLinkedIn(long companyId, String memberId, long addUser)
			throws SystemException {

		long increment = CounterLocalServiceUtil.increment(UserLinkedIn.class
				.getName());

		UserLinkedIn createUserLinkedIn = UserLinkedInLocalServiceUtil
				.createUserLinkedIn(increment);
		createUserLinkedIn.setCompanyId(companyId);
		createUserLinkedIn.setLinkedId(memberId);
		createUserLinkedIn.setUserId(addUser);

		UserLinkedIn addUserLinkedIn = UserLinkedInLocalServiceUtil
				.addUserLinkedIn(createUserLinkedIn);
		if (addUserLinkedIn != null) {
			if (_log.isDebugEnabled()) {
				_log.debug("addUserLinkedIn userId=" + addUserLinkedIn.getId());
			}
		}
	}

	/**
	 * Validate user emailAddress
	 * 
	 * @param companyId
	 * @param emailAddress
	 * @throws SystemException
	 * @throws PortalException
	 */
	private void validateEmailAddress(long companyId, String emailAddress)
			throws PortalException, SystemException {
		if (!Validator.isEmailAddress(emailAddress)) {
			throw new UserEmailAddressException();
		} else {
			try {
				long userIdByEmailAddress = UserLocalServiceUtil
						.getUserIdByEmailAddress(companyId, emailAddress);
				if (userIdByEmailAddress > 0)
					throw new UserEmailAddressException();
			} catch (NoSuchUserException e) {
				// _log.error(e);
			}
		}
	}

	private User addUser(long companyId, String emailAddress,
			JSONObject jsonObject) throws Exception {

		String firstName = jsonObject.getString("firstName");
		String lastName = jsonObject.getString("lastName");
		String middleName = jsonObject.getString("maidenName") != null ? jsonObject
				.getString("maidenName") : StringPool.BLANK;
		String jobTitle = jsonObject.getString("headline") != null ? jsonObject
				.getString("headline") : StringPool.BLANK;

		String comments = jsonObject.getString("summary");

		_log.debug("addUser - firstName=" + firstName + ", lastName="
				+ lastName + ", middleName=" + middleName + ", jobTitle=");

		long creatorUserId = 0;
		boolean autoPassword = true;
		String password1 = StringPool.BLANK;
		String password2 = StringPool.BLANK;
		boolean autoScreenName = true;
		String screenName = StringPool.BLANK;
		long facebookId = 0;
		String openId = StringPool.BLANK;
		Locale locale = LocaleUtil.getDefault();
		int prefixId = 0;
		int suffixId = 0;
		int birthdayMonth = Calendar.JANUARY;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		long[] groupIds = null;
		long[] organizationIds = null;
		long[] roleIds = null;
		long[] userGroupIds = null;
		boolean sendEmail = true;
		boolean male = true;

		ServiceContext serviceContext = new ServiceContext();

		User user = UserLocalServiceUtil.addUser(creatorUserId, companyId,
				autoPassword, password1, password2, autoScreenName, screenName,
				emailAddress, facebookId, openId, locale, firstName,
				middleName, lastName, prefixId, suffixId, male, birthdayMonth,
				birthdayDay, birthdayYear, jobTitle, groupIds, organizationIds,
				roleIds, userGroupIds, sendEmail, serviceContext);

		UserLocalServiceUtil.updateLastLogin(user.getUserId(),
				user.getLoginIP());

		UserLocalServiceUtil.updatePasswordReset(user.getUserId(), false);

		UserLocalServiceUtil.updateEmailAddressVerified(user.getUserId(), true);

		if (Validator.isNotNull(comments)) {
			user.setComments(comments);
			UserLocalServiceUtil.updateUser(user);
		}

		return user;

	}

	private void updateUser(long userId, JSONObject jsonObject)
			throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("updateUser - user=" + userId);
		}

		User user = UserLocalServiceUtil.getUser(userId);

		String firstName = jsonObject.getString("firstName");
		String lastName = jsonObject.getString("lastName");
		String middleName = jsonObject.getString("maidenName") != null ? jsonObject
				.getString("maidenName") : StringPool.BLANK;
		String jobTitle = jsonObject.getString("headline") != null ? jsonObject
				.getString("headline") : StringPool.BLANK;

		String comments = jsonObject.getString("summary");

		_log.debug("updateUser - firstName=" + firstName + ", lastName="
				+ lastName + ", middleName=" + middleName + ", jobTitle=");

		if (isEquals(firstName, user.getFirstName())
				&& isEquals(lastName, user.getLastName())
				&& isEquals(middleName, user.getMiddleName())
				&& isEquals(jobTitle, user.getJobTitle())
				&& isEquals(comments, user.getComments())) {

			_log.debug("updateUser - all property are in sync");
			return;
		}

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setMiddleName(middleName);
		user.setJobTitle(jobTitle);
		user.setComments(comments);

		UserLocalServiceUtil.updateUser(user);

		_log.info("user sync successfully with linkedin data");
	}

	public static boolean isEquals(String a, String b) {
		if (a == null || b == null)
			return false;
		return a.equals(b);
	}

	private static Log _log = LogFactoryUtil
			.getLog(LinkedInConnectAction.class);

	/*protected void setFacebookCredentials(HttpSession session, long companyId,
			String token) throws Exception {

		System.out.println("setFacebookCredentials - start");
		
				JSONObject jsonObject = LinkedInUtil
						.getGraphResources(companyId, "/me", token,
								"id,first-name,last-name,headline,summary,date-of-birth,picture-url");

				System.out.println("setFacebookCredentials - jsonObject=" + jsonObject);

				if ((jsonObject == null) || (jsonObject.getJSONObject("error") != null)) {
					return;
				}

				if (LinkedInUtil.isVerifiedAccountRequired(companyId)
						&& !jsonObject.getBoolean("verified")) {
					return;
				}

				User user = null;

				long linkedId = jsonObject.getLong("id");
				if (linkedId > 0) {
					session.setAttribute(LinkedInWebKeys.LINKEDIN_USER_ID,
							String.valueOf(linkedId));
				}

				String emailAddress = jsonObject.getString("email");
				if ((user == null) && Validator.isNotNull(emailAddress)) {
					session.setAttribute(LinkedInWebKeys.LINKEDIN_USER_EMAIL_ADDRESS,
							emailAddress);
					try {
						user = UserLocalServiceUtil.getUserByEmailAddress(companyId,
								emailAddress);
					} catch (NoSuchUserException nsue) {
					}
				}

				System.out.println("setFacebookCredentials - email="
						+ jsonObject.getString("email") + ", firstName="
						+ jsonObject.getString("first_name") + ", lastName="
						+ jsonObject.getString("last_name") + ", id="
						+ jsonObject.getLong("id"));

				if (user != null) {

					// updateUser(user, jsonObject);
				} else {
					// addUser(session, companyId, jsonObject);
				}
		
		System.out.println("setFacebookCredentials - end");
	}*/

}