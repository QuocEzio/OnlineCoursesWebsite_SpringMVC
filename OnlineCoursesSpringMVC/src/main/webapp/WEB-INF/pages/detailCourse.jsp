<%-- 
    Document   : detailCourse
    Created on : Aug 19, 2024, 10:18:27 AM
    Author     : QuocEzio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<section class="py-5">
    <div class="container">
        <div class="row gx-5">
            <aside class="col-lg-6">
                <div class="border rounded-4 mb-3 d-flex justify-content-center">
                    <a data-fslightbox="mygalley" class="rounded-4" target="_blank" data-type="image" >
                        <img style="max-width: 100%; max-height: 100vh; margin: auto;" class="rounded-4 fit" src="${course.logo}" />
                    </a>
                </div>
            </aside>
            <main class="col-lg-6" id="${course.courseId}" style="margin-top:10%">
                <div class="ps-lg-3">
                    <h4 class="title text-dark">
                        ${course.title}
                    </h4>
                    <div class="mb-3">
                        <span class="h5">${String.format("%,d", course.price)} VND</span>
                    </div>
                    <p>
                        ${course.description}
                    </p>

                    <div class="row">
                        <dt class="col-3">Instructor</dt>
                        <dd class="col-9">${course.instructorId.fullName}</dd>
                    </div>
                    <hr />
                    <form action="<c:url value="/cart"></c:url>" method="post">

                            <input type="hidden" name="courseId" value="${course.courseId}" />
                        <input type="hidden" name="username" value="${pageContext.request.userPrincipal.name}" />



                        <c:set var="isInCart" value="false" />
                        <c:set var="isEnrolled" value="false" />
                        <c:set var="currentUser" value="${pageContext.request.userPrincipal.name}" />

                        <!-- Kiểm tra xem courseId có trong listCarts không -->
                        <c:forEach items="${listCarts}" var="c">
                            <c:if test="${c.username == currentUser && c.courseId == course.courseId}">
                                <c:set var="isInCart" value="true" />
                            </c:if>
                        </c:forEach>

                        <!-- Kiểm tra xem courseId có trong listEnroll không -->
                        <c:forEach items="${listEnroll}" var="e">
                            <c:if test="${e.username == currentUser && e.courseId == course.courseId}">
                                <c:set var="isEnrolled" value="true" />
                            </c:if>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${isEnrolled}">
                                <a class="btn btn-success" href="<c:url value="/listLessons"></c:url>">Start Learning</a>
                            </c:when>

                            <c:when test="${isInCart}">
                                <a class="btn btn-danger" href="<c:url value="/detailCart"></c:url>">Pay to start learning</a>
                            </c:when>

                            <c:otherwise>
                                <c:if test="${pageContext.request.userPrincipal.name != null}">
                                    <button type="submit" class="btn btn-warning shadow-0">Add to cart</button>
                                </c:if>
                                <c:if test="${pageContext.request.userPrincipal.name == null}">
                                    <a href="<c:url value="/login"></c:url>" class="btn btn-danger shadow-0">Please login to add the course to the cart</a>
                                </c:if>

                            </c:otherwise>
                        </c:choose>
                    </form>
                </div>

        </div>
        </main>
        <c:if test="${isEnrolled}">               
            <c:url value="/detailCourse/${course.courseId}" var="commenturl"></c:url>
            <form:form modelAttribute="cmt" method="post" action="${commenturl}">
                <label for="review"><strong>Your comment</strong></label>
                <form:textarea path="comment" class="form-control" rows="2" />
                <input type="submit" value="Submit" class="btn btn-primary"/>
            </form:form>
        </c:if> 

        <hr/>
        <div class="reviews">
            <c:forEach items="${reviews}" var="r">
                <div class="content row">
                    <div class="col-md-1">
                        <img src="<c:url value="${r.userId.avatar}" />" class="rounded-circle img-fluid" />
                    </div>
                    <div class="col-md-11">
                        <p>${r.comment}</p>
                        <p class="commentDate">${r.createdAt}</p>
                    </div>
                </div>
            </c:forEach>
            <ul class="pagination">
                <c:forEach begin="1" end="${Math.ceil(reviewsCounter/3)}" var="i">
                    <c:url value="/detailCourse/${course.courseId}" var="path">
                        <c:param name="page" value="${i}"></c:param>
                    </c:url>
                    <li class="page-item"><a class="page-link" href="${path}">${i}</a></li>
                    </c:forEach>
            </ul>

        </div>
    </div>
</div>

</section>

<script>
    window.onload = function () {
        let dates = document.getElementsByClassName("commentDate");
        for (let i = 0; i < dates.length; i++) {
            dates[i].innerText = "Commented " + moment(dates[i].innerText).fromNow();
        }
    };
</script>











