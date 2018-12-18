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

<table class="formtable">
    <tr>
        <th>Username</th>
        <th>Email</th>
        <th>Role</th>
        <th>Enabled</th>
        <th>Actions</th>

    </tr>
    <c:forEach var="user" items="${users}">

        <tr>
            <td><c:out value="${user.username}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${user.authority}"></c:out></td>
            <td><c:out value="${user.enabled}"></c:out></td>
            <td>
                <a class="delete" id="delete" type="submit" value="Delete" name="delete"
                   href="${pageContext.request.contextPath}/deleteUser/<c:out value='${user.username}' />">Delete</a>
            </td>
        </tr>

    </c:forEach>

</table>
