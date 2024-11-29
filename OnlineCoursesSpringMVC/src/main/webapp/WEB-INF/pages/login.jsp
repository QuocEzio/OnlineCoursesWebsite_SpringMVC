<%-- 
    Document   : login
    Created on : Aug 15, 2024, 11:25:03 AM
    Author     : QuocEzio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<spring:url value="/login" var="action" />

<!-- Custom styles for the login page -->
<style>
    .login-container {
        max-width: 400px;
        margin: 50px auto;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 10px;
        background-color: #f9f9f9;
    }

    .form-group {
        margin-bottom: 15px;
    }

    .form-control {
        width: 100%;
        padding: 10px;
        font-size: 14px;
        border-radius: 5px;
        border: 1px solid #ccc;
    }

    .btn {
        width: 100%;
        padding: 10px;
        font-size: 16px;
    }

    .btn-warning, .btn-danger {
        width: auto;
        margin-top: 10px;
        display: inline-block;
    }

    .alert {
        margin-bottom: 15px;
        padding: 10px;
        border-radius: 5px;
    }
</style>

<div class="login-container">
    <form action="${action}" method="post">
        <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                Please check your account name and password!
            </div>
        </c:if>
        <c:if test="${param.accessDenied != null}">
            <div class="alert alert-danger">
                You don't have access!
            </div>
        </c:if>

        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" class="form-control" />
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" class="form-control" />
        </div>

        <div class="form-group">
            <input type="submit" class="btn btn-success" value="Login" />
            <a href="<c:url value='/register' />" class="btn btn-warning">Register</a>
            <a href="<c:url value='/change-password' />" class="btn btn-danger">Forgot Password</a>
        </div>
    </form>
</div>



