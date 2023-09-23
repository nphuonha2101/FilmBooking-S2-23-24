<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 15-09-2023
  Time: 7:38 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="two-col__wrapper">
    <div class="left-col__wrapper">
        <div class="footer-text">
            <h2>Film Booking</h2>
            <p>&copy; 2023 - Đoàn Quốc Đăng và Nguyễn Phương Nhã. Mọi quyền được bảo lưu.</p>
        </div>
    </div>

    <div class="right-col__wrapper">
        <h2>Quick Links</h2>
        <ul id="link-list">
            <li><a href="https://github.com/conmuaxadan" target="_blank">-> Trang GitHub của Đoàn Quốc Đăng</a></li>
            <li><a href="https://github.com/nphuonha2101" target="_blank">-> Trang GitHub của Nguyễn Phương Nhã</a></li>
            <li><a href="https://github.com/nphuonha2101/FilmBooking-WebProgramming" target="_blank">-> Trang GitHub của
                Project</a></li>
        </ul>
    </div>
</div>

<!-- Back to Top -->
<div id="back-to-top" class="back-to-top light-filled-button button">
   <span class="material-symbols-outlined">
expand_less</span>
</div>

<!-- JavaScript -->
<%--<script type="text/javascript">--%>
<%--    <%@include file="resources/js/handlesBackToTop.js"%>--%>
<%--    <%@include file="resources/js/handlesSlideShow.js"%>--%>
<%--    <%@include file="resources/js/handlesTab.js"%>--%>
<%--    <%@include file="resources/js/handlesUploadFilmImg.js"%>--%>
<%--</script>--%>
<script type="text/javascript" src="<c:url value='/resources/js/handlesNavigationBar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/handlesBackToTop.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/handlesTab.js'/>"></script>
<%--<script type="text/javascript" src="<c:url value='/resources/js/handlesValidateForm.js'/>"></script>--%>
<script type="text/javascript" src="<c:url value='/resources/js/handlesUploadFilmImg.js'/>"></script>
