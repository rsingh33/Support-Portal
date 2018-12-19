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
    <table id="myTable" border="1" cellpadding="5">
        <tr>
            <th>Reporter</th>
            <th>Email Subject</th>
            <th>Jira</th>
            <th>Status</th>
            <th>Currently With</th>
            <th>Environment</th>
            <th>Comments</th>
            <th>Last Modified</th>
            <th>Actions</th>
        </tr>
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
    </table>
</div>
