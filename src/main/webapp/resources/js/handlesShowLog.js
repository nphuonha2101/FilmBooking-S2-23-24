
$(function() {
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

                // Tạo một hàng mới với dữ liệu nhận được từ API
                const logDetails = result.data;
                const row = `
                    <tr>
                        <td>${logDetails.logID}</td>
                        <td>${logDetails.username}</td>
                        <td>${logDetails.reqIP}</td>
                        <td>${logDetails.action}</td>
                        <td>${logDetails.level}</td>
                        <td>${logDetails.targetTable}</td>
                        <td>${logDetails.beforeValue}</td>
                        <td>${logDetails.afterValue}</td>
                        <td>${logDetails.createdAt}</td>
                        <td>${logDetails.updatedAt}</td>
                    </tr>
                `;
                $('#modal-body-content').append(row);

                // Hiển thị modal
                $('#detailsModal').modal('show');
            }
        });
    });
});