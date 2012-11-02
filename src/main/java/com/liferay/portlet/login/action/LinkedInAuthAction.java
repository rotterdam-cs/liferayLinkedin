package com.liferay.portlet.login.action;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Person;
import com.google.code.linkedinapi.schema.SiteStandardProfileRequest;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.linkedin.LinkedInConnectUtil;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.login.util.LoginUtil;
import oauth.signpost.OAuth;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.taglib.tiles.ComponentConstants;
import org.apache.struts.tiles.ComponentContext;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author V. Koshelenko
 */
public class LinkedInAuthAction extends BaseStrutsPortletAction {

    @Override
    public void processAction(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        if (ENTER_EMAIL_ACTION.equals(actionRequest.getParameter(ACTION))) {

            ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

            long companyId = themeDisplay.getCompanyId();

            HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
            HttpServletResponse response = PortalUtil.getHttpServletResponse(actionResponse);

            String emailAddress = actionRequest.getParameter(EMAIL_PARAMETER);

            Person profile = LinkedInConnectUtil.getLinkedInPerson(companyId);

            SiteStandardProfileRequest profileRequest = profile.getSiteStandardProfileRequest();
            String profileRequestUrl = profileRequest.getUrl();

            String linkedInProfileId = StringUtils.substringAfter(profileRequestUrl, "key=");
            if (linkedInProfileId.contains("&"))
                linkedInProfileId = StringUtils.substringBefore (linkedInProfileId, "&");

            User userToLogin;
            try {
                userToLogin = UserLocalServiceUtil.getUserByEmailAddress(companyId, emailAddress);
            } catch (NoSuchUserException e) {
                userToLogin = null;
            }

            boolean addUserError = false;

            if (userToLogin == null) {

                long creatorUserId = 0;
                boolean autoPassword = true;
                String password1 = DEFAULT_PASSWORD;
                String password2 = DEFAULT_PASSWORD;
                boolean autoScreenName = true;
                String screenName = "";
                long faceBookId = -1;
                String openId = "";
                Locale locale = themeDisplay.getLocale();
                String firstName = (profile != null) ? profile.getFirstName() : "";
                String middleName = "";
                String lastName = (profile != null) ? profile.getLastName() : "";
                int prefixId = -1;
                int suffixId = -1;
                boolean male = true;
                int birthdayMonth = 0;
                int birthdayDay = 1;
                int birthdayYear = 1970;
                String jobTitle = (profile != null) ? StringUtils.substringBefore(profile.getHeadline(), JOB_COMPANY_SEPARATOR) : "";
                long[] groupIds = {};
                long[] organizationIds = {};
                long[] roleIds = {};
                long[] userGroupIds = {};
                boolean sendEmail = false;
                ServiceContext serviceContext = new ServiceContext();

                try{

                    userToLogin = UserLocalServiceUtil.addUser(
                            creatorUserId,
                            companyId,
                            autoPassword,
                            password1,
                            password2,
                            autoScreenName,
                            screenName,
                            emailAddress,
                            faceBookId,
                            openId,
                            locale,
                            firstName,
                            middleName,
                            lastName,
                            prefixId,
                            suffixId,
                            male,
                            birthdayMonth,
                            birthdayDay,
                            birthdayYear,
                            jobTitle,
                            groupIds,
                            organizationIds,
                            roleIds,
                            userGroupIds,
                            sendEmail,
                            serviceContext
                    );

                } catch (Exception e) {
                    addUserError = true;
                }
            }

            if (userToLogin != null) {

                try {
                    LoginUtil.login(request, response, userToLogin.getLogin(), userToLogin.getPassword(), false, CompanyConstants.AUTH_TYPE_EA);

                    String redirectURL = PortalUtil.getPortalURL(actionRequest) + actionRequest.getParameter(REDIRECT_PARAMETER);

                    actionResponse.sendRedirect(redirectURL);

                } catch (Exception e) {
                    actionResponse.setRenderParameter("errorMsg", "Can not login user: " + e.getMessage());
                }
            }

            if (addUserError)
                actionResponse.setRenderParameter("errorMsg", "Can not save user.");
            actionResponse.setRenderParameter(ACTION, ENTER_EMAIL_ACTION);
        }
    }

    @Override
    public String render(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

        long companyId = themeDisplay.getCompanyId();

        String errorMsg = renderRequest.getParameter("errorMsg");

        String successMsg = renderRequest.getParameter("successMsg");

        HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);

        HttpSession session = request.getSession();

        if (ENTER_EMAIL_ACTION.equals(renderRequest.getParameter(ACTION))) {

            if (StringUtils.isNotBlank(successMsg))
                request.setAttribute("successMsg", successMsg);


        } else {

            String oauthVerifier = request.getParameter(OAuth.OAUTH_VERIFIER);
            String oauthToken = request.getParameter(OAuth.OAUTH_TOKEN);

            LinkedInRequestToken requestToken = (LinkedInRequestToken)session.getAttribute("requestToken");

            LinkedInAccessToken accessToken = null;

            if (oauthToken != null) {

                LinkedInOAuthService oAuthService = LinkedInConnectUtil.getLinkedInOAuthService(companyId);

                try {
                    accessToken = oAuthService.getOAuthAccessToken(requestToken, oauthVerifier);
                } catch (Exception e) {
                    errorMsg = "AccessToken error: " + e.getLocalizedMessage();
                }

                if (accessToken != null)
                    LinkedInConnectUtil.setAccessToken(companyId, accessToken);

            } else  {

                accessToken = LinkedInConnectUtil.getAccessToken(companyId);

            }

            if (accessToken != null) {

                String consumerKey = LinkedInConnectUtil.getAppId(themeDisplay.getCompanyId());

                String consumerSecret = LinkedInConnectUtil.getAppSecret(themeDisplay.getCompanyId());

                LinkedInApiClientFactory clientFactory = LinkedInApiClientFactory.newInstance(consumerKey, consumerSecret);

                LinkedInApiClient client = clientFactory.createLinkedInApiClient(accessToken);

                Person profile = client.getProfileForCurrentUser();

                LinkedInConnectUtil.setLinkedInPerson(companyId, profile);
            }

            Person profile = LinkedInConnectUtil.getLinkedInPerson(companyId);

            renderRequest.setAttribute("person", profile);

        }

        if (StringUtils.isNotBlank(errorMsg))
            renderRequest.setAttribute("errorMsg", errorMsg);

        renderResponse.setTitle(themeDisplay.translate("linkedin.enter-email"));

        ComponentContext componentContext = new ComponentContext();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(PORTLET_CONTENT, LINKED_IN_JSP_PATH);
        componentContext.addAll(paramMap);
        renderRequest.setAttribute(ComponentConstants.COMPONENT_CONTEXT, componentContext);

        return COMMON_JSP_PATH;
    }

    public static final String ACTION               = "action";
    public static final String EMAIL_PARAMETER      = "email";
    public static final String ENTER_EMAIL_ACTION   = "enter_email";
    public static final String REDIRECT_PARAMETER   = "redirect";

    public static final String DEFAULT_PASSWORD = "1111";
    public static final String JOB_COMPANY_SEPARATOR = " at ";

    public static final String COMMON_JSP_PATH = "/common/themes/portlet.jsp";
    public static final String LINKED_IN_JSP_PATH = "/portlet/login/enter_email_linkedin.jsp";

    public static final String PORTLET_CONTENT = "portlet_content";
}

