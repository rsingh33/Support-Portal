<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<p><a class="title" href="<c:url value='/'></c:url>">Offers</a></p>

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