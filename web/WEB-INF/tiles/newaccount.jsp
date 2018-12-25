<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%--<sf:form id="details" method="post" action="${pageContext.request.contextPath}/createaccount" modelAttribute="user">--%>


<%--<div class="container" id="Container3">--%>

<%--<div id="handoverPanel" class="panel panel-primary">--%>
<%--<div class="panel-heading">--%>
<%--<h3 class="panel-title">Create New Account</h3>--%>
<%--</div>--%>

<%--<div class="panel-body">--%>
<%--<div class="row">--%>
<%--<div class="col-lg-5 col-sm-5">--%>
<%--<p>* Please fill the form below</p>--%>


<!-- soeid -->
<%--<div class="form-group">--%>
<%--<label for="soeid1" class="col-sm-4 control-label">Reported By</label>--%>
<%--<div class="col-sm-8">--%>
<%--<sf:input type="text"  path="username"  name="username"  class="form-control" id="soeid1" placeholder="soeid"/>">--%>
<%--<div class="error"><sf:errors path="username"></sf:errors></div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<!-- name -->--%>
<%--<div class="form-group">--%>
<%--<label for="name" class="col-sm-4 control-label">Name</label>--%>
<%--<div class="col-sm-8">--%>
<%--<<sf:input path="name"  name="name" type="text"  class="form-control" id="name" placeholder="Fullname"--%>
<%--value="<c:out value='${handover.emailSubject}' />"/>--%>
<%--</div>--%>
<%--</div>--%>

<%--<!-- JIRA -->--%>
<%--<div class="form-group">--%>
<%--<label for="jira" class="col-sm-4 control-label">JIRA</label>--%>
<%--<div class="col-sm-8">--%>
<%--<input type="text" class="form-control" name="tracking" id="jira" placeholder="Tracking"--%>
<%--value="<c:out value='${handover.tracking}' />"/>--%>
<%--</div>--%>
<%--</div>--%>

<%--<!-- COMMENTS -->--%>
<%--<div class="form-group">--%>
<%--<label for="comment" class="col-sm-4 control-label">Comments</label>--%>
<%--<div class="col-sm-8">--%>
<%--<input type="text" class="form-control" name="comments" id="comment" placeholder="Tracking"--%>
<%--value="<c:out value='${handover.comments}' />"/>--%>
<%--</div>--%>
<%--</div>--%>


<%--<div class="col-sm-offset-2 col-sm-10">--%>
<%--<button type="submit" class="btn btn-default" value="Save">Submit</button>--%>
<%--<button align="center"--%>
<%--class="btn btn-default"--%>
<%--type="button"--%>
<%--value="Reset"--%>
<%--onmouseover="this.style.background='#3F5201'"--%>
<%--onmouseout="this.style.background='#9dce2c'"--%>
<%--onclick="this.form.reset();">Reset--%>
<%--</button>--%>

<%--<button  class="btn btn-default" href="${pageContext.request.contextPath}/handover" >Cancel</button>--%>
<%--</div>--%>


<%--</div>--%>
<%--</div>--%>

<%--</div>--%>
<%--</div>--%>

<%--</sf:form>--%>


<%----%>


<h2>Create New Account</h2>

<div class="container" id="Container3">

    <div id="handoverPanel" class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Create New Account</h3>
        </div>

        <div class="panel-body">
            <div class="row">
                <div class="col-lg-11 col-sm-11">
                    <p>* Please fill the form below</p>

                    <sf:form id="details" method="post" action="${pageContext.request.contextPath}/createaccount"
                             modelAttribute="user">

                        <table class="formtable">
                            <tr>
                                <td> Soeid:</td>
                                <td class="control"><sf:input path="username" class="form-control" name="username"
                                                              type="text"/>
                                    <div class="error"><sf:errors path="username"></sf:errors></div>

                                </td>
                            </tr>

                            <tr>
                                <td> Name:</td>
                                <td class="control"><sf:input path="name" class="form-control" name="name" type="text"/>
                                    <div class="error"><sf:errors path="name"></sf:errors></div>

                                </td>
                            </tr>

                            <tr>
                                <td> Email:</td>
                                <td class="control"><sf:input path="email" class="form-control" name="email"
                                                              type="text"/>
                                    <div class="error"><sf:errors path="email"></sf:errors></div>
                                </td>
                            </tr>
                            <tr>
                                <td> Password:</td>
                                <td><sf:input id="password" path="password" class="form-control" name="password"
                                              type="password"/>
                                    <div class="error"><sf:errors path="password"></sf:errors></div>
                                </td>
                            </tr>

                            <tr>
                                <td>Confirm Password:</td>
                                <td><input id="confirmpass" class="form-control" name="confirmpass" type="password"/>
                                    <div id="matchpass"></div>
                                </td>
                            </tr>

                            <tr>
                                <td></td>
                                <td class="control"><input class="btn btn-primary" value="Create Account" type="submit"/>
                                </td>
                            </tr>


                        </table>


                    </sf:form>


                </div>


            </div>
        </div>

    </div>
</div>
