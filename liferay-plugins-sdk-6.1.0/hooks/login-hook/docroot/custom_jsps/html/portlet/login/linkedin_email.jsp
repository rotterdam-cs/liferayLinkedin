<%--
/**
 * Copyright (c) 2012 Rotterdam CS, Inc. All rights reserved.
 
 * @author Rajesh
 */
--%>

<%@ include file="/html/portlet/login/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
%>
 
<portlet:actionURL var="linkedInAction" secure="true">
	<portlet:param name="struts_action" value="/login/linkedin_connect_oauth" />
	<portlet:param name="redirect" value="<%=redirect%>" />
</portlet:actionURL>

<aui:form action="<%= linkedInAction %>" method="post" name="fm">

	<liferay-ui:error exception="<%= UserEmailAddressException.class %>" message="please-enter-a-valid-email-address" />
	
	<aui:fieldset>
		<aui:input label="linkedin-email-address" name="emailAddress" showRequiredLabel="<%= false %>" type="text" value="">
			<aui:validator name="required" />
		</aui:input>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" value="Register" />
	</aui:button-row>
</aui:form>

		