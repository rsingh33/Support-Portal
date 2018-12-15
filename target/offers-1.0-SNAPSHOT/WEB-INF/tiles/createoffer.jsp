<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script type="text/javascript">
    function onDeleteClick(event) {


        var doDelete = confirm("Are you sure you want to delete");

        if (doDelete == false) {
            event.preventDefault();
        }
    }

    function onReady() {
        $("#delete").click(onDeleteClick);
    }

    $(document).ready(onReady);
</script>
<sf:form method="post" action="${pageContext.request.contextPath}/doCreate" modelAttribute="offer">
    <sf:input type="hidden" name="id" path="id"/>

    <table class="formtable">

        <tr>
            <td class="label"> Your Offer:</td>
            <td class="control"><sf:textarea path="text" class="control" name="text" rows="10" cols="10"></sf:textarea>
                <br/><sf:errors path="text" cssClass="error"></sf:errors></td>
        </tr>


        <tr>
            <td class="control"><input class="control" value="Save" type="submit"/></td>
        </tr>

        <c:if test="${offer.id != 0}">
            <tr>
                <td class="control"><input class="delete" id="delete" value="Delete" name="delete" type="submit"/></td>
            </tr>
        </c:if>
    </table>


</sf:form>

