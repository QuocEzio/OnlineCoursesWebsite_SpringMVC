<%-- 
    Document   : orders
    Created on : Aug 21, 2024, 3:58:53 PM
    Author     : QuocEzio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<table class="table table-striped">
    <tr>
        <th>Order ID</th>
        <th>Username</th>
        <th>Total Price</th>
        <th>Pay Day</th>
        <th></th>
    </tr>

    <c:forEach items="${orders}" var="o">
        <tr id="orders${o.orderId}">
            <td>${o.orderId}</td>
            <td>${o.username}</td>
            <td>${String.format("%,d", o.totalAmount)} VND</td>
            <td>${o.createdAt}</td>



            <td>
                <c:choose>
                    <c:when test="${o.status=='waitAdmin'}">
                        <c:url value="/acceptOrder/${o.orderId}" var="uD" />
                        <a href="${uD}" class="btn btn-danger mb-2"
                           >ACCEPT ORDER</a>
                    </c:when>
                    <c:otherwise>
                        <a href="#" class="btn btn-success mb-2"
                           >SUCCCED</a>
                    </c:otherwise>
                </c:choose>
            </td>

        </tr>           
    </c:forEach>
</table>

