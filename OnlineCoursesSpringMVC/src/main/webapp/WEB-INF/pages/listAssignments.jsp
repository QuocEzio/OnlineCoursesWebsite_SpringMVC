<%-- 
    Document   : listAssignments
    Created on : Aug 18, 2024, 5:10:28 PM
    Author     : QuocEzio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<section>
    <h1 class="text-center text-danger">Assignments List</h1>
    <div class="row">
        <div class="col-md-12 col-12">
            <s:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_INSTRUCTOR')">
                <a class="btn btn-danger m-1" href="<c:url value="/uploadAssignment" />">Upload Assignment</a>
            </s:authorize>

            <table class="table table-striped">
                <tr>


                    <th>Assignment Title</th>
                    <th>Lesson Name</th>
                    <th>Assignment Content</th>

                    <th></th>


                </tr>
                <c:forEach var="p" items="${assign}">
                    <c:set var="enrolled" value="false" />

                    <!-- Kiểm tra nếu người dùng đã đăng ký khóa học -->
                    <c:forEach var="e" items="${listEnroll}">
                        <c:if test="${p.lessonId.courseId.courseId == e.courseId && pageContext.request.userPrincipal.name == e.username}">
                            <c:set var="enrolled" value="true" />

                        </c:if>
                    </c:forEach>

                    <!-- Hiển thị bài tập nếu là admin, instructor, hoặc đã đăng ký khóa học -->
                    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN') || pageContext.request.isUserInRole('ROLE_INSTRUCTOR') || enrolled}">
                        <tr id="assign${p.assignmentId}">

                            <td>${p.title}</td>
                            <td>${p.lessonId.title}</td>
                            <td>${p.description}</td>

                            <td>
                                <s:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_INSTRUCTOR')">
                                    <c:url value="/updateAssignment/${p.assignmentId}" var="u"></c:url>
                                    <a href="${u}" class="btn btn-success mb-2">UPDATE</a>

                                    <c:url value="/deleteAssignment/${p.assignmentId}" var="uD" />
                                    <a href="${uD}" class="btn btn-danger mb-2"
                                       onclick="return confirm('Are you sure you want to delete?');">DELETE</a>
                                </s:authorize>
                                <s:authorize access="hasRole('ROLE_STUDENT')">

                                    <c:url value="/submitAssignment/${p.assignmentId}" var="uS"></c:url>


                                    <c:set var="isInSubmiss" value="false"></c:set>
                                    <c:forEach items="${listSubmiss}" var="sub">
                                        <c:if test="${sub.assignmentId==p.assignmentId}">
                                            <c:set var="isInSubmiss" value="true"></c:set>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${isInSubmiss}">
                                        <a href="${uS}" class="btn btn-success mb-2">Submitted</a>
                                    </c:if>
                                    <c:if test="${!isInSubmiss}">
                                        <a href="${uS}" class="btn btn-primary mb-2">Add a submission</a>
                                    </c:if>

                                </s:authorize>
                                <c:url value="/listSubmissons/${p.assignmentId}" var="uS"></c:url>
                                <s:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_INSTRUCTOR')">

                                    <a href="${uS}" class="btn btn-warning mb-2">Feedback submissions</a>
                                </s:authorize>
                            </td>
                        </tr>


                    </c:if>

                </c:forEach>

            </table>
        </div>


    </div>
</section>
