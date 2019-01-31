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
    <nav id="navBar" class="navbar navbar-default navbar-xs" role="navigation">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a id="tab" class="title" href="${pageContext.request.contextPath}/">Home</a></li>
                    <li><a id="tab"  class="title" href="${pageContext.request.contextPath}/handover">Handover</a></li>
                    <li><a id="tab"  class="title" href="${pageContext.request.contextPath}/backlog">Backlog</a></li>
                    <li><a id="tab"  class="title" href="${pageContext.request.contextPath}/issues">Issues</a></li>
                    <li><a id="tab"  class="title" href="${pageContext.request.contextPath}/releasemanager">SignOff-Tracker</a>
                    </li>
                    <li><a id="tab"  class="title" href="http://ddivmddsf4:8080/DevOps/index.html">Devops-Dashboard</a></li>
                    <sec:authorize access="hasRole('ROLE_admin')">
                        <li><a id="tab"  class="title" href="${pageContext.request.contextPath}/admin">Admin-Portal</a>
                        </li>
                    </sec:authorize>


                </ul>
                <div class="navbar-right">
                    <c:url var="logoutUrl" value="/logout"/>
                    <ul class="nav navbar-nav">
                        <li>
                            <span id="welcome">Welcome, <c:out value="${name}  "></c:out>  </span>
                        </li>
                        <li>
                            <form id="log123" action="${logoutUrl}" method="post">

                                <input type="submit" id="tab"  class="btn  btn-link btn-xs" value="Logout"/>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                            </form>
                        </li>
                    </ul>


                </div>
            </div>
        </div>
    </nav>
</sec:authorize>
