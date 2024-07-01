
$(function() {
    let selectLogId = 0;
    const table = $('#logTable').DataTable({
        ajax: {
            url: '/api/v1/logs?command=all',
            dataSrc: 'data'
        },
        columns: [
            { data: 'logID' },
            { data: 'username', defaultContent: 'Unknown User' },
            { data: 'reqIP' },
            { data: 'action' },
            { data: 'level' },
            { data: 'targetTable' },
            {
                data: null,
                defaultContent: '<button class="btn btn-info details-btn">Details</button>',
                orderable: false
            }
        ]
    });

    $('#logTable tbody').on('click', '.details-btn', function() {
        const data = table.row($(this).parents('tr')).data();

        $.ajax({
            url: `/api/v1/logs?command=id&id=${data.logID}`,
            type: 'GET',
            success: function(result) {
                // Xóa nội dung cũ của bảng
                $('#modal-body-content').empty();

                // Tạo các hàng mới với dữ liệu nhận được từ API
                const logDetails = result.data;
                selectLogId = logDetails.logID;

                const rows = `
                    <tr>
                        <th>LogID</th>
                        <td>${logDetails.logID}</td>
                    </tr>
                    <tr>
                        <th>Username</th>
                        <td>${logDetails.username}</td>
                    </tr>
                    <tr>
                        <th>Request IP</th>
                        <td>${logDetails.reqIP}</td>
                    </tr>
                    <tr>
                        <th>Action</th>
                        <td>${logDetails.action}</td>
                    </tr>
                    <tr>
                        <th>Level</th>
                        <td>${logDetails.level}</td>
                    </tr>
                    <tr>
                        <th>Target Table</th>
                        <td>${logDetails.targetTable}</td>
                    </tr>
                    <tr>
                        <th>Before Value</th>
                        <td>${logDetails.beforeValue}</td>
                    </tr>
                    <tr>
                        <th>After Value</th>
                        <td>${logDetails.afterValue}</td>
                    </tr>
                    <tr>
                        <th>Created At</th>
                        <td>${logDetails.createdAt}</td>
                    </tr>
                    <tr>
                        <th>Updated At</th>
                        <td>${logDetails.updatedAt}</td>
                    </tr>
                `;
                $('#modal-body-content').append(rows);

                // Hiển thị modal
                $('#detailsModal').modal('show');
            }
        });
    });

    $('#closeModalBtn').click(function() {
        $('#detailsModal').modal('hide');
        $('#modal-body-content').empty();

        $.ajax({
            url: '/admin/management/log',
            type: 'POST',
            data: {
                message: 'convert-level',
                logID: selectLogId
            },
            // success: function(response) {
            //     console.log('Message sent successfully');
            //     location.reload();
            // },
            success: function(response) {
                console.log('Message sent successfully');
                $.ajax({
                    url: `/api/v1/logs?command=id&id=${selectLogId}`,
                    type: 'GET',
                    success: function(result) {
                        const logDetails = result.data;

                        const row = table.row(function(idx, data, node) {
                            return data.logID === selectLogId;
                        });

                        row.data(logDetails).draw();
                    },
                    error: function(xhr, status, error) {
                        console.error('Error updating row: ' + error);
                    }
                });
            },
            error: function(xhr, status, error) {
                console.error('Error sending message: ' + error);
            }
        });
    });
});