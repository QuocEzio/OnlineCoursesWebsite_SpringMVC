<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change your password</title>
    <style>
        
        body {
            font-family: Arial, sans-serif;
        }
        h2 {
            color: #333;
        }
        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }
        input[type="email"], input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            padding: 10px 15px;
            background-color: #28a745;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #218838;
        }
        p.error {
            color: red;
        }
        p.success {
            color: green;
        }
    </style>
</head>
<body>
    <h2 class="text text-center"><strong>Change your password</strong></h2>
    <c:if test="${error != null}">
        <p class="error">${error}</p>
    </c:if>
    <c:if test="${success}">
        <p class="success">Password change successful!</p>
    </c:if>
    <c:choose>
        <c:when test="${!codeSent}">
            <form action="${pageContext.request.contextPath}/change-password/send-code" method="post">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
                <button type="submit">Submit a confirmation code</button>
            </form>
        </c:when>
        <c:otherwise>
            <form action="${pageContext.request.contextPath}/change-password/verify-code" method="post">
                <input type="hidden" name="email" value="${email}">
                <label for="code">Confirmation Code:</label>
                <input type="text" id="code" name="code" required>
                <label for="newPassword">New password:</label>
                <input type="password" id="newPassword" name="newPassword" required>
                <button type="submit">Change Password</button>
            </form>
        </c:otherwise>
    </c:choose>
</body>
</html>
