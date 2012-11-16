<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %> --%>
<%@ taglib prefix="aui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>

<portlet:defineObjects/>

<c:set var="exclusive" value="<%=LiferayWindowState.EXCLUSIVE.toString()%>"/>

<c:set var="namespace" value="${renderResponse.namespace}"/>

<%--
<script type="text/javascript" language="javascript">
	$.blockUI.defaults.message = '<h2><spring:message code="message.global.loading"/></h2>';
</script>
--%>
