<%-- 
    Document   : courses
    Created on : Aug 12, 2024, 3:41:57 PM
    Author     : QuocEzio
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>

<section>
    <h1 class="text-center text-danger">Courses List</h1>

    <!-- Sorting options -->
    <div class="mb-3">
        <label for="sort">Sort by:</label>
        <select id="sort" onchange="changeSort(this.value)">
            <option value="">Default</option>
            
            <option value="idDesc" ${sort == 'idDesc' ? 'selected' : ''}>Latest Courses</option>
            <option value="priceAsc" ${sort == 'priceAsc' ? 'selected' : ''}>Price Ascending</option>
            <option value="priceDesc" ${sort == 'priceDesc' ? 'selected' : ''}>Price Descending</option>
        </select>
    </div>

    <div class="row">
        <div class="col-md-9 col-12">
            <s:authorize access="hasRole('ADMIN')">
                <a class="btn btn-danger m-1" href="<c:url value="/uploadCourse" />">Upload Course</a>
            </s:authorize>
            <table class="table table-striped">
                <tr>
                    <th></th>
                    <th>Course Name</th>
                    <th>Description</th>
                    <th>Instructor Name</th>
                    <th>Course Price</th>
                    <th></th>
                </tr>
                <c:forEach var="p" items="${courses}">
                    <tr id="course${p.courseId}">
                        <td>
                            <img src="${p.logo}" width="120" height="120" />
                        </td>
                        <td>${p.title}</td>
                        <td>${p.description}</td>
                        <td>${p.instructorId.fullName}</td>
                        <td>${String.format("%,d", p.price)} VND</td>
                        <td>
                            <s:authorize access="hasRole('ROLE_ADMIN')">
                                <c:url value="/updateCourse/${p.courseId}" var="u"></c:url>
                                <a href="${u}" class="btn btn-success mb-2">UPDATE</a>

                                <c:url value="/deleteCourse/${p.courseId}" var="uD" />
                                <a href="${uD}" class="btn btn-danger mb-2"
                                   onclick="return confirm('Are you sure you want to delete?');">DELETE</a>
                            </s:authorize>
                            <c:url value="/detailCourse/${p.courseId}" var="ud" />
                            <a href="${ud}" class="btn btn-info mb-2">Detail</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <!-- Pagination -->
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                            <a class="page-link" href="<c:url value="/listCourses">
                                   <c:param name="page" value="${i}"/>
                                   <c:if test="${not empty param.sort}">
                                       <c:param name="sort" value="${param.sort}"/>
                                   </c:if>
                                   <c:if test="${not empty param.keyword}">
                                       <c:param name="keyword" value="${param.keyword}"/>
                                   </c:if>
                                   <c:if test="${not empty param.teacherName}">
                                       <c:param name="teacherName" value="${param.teacherName}"/>
                                   </c:if>
                                   <c:if test="${not empty param.fromPrice}">
                                       <c:param name="fromPrice" value="${param.fromPrice}"/>
                                   </c:if>
                                   <c:if test="${not empty param.toPrice}">
                                       <c:param name="toPrice" value="${param.toPrice}"/>
                                   </c:if>
                               </c:url>">${i}</a>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
        </div>
        <div class="col-md-3 col-12 bg-warning">
            <c:url value="/listCourses" var="action" />
            <form action="${action}">
                <div class="mb-3 mt-3">
                    <label for="keyword" class="form-label">Course Name</label>
                    <input type="text" class="form-control" id="keyword" placeholder="The name of the subject" name="keyword">
                </div>
                <div class="mb-3 mt-3">
                    <label for="teacherName" class="form-label">Instructor</label>
                    <input type="text" class="form-control" id="teacherName" placeholder="The name of the teacher" name="teacherName">
                </div>
                <div class="mb-3 mt-3">
                    <label for="fromPrice" class="form-label">From Price(VND):</label>
                    <input type="number" class="form-control" id="fromPrice" placeholder="Enter your price" name="fromPrice">
                </div>
                <div class="mb-3 mt-3">


                    <label for="toPrice" class="form-label">To Price(VND):</label>
                    <input type="number" class="form-control" id="toPrice" placeholder="Enter your price" name="toPrice">
                </div>
                <div class="mb-3 mt-3">
                    <button class="btn btn-info" type="submit" style="margin-left: 40%;">SEARCH</button>
                </div>
            </form>
        </div>
    </div>
</section>

<script>
    function changeSort(value) {
        let currentUrl = new URL(window.location.href);
        currentUrl.searchParams.set('sort', value);
        currentUrl.searchParams.set('page', '1');  
        window.location.href = currentUrl.toString();
    }
</script>





