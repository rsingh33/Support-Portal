<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
    <title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/sorttable.js"></script>
   
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
            integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

    <tiles:insertAttribute name="includes"></tiles:insertAttribute>
</head>
<body>
<div class="header"><tiles:insertAttribute name="header"></tiles:insertAttribute></div>
<div class="content"><tiles:insertAttribute name="content"></tiles:insertAttribute></div>
<hr/>
<div class="footer"><tiles:insertAttribute name="footer"></tiles:insertAttribute></div>


</body>
</html>
