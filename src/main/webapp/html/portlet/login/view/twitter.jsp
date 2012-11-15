<%@ include file="../init.jsp" %>
<script type="text/javascript" language="javascript">
    jQuery(document).ready(function(){
        var twitter = new LoginPortlet({

            loginType: "twitter",

            defaultActionUrl: "${defaultActionUrl}",
            logoutUrl: "${themeDisplay.pathMain}/portal/logout",
            forgotPasswordUrl: "${forgotPassword}",
            resetPasswordUrl: "${newPassword}",

            formSelectorId: "twitterLoginForm",
            loginSubmitId: "loginTwitter",
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
                <c:if test="${twitterUser ne null}">
                    <div class="twitterHeader">
                        <img src="/${pageContext.servletContext.servletContextName}/html/images/twitter/twitter.jpg" />
                        <h1>Twitter</h1>
                    </div>
                    <div class="linkedin-welcome">
                        Welcome, ${twitterUser.name} !
                    </div>
                    <div class="linkedin-hint">
                        Please, specify your email below:
                    </div>
                    <form id="twitterLoginForm" method="POST" action="${postLoginURL}">
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
                            <input type="button" value="Login" name="loginTwitter" id="loginTwitter" />
                        </div>
                    </form>
                </c:if>
            </c:otherwise>
        </c:choose>
    </div>
</div>