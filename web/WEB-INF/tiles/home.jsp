<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="!isAuthenticated()">
    <script type="text/javascript">
        $document.ready(function () {
            document.f.username.focus();
        });
    </script>
    <h3>Login with Username and Password</h3>
    <c:if test="${param.error != null}">
        <p class="loginerror"> Please check your credentials</p>
    </c:if>
    <form name='f' action='${pageContext.request.contextPath}/login' method='POST'>
        <table class="formtable">
            <tr>
                <td>Soeid:</td>
                <td><input class="textInput" type='text' name='username' value=''></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password'/></td>
            </tr>

            <tr>
                <td>Remember me:</td>
                <td><input type='checkbox' name='remember-me'/></td>
            </tr>

            <tr>
                <td><input name="submit" type="submit" value="Login"/></td>
                <td><a href="${pageContext.request.contextPath}/forgot" methods="get">Forgot Password</a></td>
            </tr>
            <input name="_csrf" type="hidden" value="${_csrf.token}"/>
        </table>
    </form>
    <p><a href="${pageContext.request.contextPath}/newaccount"> Create New Account</a></p>
</sec:authorize>



