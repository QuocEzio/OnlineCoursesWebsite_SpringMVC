<%-- 
    Document   : submitAssignment
    Created on : Aug 22, 2024, 9:21:04 AM
    Author     : QuocEzio
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2>Submit Assignment "${assignment.title}"</h2>
<hr/>
<p>${assignment.description}</p>
<hr/>
<c:url value="/submitAssignment" var="upload"></c:url>
<form:form modelAttribute="submission" method="post" action="${upload}">
    <c:set var="isInSubmiss" value="false"></c:set>
    <c:forEach items="${listSubmiss}" var="sub">
        <c:if test="${sub.assignmentId==assignment.assignmentId}">
            <c:set var="isInSubmiss" value="true"></c:set>
        </c:if>
    </c:forEach>
    <form:hidden path="submissionId" />
    <form:hidden path="assignmentId" />

    <div class="form-group">
        <c:choose>
            <c:when test="${!isInSubmiss}">
                <label for="content">Your submission</label>
                <form:textarea path="content" class="form-control" rows="10" />
            </c:when>

            <c:otherwise>
                <label for="content">Your submission</label>
                <form:textarea readonly="true" path="content" class="form-control" rows="10" />
            </c:otherwise>
        </c:choose>

    </div>
    </br>

    <c:if test="${isInSubmiss}">
        <div class="form-group">
            <label for="content"><strong>Instructor Feedback</strong></label>
            <form:textarea readonly="true" path="feedback" class="form-control" rows="10" />
        </div>

        <div class="form-group">
            <label for="grade"><strong>Grade</strong></label>
            <form:textarea readonly="true" path="grade" class="form-control" rows="1" />
        </div>
    </c:if>


    <c:if test="${!isInSubmiss}">
        <button type="submit" class="btn btn-primary">Submit</button>
    </c:if>
    </br>



</form:form>


