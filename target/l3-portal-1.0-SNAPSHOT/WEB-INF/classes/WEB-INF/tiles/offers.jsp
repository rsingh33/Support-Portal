
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<table class="offers">
    <tr class="offersRow" >
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

