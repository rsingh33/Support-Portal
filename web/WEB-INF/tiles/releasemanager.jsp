<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/uploadExcelFile?${_csrf.parameterName}=${_csrf.token}" var="uploadFileUrl"/>

<div> <h2>${releaseName}</h2></div>

<div>

    <form method="post" enctype="multipart/form-data" action="${uploadFileUrl}">

        <div>
            <table>
                <tr>
                    <td><input type="file" class="btn btn-xs" name="file" accept=".xls,.xlsx"/></td>
                    <td><input type="submit" class="btn btn-primary btn-xs" value="Save"/></td>
                </tr>

            </table>


        </div>

    </form>

</div>


</br>

<button id="expo" type="button" onclick="location.href='${pageContext.request.contextPath}/downloadReleaseExcel'"
        value="Export" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-download-alt"></span>
    Export
</button>



<%--<button id="expo" type="button" onclick="location.href='${pageContext.request.contextPath}/sendemail'"--%>
        <%--value="Send Email Handover" class="btn btn-primary btn-xs"><span--%>
        <%--class="glyphicon glyphicon-envelope"></span> Send Handover--%>
<%--</button>--%>
<%--&nbsp;--%>


<%--<a id="expo" class="btn btn-primary btn-xs " href="${pageContext.request.contextPath}/handoverform"--%>
   <%--role="button"><span class="glyphicon glyphicon-plus"></span> Add Issue</a>--%>


</br>
</br>
<div class="container">
    <h2>Release Progress</h2>

    <div class="progress" >
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
</div>

<c:if test="${not empty data}">
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

</c:if>

