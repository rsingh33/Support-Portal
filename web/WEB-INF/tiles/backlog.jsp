<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div id="container2" class="container-fluid">
    <div class="row">
        <div class="col-lg-12 col-sm-12">

            <div class="table-responsive">
                <table id="myTable" class="table table-striped table-hover">

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
                        <tr>
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
                                <p><b>User </b> <c:out value="${backlog.username}"></c:out>
                                    <b> Time </b> <c:out value=" ${backlog.lastMod}"></c:out></p>

                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/backlogForm/<c:out value='${backlog.id}' />"><span
                                        class="glyphicon glyphicon-pencil"></span></a>
                                &nbsp;&nbsp;&nbsp;&nbsp;

                                <a class="delete" id="delete" type="submit" value="Delete" name="delete"
                                   href="${pageContext.request.contextPath}/deleteBacklog/<c:out value='${backlog.id}' />"><span
                                        class="glyphicon glyphicon-trash"></span></a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
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

    <div id="container3" class="container">


        <div class="col-lg-offset-11 col-lg-9 col-sm-8">


            <a class="btn btn-primary" href="${pageContext.request.contextPath}/backlogForm" role="button">+ Add Issue</a>


            <%--<button type="button" onclick="location.href='${pageContext.request.contextPath}/sendemail'"
                    value="Send Email Handover" class="btn btn-primary">Send Email-Handover
            </button>

            &lt;%&ndash;<button type="button" class="btn btn-primary">Issues</button>&ndash;%&gt;
            <button type="button" onclick="location.href='${pageContext.request.contextPath}/downloadExcel'"
                    value="Export" class="btn btn-primary">Export
            </button>--%>
            <%--<button type="button" class="btn btn-primary">Devops Dashboard</button>--%>


        </div>


    </div>

</div>

