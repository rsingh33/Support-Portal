<%--suppress ALL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


</br>


<a id="expo" class="btn btn-primary btn-xs " href="${pageContext.request.contextPath}/newRelease" role="button"><span
        class="glyphicon glyphicon-plus"></span> Add New Release</a>

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
            class="glyphicon glyphicon-download-alt"></span>Export
    </button>
    <sec:authorize access="hasRole('ROLE_admin')">
        <button type="submit" name="removeRelease" class="btn btn-primary btn-xs">Delete</button>
    </sec:authorize>

</form:form>


<c:if test="${not empty data}">


    <div class="container-fluid">
            <%--<div class="col-sm-offset-1 col-sm-9">--%>
        <div id="progressbar">
            <p style=" font-family: Roboto Medium, Roboto Regular, Roboto;">Release Progress:</p>
        </div>
        <div id="progressbar" class="col-lg-3">
            <div class="progress">

                <div class="progress-bar progress-bar-success" role="progressbar" style="width:${pass}%">
                        ${pass}% &nbsp; Passed
                </div>
                <div class="progress-bar progress-bar-warning" role="progressbar" style="width:${pending}%">
                        ${pending}% &nbsp; Pending
                </div>
                <div class="progress-bar progress-bar-danger" role="progressbar" style="width:${fail}%">
                        ${fail}% &nbsp; Failed
                </div>
            </div>
        </div>


        <div id="deadLine" class="col-sm-offset-2 col-lg-1">

            <c:set var="deadLine">
                <c:out value="${deadline}"/>
            </c:set>


            <c:if test="${deadline < 2}"><p style="color: red">Deadline: &nbsp; ${deadLine}day(s)</p></c:if>
            <c:if test="${deadline >= 2}"><p style="color: black">Deadline: &nbsp;${deadLine}day(s)</p></c:if>

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
                    <th>Actions</th>

                </tr>
                </thead>
                <tbody id="tableBody">

                <c:forEach var="excelRow" items="${data}" varStatus="data">
                    <tr>
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
                            &nbsp;&nbsp;&nbsp;&nbsp;

                            <a class="delete" id="delete" type="submit" value="Delete" name="delete"
                               href="${pageContext.request.contextPath}/deleteExcelRow/<c:out value='${excelRow.id}' />"><span
                                    class="glyphicon glyphicon-trash"></span></a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>

</c:if>

