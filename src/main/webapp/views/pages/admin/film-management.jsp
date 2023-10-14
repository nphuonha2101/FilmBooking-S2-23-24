<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 23-09-2023
  Time: 10:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<section class="section align-top admin-two-cols__wrapper centered-vertical-content">
    <div class="container ">
        <jsp:include page="/views/components/admin-panel.jsp"/>
    </div>
    <div class="container centered-vertical-content">

        <h1>${sectionTitle}</h1>

        <div class="centered-vertical-content wrapper">
            <div class="justify-right-row wrapper">
                <a href="add-film" class="primary-filled-button rounded-button button submit-button icon-button">
                    <span class="material-symbols-outlined">add</span>
                    <span class="hidden-span">Thêm phim mới</span>
                </a>
            </div>
            <table>
                <thead>
                <tr>
                    <th>Phim ID</th>
                    <th>Tên phim</th>
                    <th>Giá vé</th>
                    <th>Đạo diễn</th>
                    <th>Diễn viên</th>
                    <th>Thời lượng</th>
                    <th>Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="film" items="${filmsData}" varStatus="loop">
                    <tr>
                        <td>${film.filmID}</td>
                        <td>${film.filmName}</td>
                        <td>${film.filmPrice} VNĐ</td>
                        <td>${film.director}</td>
                        <td>${film.cast}</td>
                        <td>${film.filmLength} phút</td>
                        <td>
                            <form class="hide hidden-form" method="get">
                                <input type="hidden" name="film-id_hidden"
                                       value="${film.filmID}"/>
                            </form>
                            <button class="submit-button delete-button">
                                <span class="material-symbols-outlined warning-color">delete</span>
                            </button>
                            <button class="submit-button edit-button">
                                <span class="material-symbols-outlined primary-color">edit</span>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>


