<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<sf:form id="details" method="post" class="form-horizontal"
         action="${pageContext.request.contextPath}/createaccount"
         modelAttribute="user">
    <div class="container" id="Container3">

        <div id="handoverPanel" class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Create New Account</h3>
            </div>

            <div class="panel-body">

                <div class="row">
                    <c:if test="${newAcc}">
                        <div class="col-lg-11 col-sm-11">
                            <p>* Please fill the form below</p>


                            <!-- UserName -->
                            <div class="form-group">
                                <label for="userName" class="col-sm-4 control-label">Soeid</label>
                                <div class="col-sm-8">
                                    <sf:input path="username" class="form-control" name="username"
                                              type="text"/>
                                    <div class="error"><sf:errors path="username"></sf:errors></div>

                                </div>
                            </div>
                            <!--Name -->
                            <div class="form-group">
                                <label for="Name" class="col-sm-4 control-label">Full Name</label>
                                <div class="col-sm-8">
                                    <sf:input path="name" class="form-control" name="name"
                                              type="text"/>
                                    <div class="error"><sf:errors path="name"></sf:errors></div>
                                </div>
                            </div>
                            <!-- Email ID -->
                            <div class="form-group">
                                <label for="email" class="col-sm-4 control-label">Email</label>
                                <div class="col-sm-8">
                                    <sf:input path="email" class="form-control" name="email"
                                              type="text"/>
                                    <div class="error"><sf:errors path="email"></sf:errors></div>
                                </div>
                            </div>


                            <!-- Password -->
                            <div class="form-group">
                                <label for="password" class="col-sm-4 control-label">Password</label>
                                <div class="col-sm-8">
                                    <sf:input id="password" path="password" class="form-control" name="password"
                                              type="password"/>
                                    <div class="error"><sf:errors path="password"></sf:errors></div>
                                </div>
                            </div>


                            <!-- Confirm Password -->
                            <div class="form-group">
                                <label for="confirmpass" class="col-sm-4 control-label">Confirm Password</label>
                                <div class="col-sm-8">
                                    <input id="confirmpass" class="form-control" name="confirmpass"
                                           type="password"/>
                                    <div id="matchpass"></div>
                                </div>
                            </div>


                            <div class="col-sm-offset-4 col-sm-8">
                                <button type="submit" class="btn btn-primary" value="Save">Create</button>

                                <button align="center"
                                        class="btn btn-primary"
                                        type="button"
                                        value="Reset"
                                        onclick="this.form.reset();">Reset
                                </button>


                            </div>


                        </div>
                    </c:if>

                    <c:if test="${accCreated}">
                        <p style="color: darkblue">Account Created Successfully</p>
                        <p><a href="${pageContext.request.contextPath}/login">Click here to login!!</a></p>
                    </c:if>
                </div>

            </div>
        </div>
    </div>


</sf:form>




