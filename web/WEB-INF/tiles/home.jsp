<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div><h2 id="monitorTitle"> Application Health Status</h2></div>

<%--<div id="loader" class="loader"></div>--%>
<c:if test="${not empty urlEntities}">

    </br>

    <div class="container">
        <sec:authorize access="hasRole('ROLE_admin')">
            <a id="expo" class="btn btn-primary btn-xs " href="${pageContext.request.contextPath}/monitorForm"
               role="button"><span
                    class="glyphicon glyphicon-plus"></span> Add New App</a>
        </sec:authorize>
        <input id="expo" type="button" class=" btn btn-primary btn-xs " value="Refresh"
               onclick="location.href='${pageContext.request.contextPath}/refresh'"/>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#prod">PROD</a></li>
            <li><a data-toggle="tab" href="#uat">UAT</a></li>
            <li><a data-toggle="tab" href="#sit">SIT</a></li>

        </ul>
        <div class="homeTabs">
            <div class="tab-content">
                <div id="prod" class="tab-pane fade in active">
                    <div class="table-responsive table-wrapper-scroll-y">
                        <table id="myTableProd"
                               class="table table-striped table-hover compact nowrap table-condensed prod">
                            <thead>
                            <tr id="prodTableHead">
                                <th>Apllication</th>
                                <th>Environment</th>
                                <th>Hostname</th>
                                <th>Status</th>
                                <th>Response Time(ms)</th>
                                <th>Last Refreshed</th>
                                <sec:authorize access="hasRole('ROLE_admin')">
                                    <th>Actions</th>
                                </sec:authorize>
                            </tr>
                            </thead>
                            <tbody id="prodTableBody">

                            <c:forEach var="entity" items="${urlEntities}">
                                <c:set var="environment">
                                    <c:out value="${entity.env}"/>
                                </c:set>
                                <c:if test="${environment == 'PROD' || environment == 'ALL' }">
                                    <tr id="marginChange">


                                        <td><a href="<c:out value='${entity.link}'></c:out>"> <c:out
                                                value="${entity.name}"></c:out></a>
                                        </td>
                                        <td><p><c:out value="${entity.env}"></c:out></p></td>
                                        <td><p><c:out value="${entity.hostname}"></c:out></p></td>
                                        <td>
                                            <c:set var="status">
                                                <c:out value="${entity.status}"/>
                                            </c:set>
                                            <c:if test="${status}">
                                                <img src="${pageContext.request.contextPath}/static/images/green.png"
                                                     alt=${status} height=20
                                                     width=20></img>
                                            </c:if>
                                            <c:if test="${not status}">
                                                <img src="${pageContext.request.contextPath}/static/images/amber.png"
                                                     alt=${status}  height=20
                                                     width=20></img>
                                            </c:if>
                                        </td>
                                        <td><p><c:out value="${entity.responseTime}"></c:out></p></td>
                                        <td><p><c:out value="${entity.lastRefreshed}"></c:out></p></td>

                                        <sec:authorize access="hasRole('ROLE_admin')">
                                            <td>
                                                <a href="${pageContext.request.contextPath}/monitorForm/<c:out value='${entity.id}' />"><span
                                                        class="glyphicon glyphicon-pencil"></span></a>
                                                &nbsp;&nbsp;

                                                <a class="delete" id="delete" type="submit" value="Delete" name="delete"
                                                   href="${pageContext.request.contextPath}/deleteMonitor/<c:out value='${entity.id}' />"><span
                                                        class="glyphicon glyphicon-trash"></span></a>
                                                &nbsp;&nbsp;

                                            </td>
                                        </sec:authorize>
                                    </tr>
                                </c:if>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>

                </div>
                <div id="sit" class="tab-pane fade">
                    <div class="table-responsive table-wrapper-scroll-y">
                        <table id="myTableSit" class="table table-striped table-hover compact nowrap table-condensed ">
                            <thead>
                            <tr id="sitTableHead">
                                <th>Apllication</th>
                                <th>Environment</th>
                                <th>Hostname</th>
                                <th>Status</th>
                                <th>Response Time(ms)</th>
                                <th>Last Refreshed</th>
                                <sec:authorize access="hasRole('ROLE_admin')">
                                    <th>Actions</th>
                                </sec:authorize>
                            </tr>
                            </thead>
                            <tbody id="sitTableBody">

                            <c:forEach var="entity" items="${urlEntities}">
                                <c:set var="environment">
                                    <c:out value="${entity.env}"/>
                                </c:set>
                                <c:if test="${environment == 'SIT' || environment == 'SIT BA'}">
                                    <tr id="marginChange">


                                        <td><a href="<c:out value='${entity.link}'></c:out>"> <c:out
                                                value="${entity.name}"></c:out></a>
                                        </td>
                                        <td><p><c:out value="${entity.env}"></c:out></p></td>
                                        <td><p><c:out value="${entity.hostname}"></c:out></p></td>
                                        <td>
                                            <c:set var="status">
                                                <c:out value="${entity.status}"/>
                                            </c:set>
                                            <c:if test="${status}">
                                                <img src="${pageContext.request.contextPath}/static/images/green.png"
                                                     alt=${status} height=20
                                                     width=20></img>
                                            </c:if>
                                            <c:if test="${not status}">
                                                <img src="${pageContext.request.contextPath}/static/images/amber.png"
                                                     alt=${status}  height=20
                                                     width=20></img>
                                            </c:if>
                                        </td>
                                        <td><p><c:out value="${entity.responseTime}"></c:out></p></td>
                                        <td><p><c:out value="${entity.lastRefreshed}"></c:out></p></td>

                                        <sec:authorize access="hasRole('ROLE_admin')">
                                            <td>
                                                <a href="${pageContext.request.contextPath}/monitorForm/<c:out value='${entity.id}' />"><span
                                                        class="glyphicon glyphicon-pencil"></span></a>
                                                &nbsp;&nbsp;

                                                <a class="delete" id="delete" type="submit" value="Delete" name="delete"
                                                   href="${pageContext.request.contextPath}/deleteMonitor/<c:out value='${entity.id}' />"><span
                                                        class="glyphicon glyphicon-trash"></span></a>

                                            </td>
                                        </sec:authorize>
                                    </tr>
                                </c:if>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="uat" class="tab-pane fade">
                    <div class="table-responsive table-wrapper-scroll-y">
                        <table id="myTableUat" class="table table-striped table-hover compact nowrap table-condensed ">
                            <thead>
                            <tr id="uatTableHead">
                                <th>Application</th>
                                <th>Environment</th>
                                <th>Hostname</th>
                                <th>Status</th>
                                <th>Response Time(ms)</th>
                                <th>Last Refreshed</th>
                                <sec:authorize access="hasRole('ROLE_admin')">
                                    <th>Actions</th>
                                </sec:authorize>
                            </tr>
                            </thead>
                            <tbody id="uatTableBody">

                            <c:forEach var="entity" items="${urlEntities}">
                                <c:set var="environment">
                                    <c:out value="${entity.env}"/>
                                </c:set>
                                <c:if test="${environment == 'UAT'}">
                                    <tr id="marginChange">


                                        <td><a href="<c:out value='${entity.link}'></c:out>"> <c:out
                                                value="${entity.name}"></c:out></a>
                                        </td>
                                        <td><p><c:out value="${entity.env}"></c:out></p></td>
                                        <td><p><c:out value="${entity.hostname}"></c:out></p></td>
                                        <td>
                                            <c:set var="status">
                                                <c:out value="${entity.status}"/>
                                            </c:set>
                                            <c:if test="${status}">
                                                <img src="${pageContext.request.contextPath}/static/images/green.png"
                                                     alt=${status} height=20
                                                     width=20></img>
                                            </c:if>
                                            <c:if test="${not status}">
                                                <img src="${pageContext.request.contextPath}/static/images/amber.png"
                                                     alt=${status}  height=20
                                                     width=20></img>
                                            </c:if>
                                        </td>
                                        <td><p><c:out value="${entity.responseTime}"></c:out></p></td>
                                        <td><p><c:out value="${entity.lastRefreshed}"></c:out></p></td>

                                        <sec:authorize access="hasRole('ROLE_admin')">
                                            <td>
                                                <a href="${pageContext.request.contextPath}/monitorForm/<c:out value='${entity.id}' />"><span
                                                        class="glyphicon glyphicon-pencil"></span></a>
                                                &nbsp;&nbsp;

                                                <a class="delete" id="delete" type="submit" value="Delete" name="delete"
                                                   href="${pageContext.request.contextPath}/deleteMonitor/<c:out value='${entity.id}' />"><span
                                                        class="glyphicon glyphicon-trash"></span></a>

                                            </td>
                                        </sec:authorize>
                                    </tr>
                                </c:if>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
    </div>
</c:if>

