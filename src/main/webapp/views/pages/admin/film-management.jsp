<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 23-09-2023
  Time: 10:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/views/pages/admin/admin.jsp"/>

<section class="section container centered-vertical-content">
    <h1>${sectionTitle}</h1>


    <div class="centered-vertical-content wrapper">
        <div class="slide wrapper centered-vertical-content">
            <c:forEach var="film" items="${filmsData}" varStatus="loop">
                <div class="wide-card align-left" id="card-${loop.index}">
                    <div class="centered-vertical-content wrapper">
                        <div class="card-header align-left">
                            <h3>${film.filmName}</h3>
                        </div>
                        <div class="card-main align-left">
                            <p class="description">${film.price}</p>
                            <p class="description">${film.genre}</p>
<%--                            <p>${film.releaseDate}</p>--%>
                        </div>
                        <div class="card-footer align-right">
                            <button class="outlined-button rounded-button button submit-button delete-button">Delete
                            </button>
                            <button class="primary-filled-button rounded-button button submit-button edit-button">Edit
                            </button>
                        </div>
<%--                            <p>${film.description}</p>--%>
                        <form class="hide hidden-form" method="get">
                            <input type="hidden" name="film-id_hidden"
                                   value="${film.filmID}"/>
                        </form>
                    </div>
                    <div class=>

                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>


