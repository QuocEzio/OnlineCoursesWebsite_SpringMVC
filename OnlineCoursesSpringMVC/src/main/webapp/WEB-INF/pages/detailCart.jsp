<%-- 
    Document   : detailCart
    Created on : Aug 19, 2024, 8:32:29 PM
    Author     : QuocEzio
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<table class="table table-striped">
    <tr>
        <th>Username</th>
        <th>Course Name</th>
        <th>Course Price</th>
        <th></th>
    </tr>
    <c:set var="totalPrice" value="${0}" ></c:set>
    <c:forEach items="${carts}" var="c">
        <tr id="carts${c.cartId}">

            <td>${c.username}</td>

            <c:forEach items="${listCourses}" var="course">
                <c:if test="${c.courseId == course.courseId}">
                    <td>${course.title}</td>
                </c:if>
            </c:forEach>


            <c:forEach items="${listCourses}" var="course">
                <c:if test="${c.courseId == course.courseId}">
                    <td>${String.format("%,d", course.price)} VND</td>
                    <c:set var="totalPrice" value="${course.price + totalPrice}" ></c:set>
                </c:if>
            </c:forEach>
            <td>
                <c:url value="/detailCart/deleteCartId/${c.cartId}" var="uD" />
                <a href="${uD}" class="btn btn-danger mb-2"
                   onclick="return confirm('Are you sure you want to delete?');">DELETE</a>
            </td>
        </tr>           
    </c:forEach>
</table>
<section>
    <c:if test="${totalPrice!=0}">
        <form action="<c:url value="/paying"></c:url>" method="post">
                <div class="form-group" style=
                     "margin-left: 40%;
                     margin-top: 20px;
                     margin-bottom: 20px;" 
                     >
                <input type="hidden" name="username" value="${pageContext.request.userPrincipal.name}" />
                <input type="hidden" name="total" value="${totalPrice}" />

                <c:set var="isInOrders" value="false" />
                <c:set var="accepted" value="false" />
                <c:forEach items="${listOrders}" var="o">
                    <c:if test="${o.username == pageContext.request.userPrincipal.name && o.totalAmount == totalPrice && o.status=='waitAdmin'}">
                        <c:set var="isInOrders" value="true" />
                    </c:if>
                    <c:if test="${o.username == pageContext.request.userPrincipal.name && o.totalAmount == totalPrice && o.status=='succeed'}">
                        <c:set var="accepted" value="true" />
                    </c:if>
                </c:forEach>

                <c:choose>
                    <c:when test="${isInOrders}">
                        <a class="btn btn-danger"href="<c:url value="/"></c:url>">Come back in a few minutes</a>
                    </c:when>
                    <c:when test="${accepted}">
                        <a class="btn btn-success"href="<c:url value="/addEnroll"></c:url>">Click here to activate all courses</a>
                    </c:when>

                    <c:otherwise>
                        <button onclick="return confirm('Surely you have chosen the courses that you want to study before paying');"
                                type="submit" class="btn btn-warning shadow-0">PAY ${String.format("%,d", totalPrice)} VND </button>
                    </c:otherwise>
                </c:choose>



            </div>
        </form>
    </c:if>
</section>
