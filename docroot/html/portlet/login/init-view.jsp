<%@ include file="../init-common.jsp" %>
<%@page import="com.liferay.portal.security.auth.AuthTokenUtil"%>
<%@ taglib prefix="theme" uri="http://liferay.com/tld/theme" %>
<%@ include file="/html/portlet/init-common.jsp" %>

<theme:defineObjects/>

<c:set var="homeURL" value="${themeDisplay.portalURL}"/>
<c:set var="defRedirect" value="${homeURL}"/>

<c:set var="signInURL" value="${themeDisplay.URLSignIn}"/>

<% String p_auth = AuthTokenUtil.getToken(request); %>
<c:set var="p_auth" value="<%= AuthTokenUtil.getToken(request)%>" />
<c:set var="postLoginURL" value="${homeURL}?p_auth=dEDBB1nu&p_p_id=58&p_p_lifecycle=1&p_p_state=normal&p_p_mode=view&saveLastPath=0&_58_struts_action=%2Flogin%2Flogin"/>

<portlet:actionURL var="defaultActionUrl" windowState="exclusive"/>

<portlet:actionURL var="loginAction" windowState="normal">
    <portlet:param name="action" value="loginAction"/>
</portlet:actionURL>

<portlet:renderURL var="defaultURL" portletMode="view" windowState="normal"/>

<portlet:renderURL var="homeUrl" portletMode="view" windowState="normal">
    <portlet:param name="action" value="backToHome"/>
</portlet:renderURL>

<portlet:resourceURL var="captchaURL" id="captcha">
</portlet:resourceURL>

<portlet:actionURL var="processForgot" windowState="normal">
    <portlet:param name="action" value="processForgot"/>
</portlet:actionURL>

<portlet:renderURL var="defaultURL" portletMode="view" windowState="normal"/>

<portlet:renderURL var="newPassword">
    <portlet:param name="action" value="password"/>
</portlet:renderURL>

<portlet:renderURL var="successLogin">
    <portlet:param name="action" value="successLogin"/>
</portlet:renderURL>

<portlet:renderURL var="forgotPassword">
    <portlet:param name="action" value="forgotPassword"/>
</portlet:renderURL>

<portlet:actionURL var="postLoginAction" windowState="normal">
    <portlet:param name="action" value="postLogin"/>
</portlet:actionURL>

<portlet:renderURL var="sendMailUrl" windowState="normal">
    <portlet:param name="action" value="mail"/>
</portlet:renderURL>

<portlet:renderURL var="savePreferenceURL">
    <portlet:param name="action" value="savePrefs"/>
</portlet:renderURL>

<portlet:actionURL var="enterEmailURL">
    <portlet:param name="action" value="linked_in_login" />
    <portlet:param name="redirect" value="/web/guest/home" />
</portlet:actionURL>

<c:set var="postLoginURL_" value="${postLoginAction}"/>