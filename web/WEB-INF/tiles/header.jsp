<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript">

    $('#myTabs li:eq(0) a').tab('show'); // Select First tab (0-indexed)
    $('#myTabs li:eq(1) a').tab('show');// Select Second tab (0-indexed)
    $('#myTabs li:eq(2) a').tab('show'); // Select third tab (0-indexed)
    $('#myTabs li:eq(3) a').tab('show'); // Select First tab (0-indexed)
    $('#myTabs li:eq(4) a').tab('show');// Select Second tab (0-indexed)


</script>

<div class="header">
    <img src="${pageContext.request.contextPath}/static/images/citi_logo2.svg" class="header_logo">
    <div class="header_logo__text">
        <div class="header_logo__text--primary">Support Portal</div>
        <div class="header_logo__text--secondary">Fueled by Singh Sab</div>
    </div>
    <img src="${pageContext.request.contextPath}/static/images/isg.svg" class="header_logoisg">
</div>
</div>



<sec:authorize access="isAuthenticated()">
    <div id="headerDiv" class="container-fluid">

        <%--<ul class="nav nav-tabs" id="myTabs" role="tablist">--%>
            <%--<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/" id="home-tab"--%>
                                                      <%--role="tab" data-toggle="tab"--%>
                                                      <%--aria-controls="home" aria-expanded="true">Home</a></li>--%>
            <%--<li role="presentation" class=""><a href="${pageContext.request.contextPath}/handover" role="tab"  data-toggle="tab"--%>
                                                      <%--aria-controls="profile" aria-expanded="false">Handover</a></li>--%>
            <%--<li role="presentation" class=""><a href="${pageContext.request.contextPath}/issue" role="tab"  data-toggle="tab"--%>
                                                      <%--aria-controls="profile" aria-expanded="false">Issues-Database</a></li>--%>
            <%--<li role="presentation" class=""><a href="${pageContext.request.contextPath}/releasemanager" role="tab"  data-toggle="tab"--%>
                                                      <%--aria-controls="profile" aria-expanded="false">Release Manager</a></li>--%>

    <%--<sec:authorize access="hasRole('ROLE_admin')">--%>
            <%--<li role="presentation" class=""><a href="${pageContext.request.contextPath}/admin" role="tab"  data-toggle="tab"--%>
                                                      <%--aria-controls="profile" aria-expanded="false">Admin</a></li>--%>
    <%--</sec:authorize>--%>


                <%--</ul>--%>
            <%--</li>--%>
        <%--</ul>--%>


        <ul id="myTabs" class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a class="title" href="${pageContext.request.contextPath}/">Home</a>
            </li>
            <li role="presentation"><a class="title" href="${pageContext.request.contextPath}/handover">Handover</a>
            </li>
            <li role="presentation"><a class="title"
                                       href="${pageContext.request.contextPath}/issues">Issues-Database</a></li>
            <li role="presentation"><a class="title" href="${pageContext.request.contextPath}/releasemanager">Release-Manager</a>
            </li>
                <%--<li role="presentation"><a class="title" href="http://ddivmddsf4:8080/DevOps/index.html">DevOps-Dashboard</a></li>--%>

            <sec:authorize access="hasRole('ROLE_admin')">
                <li role="presentation"><a class="title" href="${pageContext.request.contextPath}/admin">Admin</a></li>
            </sec:authorize>

            <c:url var="logoutUrl" value="/logout"/>
            <form class="logout" action="${logoutUrl}" method="post">
                Welcome <c:out value="${name}"></c:out>
                <input type="submit" value="Logout"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </ul>


    </div>
</sec:authorize>


<%--Old Code--%>
<%--<sec:authorize access="isAuthenticated()">--%>


<%--&lt;%&ndash;<div class="panel panel-default">&ndash;%&gt;--%>

<%--&lt;%&ndash;&lt;%&ndash;<div class="panel-body">&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;&lt;%&ndash;<a class="title" href="${pageContext.request.contextPath}/">Home</a>&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;&lt;%&ndash;&nbsp;&nbsp;&nbsp;&nbsp;&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;<a class="title" href="${pageContext.request.contextPath}/handover">Handover</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;&nbsp;&nbsp;&nbsp;&nbsp;&ndash;%&gt;--%>
<%--&lt;%&ndash;<a class="title" href="${pageContext.request.contextPath}/issues">Issues-Database</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;&nbsp;&nbsp;&nbsp;&nbsp;&ndash;%&gt;--%>
<%--&lt;%&ndash;<a class="title" href="${pageContext.request.contextPath}/releasemanager">Release-Manager</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;&nbsp;&nbsp;&nbsp;&nbsp;&ndash;%&gt;--%>
<%--&lt;%&ndash;<a class="title" href="http://ddivmddsf4:8080/DevOps/index.html">DevOps-Dashboard</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;&nbsp;&nbsp;&nbsp;&nbsp;&ndash;%&gt;--%>
<%--&lt;%&ndash;<sec:authorize access="hasRole('ROLE_admin')">&ndash;%&gt;--%>
<%--&lt;%&ndash;<a class="title" href="${pageContext.request.contextPath}/admin">Admin</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;</sec:authorize>&ndash;%&gt;--%>

<%--&lt;%&ndash;<c:url var="logoutUrl" value="/logout"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;<form class="logout" action="${logoutUrl}" method="post">&ndash;%&gt;--%>
<%--&lt;%&ndash;<input type="submit" value="Logout"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;</form>&ndash;%&gt;--%>

<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>

<%--&lt;%&ndash;<sec:authorize access="isAuthenticated()">&ndash;%&gt;--%>

<%--&lt;%&ndash;<a><%= request.getSession().getAttribute("usernamer") %></a>&ndash;%&gt;--%>

<%--</sec:authorize>--%>

