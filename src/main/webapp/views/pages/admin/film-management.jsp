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
            <div class="slide wrapper centered-vertical-content">
                <c:forEach var="film" items="${filmsData}" varStatus="loop">
                    <div class="wide-card " id="card-${loop.index}">
                        <div class="wide-card_contents">
                            <h5>${film.filmName}</h5>
                            <p class="description">dsjhfd</p>

                            <p class="description">Mô tả</p>
                            <!-- handles in JS file name = handlesSubmitHiddenForm.js -->
                            <button class="outlined-warning-button rounded-button button submit-button delete-button">Xoá
                            </button>
                            <button class="primary-filled-button rounded-button button submit-button edit-button">
                                Sửa
                            </button>

                        </div>

                        <form class="hide hidden-form" method="get">
                            <input type="hidden" name="film-id_hidden"
                                   value="${film.filmID}"/>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </div>
        <a href="add-film" class="primary-filled-button rounded-button button submit-button">Thêm phim mới</a>
    </div>
</section>


