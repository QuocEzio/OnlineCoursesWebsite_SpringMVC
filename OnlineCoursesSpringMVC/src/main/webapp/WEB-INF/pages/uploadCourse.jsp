<%-- 
    Document   : uploadCourse
    Created on : Aug 12, 2024, 5:05:33 PM
    Author     : QuocEzio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:choose>
    <c:when test="${course.courseId == null}">
        <h1 class="text-center text-danger">Course Upload</h1>
    </c:when>
    <c:otherwise>
        <h1 class="text-center text-danger">Course Update</h1>
    </c:otherwise>
</c:choose>

<c:url value="/uploadCourse" var="upload"></c:url>

<form:form method="post" action="${upload}" modelAttribute="course" enctype="multipart/form-data">
    <div class="form-group">
        <label for="title" style="font-weight:bold;">Name course</label>
        <form:input type="text" id="title" path="title" cssClass="form-control"/>
        <form:errors path="title" cssClass="text-danger" element="div"/>
    </div>
    <div class="form-group">
        <label for="description" style="font-weight:bold;">Description</label>
        <form:textarea id="description" path="description" cssClass="form-control"></form:textarea> 
        <form:errors path="description" cssClass="text-danger" element="div"/>
    </div>
    <div class="form-group">
        <label for="price" style="font-weight:bold;">Price of course</label>
        <form:input type="text" id="price" path="price" cssClass="form-control"/>   
        <form:errors path="price" cssClass="text-danger" element="div"/>
    </div>
    <div class="form-group">
        <label for="file" style="font-weight:bold;" >Logo of course</label>
        <form:input type="file" accept=".jpg,.png" id="file" path="file" cssClass="form-control"/> 
        <c:if test="${course.logo != null}">
            <img class="mt-1" src="${course.logo}" alt="${course.logo}" width="120" height="120" />
        </c:if>

    </div>
    <div class="form-group">
        <label for="browser" class="form-label" style="font-weight:bold;" >Instructor</label>
        <form:select class="form-select" path="instructorId" >
            <c:forEach items="${listUsers}" var="u">
                <c:choose>
                    <c:when test="${u.userId == course.instructorId.userId}">
                        <option value="${u.userId}" selected>${u.fullName}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${u.userId}">${u.fullName}</option>
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
        <form:hidden path="courseId"></form:hidden>
        <form:hidden path="logo"></form:hidden>
        <c:choose>
            <c:when test="${course.courseId == null}">
                <input type="submit" value="Add Course" class="btn btn-danger"/>
            </c:when>
            <c:otherwise>
                <input type="submit" value="Update Course" class="btn btn-danger"/>
            </c:otherwise>
        </c:choose>
    </div>
</form:form>


