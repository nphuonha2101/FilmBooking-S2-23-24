<%--
  Created by IntelliJ IDEA.
  User: QDang
  Date: 10-10-2023
  Time: 10:27
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
                <a href="add-showtime" class="primary-filled-button rounded-button button submit-button icon-button">
                    <span class="material-symbols-outlined">add</span>
                    <span class="hidden-span">Thêm suất chiếu mới</span>
                </a>
            </div>
            <table>
                <thead>
                <tr>
                    <th>Showtime ID</th>
                    <th>Tên phim</th>
                    <th>Tên phòng</th>
                    <th>Ngày chiếu</th>
                    <th>Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="showtimeView" items="${showtimeViewDetails}" varStatus="loop">
                    <tr>
                        <td>${showtimeView.showtimeID}</td>
                        <td>${showtimeView.filmName}</td>
                        <td>${showtimeView.roomName}</td>
<%--                        <td>${showtimeView.theaterName}</td>--%>
                        <td>${showtimeView.showtimeDate}</td>

                        <td>
                            <form class="hide hidden-form" method="get">
                                <input type="hidden" name="showtime-id_hidden"
                                       value="${showtimeView.showtimeID}"/>
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