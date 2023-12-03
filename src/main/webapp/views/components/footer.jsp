<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 15-09-2023
  Time: 7:38 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:choose>
    <c:when test="${empty sessionScope.lang || sessionScope.lang eq 'default'}">
        <fmt:setLocale value="default"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${sessionScope.lang}"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="properties.message" var="msg"/>

<div class="footer">
    <div>
        <div class="footer-text">
            <h2>FilmBooking</h2>
            <p><fmt:message key="footerCopyright" bundle="${msg}"/> </p>
        </div>
    </div>

    <div>
        <h2><fmt:message key="footerQuickLinks" bundle="${msg}"/></h2>
        <ul class="link-list">
            <li><a href="home">-> <fmt:message key="home" bundle="${msg}"/></a></li>
            <li><a href="booking-history">-> <fmt:message key="bookingHistory" bundle="${msg}"/></a></li>
            <li><a href="account-info">-> <fmt:message key="yourAccount" bundle="${msg}"/></a></li>
        </ul>
    </div>
    <div>
        <h2><fmt:message key="aboutUsFooter" bundle="${msg}"/></h2>
        <ul class="link-list">

            <li><a href="https://github.com/conmuaxadan" target="_blank">-> <fmt:message key="footerDQDGitHub" bundle="${msg}"/></a></li>
            <li><a href="https://github.com/nphuonha2101" target="_blank">-> <fmt:message key="footerNPNGitHub" bundle="${msg}"/></a></li>
            <li><a href="https://github.com/nphuonha2101/FilmBooking-WebProgramming" target="_blank">->
                <fmt:message key="footerProjectGitHub" bundle="${msg}"/>
            </a>
            </li>
        </ul>
    </div>
</div>

<!-- Back to Top -->
<div id="back-to-top" class="back-to-top light-filled-button button">
   <span class="material-symbols-rounded">
expand_less</span>
</div>

<!-- JavaScript -->
<script type="module" src="<c:url value="/resources/js/handlesScrolls.js"/>"></script>
<script type="module" src="<c:url value="/resources/js/utils.js"/>"></script>
<script type="module" src="<c:url value="/resources/js/handlesUploadFilmImg.js"/>"></script>
<script type="module" src="<c:url value="/resources/js/handlesSubmitHiddenForm.js"/>"></script>
<script type="module" src="<c:url value="/resources/js/handlesChooseSeats.js"/>"></script>
<script type="module" src="<c:url value="/resources/js/handlesAccordions.js"/>"></script>
<script type="module" src="<c:url value="/resources/js/handlesAnimation.js"/>"></script>
<script type="module" src="<c:url value="/resources/js/handlesSearchModal.js"/>"></script>
<script type="module" src="<c:url value="/resources/js/checkCookiesEnabled.js"/>"></script>
<script type="module" src="<c:url value="/resources/js/handlesDisplaySelectedGenres.js"/>"></script>
