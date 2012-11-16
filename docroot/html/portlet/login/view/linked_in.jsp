<%@ include file="../init.jsp" %>
<script type="text/javascript" language="javascript">
    jQuery(document).ready(function(){
        var linkedIn = new LoginPortlet({

            loginType: "linkedIn",

            defaultActionUrl: "${defaultActionUrl}",
            logoutUrl: "${themeDisplay.pathMain}/portal/logout",
            forgotPasswordUrl: "${forgotPassword}",
            resetPasswordUrl: "${newPassword}",

            formSelectorId: "linkedInLoginForm",
            loginSubmitId: "loginLinkedIn",
            forgetPasswordLinkId: "forgetPasswordLink",
            loginId: "login",
            passwordId: "password",
            login58Id: "_58_login",
            password58Id: "_58_password"
        });
    });
</script>
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
                Welcome, ${themeDisplay.user.firstName} ${themeDisplay.user.lastName} !
            </c:when>
            <c:otherwise>
                <c:if test="${person ne null}">
                    <div class="linkedInHeader">
                        <img src="/${pageContext.servletContext.servletContextName}/html/images/linkedin/linkedin.jpg" />
                        <h1>LinkedIN</h1>
                    </div>
                    <div class="linkedin-welcome">
                        Welcome, ${person.firstName} ${person.lastName}!
                    </div>
                    <div class="linkedin-hint">
                        Please, specify your email below:
                    </div>
                    <form id="linkedInLoginForm" method="POST" action="${postLoginURL}">
                        <div class="linkedin-form-body">
                            <div class="linkedin-left">
                                <label for="email">E-mail address</label>
                            </div>
                            <div class="linkedin-right">
                                <input type="text" name="email" id="email" />
                                <input type="hidden" name="_58_login" id="_58_login"/>
                                <input type="hidden" name="_58_password" id="_58_password"/>
                                <input type="hidden" name="_58_redirect" value="${defRedirect}">
                            </div>
                        </div>
                        <div class="linkedin-form-footer">
                            <input type="button" value="Login" name="loginLinkedIn" id="loginLinkedIn" style="" />
                        </div>
                    </form>
                </c:if>
            </c:otherwise>
        </c:choose>
    </div>
</div>