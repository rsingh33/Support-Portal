<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<table class="offers">
    <tr class="offersRow">
        <th class="offersHead">Name</th>
        <th>Email</th>
        <th>Offer</th>
    </tr>
    <c:forEach var="offer" items="${offers}">
        <tr>
            <td class="offersCol"><p><c:out value="${offer.user.name}"></c:out></p></td>
            <td><p><c:out value="${offer.user.email}"></c:out></p></td>
            <td><p><c:out value="${offer.text}"></c:out></p></td>
        </tr>
    </c:forEach>
</table>
<p><br/></p>
<c:choose>
    <c:when test="${hasOffer}">
        <p><a href="${pageContext.request.contextPath}/createoffer">Edit or Delete your offer</a></p>
    </c:when>

    <c:otherwise>
        <p><a href="${pageContext.request.contextPath}/createoffer">Add a new Offer</a></p>
    </c:otherwise>

</c:choose>


<sec:authorize access="hasRole('ROLE_admin')">
    <p><a href="${pageContext.request.contextPath}/admin">Admin</a></p>
</sec:authorize>
