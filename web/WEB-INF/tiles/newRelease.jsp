<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>

    <c:url value="/uploadExcelFile?${_csrf.parameterName}=${_csrf.token}" var="uploadFileUrl"/>
    <form method="post" enctype="multipart/form-data" action="${uploadFileUrl}">

        <div>
            <td><input type="file" class="btn btn-xs" name="file" accept=".xls,.xlsx"/></td>

        </div>
        <div>
            <input type="date" name="deadline" class="date-picker" value="Checkout Deadline: "/>
        </div>
        <div>
            <input type="text" name="releaseName" class="date-picker" value="Release Name "/>
        </div>

        <div><input type="submit" class="btn btn-primary btn-xs" value="Save"/></div>
    </form>

</div>