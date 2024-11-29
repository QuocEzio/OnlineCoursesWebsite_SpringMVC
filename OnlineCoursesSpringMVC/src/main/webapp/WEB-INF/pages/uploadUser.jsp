<%-- 
    Document   : uploadUser
    Created on : Aug 18, 2024, 8:42:04 PM
    Author     : QuocEzio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>


<c:url value="/updateUser/{userId}" var="upload"></c:url>

<form:form method="post" action="${upload}" modelAttribute="user" enctype="multipart/form-data">

    <div class="form-group">
        <label for="username" style="font-weight:bold;">Username</label>
        <form:input type="text" id="username" path="username" cssClass="form-control"/>
        <form:errors path="username" cssClass="text-danger" element="div"/>


    </div>
    <div class="form-group">
        <label for="email" style="font-weight:bold;">Email</label>
        <form:input type="email" id="email" path="email" cssClass="form-control"/>   
        <form:errors path="email" cssClass="text-danger" element="div"/>
    </div>
    <div class="form-group">
        <label for="fullName" style="font-weight:bold;">Full Name</label>
        <form:input type="text" id="fullName" path="fullName" cssClass="form-control"/>    

    </div>

    <div class="form-group">
        <label for="file" style="font-weight:bold;" >Avatar</label>
        <form:input type="file" accept=".jpg,.png" id="file" path="file" cssClass="form-control"/> 
        <c:if test="${user.avatar != null}">
            <img class="mt-1" src="${user.avatar}" alt="${user.avatar}" width="120" height="120" />
        </c:if>

    </div>
    <s:authorize access="hasRole('ADMIN')">
        <div class="form-group">
            <label for="browser" class="form-label" style="font-weight:bold;" >Role</label>

            <form:select class="form-select" path="userRole" >
                <option value="ROLE_ADMIN">Admin</option>
                <option value="ROLE_STUDENT">Student</option>
                <option value="ROLE_INSTRUCTOR">Instructor</option>
            </form:select>       
        </div>
    </s:authorize>

    <div class="form-group" style=
         "margin-left: 45%;
         margin-top: 20px;
         margin-bottom: 20px;" 
         >


        <form:hidden path="userId"></form:hidden>
        <form:hidden path="avatar"></form:hidden>
        <form:hidden path="password"></form:hidden>
       

        
                <input type="submit" value="Update User" class="btn btn-danger"/>

    </div>
</div>
</form:form>
