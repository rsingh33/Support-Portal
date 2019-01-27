<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<form:form method="post" action="${pageContext.request.contextPath}/saveBacklog" class="form-horizontal" modelAttribute="backlog">

    <c:if test="${backlog != null}">
        <input type="hidden" name="id" value="<c:out value='${backlog.id}' />"/>
    </c:if>

    <div class="container" id="Container3">

        <div id="handoverPanel" class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Backlog Form</h3>
            </div>

            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12 col-sm-12">
                        <p>* Please fill the form below</p>


                        <!-- Reported By -->
                        <div class="form-group">
                            <label for="reportedBy" class="col-sm-2 control-label">Reported By</label>
                            <div class="col-sm-10">
                                <input type="text" name="reportedBy" class="form-control" id="reportedBy"
                                       placeholder="Reporter"
                                       size="100"
                                       value="<c:out value='${backlog.reportedBy}' />">
                            </div>
                        </div>
                        <!-- Subject -->
                        <div class="form-group">
                            <label for="emailSub" class="col-sm-2 control-label">Email Subject</label>
                            <div class="col-sm-10">
                                <input type="text" name="emailSubject" class="form-control" id="emailSub"
                                       placeholder="Email Subject"
                                       value="<c:out value='${backlog.emailSubject}' />"/>
                            </div>
                        </div>

                        <!-- JIRA -->
                        <div class="form-group">
                            <label for="jira" class="col-sm-2 control-label">Jira Number</label>
                            <div class="col-sm-10">
                                <input type="text" pattern= "C167433-[0-9]{3,4}|C167433E-[0-9]{3,4}$"
                                       title="Please Enter the Jira Number only. Example C167433-123" class="form-control" name="tracking" id="jira"
                                       placeholder="Jira number"
                                       value="<c:out value='${backlog.tracking}' />"/>
                            </div>
                        </div>

                        <!-- COMMENTS -->
                        <div class="form-group">
                            <label for="comment" class="col-sm-2 control-label">Comments</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="comments" id="comment"
                                       placeholder="Comments"
                                       value="<c:out value='${backlog.comments}' />"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="dropdownMenu1" class="col-sm-2 control-label">Priority</label>
                            <div class="col-sm-10">
                                <div class="dropdown">

                                    <form:select path="status"  class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                                 data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" value="<c:out value='${backlog.status}' />">
                                        <option disabled selected > -- select an option -- </option>
                                        <form:options items="${status}"/>
                                    </form:select>




                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="dropdownMenu2" class="col-sm-2 control-label">Environment</label>
                            <div class="col-sm-10">
                                <div class="dropdown">

                                    <form:select path="environment"
                                                 class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2"
                                                 data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"
                                                 value="<c:out value='${backlog.environment}' />">
                                        <option disabled selected > -- select an option -- </option>
                                        <form:options items="${env}"/>
                                    </form:select>



                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="dropdownMenu3" class="col-sm-2 control-label">Currently With</label>
                            <div class="col-sm-10">
                                <div class="dropdown">

                                    <form:select
                                            path="currentlyWith"
                                            class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu3"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"
                                            value="<c:out value='${backlog.currentlyWith}' />">
                                        <option disabled selected > -- select an option -- </option>
                                        <form:options items="${curr}"/>
                                    </form:select>

                                </div>
                            </div>
                        </div>


                            <div class="col-sm-offset-2 col-sm-10">
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
    </div>
</form:form>

