<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 27-09-2023
  Time: 10:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- Button upload file -->
<c:set var="editFilm" value='${editFilm}'/>
<section class="section align-top admin-two-cols__wrapper centered-vertical-content">
    <div class="container ">
        <jsp:include page="/views/components/admin-panel.jsp"/>
    </div>
    <div class="container centered-vertical-content">

        <h1>${sectionTitle}</h1>


        <form method="post" action="edit-film" enctype="multipart/form-data">
            <div class="centered-vertical-content">
                <div class="two-col__wrapper centered-vertical-content">
                    <!-- text form in left -->
                    <div class="left-col">
                        <label for="film-id">Phim ID</label>
                        <input class="readonly-input" type="text" placeholder="Phim ID" name="film-id" id="film-id"
                               value="${editFilm.filmID}"
                               readonly/>
                        <label for="film-name">Tên phim</label>
                        <input type="text" placeholder="Tên phim" name="film-name" id="film-name"
                               value="${editFilm.filmName}" required/>
                        <label for="film-price">Giá vé</label>
                        <input type="number" min="0" placeholder="Giá vé" name="film-price" id="film-price"
                               value="${editFilm.filmPrice}"
                               required/>
                        <label for="director">Đạo diễn</label>
                        <input type="text" placeholder="Đạo diễn" name="director" id="director"
                               value="${editFilm.director}"
                               required/>
                        <label for="actors">Diễn viên</label>
                        <input type="text" placeholder="Diễn viên" name="actors" id="actors"
                               value="${editFilm.cast}"
                               required/>

                    </div>

                    <!-- hidden form in right -->
                    <div class="centered-vertical-content right-col">
                        <input type="file" id="upload-img" name="upload-img" value="Chọn ảnh"/>
                        <div class="film-img-in-card" id="film-img" style="background-image: url('<c:url
                                value="${editFilm.imgPath}"/>')"></div>
                        <input type="hidden" name="film-img-name" id="film-img-name" value="">
                        <label style="margin: 2rem;" for="upload-img"
                               class="primary-filled-button button rounded-button">Chọn
                            ảnh</label>
                    </div>


                    <div class="left-col">
                        <label for="film-length">Độ dài phim</label>
                        <input type="number" min="0" placeholder="Độ dài phim" name="film-length" id="film-length"
                               value="${editFilm.filmLength}"
                               required/>
                    </div>
                    <div class="right-col">
                        <label for="genre-ids">Mã thể loại</label>
                        <input type="text" placeholder="Mã thể loại" name="genre-ids" id="genre-ids"
                               value="${filmGenreIDs}"
                               required/>
                    </div>
                </div>

                <div class="wrapper centered-vertical-content">
                    <label for="film-description_textarea">
                        Mô tả phim
                    </label>
                    <textarea class="none-resize_textarea" name="film-description" id="film-description_textarea"
                              placeholder="Nhập mô tả phim">${editFilm.filmDescription}</textarea>
                    <input type="submit" class="primary-filled-button button" value="Sửa phim">
                </div>
            </div>
        </form>

    </div>
</section>