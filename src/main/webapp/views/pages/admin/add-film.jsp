<%--
  Created by IntelliJ IDEA.
  User: QDang
  Date: 23-09-2023
  Time: 09:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- Button upload file -->

<section class="section align-top admin-two-cols__wrapper centered-vertical-content">
    <div class="container ">
        <jsp:include page="/views/components/admin-panel.jsp"/>
    </div>
    <div class="container centered-vertical-content">

        <h1>${sectionTitle}</h1>


        <form method="post" action="add-film" enctype="multipart/form-data">
            <div class="two-col__wrapper centered-vertical-content">
                <!-- text form in left -->
                <div>
                    <label for="film-id">Phim ID</label>
                    <input type="text" placeholder="Phim ID" name="film-id" id="film-id" required/>
                    <label for="film-name">Tên phim</label>
                    <input type="text" placeholder="Tên phim" name="film-name" id="film-name" required/>
                    <label for="film-price">Giá vé</label>
                    <input type="number" min="0" placeholder="Giá vé" name="film-price" id="film-price"
                           required/>
                    <label for="director">Đạo diễn</label>
                    <input type="text" placeholder="Đạo diễn" name="director" id="director"
                           required/>
                    <label for="actor">Diễn viên</label>
                    <input type="text" placeholder="Diễn viên" name="actor" id="actor"
                           required/>
                    <label for="film-length">Độ dài phim</label>
                    <input type="number" min="0" placeholder="Độ dài phim" name="film-length" id="film-length"
                           required/>

                </div>


                <!-- hidden form in right -->
                <div class="centered-vertical-content">
                    <label style="margin: 2rem;" for="upload-img" class="primary-filled-button button rounded-button">Chọn
                        ảnh</label>
                    <input type="file" id="upload-img" name="upload-img" value="Chọn ảnh"/>
                    <div class="img wrapper" id="film-img"></div>
                    <input type="hidden" name="film-img-path" id="film-img-path" value="">
                </div>

                <input type="submit" class="primary-filled-button button" value="Thêm phim">
            </div>
        </form>

    </div>
</section>
