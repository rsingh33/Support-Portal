<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
    <title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css">
  &lt;%&ndash;  <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery.js"></script>&ndash;%&gt;
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"/>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>


    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">


    <tiles:insertAttribute name="includes"></tiles:insertAttribute>
</head>
<body>
<div class="header"><tiles:insertAttribute name="header"></tiles:insertAttribute></div>
<div class="content"><tiles:insertAttribute name="content"></tiles:insertAttribute></div>
<hr/>
<div class="footer"><tiles:insertAttribute name="footer"></tiles:insertAttribute></div>


</body>
</html>
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
    <title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css">
    <%--  <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery.js"></script>--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
    <%--<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.3.1.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css"/>


    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>

    <tiles:insertAttribute name="includes"></tiles:insertAttribute>
</head>
<body>
<div class="header"><tiles:insertAttribute name="header"></tiles:insertAttribute></div>
<div class="content"><tiles:insertAttribute name="content"></tiles:insertAttribute></div>
<hr/>
<div class="footer"><tiles:insertAttribute name="footer"></tiles:insertAttribute></div>


</body>
</html>
