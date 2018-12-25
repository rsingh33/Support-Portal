<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="container1" class="container-fluid">


</div>


<div id="container2" class="container-fluid">
    <div class="row">
        <div class="col-lg-12 col-sm-12">

            <div class="table-responsive">
                <table id="myTable" class="table table-striped table-sortable">

                    <thead>
                    <tr id="tableHead">
                        <th>Username</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Enabled</th>
                        <th>Actions</th>

                    </tr>
                    </thead>

                    <tbody id="tableBody">

                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td><c:out value="${user.username}"></c:out></td>
                            <td><c:out value="${user.email}"></c:out></td>
                            <td><c:out value="${user.authority}"></c:out></td>
                            <td><c:out value="${user.enabled}"></c:out></td>
                            <td>
                                <a class="delete" id="delete" type="submit" value="Delete" name="delete"
                                   href="${pageContext.request.contextPath}/deleteUser/<c:out value='${user.username}' />"><span
                                        class="glyphicon glyphicon-trash"></span></a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>

                </table>


            </div>
        </div>
    </div>


    <div>
        <a href="#" class="paginate" id="previous">Previous</a> |
        <a href="#" class="paginate" id="next">Next</a>
    </div>
</div>



<%--<table class="sortable" id="myTable">--%>
    <%--<tr>--%>
        <%--<th>Username</th>--%>
        <%--<th>Email</th>--%>
        <%--<th>Role</th>--%>
        <%--<th>Enabled</th>--%>
        <%--<th>Actions</th>--%>

    <%--</tr>--%>
    <%--<c:forEach var="user" items="${users}">--%>

        <%--<tr>--%>
            <%--<td><c:out value="${user.username}"></c:out></td>--%>
            <%--<td><c:out value="${user.email}"></c:out></td>--%>
            <%--<td><c:out value="${user.authority}"></c:out></td>--%>
            <%--<td><c:out value="${user.enabled}"></c:out></td>--%>
            <%--<td>--%>
                <%--<a class="delete" id="delete" type="submit" value="Delete" name="delete"--%>
                   <%--href="${pageContext.request.contextPath}/deleteUser/<c:out value='${user.username}' />">Delete</a>--%>
            <%--</td>--%>
        <%--</tr>--%>

    <%--</c:forEach>--%>

<%--</table>--%>
