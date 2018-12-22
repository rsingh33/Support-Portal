<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div>
    <input type="button" onclick="location.href='${pageContext.request.contextPath}/sendemail' "
           value="Send Email Handover">
</div>

<div>
    <input type="button" onclick="location.href='${pageContext.request.contextPath}/downloadExcel'" value="Export">

</div>

<h2>
    <a href="${pageContext.request.contextPath}/handoverform" style="alignment: right">Add New Issue</a>
</h2>

<div><input type="text" id="myInput" onkeyup="filter()" placeholder="Filter by Reporter"></div>
<div align="center">
    <table class="sortable" id="myTable" border="1" cellpadding="5">
        <thead>
        <th>Reporter</th>
        <th>Email Subject</th>
        <th>Jira</th>
        <th>Status</th>
        <th>Currently With</th>
        <th>Environment</th>
        <th>Comments</th>
        <th>Last Modified</th>
        <th>Actions</th>
        </thead>
        <tbody>
        <c:forEach var="handover" items="${handovers}">
            <tr>
                <td><p><c:out value="${handover.reportedBy}"></c:out></p></td>
                <td><p><c:out value="${handover.emailSubject}"></c:out></p></td>
                <td><p><a
                        href="https://cedt-icg-jira.nam.nsroot.net/jira/browse/<c:out value='${handover.tracking}'></c:out>">
                        ${handover.tracking}</a></p></td>
                <td><p><c:out value="${handover.status}"></c:out></p></td>
                <td><p><c:out value="${handover.currentlyWith}"></c:out></p></td>
                <td><p><c:out value="${handover.environment}"></c:out></p></td>
                <td><p><c:out value="${handover.comments}"></c:out></p></td>
                <td><p><c:out value="${handover.lastMod}"></c:out></p></td>
                <td>
                    <button><a href="${pageContext.request.contextPath}/handoverform/<c:out value='${handover.id}'/>">Edit</a>
                    </button>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <button><a class="delete" id="delete" type="submit" value="delete" name="delete"
                               href="${pageContext.request.contextPath}/delete/<c:out value='${handover.id}'/>">Delete</a>
                    </button>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div>
    <a href="#" class="paginate" id="previous">Previous</a> |
    <a href="#" class="paginate" id="next">Next</a>
</div>
