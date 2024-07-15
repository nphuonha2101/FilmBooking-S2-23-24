<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Raindrop
  Date: 09/07/2024
  Time: 15:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:choose>
    <c:when test="${empty sessionScope.lang || sessionScope.lang eq 'default'}">
        <fmt:setLocale value="default"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${sessionScope.lang}"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="properties.messageAdmin" var="adminMsg"/>
<section class="section justify-content-start d-flex flex-column align-items-center">

    <jsp:include page="/views/components/admin-panel.jsp"/>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center mb-4">
                <h2><fmt:message bundle="${adminMsg}" key="revenueManagement"/></h2>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div id="titleYear"></div>
                <div id="chartContainer" style="height: 370px; width: 100%;"></div>
                <div class="year-selector d-flex justify-content-center align-items-center">
                    <button id="decreaseYear" class="btn btn-secondary"> <</button>
                    <span id="yearDisplay" class="mx-3">2024</span>
                    <button id="increaseYear" class="btn btn-secondary"> ></button>
                </div>

            </div>
            <div class="col">
                <div id="chartContainer2" style="height: 370px; width: 100%;"></div>
                <div class="col">
                    <div class="form-group row">
                        <label for="dateStart" class="col-form-label col-sm-3">Từ ngày: </label>
                        <div class="col-sm-3">
                            <input type="date" id="dateStart" class="form-control">
                        </div>
                        <div id="dailyRevenueDisplay" class="col-sm-6">Doanh thu: <span id="dailyRevenue"></span>
                        </div>
                    </div>

                    <div class="col">
                        <div class="form-group row">
                            <label for="dateStart" class="col-form-label col-sm-3">Đến ngày: </label>
                            <div class="col-sm-3">
                                <input type="date" id="dateEnd" class="form-control">
                            </div>
                            <button id="okButton" class="btn btn-primary mb-2  col-sm-3"
                                    onclick="revenueDates($('#dateStart').val(), $('#dateEnd').val())">OK
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/resources/js/handesRevenue.js"></script>
</section>