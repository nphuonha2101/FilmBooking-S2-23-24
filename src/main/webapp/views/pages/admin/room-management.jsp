<%--
  Created by IntelliJ IDEA.
  User: QDang
  Date: 17-10-2023
  Time: 14:14
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
                    <span class="hidden-span">Thêm phòng mới</span>
                </a>
            </div>
            <table>
                <thead>
                <tr>
                    <th>ID Phòng</th>
                    <th>Tên phòng</th>
                    <th>Số hàng</th>
                    <th>Số cột</th>
                    <th>Chi nhánh</th>
                    <th>Số ghế trống</th>
                    <th>Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:set var="roomAvailableSeatsMap" value="${roomAvailableSeatsMap}"/>
                <c:forEach var="room" items="${roomData}" varStatus="loop">
                    <tr>
                        <td>${room.roomID}</td>
                        <td>${room.roomName}</td>
                        <td>${room.seatRows}</td>
                        <td>${room.seatCols}</td>
                        <td>${room.theaterName}</td>
                        <td>${roomAvailableSeatsMap[room.roomID]}</td>

                        <td>
                            <form class="hide hidden-form" method="get">
                                <input type="hidden" name="room-id_hidden"
                                       value="${room.roomID}"/>
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
