<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript">

    $('#myTabs li:eq(0) a').tab('show'); // Select First tab (0-indexed)
    $('#myTabs li:eq(1) a').tab('show');// Select Second tab (0-indexed)
    $('#myTabs li:eq(2) a').tab('show'); // Select third tab (0-indexed)
    $('#myTabs li:eq(3) a').tab('show'); // Select First tab (0-indexed)
    $('#myTabs li:eq(4) a').tab('show');// Select Second tab (0-indexed)
    $('#myTabs li:eq(5) a').tab('show');// Select Second tab (0-indexed)
    $('#myTabs li:eq(6) a').tab('show');// Select Second tab (0-indexed)

</script>

<div class="header">
    <img src="${pageContext.request.contextPath}/static/images/citi_logo2.svg" class="header_logo">
    <div class="header_logo__text">
        <div class="header_logo__text--primary">Support Portal</div>
        <div class="header_logo__text--secondary">Issues & Release Manager</div>
    </div>
    <img src="${pageContext.request.contextPath}/static/images/isg.svg" class="header_logoisg">
</div>
</div>


<sec:authorize access="isAuthenticated()">
    <div id="headerDiv" class="container-fluid">


        <ul id="myTabs" class="nav nav-tabs" role="tablist">
            <li role="presentation"><a class="title" href="${pageContext.request.contextPath}/">Home</a>
            </li>
            <li role="presentation"><a class="title" href="${pageContext.request.contextPath}/handover">Handover</a>
            </li>
            <li role="presentation"><a class="title" href="${pageContext.request.contextPath}/backlog">Backlog</a>
            </li>
            <li role="presentation"><a class="title"
                                       href="${pageContext.request.contextPath}/issues">Issues-Database</a></li>
            <li role="presentation"><a class="title" href="${pageContext.request.contextPath}/releasemanager">Release-Manager</a>
            <li role="presentation"><a class="title"
                                       href="http://ddivmddsf4:8080/DevOps/index.html">Devops-Dashboard</a>
            </li>
                <%--<li role="presentation"><a class="title" href="http://ddivmddsf4:8080/DevOps/index.html">DevOps-Dashboard</a></li>--%>

            <sec:authorize access="hasRole('ROLE_admin')">
                <li role="presentation"><a class="title" href="${pageContext.request.contextPath}/admin">Admin</a></li>
            </sec:authorize>

            <c:url var="logoutUrl" value="/logout"/>

            <form class="logout" action="${logoutUrl}" method="post">
                Welcome, <c:out value="${name}"></c:out>
                <input type="submit" class="btn btn-primary"value="Logout"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </ul>


    </div>
</sec:authorize>

