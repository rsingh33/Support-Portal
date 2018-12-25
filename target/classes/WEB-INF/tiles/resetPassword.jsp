<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<div class="container" id="Container3">

    <div id="handoverPanel" class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Change Password</h3>
        </div>

        <div class="panel-body">
            <div class="row">
                <div class="col-lg-11 col-sm-11">
                    <p>* Please fill the form below</p>

                    <sf:form id="details" method="post" action="${pageContext.request.contextPath}/reset"
                    >

                    <table class="formtable">

                        <tr hidden>

                            <td>
                                <input id="token" name="token" size="50" value="${resetToken}"/>
                            </td>
                        </tr>

                        <tr>
                            <td > Password:</td>
                            <td>
                                <input type="password" id="password" required name="password" class="form-control"
                                       size="50"/>
                                <div class="error"><sf:errors path="password"></sf:errors></div>
                            </td>
                        </tr>

                        <tr>
                            <td > Confirm Password:</td>
                            <td>
                                <input type="password" id="confirmpass" required name="confirmPassword"
                                       class="form-control" size="50"/>
                                <div id="matchpass"></div>
                            </td>
                        </tr>

                        <tr>
                            <td align="center">
                                <input class="btn btn-primary" type="submit" id="submitReset" value="Save"/>

                            </td>
                            <div> ${message} </div>
                        </tr>
                    </table>
                </div>
                <div id="divCheckPasswordMatch"></div>



                </sf:form>


            </div>


        </div>
    </div>

</div>
</div>


<%--<sf:form  method="post" action="${pageContext.request.contextPath}/reset" >--%>
<%--<div>--%>
<%--<table border="1" cellpadding="5">--%>
<%--<caption>--%>
<%--<h2>--%>
<%--Please enter the passwords:--%>
<%--</h2>--%>
<%--</caption>--%>
<%--<tr hidden>--%>

<%--<td>--%>
<%--<input id="token" name="token" size="50" value="${resetToken}"/>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td class="label"> Password:</td>--%>
<%--<td>--%>
<%--<input type="password" id="password" required name="password" class="form-control" size="50"/>--%>
<%--<div class="error"><sf:errors path="password"></sf:errors></div>--%>
<%--</td>--%>
<%--</tr>--%>

<%--<tr>--%>
<%--<td class="label"> Confirm Password:</td>--%>
<%--<td>--%>
<%--<input type="password" id="confirmpass" required name="confirmPassword" class="form-control" size="50"/>--%>
<%--<div id="matchpass"></div>--%>
<%--</td>--%>
<%--</tr>--%>

<%--<tr>--%>
<%--<td align="center">--%>
<%--<input type="submit" id="submitReset" value="Save"/>--%>

<%--</td>--%>
<%--</tr>--%>
<%--</table>--%>
<%--</div>--%>
<%--<div id="divCheckPasswordMatch"></div>--%>
<%--<div> ${message} </div>--%>
<%--</sf:form>--%>


