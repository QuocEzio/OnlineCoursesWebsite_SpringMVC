<%-- 
    Document   : listLessons
    Created on : Aug 15, 2024, 7:02:39 PM
    Author     : QuocEzio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
    <h1 class="text-center text-danger">Lessons List</h1>
    <div class="row">
        <div class="col-md-12 col-12">
            <s:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_INSTRUCTOR')">
                <a class="btn btn-danger m-1" href="<c:url value="/uploadLesson" />">Upload Lesson</a>
            </s:authorize>

            <table class="table table-striped">
                <tr>


                    <th>Course Name</th>
                    <th>Lesson Name</th>
                    <th>Video Of Lesson</th>
                    <th></th>


                </tr>
                <c:forEach var="p" items="${lesson}">
                    <c:set var="enrolled" value="false" />

                    <!-- Kiểm tra nếu người dùng đã đăng ký khóa học -->
                    <c:forEach var="e" items="${listEnroll}">
                        <c:if test="${p.courseId.courseId == e.courseId && pageContext.request.userPrincipal.name == e.username}">
                            <c:set var="enrolled" value="true" />

                        </c:if>
                    </c:forEach>

                    <!-- Hiển thị bài hoc nếu là admin, instructor, hoặc đã đăng ký khóa học -->
                    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN') || pageContext.request.isUserInRole('ROLE_INSTRUCTOR') || enrolled}">
                        <tr id="lesson${p.lessonId}">


                            <td>${p.courseId.title}</td>
                            <td>${p.title}</td>
                            <td><a style="text-decoration: none; "target="_blank" href="${p.content}" class="text text-danger">Video ${p.title}</a></td>
                            <td>
                                <s:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_INSTRUCTOR')">
                                    <c:url value="/updateLesson/${p.lessonId}" var="u"></c:url>
                                    <a href="${u}" class="btn btn-success mb-2">UPDATE</a>

                                    <c:url value="/deleteLesson/${p.lessonId}" var="uD" />
                                    <a href="${uD}" class="btn btn-danger mb-2"
                                       onclick="return confirm('Are you sure you want to delete?');">DELETE</a>
                                </s:authorize>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>


    </div>
</section>
