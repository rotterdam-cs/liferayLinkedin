<%@ include file="../init.jsp" %>
<script type="text/javascript" language="javascript">
    jQuery(document).ready(function(){
        var loginPortlet = new LoginPortlet({

            loginType: "standard",

            defaultActionUrl: "${defaultActionUrl}",
            logoutUrl: "${themeDisplay.pathMain}/portal/logout",
            forgotPasswordUrl: "${forgotPassword}",
            resetPasswordUrl: "${newPassword}",

            formSelectorId: "signInForm",
            loginSubmitId: "loginSubmit",
            forgetPasswordLinkId: "forgetPasswordLink",
            loginId: "login",
            passwordId: "password",
            login58Id: "_58_login",
            password58Id: "_58_password"
        });
    });
</script>
<jsp:include page="header.jsp" />
<div class="roundedcornr_login_box">
    <div class="roundedcornr_login_top">
        <div></div>
    </div>
    <div class="loginRegion">

        <c:if test="${errorMsg ne null}">
            <div class="errorMsg">
                    ${errorMsg}
            </div>
        </c:if>
        <c:if test="${successMsg ne null}">
            <div class="successMsg">
                    ${successMsg}
            </div>
        </c:if>

        <c:choose>
            <c:when test="${themeDisplay.signedIn}">
                <div>
                    Welcome, ${themeDisplay.user.firstName} ${themeDisplay.user.lastName}!
                </div>
            </c:when>
            <c:otherwise>
                <div class="leftSide">
                    <div style="display: none" id="errMsgContent">
                        <p class="infoLine">
                            <spring:message code="message.login.error"/>
                        </p>
                    </div>
                    <div id="msgContent">
                        <div><spring:message code="message.login.welcome"/></div>
                        <div><spring:message code="message.login.welcome.msg"/></div>
                    </div>

                    <p class="infoLine"><spring:message code="message.login.if.you.forgot.password.begin"/> <a
                            id="forgetPasswordLink" class="pointer">
                        <spring:message code="message.login.if.you.forgot.password.end"/></a>.
                    </p>

                    <div class="checkLogin"><spring:message code="message.login.remember.me"/>
                        <input id="_58_rememberMe" type="hidden" value="false" name="_58_rememberMe">
                        <input type="checkbox" name="_58_rememberMeCheckbox" id="_58_rememberMeCheckbox" onclick="Liferay.Util.updateCheckboxValue(this);"/>
                    </div>

                </div>
                <div class="rightSide">
                    <form id="signInForm" name="signInForm" method="POST" action="${postLoginURL}">
                        <div class="rowInput">
                            <label for="login"><spring:message code="message.login.username"/></label>
                            <div id="loginInputField" class="inputField">
                                <input type="text" name="login" id="login"/>
                                <input type="hidden" name="_58_login" id="_58_login"/>
                            </div>
                        </div>
                        <div class="rowInput">
                            <label for="password"><spring:message code="message.login.password"/></label>
                            <div id="passwordInputField" class="inputField">
                                <input type="password" name="password" id="password"/>
                                <input type="hidden" name="_58_password" id="_58_password"/>
                            </div>
                            <input type="hidden" name="_58_redirect" value="${defRedirect}">

                            <div class="hidden error_text" id="error_div">
                                <spring:message code="message.login.error.wrong"/>
                                <br/>
                                <b><spring:message code="message.login.error.repeat"/></b>
                            </div>

                        </div>
                        <div class="lineButton">
                            <a id="loginSubmit" class="cursor-pointer buttonLogin">
                                <spring:message code="message.login.login"/>
                            </a>
                        </div>
                    </form>
                </div>
                <div class="clear"></div>
                <jsp:include page="navigation.jsp" />
            </c:otherwise>
        </c:choose>
    </div>
    <div class="roundedcornr_login_bottom">
        <div></div>
    </div>
</div>