<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<div class="container" id="Container3">

    <div id="handoverPanel" class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Password Reset</h3>
        </div>

        <div class="panel-body">
            <div class="row">
                <c:if test="${forgotlink}">
                <div class="col-lg-7 col-sm-7">
                    <p>* Please enter your registered email below</p>

                    <sf:form id="details" method="post" action="${pageContext.request.contextPath}/forgot"
                             modelAttribute="message">

                        <table class="formtable">
                            <tr>
                                <td> Email:</td>
                                <td class="control"><input class="form-control"  name="email" type="text"></td>
                                <td class="control"><input class="btn btn-primary" value="send" type="submit"/>
                            </tr>
                            <tr><p> ${message}</p></tr>
                        </table>

                    </sf:form>


                </div>
                </c:if>

            </div>
        </div>

    </div>
</div>
