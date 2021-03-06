<%--suppress ALL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


</br>


<a id="expo" class="btn btn-primary btn-xs " href="${pageContext.request.contextPath}/newRelease" role="button"><span
        class="glyphicon glyphicon-plus"></span> Add New Release</a>

<button id="expo" type="button" onclick="location.href='${pageContext.request.contextPath}/downloadTemplate'"
        value="Export" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-download-alt"></span>
    Template
</button>
<%--@elvariable id="excelRow" type="com.citi.spring.web.dao.entity.ExcelRow"--%>

<form:form action="releaseHandler" method="post" modelAttribute="excelRow">

    <form:select path="releaseName" class="btn btn-default dropdown-toggle btn-xs" name="releaseName"
                 type="button"
                 id="dropdownMenu1"
                 data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
        <option disabled selected>Release Number</option>
        <form:options items="${releases}"/>
    </form:select>


    <button type="submit" name="getRelease" class="btn btn-primary btn-xs">Show</button>

    <button type="submit" name="downloadReleaseExcel" class="btn btn-primary btn-xs"> <span
            class="glyphicon glyphicon-download-alt"></span>Export</button>
    <sec:authorize access="hasRole('ROLE_admin')">
        <button type="submit" name="removeRelease" class="btn btn-primary btn-xs">Delete</button>
        <button type="submit" name="sendReminder" class="btn btn-primary btn-xs">Send Reminder</button>
    </sec:authorize>


</form:form>

<c:if test="${not empty success}">
    <div class="alert alert-success" role="alert">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${success}
    </div>
</c:if>

<c:if test="${not empty warning}">

    <div class="alert alert-warning" role="alert">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${warning}&nbsp;
    </div>
</c:if>

<c:if test="${not empty exception}">

    <div class="alert alert-danger" role="alert">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${exception}
    </div>
</c:if>


<c:if test="${not empty data}">


    <div class="container-fluid">

        <div id="progressbar">
            <p style=" font-family: Roboto Medium, Roboto Regular, Roboto; font-weight: bold;">Release Progress:</p>
        </div>
        <div id="progressbar" class="col-lg-2">
            <div class="progress">

                <div class="progress-bar progress-bar-success  " role="progressbar" style="width:${pass}%">
                        ${pass}% &nbsp; Passed
                </div>
                <div class="progress-bar progress-bar-warning " role="progressbar" style="width:${pending}%">
                        ${pending}% &nbsp; Pending
                </div>
                <div class="progress-bar progress-bar-danger " role="progressbar" style="width:${fail}%">
                        ${fail}% &nbsp; Failed
                </div>
            </div>
        </div>

        <div >
            <div id="deadLine" >

                <p id="counter"></p>

            </div>

            <div id="deadLine">
                <p style="font-family: Roboto Medium, Roboto Regular, Roboto;font-weight: bold;">Signoff
                    Deadline:&nbsp;</p>
            </div>
        </div>
    </div>

    </br>
    <div class="container-fluid">
        <div class="table-responsive table-wrapper-scroll-y">
            <table id="myTable" class="table table-striped table-hover compact nowrap table-condensed ">
                <thead>
                <tr id="tableHead">
                    <th>Key</th>
                    <th>Summary</th>
                    <th>Engineer</th>
                    <th>Script Number</th>
                    <th>UAT Tester</th>
                    <th>UAT Status</th>
                    <th>Comments</th>
                    <th>Edit</th>

                </tr>
                </thead>
                <tbody id="tableBody">

                <c:forEach var="excelRow" items="${data}" varStatus="data">
                    <tr id="marginChange">
                        <td><p><a
                                href="https://cedt-icg-jira.nam.nsroot.net/jira/browse/<c:out value='${excelRow.jiraKey}'></c:out>">
                                ${excelRow.jiraKey}</a></p></td>

                        <td><p class="wrapText"><c:out value="${excelRow.summary}"></c:out></p></td>
                            <%--myFunction()--%>
                        <td><p><c:out value="${excelRow.engineer}"></c:out></p></td>
                        <td><p><c:out value="${excelRow.scriptLocation}"></c:out></p></td>
                        <td><p><c:out value="${excelRow.tester}"></c:out></p></td>
                        <td id="statusCount"><p><c:out value="${excelRow.status}"></c:out></p></td>
                        <td><p><c:out value="${excelRow.comments}"></c:out></p></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/releasemanagerform/<c:out value='${excelRow.id}' />"><span
                                    class="glyphicon glyphicon-pencil"></span></a>


                                <%--<a class="delete" id="delete" type="submit" value="Delete" name="delete"
                                   href="${pageContext.request.contextPath}/deleteExcelRow/<c:out value='${excelRow.id}' />"><span
                                        class="glyphicon glyphicon-trash"></span></a>--%>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>

</c:if>

