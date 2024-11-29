<%-- 
    Document   : feedback
    Created on : Aug 25, 2024, 12:07:23 PM
    Author     : QuocEzio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:forEach items="${listAssign}" var="a">
    <c:if test="${a.assignmentId==submission.assignmentId}">

        <h2>Feedback Assignment "${a.title}"</h2>
    </c:if>

</c:forEach>
<hr/>
<c:forEach items="${listAssign}" var="a">
    <c:if test="${a.assignmentId==submission.assignmentId}">

        <p>Question: ${a.description}<p>
        </c:if>

    </c:forEach>
<hr/>


<p>The answer from <strong>${submission.username}:</strong></p>
<p>${submission.content}</p>

<hr/>
<c:url value="/feedback/{submissionId}" var="feedback"></c:url>
<form:form modelAttribute="submission" method="post" action="${feedback}">
    <form:hidden path="submissionId" />
    <form:hidden path="assignmentId" />


    <div class="form-group">
        <label for="content">Your Feedback:</label>
        <form:textarea path="feedback" class="form-control" rows="10" />
    </div>

    <div class="form-group">
        <label for="grade">Grade:</label>
        <form:textarea path="grade" class="form-control" rows="1" />
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>
</form:form>
