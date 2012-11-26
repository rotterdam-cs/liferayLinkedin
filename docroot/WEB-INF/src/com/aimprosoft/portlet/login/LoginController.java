package com.aimprosoft.portlet.login;


import com.aimprosoft.common.config.ApplicationPropsBean;
import com.aimprosoft.common.service.mail.impl.LiferayMailService;
import com.aimprosoft.common.spring.ObjectFactory;
import com.aimprosoft.common.util.JacksonJsonViewHelper;
import com.aimprosoft.common.util.MessagesHelper;
import com.aimprosoft.portlet.login.model.LoginConstants;
import com.aimprosoft.portlet.login.social.linkedin.LinkedInConnectUtil;
import com.aimprosoft.portlet.login.social.twitter.TwitterConnectUtil;
import com.aimprosoft.portlet.login.social.twitter.TwitterConstants;
import com.aimprosoft.portlet.login.util.LoginPreferencesUtil;
import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Person;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.captcha.CaptchaUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.Authenticator;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.ContentUtil;
import oauth.signpost.OAuth;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import twitter4j.Twitter;
import twitter4j.auth.RequestToken;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("VIEW")
@SessionAttributes("messages")
public class LoginController {

    private Logger _logger = Logger.getLogger(getClass());

    private ApplicationPropsBean props = null;

    private LiferayMailService mailService = null;

    public static final String SUCCESS_MSG = "successMsg";
    public static final String ERROR_MSG = "errorMsg";

