<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<form:form method="post" action="${pageContext.request.contextPath}/saveRelease" class="form-horizontal"
           modelAttribute="excelRow">

    <c:if test="${excelRow != null}">
        <input type="hidden" name="id" value="<c:out value='${excelRow.id}' />"/>
        <input type="hidden" name="jiraKey" value="<c:out value='${excelRow.jiraKey}' />"/>
        <input type="hidden" name="scriptLocation" value="<c:out value='${excelRow.scriptLocation}' />"/>
        <input type="hidden" name="deadline" value="<c:out value='${excelRow.deadline}' />"/>
        <input type="hidden" name="releaseName" value="<c:out value='${excelRow.releaseName}' />"/>

    </c:if>

    <div class="container" id="Container3">

    <div id="handoverPanel" class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Issue Form</h3>
        </div>

        <div class="panel-body">
            <div class="row">
                <div class="col-lg-8 col-sm-8">
                    <p>* Please fill the form below</p>


                    <!-- SUMMARY -->
                    <div class="form-group">
                        <label for="summary" class="col-sm-4 control-label">Summary</label>
                        <div class="col-sm-8">
                            <input type="text" name="summary" id="summary" class="form-control"
                                   placeholder="Summary"
                                   value="<c:out value='${excelRow.summary}' />">

                        </div>
                    </div>


                        <%--<!-- Engineer -->--%>
                    <div class=" form-group">
                        <label for="engineer" class="col-sm-4 control-label">Assigned Engineer</label>
                        <div class="col-sm-8">
                            <input type="text" name="engineer" id="engineer" class="form-control"
                                   placeholder="Engineer Name"
                                   value="<c:out value='${excelRow.engineer}' />">

                        </div>
                    </div>

                        <%--<!-- UAT Tester -->--%>
                    <div class=" form-group">
                        <label for="tester" class="col-sm-4 control-label">UAT Tester</label>
                        <div id ="testerDiv" class="col-sm-8">
                            <form:select path="tester"
                                         data-live-search = "true"
                                         class=" dropdown-content  dropdown-toggle" name="releaseName"
                                         type="button"
                                         id="tester"
                                         data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"
                                         value="<c:out value='${excelRow.tester}' />">
                                <option disabled selected>--Tester Name--</option>
                                <form:options items="${userList}"/>
                            </form:select>
                        </div>
                    </div>

                        <%--<!-- Comment -->--%>
                    <div class=" form-group">
                        <label for="comments" class="col-sm-4 control-label">Comments</label>
                        <div class="col-sm-8">
                            <input type="text" name="comments" id="comments" class="form-control"
                                   placeholder="comments"
                                   value="<c:out value='${excelRow.comments}' />">
                        </div>
                    </div>


                    <!-- UAT Status -->
                    <div class="form-group">
                        <label for="dropdownMenu1" class="col-sm-4 control-label">UAT Status</label>
                        <div class="col-sm-8">
                            <div class="dropdown">

                                <form:select path="status" class="btn btn-default dropdown-toggle" type="button"
                                             id="dropdownMenu1"
                                             data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"
                                             value="<c:out value='${excelRow.status}' />">
                                    <option disabled selected> -- select an option --</option>
                                    <%--<option label=" "></option>--%>
                                    <form:options items="${uatStatus}"/>
                                </form:select>


                            </div>
                        </div>
                    </div>


                    <div class="col-sm-offset-4 col-sm-10">
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

</form:form>






