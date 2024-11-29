<%-- 
    Document   : uploadLesson
    Created on : Aug 16, 2024, 12:05:47 PM
    Author     : QuocEzio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:url value="/uploadLesson" var="upload"></c:url>

<form:form method="post" action="${upload}" modelAttribute="lesson" enctype="multipart/form-data">
    <div class="form-group">
        <label for="title" style="font-weight:bold;">Name Lesson</label>
        <form:input type="text" id="title" path="title" cssClass="form-control"/>
        <form:errors path="title" cssClass="text-danger" element="div"/>
    </div>
    <div class="form-group">
        <label for="content" style="font-weight:bold;">Video Of Lesson</label>
        <form:textarea id="content" path="content" cssClass="form-control"></form:textarea> 
        <form:errors path="content" cssClass="text-danger" element="div"/>
    </div>
    <div class="form-group">
        <label for="browser" class="form-label" style="font-weight:bold;" >Course Name</label>
        <form:select class="form-select" path="courseId" >
            <c:forEach items="${listCourses}" var="u">
                <c:choose>
                    <c:when test="${u.courseId == lesson.courseId.courseId}">
                        <option value="${u.courseId}" selected>${u.title}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${u.courseId}">${u.title}</option>
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
        <form:hidden path="lessonId"></form:hidden>

        <c:choose>
            <c:when test="${lesson.lessonId == null}">
                <input type="submit" value="Add Lesson" class="btn btn-danger"/>
            </c:when>
            <c:otherwise>
                <input type="submit" value="Update Lesson" class="btn btn-danger"/>
            </c:otherwise>
        </c:choose>
    </div>
</form:form>
