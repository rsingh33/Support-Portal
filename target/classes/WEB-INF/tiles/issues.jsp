<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    function onDeleteClick(event) {


        var doDelete = confirm("Are you sure you want to delete");

        if (doDelete == false) {
            event.preventDefault();
        }
    }

    function onReady() {
        $("#delete").click(onDeleteClick);
    }

    $(document).ready(onReady);
</script>
<h2>
    <a href="${pageContext.request.contextPath}/issuesform">Add New Issue</a>
</h2>

<div align="center">
    <table border="1" cellpadding="5">
        <tr>
            <th>Issue Description</th>
            <th>Workaround</th>
            <th>Solution</th>
            <th>Jira</th>
            <th>Source</th>
            <th>Last Modified</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="issue" items="${issue}">
            <tr>
                <td><p><c:out value="${issue.issueDescription}"></c:out></p></td>
                <td><p><c:out value="${issue.workaround}"></c:out></p></td>
                <td><p><c:out value="${issue.solution}"></c:out></p></td>
                <td><p><a href="https://cedt-icg-jira.nam.nsroot.net/jira/browse/<c:out value='${issue.jira}'></c:out>">${issue.jira}</a></p></td>
                <td><p><c:out value="${issue.sourceSystem}"></c:out></p></td>
                <td><p><c:out value="${issue.lastMod}"></c:out></p></td>
                <td>
                    <a href="${pageContext.request.contextPath}/issuesform/<c:out value='${issue.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a class="delete" id="delete" type="submit" value="Delete" name="delete"
                       href="${pageContext.request.contextPath}/deleteIssue/<c:out value='${issue.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>



