<%--
  Created by IntelliJ IDEA.
  User: NhaNguyen
  Date: 17-09-2023
  Time: 8:27 PM
  To change this template use File | Settings | File Templates.
--%>
<c:if test="${not empty sessionScope.filmId}">
    <script>showModal()</script>
</c:if>
<div class="modal" id="modal">
    <div class="modal-content">
        <div class="centered-content modal-header">
            <h2>Booking Film: ${filmName}</h2>
        </div>
        <div class="modal-body">
            <p>${param.filmID}</p>
        </div>
        <div class="modal-footer">
            <button class="outlined-button button" id="close-modal-button">Cancel</button>
            <button class="light-filled-button button" id="submit-button">Submit</button>
        </div>
    </div>
</div>
