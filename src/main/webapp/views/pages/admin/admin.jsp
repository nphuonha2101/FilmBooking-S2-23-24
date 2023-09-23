<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 16-09-2023
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="section">
    <div class="tab wrapper centered-content">
        <div class="tab-headers centered-content container">
            <label for="admin-film-content" class="tab-label active">Quản lý phim</label>
            <label for="admin-registration-content" class="tab-label">Quản lý đặt phim</label>
        </div>

        <div class="tab-contents wrapper">
            <div id="admin-film-content" class="tab-content container active">
                <div class="two-col__wrapper header centered-content">
                    <h3>Danh sách phim</h3>
                    <h3>Thêm phim</h3>
                </div>
                <div class="two-col__wrapper content">
                    <div class="centered-content wrapper" id="film-list left">
                        <div class="slide wrapper">
                            <c:forEach var="film" items="${filmsData}" varStatus="loop">
                                <div class="film-card two-col__wrapper" id="card-${loop.index}">
                                    <div class="row-align wrapper">
                                        <h4>${film.filmName}</h4>
                                        <p>${film.price}</p>
                                        <p>${film.genre}</p>
                                        <form class="hide hidden-form" method="get">
                                            <input type="hidden" name="film-id_hidden"
                                                   value="${film.filmID}"/>
                                        </form>
                                    </div>
                                    <div class="wrapper align-center">
                                        <button class="outlined-button rounded-button button submit-button delete-button" >Delete</button>
                                        <button class="primary-filled-button rounded-button button submit-button edit-button" >Edit</button>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>


                    <div class="centered-content" id="film-add right">
                        <jsp:include page="/views/pages/admin/add-film.jsp"/>
                    </div>
                </div>
            </div>

            <div id="admin-registration-content" class="tab-content container">
                <h2>Quản lý đặt phim</h2>
            </div>
        </div>
    </div>


</section>
