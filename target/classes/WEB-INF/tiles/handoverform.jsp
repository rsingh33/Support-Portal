<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<form:form method="post" action="${pageContext.request.contextPath}/save" modelAttribute="handover">

    <c:if test="${handover != null}">
        <input type="hidden" name="id" value="<c:out value='${handover.id}' />"/>
    </c:if>

    <div class="container" id="Container3">

        <div id="handoverPanel" class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Handover Form</h3>
            </div>

            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-7 col-sm-7">
                        <p>* Please fill the form below</p>


                        <!-- Reported By -->
                        <div class="form-group">
                            <label for="reportedBy" class="col-sm-4 control-label">Reported By</label>
                            <div class="col-sm-8">
                                <input type="text" name="reportedBy" class="form-control" id="reportedBy"
                                       placeholder="Reporter"
                                       size="100"
                                       value="<c:out value='${handover.reportedBy}' />">
                            </div>
                        </div>
                        <!-- Subject -->
                        <div class="form-group">
                            <label for="emailSub" class="col-sm-4 control-label">Email Subject</label>
                            <div class="col-sm-8">
                                <input type="text" name="emailSubject" class="form-control" id="emailSub"
                                       placeholder="Email Subject"
                                       value="<c:out value='${handover.emailSubject}' />"/>
                            </div>
                        </div>

                        <!-- JIRA -->
                        <div class="form-group">
                            <label for="jira" class="col-sm-4 control-label">Jira Number</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="tracking" id="jira"
                                       placeholder="Jira number"
                                       value="<c:out value='${handover.tracking}' />"/>
                            </div>
                        </div>

                        <!-- COMMENTS -->
                        <div class="form-group">
                            <label for="comment" class="col-sm-4 control-label">Comments</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="comments" id="comment"
                                       placeholder="Comments"
                                       value="<c:out value='${handover.comments}' />"/>
                            </div>
                        </div>


                        <!-- JIRA -->
                        <div class="form-group">
                            <label for="dropdownMenu1" class="col-sm-4 control-label">Status</label>
                            <div class="col-sm-8">
                                <div class="dropdown">
                                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"
                                            path="status"
                                            value="<c:out value='${handover.status}' />">
                                        <form:select path="status" value="<c:out value='${handover.status}' />">
                                            <form:options items="${status}"/>
                                        </form:select>


                                        <span class="caret"></span>
                                    </button>


                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="dropdownMenu1" class="col-sm-4 control-label">Environment</label>
                            <div class="col-sm-8">
                                <div class="dropdown">
                                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                        <form:select path="environment"
                                                     value="<c:out value='${handover.environment}' />">
                                            <form:options items="${env}"/>
                                        </form:select>

                                        <span class="caret"></span>
                                    </button>


                                </div>
                            </div>


                            <div class="form-group">
                                <label for="dropdownMenu1" class="col-sm-4 control-label">Currently With</label>
                                <div class="col-sm-8">
                                    <div class="dropdown">
                                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu3"
                                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                            <form:select
                                                    path="currentlyWith"
                                                    value="<c:out value='${handover.currentlyWith}' />">
                                                <form:options items="${curr}"/>
                                            </form:select>
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">

                                        </ul>
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

