<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 24-09-2023
  Time: 10:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/views/pages/admin/admin.jsp"/>

<section class="section container centered-vertical-content">
    <h1>${sectionTitle}</h1>
    <div class="slide wrapper">
<%--        <c:forEach var="film" items="${filmsData}" varStatus="loop">--%>
<%--            <div class="film-card two-col__wrapper" id="card-${loop.index}">--%>
<%--                <div class="row-align wrapper">--%>
<%--                    <h4>${film.filmName}</h4>--%>
<%--                    <p>${film.price}</p>--%>
<%--                    <p>${film.genre}</p>--%>
<%--                    <form class="hide hidden-form" method="get">--%>
<%--                        <input type="hidden" name="film-id_hidden"--%>
<%--                               value="${film.filmID}"/>--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--                <div class="wrapper align-center">--%>
<%--                   --%>
<%--                    <button class="primary-filled-button rounded-button button submit-button">In hoá đơn--%>
<%--                    </button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </c:forEach>--%>
    <div class="wide-card two-col__wrapper" >
        <div class="row-align wrapper">
            <h4>Test</h4>
            <p>4567</p>
            <p>fdkgjj</p>
            <form class="hide hidden-form" method="get">
                <input type="hidden" name="film-id_hidden"
                       value="test"/>
            </form>
        </div>
        <div class="wrapper justify-center-row">
            <button class="primary-filled-button rounded-button button submit-button">In hoá đơn
            </button>
        </div>
    </div>
    </div>
</section>