    @RequestMapping
    public Object view(RenderRequest renderRequest, RenderResponse renderResponse, ModelMap modelMap) {

        HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
        HttpSession session = request.getSession();

        final Object infoMap = renderRequest.getPortletSession().getAttribute("infoMap");

        if (!modelMap.containsKey("messages")) {
            String messages = MessagesHelper.getMessagesLikeJSON(renderRequest, "message.login");
            modelMap.put("messages", messages);
        }

        if (infoMap != null) {

            try {

                return JacksonJsonViewHelper.getJsonView("infoMap", infoMap);

            } finally {

                renderRequest.getPortletSession().removeAttribute("infoMap");
            }
        }

        try {

            boolean linkedInEnabled = LinkedInConnectUtil.isEnabled(renderRequest);
            if (linkedInEnabled) {

                String linkedInRedirectURL = LinkedInConnectUtil.getRedirectURL(renderRequest);

                //set URL parameters
                linkedInRedirectURL = HttpUtil.addParameter(linkedInRedirectURL, "p_p_id", "LOGIN_PORTLET_WAR_loginportlet");
                linkedInRedirectURL = HttpUtil.addParameter(linkedInRedirectURL, "p_p_state", "normal");
                linkedInRedirectURL = HttpUtil.addParameter(linkedInRedirectURL, "p_p_mode", "view");

                String strutsActionParameter = "_LOGIN_PORTLET_WAR_loginportlet_action";
                String strutsActionValue = "linked_in";

                linkedInRedirectURL = HttpUtil.addParameter(linkedInRedirectURL, strutsActionParameter, strutsActionValue);

                LinkedInRequestToken requestToken = LinkedInConnectUtil.getLinkedInRequestToken(linkedInRedirectURL, renderRequest);

                session.setAttribute("requestToken", requestToken);

                String linkedInAuthUrl = requestToken.getAuthorizationUrl();

                modelMap.put("linkedInAuthUrl", linkedInAuthUrl);
            }

        } catch (SystemException e) {
            _logger.error("LinkedIn error: " + e.getMessage());
        }

        try {

            boolean twitterEnabled = TwitterConnectUtil.isEnabled(renderRequest);
            if (twitterEnabled) {

                String twitterRedirectURL = TwitterConnectUtil.getRedirectURL(renderRequest);

                twitterRedirectURL = HttpUtil.addParameter(twitterRedirectURL, "p_p_id", "LOGIN_PORTLET_WAR_loginportlet");
                twitterRedirectURL = HttpUtil.addParameter(twitterRedirectURL, "p_p_state", "normal");
                twitterRedirectURL = HttpUtil.addParameter(twitterRedirectURL, "p_p_mode", "view");

                String twitterStrutsActionParameter = "_LOGIN_PORTLET_WAR_loginportlet_action";
                String twitterStrutsActionValue = "twitter";

                twitterRedirectURL = HttpUtil.addParameter(twitterRedirectURL, twitterStrutsActionParameter, twitterStrutsActionValue);

                Twitter twitter = TwitterConnectUtil.getTwitter(renderRequest);

                RequestToken twitterRequestToken = TwitterConnectUtil.getTwitterRequestToken(renderRequest, twitterRedirectURL);

                session.setAttribute("twitter", twitter);
                session.setAttribute("twitterRequestToken", twitterRequestToken);

                String twitterAuthUrl = twitterRequestToken.getAuthorizationURL();

                modelMap.put("twitterAuthUrl", twitterAuthUrl);
            }

        } catch (Exception e) {
            _logger.error("Twitter error: " + e.getMessage());
        }

        return new ModelAndView("/login/view/index", modelMap);
//        return new ModelAndView("/html/portlet/login/view/index.jsp", modelMap);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ActionMapping(params = "action=loginAction")
    public void loginAction(@RequestParam(value = "login") String loginName, @RequestParam(value = "password") String loginPassword,
                            ActionRequest portletRequest, ActionResponse portletResponse, Model model) throws Exception {


        if (StringUtils.isBlank(loginName)) {
            portletResponse.setRenderParameter(ERROR_MSG, "Error. Login is blank.");
            return;
        }

        Map<String, String> infoMap = new HashMap<String, String>();

        HttpServletRequest request = PortalUtil.getHttpServletRequest(portletRequest);
        HttpServletResponse response = PortalUtil.getHttpServletResponse(portletResponse);
        try {

            Company company = PortalUtil.getCompany(portletRequest);
            String authType;
            if (Validator.isEmailAddress(loginName)) {
                authType = CompanyConstants.AUTH_TYPE_EA;
            } else {
                authType = CompanyConstants.AUTH_TYPE_SN;
            }

            Map<String, String[]> headerMap = getHeadersMap(request);

            Map<String, String[]> parameterMap = request.getParameterMap();

            int authResult = authenticateUser(authType, company.getCompanyId(), loginName, loginPassword, headerMap, parameterMap);

            if (authResult == Authenticator.SUCCESS) {

                User user = getUser(authType, loginName, company.getCompanyId());

                String userMail = user.getEmailAddress();

                if (!authType.equals(company.getAuthType())) {     //if user try login with mail, replace mail with displayname

                    infoMap.put("trueLogin", user.getScreenName());
                }
                if (checkMailForPattern(userMail)) {

                    infoMap.put("status", "mail-processing");      // if user have no mail

                } else if (user.isPasswordReset()) {

                    infoMap.put("status", "resetPassword");

                } else {
                    infoMap.put("status", "success");
                }

                portletResponse.setRenderParameter(SUCCESS_MSG, "You have logged in successfully.");

            } else {

                portletResponse.setRenderParameter(ERROR_MSG, "Error. Authentication failed.");
            }

        } catch (Exception e) {

            portletResponse.setRenderParameter(ERROR_MSG, "Error. Unexpected error occured.");
        }

        portletRequest.getPortletSession().setAttribute("infoMap", infoMap);
    }

    @ActionMapping(params = "action=linked_in_login")
    public void linkedInLoginAction(ActionRequest actionRequest, ActionResponse actionResponse) {


        Map<String, String> infoMap = new HashMap<String, String>();

        String errorMsg;

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long companyId = themeDisplay.getCompanyId();

        HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
        HttpServletResponse response = PortalUtil.getHttpServletResponse(actionResponse);

        String emailAddress = actionRequest.getParameter("email");

        Person profile = LinkedInConnectUtil.getLinkedInPerson();

        User user = null;
        try {
            user = UserLocalServiceUtil.getUserByEmailAddress(companyId, emailAddress);
        } catch (NoSuchUserException e) {
            user = null;
        } catch (Exception e) {
            errorMsg = "Unexpected error.";
        }

        boolean addUserError = false;

        if (user == null) {

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
            String jobTitle = (profile != null) ? StringUtils.substringBefore(profile.getHeadline(), " at ") : "";
            long[] groupIds = {};
            long[] organizationIds = {};
            long[] roleIds = {};
            long[] userGroupIds = {};
            boolean sendEmail = false;
            ServiceContext serviceContext = new ServiceContext();

            try {

                user = UserLocalServiceUtil.addUser(
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

        if (user != null) {

            boolean passwordEncrypted = user.getPasswordEncrypted();
            if (passwordEncrypted) {
                user.setPasswordEncrypted(false);
                try {
                    UserLocalServiceUtil.updateUser(user);
                } catch (SystemException e) {
                    System.out.println("Can not update user");
                }
            }

            infoMap.put("status", "success");
            infoMap.put("userLogin", user.getEmailAddress());
            infoMap.put("userPassword", user.getPassword());

            actionRequest.getPortletSession().setAttribute("infoMap", infoMap);
        }
    }

    @ActionMapping(params = "action=twitter_login")
    public void twitterLoginAction(ActionRequest actionRequest, ActionResponse actionResponse) {

        Map<String, String> infoMap = new HashMap<String, String>();

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        long companyId = themeDisplay.getCompanyId();

        String emailAddress = actionRequest.getParameter("email");

        twitter4j.User twitterUser = TwitterConnectUtil.getTwitterUser();

        User user;
        try{
            user = UserLocalServiceUtil.getUserByEmailAddress(companyId, emailAddress);
        } catch (Exception e) {
            user = null;
        }

        if (user == null) {

            long creatorUserId = 0;
            boolean autoPassword = true;
            String password1 = DEFAULT_PASSWORD;
            String password2 = DEFAULT_PASSWORD;
            boolean autoScreenName = true;
            String screenName = "";
            long faceBookId = -1;
            String openId = "";
            Locale locale = themeDisplay.getLocale();
            String firstName = StringUtils.substringBefore(twitterUser.getName(), " ");
            String middleName = "";
            String lastName = StringUtils.substringAfter(twitterUser.getName(), " ");
            int prefixId = -1;
            int suffixId = -1;
            boolean male = true;
            int birthdayMonth = 0;
            int birthdayDay = 1;
            int birthdayYear = 1970;
            String jobTitle = "";
            long[] groupIds = {};
            long[] organizationIds = {};
            long[] roleIds = {};
            long[] userGroupIds = {};
            boolean sendEmail = false;
            ServiceContext serviceContext = new ServiceContext();

            try{
                user = UserLocalServiceUtil.addUser(
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
                _logger.error("Can not add user: " + e.getMessage());
            }
        }

        if (user != null) {

            boolean passwordEncrypted = user.getPasswordEncrypted();
            if (passwordEncrypted) {
                user.setPasswordEncrypted(false);
                try {
                    UserLocalServiceUtil.updateUser(user);
                } catch (SystemException e) {
                    System.out.println("Can not update user");
                }
            }

            infoMap.put("status", "success");
            infoMap.put("userLogin", user.getEmailAddress());
            infoMap.put("userPassword", user.getPassword());

            actionRequest.getPortletSession().setAttribute("infoMap", infoMap);
        }
    }


    @RenderMapping(params = "action=linked_in")
    public ModelAndView linkedInLoginRender(RenderRequest renderRequest, RenderResponse renderResponse) {

        String successMsg = "";
        String errorMsg = "";

        HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
        HttpSession session = request.getSession();

        String oauthVerifier = request.getParameter(OAuth.OAUTH_VERIFIER);
        String oauthToken = request.getParameter(OAuth.OAUTH_TOKEN);

        LinkedInRequestToken requestToken = (LinkedInRequestToken) session.getAttribute("requestToken");

        LinkedInAccessToken accessToken = null;

        try {

            if (oauthToken != null) {

                LinkedInOAuthService oAuthService = LinkedInConnectUtil.getLinkedInOAuthService(renderRequest);

                accessToken = oAuthService.getOAuthAccessToken(requestToken, oauthVerifier);

                if (accessToken != null) {
                    LinkedInConnectUtil.setAccessToken(accessToken);
                }

            } else {

                accessToken = LinkedInConnectUtil.getAccessToken(requestToken, oauthVerifier, renderRequest);
            }

            if (accessToken != null) {

                String consumerKey = LinkedInConnectUtil.getAppId(renderRequest);

                String consumerSecret = LinkedInConnectUtil.getAppSecret(renderRequest);

                LinkedInApiClientFactory clientFactory = LinkedInApiClientFactory.newInstance(consumerKey, consumerSecret);

                LinkedInApiClient client = clientFactory.createLinkedInApiClient(accessToken);

                Person profile = client.getProfileForCurrentUser();

                LinkedInConnectUtil.setLinkedInPerson(profile);
            }

            Person profile = LinkedInConnectUtil.getLinkedInPerson();
            renderRequest.setAttribute("person", profile);


        } catch (Exception e) {
            errorMsg = "Error: " + e.getMessage();
        }

        return new ModelAndView("/login/view/linked_in");
    }


    @RenderMapping(params = "action=twitter")
    public ModelAndView twitterLoginRender(RenderRequest renderRequest, RenderResponse renderResponse) {

        HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
        HttpSession session = request.getSession();

        Twitter twitter = (Twitter)session.getAttribute(TwitterConstants.TWITTER);
        RequestToken requestToken = (RequestToken)session.getAttribute(TwitterConstants.TWITTER_REQUEST_TOKEN);

        if (requestToken != null) {

            try {

                String verifier = request.getParameter(TwitterConstants.OAUTH_VERIFIER);
                session.removeAttribute(TwitterConstants.TWITTER_REQUEST_TOKEN);

                twitter.getOAuthAccessToken(requestToken, verifier);

                twitter4j.User user = twitter.verifyCredentials();

                TwitterConnectUtil.setTwitterUser(user);

                renderRequest.setAttribute("twitterUser", user);

            } catch (Exception e) {

                _logger.error("Twitter error: " + e.getMessage());
            }
        }

        return new ModelAndView("/login/view/twitter");
    }

    @RenderMapping(params = "action=backToHome")
    public ModelAndView backToHome() {
        return new ModelAndView("/login/view/index");
    }

    @RenderMapping(params = "action=password")
    public ModelAndView newPassword() {
        return new ModelAndView("/login/view/new-password");
    }

    @RenderMapping(params = "action=successLogin")
    public ModelAndView successLogin() {
        return new ModelAndView("/login/view/success-login");
    }

    @RenderMapping(params = "action=forgotPassword")
    public ModelAndView forgotPassword(RenderRequest request, @RequestParam(value = "_58_login", defaultValue = "") String loginName,
                                       @RequestParam(value = "_58_password", defaultValue = "") String password) {
        Map<String, String[]> params = request.getParameterMap();

        ModelMap modelMap = new ModelMap();

        if (StringUtils.isNotBlank(loginName)) {
            modelMap.put("loginName", loginName);
            modelMap.put("password", password);

            String mail = "";
            try {
                User user;
                if (Validator.isEmailAddress(loginName))
                    user = UserLocalServiceUtil.getUserByEmailAddress(CompanyThreadLocal.getCompanyId(), loginName);
                else
                    user = UserLocalServiceUtil.getUserByScreenName(CompanyThreadLocal.getCompanyId(), loginName);

                mail = user.getEmailAddress();
            } catch (Exception e) {
                _logger.error(e);
            }

            if (StringUtils.isNotBlank(mail) && checkMailForPattern(mail))
                return new ModelAndView("/login/view/mail-processing", modelMap);
        }

        for (String key : params.keySet()) {
            modelMap.put(key, params.get(key)[0]);
        }
        return new ModelAndView("/login/view/forgot-password", modelMap);
    }


    @ResourceMapping("captcha")
    public void captchaProcess(ResourceRequest resourceRequest,
                               ResourceResponse resourceResponse) throws Exception {

        try {
            CaptchaUtil.serveImage(resourceRequest, resourceResponse);
        } catch (Exception e) {
            _logger.warn("Could not get captcha, cause - " + e.getMessage(), e);
        }
    }

    @ActionMapping(params = "action=processForgot")
    public void processForgot(ActionRequest request, ActionResponse response) throws Exception {

        Map<String, String> errors = new HashMap<String, String>();

        String userEmail = request.getParameter("email");
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        if (Validator.isEmailAddress(userEmail)) {

            long companyId = themeDisplay.getCompany().getCompanyId();

            User user = null;
            try {
                user = UserLocalServiceUtil.getUserByEmailAddress(companyId, userEmail);
            } catch (Exception e) {
                errors.put("emailError", "user.does.not.exist");
            }
            if (Validator.isNotNull(user)) {

                errors.putAll(checkCaptcha(request));

                if (errors.isEmpty()) {

                    PortletPreferences preferences = request.getPreferences();
                    String languageId = LanguageUtil.getLanguageId(request);

                    String emailFromName = LoginPreferencesUtil.getPreference(request, LoginConstants.EMAIL_FROM_NAME);
                    String emailFromAddress = LoginPreferencesUtil.getPreference(request, LoginConstants.EMAIL_FROM_ADDRESS);

                    String emailToAddress = user.getEmailAddress();

                    String subject = preferences.getValue("emailPasswordSentSubject_" + languageId, null);
                    String body = preferences.getValue("emailPasswordSentBody_" + languageId, null);

                    sendResetPassword(request, emailFromName, emailFromAddress, emailToAddress, subject, body);

                    response.sendRedirect(themeDisplay.getURLHome());
                }
            }

        } else {
            errors.put("emailError", "email.invalid");
        }

        if (!errors.isEmpty()) {
            response.setRenderParameter("action", "forgotPassword");
            response.setRenderParameter("email", userEmail);
            for (String key : errors.keySet()) {
                response.setRenderParameter(key, errors.get(key));
            }
        }
    }


    private Map<String, String> checkCaptcha(ActionRequest request) throws Exception {

        Map<String, String> errors = new HashMap<String, String>();

        String enteredCaptchaText = ParamUtil.getString(request, "captchaText");

        PortletSession session = request.getPortletSession();
        String captchaText = getCaptchaValueFromSession(session);
        if (Validator.isNull(captchaText)) {
            errors.put("captchaError", "captcha.not.found");
            return errors;
        }
        if (!StringUtils.equals(captchaText, enteredCaptchaText)) {
            errors.put("captchaError", "captcha.invalid");
            return errors;
        }
        return errors;
    }

    private String getCaptchaValueFromSession(PortletSession session) {
        Enumeration<String> atNames = session.getAttributeNames();
        while (atNames.hasMoreElements()) {
            String name = atNames.nextElement();
            if (name.contains("CAPTCHA_TEXT")) {
                return (String) session.getAttribute(name);
            }
        }
        return null;
    }

    public static void sendResetPassword(
            ActionRequest actionRequest, String fromName, String fromAddress,
            String toAddress, String subject, String body)
            throws Exception {

        HttpServletRequest request = PortalUtil.getHttpServletRequest(
                actionRequest);

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(
                com.liferay.portal.kernel.util.WebKeys.THEME_DISPLAY);

        Company company = themeDisplay.getCompany();

        if (!company.isSendPassword()) {
            return;
        }

        ServiceContext serviceContext = ServiceContextFactory.getInstance(User.class.getName(), actionRequest);

        UserLocalServiceUtil.sendPassword(company.getCompanyId(), toAddress, fromName, fromAddress, subject, body, serviceContext);

        String message = MessagesHelper.getApplicationMessage("message.resetpassword.mailsent", actionRequest);
        SessionMessages.add(actionRequest, "request_processed", message);
    }

    @RenderMapping(params = "action=mail")
    public ModelAndView setMailAction(@RequestParam(value = "mail", defaultValue = "") String mail,
                                      @RequestParam(value = "confirmMail", defaultValue = "") String confirmMail,
                                      @RequestParam("loginName") String loginName, @RequestParam("password") String password, ModelMap modelMap, RenderRequest request) {

        if (StringUtils.isNotBlank(mail) && StringUtils.isNotBlank(confirmMail) && mail.equals(confirmMail)) {
            try {

                if (!Validator.isEmailAddress(mail)) {     //check new mail address for valid
                    modelMap.put("status", "noValid");
                    modelMap.put("loginName", loginName);
                } else if (isUserWithThatMailExist(mail)) {    //check if mail already exist in portal

                    modelMap.put("status", "alreadyExist");
                    modelMap.put("loginName", loginName);

                } else {

                    User user;

                    if (Validator.isEmailAddress(loginName))             //if user login by email
                        user = UserLocalServiceUtil.getUserByEmailAddress(CompanyThreadLocal.getCompanyId(), loginName);
                    else                                                //if user login by display name
                        user = UserLocalServiceUtil.getUserByScreenName(CompanyThreadLocal.getCompanyId(), loginName);

                    user.setEmailAddress(mail);
                    user = UserLocalServiceUtil.updateUser(user);
                    sendMailNotification(mail, user, password);

                    Map<String, String[]> headerMap = getHeadersMap(PortalUtil.getHttpServletRequest(request));

                    Map<String, String[]> parameterMap = request.getParameterMap();

                    int authResult = authenticateUser(CompanyConstants.AUTH_TYPE_SN, CompanyThreadLocal.getCompanyId(), loginName, password, headerMap, parameterMap);

                    modelMap.put("status", "success");
                    modelMap.put("login", loginName);
                    modelMap.put("password", password);
                }

            } catch (Exception e) {
                _logger.error(e);
            }
        } else {
            modelMap.put("status", "error");
            modelMap.put("loginName", loginName);

        }
        return new ModelAndView("/login/view/mail-processing", modelMap);

    }


    private boolean checkMailForPattern(String mail) {

        int separatorIndex = mail.indexOf("@");

        if (separatorIndex != -1) {
            String mailPart = mail.substring(separatorIndex + 1);
            String pattern = "no-emailaddress.com";
            return mailPart.equals(pattern);
        }
        return false;
    }

    private boolean isUserWithThatMailExist(String mail) {
        try {
            UserLocalServiceUtil.getUserByEmailAddress(CompanyThreadLocal.getCompanyId(), mail);  //check for existing mail, if no exceptions - user with that email exist
        } catch (NoSuchUserException e) {
            return false;
        } catch (Exception e) {
            _logger.error(e);
        }
        return true;
    }

    private void sendMailNotification(String mail, User user, String password) throws Exception {

        String from = getProps().getRegistrationEmailFrom();
        String subject = getProps().getRegistrationEmailSubject();

        String template = ContentUtil.get("./template/email/welcome-new-user-template.vm");


        template = StringUtil.replace(
                template,
                new String[]{
                        "[$EMAIL]",
                        "[$PASSWORD]"
                },
                new String[]{
                        mail,
                        password
                }
        );

        getMailService().sendEmail(from, mail, subject, template);
    }

    @SuppressWarnings("unchecked")
    private Map<String, String[]> getHeadersMap(HttpServletRequest request) {

        Map headersMap = new HashMap<String, String>();
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {

            String name = headerNames.nextElement();

            Enumeration<String> requestHeaders = request.getHeaders(name);

            List<String> headers = new ArrayList<String>();

            while (requestHeaders.hasMoreElements()) {
                String value = requestHeaders.nextElement();

                headers.add(value);
            }

            headersMap.put(name, headers.toArray(new String[headers.size()]));
        }

        return headersMap;
    }

    private int authenticateUser(String authType, long companyId, String loginName, String loginPassword, Map<String, String[]> headerMap, Map<String, String[]> parameterMap) {

        int authResult = 0;

        try {
            Map<String, Object> resultsMap = new HashMap<String, Object>();

            if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
                authResult = UserLocalServiceUtil.authenticateByEmailAddress(companyId, loginName, loginPassword, headerMap, parameterMap, resultsMap);
            } else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
                long userId = Long.parseLong(loginName);
                authResult = UserLocalServiceUtil.authenticateByUserId(companyId, userId, loginPassword, headerMap, parameterMap, resultsMap);
            } else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
                authResult = UserLocalServiceUtil.authenticateByScreenName(companyId, loginName, loginPassword, headerMap, parameterMap, resultsMap);
            }

        } catch (Exception ignored) {
        }

        return authResult;
    }


    private User getUser(String authType, String login, long companyId) {

        User user = null;

        try {
            if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {

                user = UserLocalServiceUtil.getUserByEmailAddress(companyId, login);

            } else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {

                user = UserLocalServiceUtil.getUserById(companyId, Long.parseLong(login));

            } else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {

                user = UserLocalServiceUtil.getUserByScreenName(companyId, login);

            }
        } catch (Exception ignored) {
        }

        return user;
    }

    private ApplicationPropsBean getProps(){
        if(this.props!=null)
            return props;
        return ObjectFactory.getBean(ApplicationPropsBean.class);
    }

    private LiferayMailService getMailService(){
        if(this.mailService!=null)
            return mailService;
        return ObjectFactory.getBean(LiferayMailService.class);
    }

    public static final String DEFAULT_PASSWORD = "1111";
}
