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

<a id="expo" class="btn btn-primary btn-xs " href="${pageContext.request.contextPath}/issuesform" role="button"><span
        class="glyphicon glyphicon-plus"></span> Add Issue</a>

</br>
</br>


<div id="container2" class="container-fluid">
    <div class="row">
        <div class="col-lg-12 col-sm-12">

            <div class="table-responsive">
                <table id="myTable" class="table table-striped table-hover compact nowrap table-condensed">

                    <thead>
                    <tr id="tableHead">
                        <th>Issue Description</th>
                        <th>Workaround</th>
                        <th>Cause</th>
                        <th>Jira</th>
                        <th>Source</th>
                        <th>Last Mod Info</th>
                        <th>Actions</th>

                    </tr>
                    </thead>

                    <tbody id="tableBody">

                    <c:forEach var="issue" items="${issue}">
                        <tr id="marginChange">
                            <td><p class="wrapText"><c:out value="${issue.issueDescription}"></c:out></p></td>
                            <td><p class="wrapText"><c:out value="${issue.workaround}"></c:out></p></td>
                            <td><p class="wrapText"><c:out value="${issue.solution}"></c:out></p></td>
                            <td><p><a
                                    href="https://cedt-icg-jira.nam.nsroot.net/jira/browse/<c:out value='${issue.jira}'></c:out>">${issue.jira}</a>
                            </p></td>
                            <td><p><c:out value="${issue.sourceSystem}"></c:out></p></td>
                            <td>
                                <p><b>User: </b> <c:out value="${issue.username}"></c:out>, <b>Time: </b> <c:out
                                        value=" ${issue.lastMod}"></c:out></p>

                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/issuesform/<c:out value='${issue.id}' />"><span
                                        class="glyphicon glyphicon-pencil"></span></a>&nbsp;&nbsp;
                                <a class="delete" id="delete" type="submit" value="Delete" name="delete"
                                   href="${pageContext.request.contextPath}/deleteIssue/<c:out value='${issue.id}' />"><span
                                        class="glyphicon glyphicon-trash"></span></a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>

                </table>

            </div>

        </div>
    </div>
</div>





