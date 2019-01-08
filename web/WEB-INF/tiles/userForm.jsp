<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<sf:form method="post" action="${pageContext.request.contextPath}/saveUser" class="form-horizontal"
         modelAttribute="user">

    <c:if test="${user != null}">
        <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
        <input type="hidden" name="username" value="<c:out value='${user.username}' />"/>
        <input type="hidden" name="password" value="<c:out value='${user.password}' />"/>
        <input type="hidden" name="resetToken" value="<c:out value='${user.resetToken}' />"/>
    </c:if>

    <div class="container" id="Container3">


        <div id="handoverPanel" class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">User Form</h3>
            </div>

            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12 col-sm-12">
                        <p>* Please fill the form below</p>


                        <%--<!-- UserName -->--%>
                        <%--<div class="form-group">--%>
                            <%--<label for="userName" class="col-sm-4 control-label">Username</label>--%>
                            <%--<div class="col-sm-8">--%>
                                <%--<input type="text" name="userName" class="form-control" id="userName"--%>
                                       <%--placeholder="UserName"--%>
                                       <%--size="100"--%>
                                       <%--value="<c:out value='${user.username}' />">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <!--Name -->
                        <div class="form-group">
                            <label for="Name" class="col-sm-1 control-label">Name</label>
                            <div class="col-sm-11">
                                <sf:input path="name" class="form-control" name="name" type="text"/>
                                <div class="error"><sf:errors path="name"></sf:errors></div>
                            </div>
                        </div>
                        <!-- Email ID -->
                        <div class="form-group">
                            <label for="email" class="col-sm-1 control-label">Email</label>
                            <div class="col-sm-11">
                                <sf:input path="email" class="form-control" name="email"
                                          type="text"/>
                                <div class="error"><sf:errors path="email"></sf:errors></div>
                            </div>
                        </div>


                        <!-- Role -->
                        <div class="form-group">
                            <label for="dropdownMenu1" class="col-sm-1 control-label">Role</label>
                            <div class="col-sm-11">
                                <div class="dropdown">

                                    <sf:select path="authority" class="btn btn-default dropdown-toggle" type="button"
                                               id="dropdownMenu1"
                                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"
                                               value="<c:out value='${user.authority}' />">
                                        <sf:options items="${authority}"></sf:options>

                                    </sf:select>
                                    <div class="error"><sf:errors path="authority"></sf:errors></div>


                                </div>
                            </div>
                        </div>


                            <%--<!-- Enabled -->--%>
                        <div class="form-group">
                            <label for="dropdownMenu2" class="col-sm-1 control-label">Enabled</label>
                            <div class="col-sm-8">
                                <sf:select path="enabled" class="btn btn-default dropdown-toggle" type="button"
                                           id="dropdownMenu2"
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"
                                           value="<c:out value='${user.enabled}' />">
                                    <sf:options items="${enabled}"></sf:options>

                                </sf:select>
                                <div class="error"><sf:errors path="enabled"></sf:errors></div>
                            </div>
                        </div>


                        <div class="col-sm-offset-1 col-sm-8">
                            <button type="submit" class="btn btn-primary" value="Save">Save</button>

                            <button align="center"
                                    class="btn btn-primary"
                                    type="button"
                                    value="Reset"
                                    onclick="this.form.reset();">Reset
                            </button>


                        </div>





                    </div>
                </div>

            </div>
        </div>
    </div>

</sf:form>

