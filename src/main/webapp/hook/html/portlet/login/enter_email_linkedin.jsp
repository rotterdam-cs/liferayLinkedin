<%@ include file="/html/portlet/login/init.jsp" %>

<style type="text/css">
	.navigation {
		background: #eee;
		border-top: 1px solid #BFBFBF;
		padding: 10px;
	}

    .linkedin-welcome {
        margin: 5px;
        font-size: 12pt;
        width: 420px;
        text-align: center;
    }

    .linkedin-hint {
        color: gray;
        font-size: 10pt;
        width: 420px;
        text-align: center;
    }

    .linkedin-form-body {
        overflow: hidden;
        clear: both;
        width: 420px;
    }

    .linkedin-left {
        float: left;
        text-align: right;
        margin: 5px;
        width: 200px;
    }

    .linkedin-right {
        float: right;
        text-align: left;
        margin: 5px;
        width: 200px;
    }


    .linkedin-form-footer {
        width: 420px;
        text-align: center;
    }

    .linkedin-form-footer input {
        margin-bottom: 20px;
    }
</style>

<portlet:actionURL var="enterEmailURL">
	<portlet:param name="saveLastPath" value="0" />
	<portlet:param name="action" value="enter_email" />
	<portlet:param name="redirect" value="/web/guest/home" />
	<portlet:param name="struts_action" value="/login/enter_email" />
</portlet:actionURL>

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

<c:if test="${person ne null}">

    <div class="linkedin-welcome">
        Welcome, ${person.firstName} ${person.lastName}!
    </div>
    <div class="linkedin-hint">
        Please, specify your email below:
    </div>


    <form action="${enterEmailURL}" method="post">
        <div class="linkedin-form-body">
            <div class="linkedin-left">
                E-Mail:
            </div>
            <div class="linkedin-right">
                <input type="text" name="email" />
            </div>
        </div>
        <div class="linkedin-form-footer">
            <input type="submit" value="Send" />
        </div>
    </form>
</c:if>

<liferay-util:include page="/html/portlet/login/navigation.jsp" />