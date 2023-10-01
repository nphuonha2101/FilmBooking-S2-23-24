<%--
  Created by IntelliJ IDEA.
  User: QDang
  Date: 23-09-2023
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="container centered-vertical-content">
    <div class="centered-vertical-content wrapper">
        <h1>${title}</h1>
        <form action="upload-file" enctype="multipart/form-data" id="form">
            <input type="file" id="file-upload" name="file-upload"/>
            <input type="submit" class="primary-filled-button button rounded-button" value="Tải lên"/>
        </form>
        <div class="modal-footer">

        </div>
    </div>
</div>
