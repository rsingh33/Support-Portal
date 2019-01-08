<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="container1" class="container">


    <div class="col-lg-offset-8 col-lg-8 col-sm-5">

        <button id ="expo" type="button" onclick="location.href='${pageContext.request.contextPath}/downloadExcel'"
                value="Export" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-download-alt"></span> Export</button>


        <button id ="expo" type="button" onclick="location.href='${pageContext.request.contextPath}/sendemail'"
                value="Send Email Handover" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-envelope"></span> Send Handover</button>
        &nbsp;


        <a id ="expo" class="btn btn-primary btn-sm " href="${pageContext.request.contextPath}/handoverform" role="button"><span class="glyphicon glyphicon-plus"></span> Add Issue</a>



    </div>


</div>


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

                    <c:forEach var="handover" items="${handovers}">
                        <tr>
                            <td><p><c:out value="${handover.reportedBy}"></c:out></p></td>
                            <td><p class="wrapText"><c:out value="${handover.emailSubject}"></c:out></p></td>
                            <td><p><a
                                    href="https://cedt-icg-jira.nam.nsroot.net/jira/browse/<c:out value='${handover.tracking}'></c:out>">
                                    ${handover.tracking}</a></p></td>
                            <td><p><c:out value="${handover.status}"></c:out></p></td>
                            <td><p><c:out value="${handover.currentlyWith}"></c:out></p></td>
                            <td><p><c:out value="${handover.environment}"></c:out></p></td>
                            <td><p><c:out value="${handover.comments}"></c:out></p></td>
                            <td>
                                <p><b>User: </b> <c:out value="${handover.username}"></c:out>,  <b>Time: </b> <c:out value=" ${handover.lastMod}"></c:out></p>

                            </td>
                            <td id="action">
                                <a href="${pageContext.request.contextPath}/handoverform/<c:out value='${handover.id}' />"><span
                                        class="glyphicon glyphicon-pencil"></span></a>
                                &nbsp;&nbsp;

                                <a class="delete" id="delete" type="submit" value="Delete" name="delete"
                                   href="${pageContext.request.contextPath}/delete/<c:out value='${handover.id}' />"><span
                                        class="glyphicon glyphicon-trash"></span></a>
                                &nbsp;&nbsp;
                                <a class="move" id="move" type="submit" value="Move" name="move"
                                   href="${pageContext.request.contextPath}/moveToBacklog/<c:out value='${handover.id}' />"><span
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

