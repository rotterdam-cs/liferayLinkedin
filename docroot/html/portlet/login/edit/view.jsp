<%@ include file="../init.jsp" %>
<div class="faqWrapper">
    <header class="titleLogin">
        <h2><spring:message code="message.preferences"/></h2>
    </header>
    <c:if test="${successMsg ne null}">
        <div id="successMsg" class="portlet-msg-info">${successMsg}</div>
    </c:if>
    <c:if test="${errorMsg ne null}">
        <div id="errorMsg" class="portlet-msg-error">${errorMsg}</div>
    </c:if>
    <form:form modelAttribute="preferences" action="${savePreferenceURL}" id="preferencesForm" method="post">
        <div>
            <div class="formLine">
                <form:label path="linkedInIsEnabled">Linked In Enabled:</form:label>
                <form:checkbox path="linkedInIsEnabled" />
            </div>

            <div class="formLine">
                <form:label path="linkedInAppId">LinkedIn App Id: </form:label>
                <form:input path="linkedInAppId"/>
            </div>

            <div class="formLine">
                <form:label path="linkedInAppSecret">LinkedIn App Secret: </form:label>
                <form:input path="linkedInAppSecret"/>
            </div>

            <div class="formLine">
                <form:label path="linkedInRedirectURL">LinkedIn Redirect URL: </form:label>
                <form:input path="linkedInRedirectURL"/>
            </div>

            <div class="formLine">
                <form:label path="twitterIsEnabled">Twitter Enabled:</form:label>
                <form:checkbox path="twitterIsEnabled" />
            </div>

            <div class="formLine">
                <form:label path="twitterAppId">Twitter App Id: </form:label>
                <form:input path="twitterAppId"/>
            </div>

            <div class="formLine">
                <form:label path="twitterAppSecret">Twitter App Secret: </form:label>
                <form:input path="twitterAppSecret"/>
            </div>

            <div class="formLine">
                <form:label path="twitterRedirectURL">Twitter Redirect URL: </form:label>
                <form:input path="twitterRedirectURL"/>
            </div>

            <div class="formLine">
                <form:label path="emailFromName">E-mail from name: </form:label>
                <form:input path="emailFromName"/>
            </div>
            <div class="formLine">
                <form:label path="emailFromAddress">E-mail from address: </form:label>
                <form:input path="emailFromAddress"/>
            </div>
        </div>
        <input type="submit" value="Save" />
    </form:form>
</div>