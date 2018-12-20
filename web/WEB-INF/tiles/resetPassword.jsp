<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<h2>Please enter your email to get a password reset link</h2>

<form:form action="${pageContext.request.contextPath}/reset" method="post">
    <table>
        <tr>
            <td class="label"> Password:</td>
            <td><sf:input id="password" path="password" class="control" name="password" type="password"/>
                <div class="error"><sf:errors path="password"></sf:errors></div>
            </td>
        </tr>

        <tr>
            <td class="label">Confirm Password:</td>
            <td><input id="confirmpass" class="control" name="confirmpass" type="password"/>
                <div id="matchpass"></div>
            </td>
        </tr>
        <tr>
            <td class="label"></td>
            <td class="control"><input class="control" value="Change Password" type="submit"/></td>
        </tr>

    </table>
</form:form>

