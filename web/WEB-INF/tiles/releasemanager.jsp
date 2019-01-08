<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/uploadExcelFile?${_csrf.parameterName}=${_csrf.token}" var="uploadFileUrl"/>

<%--<c:url value="/readPOI" var="readPOIUrl"/>--%>

<br/>
<div>

    <form method="post" enctype="multipart/form-data" action="${uploadFileUrl}">

        <div><input type="file" name="file" accept=".xls,.xlsx"/>
            <input type="submit"  class="btn btn-primary btn-sm " value="Save"/>
        </div>

    </form>
    <p style="color: green">${message }</p>
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

            <c:forEach var="excelRow" items="${data}">
                <tr>
                    <td><p><a
                            href="https://cedt-icg-jira.nam.nsroot.net/jira/browse/<c:out value='${excelRow.jiraKey}'></c:out>">
                            ${excelRow.jiraKey}</a></p></td>

                    <td><p class="wrapText"><c:out value="${excelRow.summary}"></c:out></p></td>

                    <td><p><c:out value="${excelRow.engineer}"></c:out></p></td>
                    <td><p><c:out value="${excelRow.scriptLocation}"></c:out></p></td>
                    <td><p><c:out value="${excelRow.tester}"></c:out></p></td>
                    <td><p><c:out value="${excelRow.status}"></c:out></p></td>
                    <td><p><c:out value="${excelRow.comments}"></c:out></p></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/excelRowform/<c:out value='${excelRow.id}' />"><span
                                class="glyphicon glyphicon-pencil"></span></a>
                        &nbsp;&nbsp;&nbsp;&nbsp;

                        <a class="delete" id="delete" type="submit" value="Delete" name="delete"
                           href="${pageContext.request.contextPath}/delete/<c:out value='${excelRow.id}' />"><span
                                class="glyphicon glyphicon-trash"></span></a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>

</c:if>

