<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 23-09-2023
  Time: 10:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:choose>
    <c:when test="${empty sessionScope.lang || sessionScope.lang eq 'default'}">
        <fmt:setLocale value="default"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${sessionScope.lang}"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="properties.messageAdmin" var="adminMsg"/>
<section class="section justify-content-start d-flex flex-column align-items-center">

    <jsp:include page="/views/components/admin-panel.jsp"/>

    <div class="container d-flex flex-column align-items-center">
        <h2><fmt:message bundle="${adminMsg}" key="filmManagement"/></h2>
        <div class="d-flex flex-column align-items-center wrapper">


            <%--        Status Code Messages--%>
            <jsp:include page="/views/components/status-code-message.jsp"/>

            <div class="d-flex justify-content-end wrapper">
                <a href="<c:url value="${pageContext.request.contextPath}/admin/add/film"/>"
                   class="rotate-icon primary-filled-button rounded-button button submit-button icon-button">
                    <span class="material-symbols-rounded">add</span>
                    <span class="hidden-span"><fmt:message bundle="${adminMsg}" key="addNewFilm"/></span>
                </a>
            </div>
            <table class="film-table">
                <thead>
                <tr>
                    <th><fmt:message bundle="${adminMsg}" key="filmID"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="filmName"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="ticketPrices"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="director"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="actors"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="filmLength"/></th>
                    <th><fmt:message bundle="${adminMsg}" key="actions"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="film" items="${filmsData}" varStatus="loop">
                    <tr id="film-row-${film.filmID}">
                        <td>${film.filmID}</td>
                        <td>${film.filmName}</td>
                        <td>${film.filmPrice} VNĐ</td>
                        <td>${film.director}</td>
                        <td>${film.cast}</td>
                        <td>${film.filmLength} <fmt:message bundle="${adminMsg}" key="minutes"/></td>
                        <td>
                            <span onclick="deleteFilm('${film.slug}','${film.filmID}')"
                                  class="rotate-icon material-symbols-rounded warning-color delete_btn">delete</span>
                            <a href="<c:url value='${pageContext.request.contextPath}/admin/edit/film?film=${film.slug}'/>">
                                <span class="material-symbols-rounded primary-color rotate-icon">edit</span>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <%-- Pagination --%>
        <jsp:include page="/views/components/pagination.jsp"/>
    </div>
    <script>
        function deleteFilm(slug, id) {
            var filmID = getFilmId(id);
            $.ajax({
                url: '${pageContext.request.contextPath}/admin/delete/film',
                method: 'POST',
                data: {film: slug},
                success: function (data) {
                    alert('Xoá phim thành công!');
                    $('#film-row-' + filmID).remove();
                    // $('.film-table tbody').reload();
                },
                error: function (xhr, status, error) {
                    alert('Có lỗi khi xóa phim: ' + error);
                }
            });
        }

        function getFilmId(filmId) {
            return filmId;
        }
    </script>
</section>
