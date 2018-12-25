<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<form:form method="post" action="${pageContext.request.contextPath}/saveIssue" modelAttribute="issue">

    <c:if test="${issue != null}">
        <input type="hidden" name="id" value="<c:out value='${issue.id}' />"/>
    </c:if>

    <div class="container" id="Container3">

    <div id="issuePanel" class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">Issue Form</h3>
    </div>

    <div class="panel-body">
        <div class="row">
            <div class="col-lg-5 col-sm-5">
                <p>* Please fill the form below</p>


                <!-- issueDescription -->
                <div class="form-group">
                    <label for="issueDescription" class="col-sm-4 control-label">Reported By</label>
                    <div class="col-sm-8">
                        <input type="text" name="issueDescription" id="issueDescription" class="form-control"
                               placeholder="Issue Description"
                               value="<c:out value='${issue.issueDescription}' />">
                    </div>
                </div>
                <!-- workaround -->
                <div class="form-group">
                    <label for="workaround" class="col-sm-4 control-label">Email Subject</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="workaround" placeholder="Workaround"
                               name="workaround"
                               value="<c:out value='${issue.workaround}' />"/>
                    </div>
                </div>

                <!-- JIRA -->
                <div class="form-group">
                    <label for="jira" class="col-sm-4 control-label">JIRA</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="jira" placeholder="JIRA number"
                               name="jira"
                               value="<c:out value='${issue.jira}' />"/>
                    </div>
                </div>

                <!-- solution -->
                <div class="form-group">
                    <label for="solution" class="col-sm-4 control-label">Comments</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="solution" placeholder="solution"
                               name="solution"
                               value="<c:out value='${issue.solution}' />"/>
                    </div>
                </div>


                <div class="form-group">
                    <label for="dropdownMenu1" class="col-sm-4 control-label">Environment</label>
                    <div class="col-sm-8">
                        <div class="dropdown">
                            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <form:select path="sourceSystem" value="<c:out value='${issue.sourceSystem}' />">
                                    <form:options items="${sourceSystem}"/>
                                </form:select>

                                <span class="caret"></span>
                            </button>


                        </div>
                    </div>


                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" value="Save">Submit</button>
                        <button align="center"
                                class="btn btn-default"
                                type="button"
                                value="Reset"
                                onmouseover="this.style.background='#3F5201'"
                                onmouseout="this.style.background='#9dce2c'"
                                onclick="this.form.reset();">Reset
                        </button>
                        <button  class="btn btn-default" href="${pageContext.request.contextPath}/issues" >Cancel</button>

                    </div>


                </div>
            </div>

        </div>
    </div>

</form:form>






