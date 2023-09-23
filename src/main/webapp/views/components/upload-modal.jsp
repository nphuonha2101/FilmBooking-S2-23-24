<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 17-09-2023
  Time: 8:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal" id="modal">
    <div class="modal-content">
        <h1>${modalTitle}</h1>
        <form action="upload-file" enctype="multipart/form-data" id="form">
            <input type="file" id="file-upload" name="file-upload">
        </form>
        <div class="modal-footer">
            <button class="outlined-button button" id="close-modal-button">Cancel</button>
            <button class="light-filled-button button" id="submit-button">Submit</button>
        </div>
    </div>
</div>
