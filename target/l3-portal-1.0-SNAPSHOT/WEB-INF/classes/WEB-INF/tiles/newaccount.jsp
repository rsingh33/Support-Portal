
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<h2>Create New Account</h2>
<sf:form id="details" method="post" action="${pageContext.request.contextPath}/createaccount" modelAttribute="user">

    <table class="formtable">
        <tr>
            <td class="label"> Soeid:</td>
            <td class="control"><sf:input path="username" class="control" name="username" type="text"/>
                <div class="error"><sf:errors path="username"></sf:errors></div>

            </td>
        </tr>

        <tr>
            <td class="label"> Name:</td>
            <td class="control"><sf:input path="name" class="control" name="name" type="text"/>
                <div class="error"><sf:errors path="name"></sf:errors></div>

            </td>
        </tr>

        <tr>
            <td class="label"> Email:</td>
            <td class="control"><sf:input path="email" class="control" name="email" type="text"/>
                <div class="error"><sf:errors path="email"></sf:errors></div>
            </td>
        </tr>
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
            <td class="control"><input class="control" value="Create Account" type="submit"/></td>
        </tr>


    </table>
</sf:form>
