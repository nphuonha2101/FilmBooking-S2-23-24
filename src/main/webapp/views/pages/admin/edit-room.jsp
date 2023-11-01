<%--
  Created by IntelliJ IDEA.
  User: ConMuaXaDan
  Date: 10/24/2023
  Time: 1:12 PM
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
            <!-- text form in left -->
            <div class="wrapper centered-vertical-content">
                <div>
                    <c:set var="editRoom" value="${editRoom}"/>
                    <form method="post" action="edit-room">
                        <label for="room-id">Room ID</label>
                        <input class="readonly-input" type="text" placeholder="Mã phòng" name="room-id" id="room-id"
                               value="${editRoom.roomID}"
                               readonly/>
                        <label for="room-name">Tên phòng</label>
                        <input type="text" placeholder="Tên phòng" name="room-name" id="room-name"
                               value="${editRoom.roomName}"
                               required/>

                        <label for="seat-rows">Số hàng</label>
                        <input type="number" min="0" placeholder="Số hàng" name="seat-rows" id="seat-rows"
                               value="${editRoom.seatRows}"
                               required/>
                        <label for="seat-cols">Số cột</label>
                        <input type="number" min="0" placeholder="Số cột" name="seat-cols" id="seat-cols"
                               value="${editRoom.seatCols}"
                               required/>
                        <label for="theater-id">Chi nhánh</label>
                        <select id="theater-id" name="theater-id">
                            <c:forEach var="theater" items="${theaters}">
                                <c:choose>
                                    <c:when test="${editRoom.theaterID eq theater.theaterID}">
                                        <option selected value="${theater.theaterID}">${theater.theaterName}
                                            - ${theater.theaterAddress}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${theater.theaterID}">${theater.theaterName}
                                            - ${theater.theaterAddress}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <div class="centered-horizontal-content">
                                <input class="primary-filled-button button" type="submit" value="Sửa phòng">
                            </div>
                        </select>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
