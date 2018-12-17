<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<form:form method="post" action="${pageContext.request.contextPath}/save" modelAttribute="handover">

    <table border="1" cellpadding="5">
        <caption>
            <h2>
                Please fill the form below.
            </h2>
        </caption>
        <c:if test="${handover != null}">
            <input type="hidden" name="id" value="<c:out value='${handover.id}' />"/>
        </c:if>
        <tr>
            <th>Reported By:</th>
            <td>
                <input type="text" name="reportedBy" size="100"
                       value="<c:out value='${handover.reportedBy}' />"
                />
            </td>
        </tr>

        <tr>
            <th>Email Subject:</th>
            <td>
                <input type="text" name="emailSubject" size="100"
                       value="<c:out value='${handover.emailSubject}' />"
                />
            </td>
        </tr>

        <tr>
            <th>Jira:</th>
            <td>
                <input type="text" name="tracking" size="100"
                       value="<c:out value='${handover.tracking}' />"
                />
            </td>
        </tr>

        <tr>
            <th>Status:</th>
            <td><form:select path="status" value="<c:out value='${handover.status}' />">
                <form:options items="${status}"/>
            </form:select>
            </td>

        </tr>
        <tr>
            <th>Environment:</th>
            <td><form:select path="environment" value="<c:out value='${handover.environment}' />">
                <form:options items="${env}"/>
            </form:select>
            </td>

        </tr>
        <tr>
            <th>Currently With:</th>
            <td><form:select path="currentlyWith" value="<c:out value='${handover.currentlyWith}' />">
                <form:options items="${curr}"/>
            </form:select>
            </td>

        </tr>


        <tr>
            <th>Comments:</th>
            <td>
                <input type="text" name="comments" size="100"
                       value="<c:out value='${handover.comments}' />"
                />
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

        </tr>
    </table>
</form:form>

