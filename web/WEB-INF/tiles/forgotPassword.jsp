<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<h2>Please enter your email to get a password reset link</h2>

<form:form  action="${pageContext.request.contextPath}/forgot" method="post" modelAttribute="message">
    <table>
        <tr>
            <td>
                <input type="text" name="email">
            </td>
            <td>
                <button type="submit">Send email</button>
            </td>

        </tr>

    </table>

    <div><p>${message}</p></div>
</form:form>


