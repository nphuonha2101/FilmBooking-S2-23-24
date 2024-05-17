<%--
  Created by IntelliJ IDEA.
  User: nphuo
  Date: 5/17/2024
  Time: 8:02 AM
  To change this template use File | Settings | File Templates.
--%>
<div class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h2 class="modal-title">User Management</h2>
            <span class="close-button">&times;</span>
        </div>
        <div class="modal-body">
            <div class="wrapper">
                <form id="user-info_form">
                    <label for="username">Username</label>
                    <input type="text" name="username" id="username" class="readonly-input" readonly>

                    <label for="full-name">Full Name</label>
                    <input type="text" name="full-name" id="full-name">

                    <label for="email">Email</label>
                    <input type="email" name="email" id="email" readonly>

                    <label for="role">Role</label>
                    <select name="role" id="role">
                        <option value="admin">Admin</option>
                        <option value="user">User</option>
                    </select>

                    <button type="submit" class="primary-filled-button rounded-button button submit-button icon-button">
                        <span class="material-symbols-rounded">save</span>
                        <span class="hidden-span">Save</span>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/handlesCloseModal.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/handlesSubmitForm.js"></script>
<script>
    handlesSubmitForm('user-info_form', 'PATCH', '/api/v1/users');
</script>
