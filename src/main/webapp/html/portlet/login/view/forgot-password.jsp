<%@ include file="../init.jsp" %>
<div class="roundedcornr_login_box">
    <div class="roundedcornr_login_top">
        <div></div>
    </div>
    <div class="loginRegion">
        <div class="leftSide">

            <p class="infoLine"><spring:message code="message.login.if.you.forgot.password"/>
            <%--, <a href="${forgotPassword}"><spring:message code="message.login.if.you.forgot.password.end"/></a>.--%></p>

            <%--<div class="checkLogin"><spring:message code="message.login.remember.me"/> <input type="checkbox" checked="true"/></div>--%>
        </div>
        <div class="rightSide">

            <form id="forgotPasswordForm" action="${processForgot}" method="POST">

                <div class="rowInput">
                    <label for="email"><spring:message code="message.login.enter.mail"/></label>
                    <div class="inputField"><input type="text" id="email" name="email" value="${email}"/></div>
                    <c:if test="${emailError ne null}">
                        <div id="message" style="color:red;"><spring:message code="${emailError}"/></div>
                    </c:if>
                </div>
                <div class="rowInput">

                    <span><spring:message code="message.login.copy.code"/></span>
                    <liferay-ui:captcha url="${captchaURL}"/>
                    <c:if test="${captchaError ne null}">
                        <div id="message" style="color:red;"><spring:message code="${captchaError}"/> </div>
                    </c:if>

                    <label for="captchaText"><spring:message code="message.login.copy.code"/></label>
                    <div><img src="/portlet/html/images/codeCopy.png" /></div>
                 <div class="inputFieldSmall"><input type="text" id="captchaText" name="captchaText" /></div>
                </div>
                <div class="lineButton">
                    <a class="buttonLogin" href="javascript:void(0)" onclick="processForgot()"><spring:message code="message.login.continue"/></a>
                </div>
            </form>
        </div>
        <div class="clear"></div>
    </div>
    <div class="roundedcornr_login_bottom">
        <div></div>
    </div>
</div>

<script type="text/javascript">

    function processForgot() {

        $('#forgotPasswordForm').submit();

    }

    function successResponse(responseText, statusText, xhr, $form) {

       console.log(responseText, statusText, xhr, $form);
    }

</script>