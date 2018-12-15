<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<form:form method="post" action="${pageContext.request.contextPath}/saveIssue" modelAttribute="issue">

    <table border="1" cellpadding="5">
        <caption>
            <h2>
                <caption>
                    <h2>
                        Please fill the form below.
                    </h2>
                </caption>
            </h2>
        </caption>
        <c:if test="${issue != null}">
            <input type="hidden" name="id" value="<c:out value='${issue.id}' />"/>
        </c:if>
        <tr>
            <th>Issue Description:</th>
            <td>
                <input type="text" name="issueDescription" size="100"
                       value="<c:out value='${issue.issueDescription}' />"
                />
            </td>
        </tr>

        <tr>
            <th>Workaround:</th>
            <td>
                <input type="text" name="workaround" size="100"
                       value="<c:out value='${issue.workaround}' />"
                />
            </td>
        </tr>
        <tr>
            <th>Jira:</th>
            <td>
                <input type="text" name="jira" size="100"
                       value="<c:out value='${issue.jira}' />"
                />
            </td>
        </tr>
        <tr>
            <th>Solution:</th>
            <td>
                <input type="text" name="solution" size="100"
                       value="<c:out value='${issue.solution}' />"
                />
            </td>
        </tr>

        <tr>
            <th>Source:</th>
            <td><form:select path="sourceSystem" value="<c:out value='${issue.sourceSystem}' />">
                <form:options items="${sourceSystem}"/>
            </form:select>
            </td>

        </tr>


        <tr>
            <td align="center">
                <input type="submit" value="Save"/>

            </td>
            <td><input align="center"
                       type="button"
                       value="Reset"
                       onmouseover="this.style.background='#3F5201'"
                       onmouseout="this.style.background='#9dce2c'"
                       onclick="this.form.reset();"/></td>

        </tr>>
        </tr>
    </table>
</form:form>

