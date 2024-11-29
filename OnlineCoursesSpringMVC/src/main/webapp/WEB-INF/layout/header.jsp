<%-- 
    Document   : header
    Created on : Aug 12, 2024, 11:33:25 AM
    Author     : QuocEzio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
        <img src="https://img.freepik.com/premium-vector/online-school-logo-learning-logo-design-vector_567288-21.jpg?w=2000" 
             alt="Logo" width="50" height="50">

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mynavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <c:url value="/listCourses" var="uL"></c:url>

                        <a class="nav-link" href="${uL}">Courses</a>
                </li>
                <s:authorize access="hasAnyRole('ROLE_ADMIN')">
                    <li class="nav-item">
                        <c:url value="/listUsers" var="uU"></c:url>
                        <a class="nav-link" href="${uU}">Users</a>
                    </li>
                </s:authorize>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li class="nav-item">
                        <c:url value="/listLessons" var="uLe"></c:url>
                        <a class="nav-link" href="${uLe}">Lessons</a>
                    </li>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li class="nav-item">
                        <c:url value="/listAssignments" var="uA"></c:url>
                        <a class="nav-link" href="${uA}">Assignments</a>
                    </li>
                </c:if>



                <c:if test="${pageContext.request.userPrincipal.name != null && !pageContext.request.isUserInRole('ROLE_ADMIN') && !pageContext.request.isUserInRole('ROLE_INSTRUCTOR')}">
                    <li class="nav-item">
                        <c:url value="/detailCart" var="uC"></c:url>
                        <a class="nav-link" href="${uC}">Cart</a>
                    </li>
                </c:if>
                <s:authorize access="hasRole('ADMIN')">
                    <li class="nav-item">
                        <c:url value="/orders" var="uO"></c:url>
                        <a class="nav-link" href="${uO}">Orders</a>
                    </li>
                </s:authorize>
                    <s:authorize access="hasAnyRole('ROLE_ADMIN')">
                    <li class="nav-item">
                        <c:url value="/statistics" var="uS"></c:url>
                        <a class="nav-link" href="${uS}">Statistics</a>
                    </li>
                </s:authorize>




                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <li class="nav-item">
                        <a class="btn btn-danger" href="<c:url value="/login" />">
                            Login
                        </a>
                    </li>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li class="nav-item mx-2" >

                        <a class="btn btn-info" href="<c:url value="/" />">
                            Hello, ${pageContext.request.userPrincipal.name} 
                        </a>
                    </li>

                    <li class="nav-item">
                        <c:url value="/change-password" var="uCh"></c:url>
                        <a class="btn btn-warning" href="${uCh}">Change Your Password</a>
                    </li>

                    <li class="nav-item" >
                        <a class="btn btn-danger mx-2" href="<c:url value="/logout" />">
                            Logout
                        </a>
                    </li>
                </c:if>

            </ul>

        </div>
    </div>
</nav>

