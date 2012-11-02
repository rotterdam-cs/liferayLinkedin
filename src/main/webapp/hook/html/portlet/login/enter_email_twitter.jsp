<%@ include file="/html/portlet/login/init.jsp" %>

<style type="text/css">
    .navigation {
        background: #eee;
        border-top: 1px solid #BFBFBF;
        padding: 10px;
    }

    .twitter-welcome {
        margin: 5px;
        font-size: 12pt;
        width: 420px;
        text-align: center;
    }

    .twitter-hint {
        color: gray;
        font-size: 10pt;
        width: 420px;
        text-align: center;
    }

    .twitter-form-body {
        overflow: hidden;
        clear: both;
        width: 420px;
    }

    .twitter-left {
        float: left;
        text-align: right;
        margin: 5px;
        width: 200px;
    }

    .twitter-right {
        float: right;
        text-align: left;
        margin: 5px;
        width: 200px;
    }


    .twitter-form-footer {
        width: 420px;
        text-align: center;
    }

    .twitter-form-footer input {
        margin-bottom: 20px;
    }
</style>

<portlet:actionURL var="confirmTwitterURL">
    <portlet:param name="saveLastPath" value="0" />
    <portlet:param name="action" value="confirm_twitter" />
    <portlet:param name="redirect" value="/web/guest/home" />
    <portlet:param name="struts_action" value="/login/twitter" />
</portlet:actionURL>

<c:if test="${errorMsg ne null}">
    <div class="errorMsg">
        ${errorMsg}
    </div>
</c:if>

<c:if test="${twitterUser ne null}">
    <div class="twitter-welcome">
        Welcome, ${twitterUser.name}!
    </div>
    <div class="twitter-hint">
        Please, specify your email below:
    </div>
    <form action="${confirmTwitterURL}" method="post">
        <div class="twitter-form-body">
            <div class="twitter-left">
                E-Mail:
            </div>
            <div class="twitter-right">
                <input type="text" name="email" />
            </div>
        </div>
        <div class="twitter-form-footer">
            <input type="submit" value="Send" />
        </div>
    </form>
</c:if>

<liferay-util:include page="/html/portlet/login/navigation.jsp" />