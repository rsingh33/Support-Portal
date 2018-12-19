<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script type="text/javascript">
    $(document).ready(function () {
        $("a.delete").click(function (e) {
            if (!confirm('Are you sure you want to delete?')) {
                e.preventDefault();
                return false;
            }
            return true;
        });
    });

    function filter() {
        // Declare variables
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("myTable");
        tr = table.getElementsByTagName("tr");

        // Loop through all table rows, and hide those who don't match the search query
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[0];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }



</script>
<h2>
    <a href="${pageContext.request.contextPath}/issuesform">Add New Issue</a>
</h2>
<div><input type="text" id="myInput" onkeyup="filter()" placeholder="Filter by Issue Description"></div>
<div align="center">
    <table id="myTable" class="dataTable" border="1" cellpadding="5">
        <tr>
            <th align="left">Issue Description</th>
            <th align="left">Workaround</th>
            <th align="left">Solution</th>
            <th align="left">Jira</th>
            <th align="left">Source</th>
            <th align="left">Last Modified</th>
            <th align="left">Actions</th>
        </tr>
        <c:forEach var="issue" items="${issue}">
            <tr>
                <td><p><c:out value="${issue.issueDescription}"></c:out></p></td>
                <td><p><c:out value="${issue.workaround}"></c:out></p></td>
                <td><p><c:out value="${issue.solution}"></c:out></p></td>
                <td><p><a
                        href="https://cedt-icg-jira.nam.nsroot.net/jira/browse/<c:out value='${issue.jira}'></c:out>">${issue.jira}</a>
                </p></td>
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



