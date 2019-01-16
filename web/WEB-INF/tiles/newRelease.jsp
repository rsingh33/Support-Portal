<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:url value="/uploadExcelFile?${_csrf.parameterName}=${_csrf.token}" var="uploadFileUrl"/>

<form:form method="post" enctype="multipart/form-data" action="${uploadFileUrl}" class="form-horizontal">
    <div class="container " id="Container3">

        <div id="handoverPanel" class="panel panel-primary ">
            <div class="panel-heading">
                <h3 class="panel-title">New Release Form</h3>
            </div>

            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12 col-sm-12">
                        <p>* Please fill the form below</p>
                    </div>

                    <div class="form-group">
                        <label for="releaseName" class="col-sm-2 control-label">Release Name:</label>
                        <div class="col-sm-10">
                            <input type="text" id="releaseName" name="releaseName" class="date-picker"
                                   placeholder="Release Name"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="deadline" class="col-sm-2 control-label">Checkout Deadline:</label>
                        <div class="col-sm-10">
                            <input type="date" id="deadline" name="deadline" class="date-picker"
                                   value="Checkout Deadline: "/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="upload" class="col-sm-2 control-label">Upload File:</label>
                        <div class="col-sm-10">
                            <input id="upload" required type="file" name="file" accept=".xls,.xlsx"/>

                        </div>
                    </div>





                    <div class="col-sm-offset-2 col-sm-8">
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
