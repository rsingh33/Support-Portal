<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<form:form method="post" action="${pageContext.request.contextPath}/reset"  >
    <div>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Please enter the passwords:
                </h2>
            </caption>
            <tr hidden>

                <td>
                    <input  id="token" name="token" size="50" value="${resetToken}"/>
                </td>
            </tr>
            <tr>
                <td class="label"> Password:</td>
                <td>
                    <input type="password" id="password" name="password" size="50"/>
                    <div class="error"><sf:errors path="password"></sf:errors></div>
                </td>
            </tr>

            <tr>
                <td class="label"> Confirm Password:</td>
                <td>
                    <input type="password" id="confirmpass" name="confirmPassword" size="50"/>
                    <div id="matchpass"></div>
                </td>
            </tr>

            <tr>
                <td align="center">
                    <input type="submit" value="Save" onclick="document.getElementById('login-link').style.display = 'block' ;" />

                </td>
            </tr>
        </table>
    </div>
    <div class="registrationFormAlert" id="divCheckPasswordMatch"></div>
    <div >${message} </div>

</form:form>


