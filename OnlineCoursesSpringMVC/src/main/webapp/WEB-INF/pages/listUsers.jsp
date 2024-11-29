<%-- 
    Document   : listUsers
    Created on : Aug 18, 2024, 8:04:59 PM
    Author     : QuocEzio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section>
    <h1 class="text-center text-danger">User List</h1>
    <div class="row">
        <div class="col-md-12 col-12">
            <a class="btn btn-danger m-1" href="<c:url value="/register" />">Upload User</a>
            <table class="table table-striped">
                <tr>
                    <th></th>

                    <th>Username</th>
                    <th>Email</th>
                    <th>Full Name</th>
                    <th>Role</th>
                    <th></th>
                </tr>
                <c:forEach var="p" items="${user}">
                    <tr id="user${p.userId}">
                        <td>
                            <img src="${p.avatar}" width="120" height="120" />
                        </td>

                        <td>${p.username}</td>
                        <td>${p.email}</td>
                        <td>${p.fullName}</td>
                        <td>${p.userRole}</td>
                        <td>
                            <c:url value="/updateUser/${p.userId}" var="u"></c:url>
                            <a href="${u}" class="btn btn-success mb-2">UPDATE</a>

                            <c:url value="/deleteUser/${p.userId}" var="uD" />
                            <a href="${uD}" class="btn btn-danger mb-2"
                               onclick="return confirm('Are you sure you want to delete?');">DELETE</a>

                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>


    </div>
</section>
