<%--
  Created by IntelliJ IDEA.
  User: nphuo
  Date: 4/26/2024
  Time: 4:13 AM
  To change this template use File | Settings | File Templates.
--%>

<!-- HTML Table -->
<table id="myTable" class="display">
    <thead>
    <tr>
        <th>LogID</th>
        <th>Username</th>
        <th>Request IP</th>
        <th>Level</th>
        <th>Target table</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>

<script>


    $(function() {
        $('#myTable').DataTable({
            ajax: {
                url: '/api/v1/logs?command=all', // Thay thế 'API_URL' bằng URL của API của bạn
                dataSrc: '' // Sử dụng nếu dữ liệu trả về là một mảng được gói trong một đối tượng
            },
            columns: [
                { data: 'logID' },
                { data: 'user.username',
                    defaultContent: 'Unknown User'},
                { data: 'action' },
                { data: 'level' },
                { data: 'targetTable' },
            ]
        });
    });
</script>