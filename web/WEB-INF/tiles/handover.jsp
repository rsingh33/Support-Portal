<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


</br>

<button id="expo" type="button" onclick="location.href='${pageContext.request.contextPath}/downloadExcel'"
        value="Export" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-download-alt"></span>
    Export
</button>


<button id="expo" type="button" onclick="location.href='${pageContext.request.contextPath}/sendemail'"
        value="Send Email Handover" class="btn btn-primary btn-xs"><span
        class="glyphicon glyphicon-envelope"></span> Send Handover
</button>
&nbsp;


<a id="expo" class="btn btn-primary btn-xs " href="${pageContext.request.contextPath}/handoverform"
   role="button"><span class="glyphicon glyphicon-plus"></span> Add Issue</a>


</br>
</br>

<div id="container2" class="container-fluid">
    <div class="row">
        <div class="col-lg-12 col-sm-12">

            <div class="table-responsive">
                <table id="myTable" class="table table-hover compact nowrap table-condensed">
                    <thead>

                    <tr id="tableHead">
                        <th>Reporter</th>
                        <th>Email Subject</th>
                        <th>Jira</th>
                        <%-- <th>Status</th>--%>
                        <th>Currently With</th>
                        <th>Environment</th>
                        <th>Comments</th>
                        <th>Last Mod Info</th>
                        <th>Actions</th>

                    </tr>
                    </thead>

                    <tbody id="tableBody">

                    <c:forEach var="handover" items="${handovers}">

                        <c:set var="status">
                            <c:out value="${handover.status}"/>
                        </c:set>
                        <c:if test="${status == 'HIGH'}">
                            <tr id = "marginChangeHigh" style="-webkit-print-color-adjust: exact; background-color:#ffcccc" >
                        </c:if>


                        <c:if test="${status == 'NORMAL'}">
                            <tr id="marginChange">
                        </c:if>
                        <td><p><c:out value="${handover.reportedBy}"></c:out></p></td>
                        <td><p class="wrapText"><c:out value="${handover.emailSubject}"></c:out></p></td>
                        <td><p><a
                                href="https://cedt-icg-jira.nam.nsroot.net/jira/browse/<c:out value='${handover.tracking}'></c:out>">
                                ${handover.tracking}</a></p></td>
                        <%-- <td><p><c:out value="${handover.status}"></c:out></p></td>--%>
                        <td><p><c:out value="${handover.currentlyWith}"></c:out></p></td>
                        <td><p><c:out value="${handover.environment}"></c:out></p></td>
                        <td><p><c:out value="${handover.comments}"></c:out></p></td>
                        <td>
                            <p><b>User: </b> <c:out value="${handover.lastModUser}"></c:out>, <b>Time: </b> <c:out
                                    value=" ${handover.lastMod}"></c:out></p>


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


<%--

<a href="#" onClick="MyWindow=window.open
        ('${pageContext.request.contextPath}/history/<c:out value='${handover.id}' />','MyWindow',width=600,height=300);
        return false;"><span class="glyphicon glyphicon-book"></span></a>--%>
