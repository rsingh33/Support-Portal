<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<form:form method="post" action="${pageContext.request.contextPath}/saveMonitor" class="form-horizontal" modelAttribute="monitor">

    <c:if test="${monitor != null}">
        <input type="hidden" name="id" value="<c:out value='${monitor.id}' />"/>
        <input type="hidden" name="status" value="<c:out value='${monitor.status}' />"/>
        <input type="hidden" name="minResponseTime" value="<c:out value='${monitor.minResponseTime}' />"/>
        <input type="hidden" name="responseTime" value="<c:out value='${monitor.responseTime}' />"/>
    </c:if>

    <div class="container" id="Container3">

        <div id="handoverPanel" class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Monitor Form</h3>
            </div>

            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-7 col-sm-7">
                        <p>* Please fill the form below</p>


                        <!-- Reported By -->
                        <div class="form-group">
                            <label for="name" class="col-sm-4 control-label">Application Name</label>
                            <div class="col-sm-8">
                                <input type="text" name="name" class="form-control" id="name"
                                       placeholder="Application Name"
                                       size="100"
                                       value="<c:out value='${monitor.name}' />">
                            </div>
                        </div>
                        <!-- Subject -->
                        <div class="form-group">
                            <label for="link" class=" required col-sm-4 control-label">URL</label>
                            <div class="col-sm-8">
                                <input type="text" name="link" class="form-control" id="link"
                                       placeholder="URL"
                                       value="<c:out value='${monitor.link}' />"/>
                            </div>
                            <div class="error"><form:errors path="link"></form:errors></div>
                        </div>

                        <!-- JIRA -->
                        <div class="form-group">
                            <label for="hostname" class="  col-sm-4 control-label">Hostname</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="hostname" id="hostname"
                                       placeholder="Hostname"
                                       value="<c:out value='${monitor.hostname}' />"/>
                            </div>

                        </div>




                        <div class="form-group">
                            <label for="dropdownMenu1" class="required col-sm-4 control-label">Environment</label>
                            <div class="col-sm-8">
                                <div class="dropdown">

                                    <form:select path="env"  class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                                 data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" value="<c:out value='${monitor.env}' />">
                                        <option disabled selected > -- select an option -- </option>
                                        <form:options items="${env}"/>
                                    </form:select>

                                </div>
                                <div class="error"><form:errors path="env"></form:errors></div>
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

