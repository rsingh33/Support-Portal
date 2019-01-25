<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</br>
<c:if test="${not empty saved}">
    <div class="alert alert-success" role="alert">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${saved} &nbsp;
    </div>
</c:if>
<c:if test="${not empty notSaved}">
    <div class="alert alert-danger" role="alert">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${notSaved} &nbsp;
    </div>
</c:if>
<c:if test="${not empty moved}">
    <div class="alert alert-success" role="alert">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${moved} &nbsp;
    </div>
</c:if>

<c:if test="${not empty notMoved}">
    <div class="alert alert-danger" role="alert">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${notMoved} &nbsp;
    </div>
</c:if>
<c:if test="${not empty deleted}">
    <div class="alert alert-success" role="alert">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${deleted} &nbsp;
    </div>
</c:if>

<c:if test="${not empty notDeleted}">
    <div class="alert alert-danger" role="alert">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${notDeleted} &nbsp;
    </div>
</c:if>
        <a id ="expo" class="btn btn-primary btn-xs " href="${pageContext.request.contextPath}/backlogForm" role="button"><span
                class="glyphicon glyphicon-plus"></span> Add
            Issue</a>

</br>
</br>


<div id="container2" class="container-fluid">
    <div class="row">
        <div class="col-lg-12 col-sm-12">

            <div class="table-responsive">
                <table id="myTable" class="table table-striped table-hover compact nowrap table-condensed">

                    <thead>
                    <tr id="tableHead">
                        <th>Reporter</th>
                        <th>Email Subject</th>
                        <th>Jira</th>
                        <th>Status</th>
                        <th>Currently With</th>
                        <th>Environment</th>
                        <th>Comments</th>
                        <th>Last Mod Info</th>
                        <th>Actions</th>

                    </tr>
                    </thead>

                    <tbody id="tableBody">

                    <c:forEach var="backlog" items="${backlogs}">
                        <tr id="marginChange">
                            <td><p><c:out value="${backlog.reportedBy}"></c:out></p></td>
                            <td><p class="wrapText"><c:out value="${backlog.emailSubject}"></c:out></p></td>
                            <td><p><a
                                    href="https://cedt-icg-jira.nam.nsroot.net/jira/browse/<c:out value='${backlog.tracking}'></c:out>">
                                    ${backlog.tracking}</a></p></td>
                            <td><p><c:out value="${backlog.status}"></c:out></p></td>
                            <td><p><c:out value="${backlog.currentlyWith}"></c:out></p></td>
                            <td><p><c:out value="${backlog.environment}"></c:out></p></td>
                            <td><p><c:out value="${backlog.comments}"></c:out></p></td>
                            <td>
                                <p><b>User: </b> <c:out value="${backlog.lastModUser}"></c:out>
                                    <b> Time: </b> <c:out value=" ${backlog.lastMod}"></c:out></p>

                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/backlogForm/<c:out value='${backlog.id}' />"><span
                                        class="glyphicon glyphicon-pencil"></span></a>
                                &nbsp;&nbsp;

                                <a class="delete" id="delete" type="submit" value="Delete" name="delete"
                                   href="${pageContext.request.contextPath}/deleteBacklog/<c:out value='${backlog.id}' />"><span
                                        class="glyphicon glyphicon-trash"></span></a>
                                &nbsp;&nbsp;
                                <a class="move" id="move" type="submit" value="Move" name="move"
                                   href="${pageContext.request.contextPath}/moveToHandover/<c:out value='${backlog.id}' />"><span
                                        class="glyphicon glyphicon-move"></span></a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>

                </table>


            </div>
        </div>
    </div>


</div>

