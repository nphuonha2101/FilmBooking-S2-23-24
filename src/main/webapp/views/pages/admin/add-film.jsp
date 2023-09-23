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

<form method="post" action="add-film" enctype="multipart/form-data">
    <div class="two-col__wrapper align-center">
        <label for="upload-img" class="light-filled-button button rounded-button">Chọn ảnh</label>
        <input type="file" id="upload-img" name="upload-img" value="Chọn ảnh"/>
        <div class="img wrapper" id="film-img"></div>
    </div>
    <input type="hidden" name="film-img-path" id="film-img-path" value="">
    <label for="film-id">Film ID</label>
    <input type="text" placeholder="Film ID | Text" name="film-id" id="film-id" required/>
    <label for="film-name">Film Name</label>
    <input type="text" placeholder="Film Name | Text" name="film-name" id="film-name" required/>
    <label for="film-price">Ticket Price</label>
    <input type="number" placeholder="Film Price | Number" name="film-price" id="film-price"
           required/>
    <label for="film-genre">Genre</label>
    <input type="text" placeholder="Film Genre | Text" name="film-genre" id="film-genre"
           required/>
    <input type="submit" class="primary-filled-button button" value="Add Film">
</form>