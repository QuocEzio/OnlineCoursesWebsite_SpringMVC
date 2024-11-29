<%-- 
    Document   : uploadAssignment
    Created on : Aug 18, 2024, 6:06:12 PM
    Author     : QuocEzio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:url value="/uploadAssignment" var="upload"></c:url>

<form:form method="post" action="${upload}" modelAttribute="assign" enctype="multipart/form-data">
    <div class="form-group">
        <label for="title" style="font-weight:bold;">Assignment Title</label>
        <form:input type="text" id="title" path="title" cssClass="form-control"/>
        <form:errors path="title" cssClass="text-danger" element="div"/>
    </div>
    <div class="form-group">
        <label for="content" style="font-weight:bold;">Assignment Content</label>
        <form:textarea id="description" path="description" cssClass="form-control"></form:textarea> 
        <form:errors path="description" cssClass="text-danger" element="div"/>
    </div>
    <div class="form-group">
        <label for="browser" class="form-label" style="font-weight:bold;" >Lesson Name</label>
        <form:select class="form-select" path="lessonId" >
            <c:forEach items="${listLessons}" var="u">
                <c:choose>
                    <c:when test="${u.lessonId == assign.lessonId.lessonId}">
                        <option value="${u.lessonId}" selected>${u.title}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${u.lessonId}">${u.title}</option>
                    </c:otherwise>
                </c:choose>  
            </c:forEach>
        </form:select>       
    </div>
    <div class="form-group" style=
         "margin-left: 45%;
         margin-top: 20px;
         margin-bottom: 20px;" 
         >
        <form:hidden path="assignmentId"></form:hidden>

        <c:choose>
            <c:when test="${assign.assignmentId == null}">
                <input type="submit" value="Add Assignment" class="btn btn-danger"/>
            </c:when>
            <c:otherwise>
                <input type="submit" value="Update Assignment" class="btn btn-danger"/>
            </c:otherwise>
        </c:choose>
    </div>
</form:form>

