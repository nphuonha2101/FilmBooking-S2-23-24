<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 16-09-2023
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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
                            <div class="film-card two-col__wrapper" id="card-1">
                                <div class="wrapper">
                                    <h2>Iron man</h2>
                                </div>
                                <div class="wrapper align-center">
                                    <button class="outlined-button rounded-button button">Delete</button>
                                    <button class="light-filled-button rounded-button button">Edit</button>
                                </div>
                            </div>

                            <div class="film-card two-col__wrapper" id="card-2">
                                <div class="wrapper">
                                    <h2>Iron man</h2>
                                </div>
                                <div class="wrapper align-center">
                                    <button class="outlined-button rounded-button button">Delete</button>
                                    <button class="light-filled-button rounded-button button">Edit</button>
                                </div>
                            </div>

                            <div class="film-card two-col__wrapper" id="card-3">
                                <div class="wrapper">
                                    <h2>Iron man</h2>
                                </div>
                                <div class="wrapper align-center">
                                    <button class="outlined-button rounded-button button">Delete</button>
                                    <button class="light-filled-button rounded-button button">Edit</button>
                                </div>
                            </div>

                            <div class="film-card two-col__wrapper" id="card-4">
                                <div class="wrapper">
                                    <h2>Iron man</h2>
                                </div>
                                <div class="wrapper align-center">
                                    <button class="outlined-button rounded-button button">Delete</button>
                                    <button class="light-filled-button rounded-button button">Edit</button>
                                </div>
                            </div>

                            <div class="film-card two-col__wrapper" id="card-5">
                                <div class="wrapper">
                                    <h2>Iron man</h2>
                                </div>
                                <div class="wrapper align-center">
                                    <button class="outlined-button rounded-button button">Delete</button>
                                    <button class="light-filled-button rounded-button button">Edit</button>
                                </div>
                            </div>

                            <div class="film-card two-col__wrapper" id="card-6">
                                <div class="wrapper">
                                    <h2>Iron man</h2>
                                </div>
                                <div class="wrapper align-center">
                                    <button class="outlined-button rounded-button button">Delete</button>
                                    <button class="light-filled-button rounded-button button">Edit</button>
                                </div>
                            </div>

                            <div class="film-card two-col__wrapper" id="card-7">
                                <div class="wrapper">
                                    <h2>Iron man</h2>
                                </div>
                                <div class="wrapper align-center">
                                    <button class="outlined-button rounded-button button">Delete</button>
                                    <button class="light-filled-button rounded-button button">Edit</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="centered-content" id="film-add right">
                        <span class="successful-span">${successfulMessage}</span>
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
