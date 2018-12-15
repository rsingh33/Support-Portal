<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
<a class="title" href="${pageContext.request.contextPath}/">Home</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a class="title" href="${pageContext.request.contextPath}/handover">Handover</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a class="title" href="${pageContext.request.contextPath}/issues">Issues-Database</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a class="title" href="${pageContext.request.contextPath}/releasemanager">Release-Manager</a>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_admin')">
    <p><a class="title" href="${pageContext.request.contextPath}/admin">Admin</a></p>
</sec:authorize>

<sec:authorize access="!isAuthenticated()">
    <p><a class="login" href="<c:url value='/login'></c:url>">Login</a></p>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <c:url var="logoutUrl" value="/logout"/>
    <form class="logout" action="${logoutUrl}" method="post">
        <input type="submit" value="Logout"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

</sec:authorize>