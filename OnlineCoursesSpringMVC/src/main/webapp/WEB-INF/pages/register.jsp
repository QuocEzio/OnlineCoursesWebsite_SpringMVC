<%-- 
    Document   : register
    Created on : Aug 15, 2024, 12:27:22 PM
    Author     : QuocEzio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>


<c:url value="/register" var="register"></c:url>

<form:form method="post" action="${register}" modelAttribute="user" enctype="multipart/form-data">

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
        <label for="password" style="font-weight:bold;">Password</label>
        <form:input type="password" id="password" path="password" cssClass="form-control"></form:input> 
        <form:errors path="password" cssClass="text-danger" element="div"/>
    </div>
    <div class="form-group">
        <label for="confirm-password" style="font-weight:bold;">Confirm-password</label>
        <form:input type="password" id="confirm-password" path="confirmPassword" cssClass="form-control"></form:input> 
        <c:if test="${errPassword!=null}">
            <div style="color: red;" >${errPassword}</div>
        </c:if>
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
            <input type="submit" value="Submit" class="btn btn-danger"/>
        </div>
    </div>
</form:form>
