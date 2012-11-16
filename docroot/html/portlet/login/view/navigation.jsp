<div id="login_navigation">
    <ul>
        <%
            Object linkedInAuthUrl = request.getAttribute("linkedInAuthUrl");
            Object twitterAuthUrl = request.getAttribute("twitterAuthUrl");
        %>
        <li>
            Login With:
        </li>
        <%--<c:if test="${!empty linkedInAuthUrl}">--%>
        <%
            if (linkedInAuthUrl != null) {
        %>
        <li>
            <a href="#" onclick="window.location.href = '${linkedInAuthUrl}'">
                <img src="/${pageContext.servletContext.servletContextName}/html/images/linkedin/linkedin.jpg" alt="Linked In" />
                Linked In
            </a>
        </li>
        <%
            }
        %>
        <%--</c:if>--%>
        <%--<c:if test="${!empty twitterAuthUrl}">--%>
        <%
            if (twitterAuthUrl != null) {
        %>
        <li>
            <a href="#" onclick="window.location.href = '${twitterAuthUrl}'">
                <img src="/${pageContext.servletContext.servletContextName}/html/images/twitter/twitter.jpg" alt="Twitter" />
                Twitter
            </a>
        </li>
        <%
            }
        %>
        <%--</c:if>--%>

    </ul>
</div>
