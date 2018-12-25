<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="container1" class="container-fluid">


    <div class="row">
        <div class="col-lg-3 col-sm-4">
            <div>

                <input type="text" class="form-control" id="myInput" onkeyup="filter()"
                       placeholder="Filter by Issue Description">
            </div>

        </div>
        <%--<div class="col-lg-9 col-sm-8">--%>
            <%--<button type="button" class="btn btn-primary">Reset Filter</button>--%>


        <%--</div>--%>


    </div>
</div>


<div id="container2" class="container-fluid">
    <div class="row">
        <div class="col-lg-12 col-sm-12">

            <div class="table-responsive">
                <table id="myTable" class="table table-striped table-sortable">

                    <thead>
                    <tr id="tableHead">
                        <th>Issue Description</th>
                        <th>Workaround</th>
                        <th>Solution</th>
                        <th>JIRA</th>
                        <th>Source</th>
                        <th>Last Modified</th>
                        <th>Actions</th>

                    </tr>
                    </thead>

                    <tbody >

                    <c:forEach var="issue" items="${issue}">
                        <tr>
                            <td><p class="wrapText"><c:out value="${issue.issueDescription}"></c:out></p></td>
                            <td><p class="wrapText"><c:out value="${issue.workaround}"></c:out></p></td>
                            <td><p class="wrapText"><c:out value="${issue.solution}"></c:out></p></td>
                            <td><p><a
                                    href="https://cedt-icg-jira.nam.nsroot.net/jira/browse/<c:out value='${issue.jira}'></c:out>">${issue.jira}</a>
                            </p></td>
                            <td><p><c:out value="${issue.sourceSystem}"></c:out></p></td>
                            <td><p><c:out value="${issue.lastMod}"></c:out></p></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/issuesform/<c:out value='${issue.id}' />"><span class="glyphicon glyphicon-pencil"></span></a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a class="delete" id="delete" type="submit" value="Delete" name="delete"
                                   href="${pageContext.request.contextPath}/deleteIssue/<c:out value='${issue.id}' />"><span class="glyphicon glyphicon-trash"></span></a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>

                </table>

            </div>

            </div>
        </div>
    </div>

    <div  class="container">


        <div class="col-lg-offset-11 col-lg-9 col-sm-8">


            <a class="btn btn-primary" href="${pageContext.request.contextPath}/issuesform" role="button">+ Add Issue</a>


            <button type="button" onclick="location.href='${pageContext.request.contextPath}/sendemail'" value="Send Email Handover" class="btn btn-primary">Send Email-Handover</button>


            <button type="button" onclick="location.href='${pageContext.request.contextPath}/downloadExcel'" value="Export" class="btn btn-primary">Export</button>
            <%--<button type="button" class="btn btn-primary">Devops Dashboard</button>--%>


        </div>


    </div>
</div>



<%--Old Version--%>
<%--<h2>--%>
    <%--<a href="${pageContext.request.contextPath}/issuesform">Add New Issue</a>--%>
<%--</h2>--%>
<%--<div><input type="text" id="myInput" onkeyup="filter()" placeholder="Filter by Issue Description"></div>--%>
<%--<div align="center">--%>
    <%--<table  class="sortable" id="myTable" class="dataTable" border="1" cellpadding="5">--%>
        <%--<thead>--%>
            <%--<th align="left">Issue Description</th>--%>
            <%--<th align="left">Workaround</th>--%>
            <%--<th align="left">Solution</th>--%>
            <%--<th align="left">Jira</th>--%>
            <%--<th align="left">Source</th>--%>
            <%--<th align="left">Last Modified</th>--%>
            <%--<th align="left">Actions</th>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <%--<c:forEach var="issue" items="${issue}">--%>
            <%--<tr>--%>
                <%--<td><p><c:out value="${issue.issueDescription}"></c:out></p></td>--%>
                <%--<td><p><c:out value="${issue.workaround}"></c:out></p></td>--%>
                <%--<td><p><c:out value="${issue.solution}"></c:out></p></td>--%>
                <%--<td><p><a--%>
                        <%--href="https://cedt-icg-jira.nam.nsroot.net/jira/browse/<c:out value='${issue.jira}'></c:out>">${issue.jira}</a>--%>
                <%--</p></td>--%>
                <%--<td><p><c:out value="${issue.sourceSystem}"></c:out></p></td>--%>
                <%--<td><p><c:out value="${issue.lastMod}"></c:out></p></td>--%>
                <%--<td>--%>
                    <%--<a href="${pageContext.request.contextPath}/issuesform/<c:out value='${issue.id}' />">Edit</a>--%>
                    <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
                    <%--<a class="delete" id="delete" type="submit" value="Delete" name="delete"--%>
                       <%--href="${pageContext.request.contextPath}/deleteIssue/<c:out value='${issue.id}' />">Delete</a>--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
        <%--</tbody>--%>
    <%--</table>--%>
<%--</div>--%>

<div>
    <a href="#" class="paginate" id="previous">Previous</a> |
    <a href="#" class="paginate" id="next">Next</a>
</div>

