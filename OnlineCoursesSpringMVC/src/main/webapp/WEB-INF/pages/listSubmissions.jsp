<%-- 
    Document   : feedbackSubmiss
    Created on : Aug 25, 2024, 11:16:24 AM
    Author     : QuocEzio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section>
    <h1 class="text-center text-danger">Submissions List</h1>
    <div class="row">
        <div class="col-md-12 col-12">


            <table class="table table-striped">
                <tr>


                    <th>Assignment Title</th>
                    <th>User Submission</th>
                    <th>Submission Day</th>
                    <th></th>


                </tr>
                <c:forEach var="s" items="${submissions}">

                    <tr id="submissions{
                            s.submissionId
                        }">
                        <td>
                            <c:forEach items="${listAssign}" var="a">
                                <c:if test="${a.assignmentId==s.assignmentId}">
                                    ${a.title}
                                </c:if>

                            </c:forEach>
                        </td>
                        <td>${s.username}</td>
                        <td>${s.submittedAt}</td>
                        <td>   
                            <c:url value="/feedback/${s.submissionId}" var="u"></c:url>
                            <a href="${u}" class="btn btn-warning mb-2">Feedback</a>
                        </td>

                    </c:forEach>
            </table>
        </div>


    </div>
</section>
