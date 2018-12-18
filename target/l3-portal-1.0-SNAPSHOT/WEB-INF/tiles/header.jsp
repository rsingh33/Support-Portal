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
    &nbsp;&nbsp;&nbsp;&nbsp;
    <a class="title" href="http://ddivmddsf4:8080/DevOps/index.html">DevOps-Dashboard</a>
    &nbsp;&nbsp;&nbsp;&nbsp;
      <sec:authorize access="hasRole('ROLE_admin')">
        <a class = "title" href="${pageContext.request.contextPath}/admin">Admin</a>
    </sec:authorize>
    <c:url var="logoutUrl" value="/logout"/>
    <form class="logout" action="${logoutUrl}" method="post">
        <input type="submit" value="Logout"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

</sec:authorize>

