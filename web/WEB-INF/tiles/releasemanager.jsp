<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


</br>


<a id="expo" class="btn btn-primary btn-xs " href="${pageContext.request.contextPath}/newRelease" role="button"><span
        class="glyphicon glyphicon-plus"></span> Add New Release</a>

<form:form method="post" action="${pageContext.request.contextPath}/getRelease" class="form-horizontal"
           modelAttribute="excelRow">

    <form:select path="releaseName" class="btn btn-default dropdown-toggle btn-xs" name="releaseName"
                type="button"
                 id="dropdownMenu1"
                 data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
        <option disabled selected> -- Select Release Number --</option>
        <form:options items="${releases}"/>
    </form:select>


    <button type="submit" class="btn btn-primary btn-xs" value="Save">show</button>

</form:form>


<c:if test="${not empty data}">


    <div id="container">
        <table width="100%">
            <tr width="100%">
                <td width="50%">
                    <div class="progress" style="width:100%">
                        <div class="progress-bar progress-bar-success" role="progressbar" style="width:${pass}%">
                                ${pass}%
                        </div>
                        <div class="progress-bar progress-bar-warning" role="progressbar" style="width:${pending}%">
                                ${pending}%
                        </div>
                        <div class="progress-bar progress-bar-danger" role="progressbar" style="width:${fail}%">
                                ${fail}%
                        </div>
                    </div>
                </td>
                <td width="35%">
                    <div>

                        Days To Signoff Deadline: ${deadline}

                    </div>
                </td>
                <td width="15%">
                    <button id="expo" type="button"
                            onclick="location.href='${pageContext.request.contextPath}/downloadReleaseExcel'"
                            value="Export" class="btn btn-primary btn-xs"><span
                            class="glyphicon glyphicon-download-alt"></span>
                        Export
                    </button>
                </td>
            </tr>
        </table>
    </div>


    <div class="table-responsive table-wrapper-scroll-y">
        <table id="myTable" class="table table-striped  table-hover ">
            <thead>
            <tr id="tableHead">
                <th>Key</th>
                <th>Summary</th>
                <th>Engineer</th>
                <th>Script Location</th>
                <th>UAT Tester</th>
                <th>UAT Status</th>
                <th>Comments</th>
                <th>Release Name</th>
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
                    <td><p><c:out value="${excelRow.releaseName}"></c:out></p></td>
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

</c:if>

