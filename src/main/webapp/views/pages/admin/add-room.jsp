<%--
  Created by IntelliJ IDEA.
  User: ConMuaXaDan
  Date: 10/24/2023
  Time: 7:54 AM
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
                    <form method="post" action="add-room">
<%--                        <label for="room-id">Room ID</label>--%>
<%--                        <input type="text" placeholder="Mã phòng" name="room-id" id="room-id"--%>
<%--                               required/>--%>
                        <label for="room-name">Tên phòng</label>
                        <input type="text" placeholder="Tên phòng" name="room-name" id="room-name"
                               required/>
                        <label for="room-rows">Số hàng</label>
                        <input type="number" min="0" placeholder="Số hàng" name="room-rows" id="room-rows"
                               required/>
                        <label for="room-cols">Số cột</label>
                        <input type="number" min="0" placeholder="Số cột" name="room-cols" id="room-cols"
                               required/>
                        <label for="theater-id">Chi nhánh</label>
                        <select id="theater-id" name="theater-id">
                            <c:forEach var="theater" items="${theaters}">
                                <option value="${theater.theaterID}">${theater.theaterName}
                                    - ${theater.theaterAddress}</option>
                            </c:forEach>
                            <div class="centered-horizontal-content">
                                <input class="primary-filled-button button" type="submit" value="Thêm phòng">
                            </div>
                        </select>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
