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
    <a href="${pageContext.request.contextPath}/handoverform" style="alignment: right">Add New Issue</a>
</h2>

<div align="center">
    <table border="1" cellpadding="5">
        <tr>
            <th>Reporter</th>
            <th>Email Subject</th>
            <th>Jira</th>
            <th>Status</th>
            <th>Currently With</th>
            <th>Environment</th>
            <th>Comments</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="handover" items="${handovers}">
            <tr>
                <td><p><c:out value="${handover.reportedBy}"></c:out></p></td>
                <td><p><c:out value="${handover.emailSubject}"></c:out></p></td>
                <td><p><a href= "https://cedt-icg-jira.nam.nsroot.net/jira/browse/<c:out value='${handover.tracking}'></c:out>">
                        ${handover.tracking}</a></p></td>
                <td><p><c:out value="${handover.status}"></c:out></p></td>
                <td><p><c:out value="${handover.currentlyWith}"></c:out></p></td>
                <td><p><c:out value="${handover.environment}"></c:out></p></td>
                <td><p><c:out value="${handover.comments}"></c:out></p></td>
                <td>
                    <a href="${pageContext.request.contextPath}/handoverform/<c:out value='${handover.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;

                    <a class="delete" id="delete" type="submit" value="Delete" name="delete"
                       href="${pageContext.request.contextPath}/delete/<c:out value='${handover.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
