$(function() {
    $('#myTable').DataTable({
        ajax: {
            url: '/api/v1/logs?command=all', // Thay thế 'API_URL' bằng URL của API của bạn
            dataSrc: 'data' // Sử dụng nếu dữ liệu trả về là một mảng được gói trong một đối tượng
        },
        columns: [
            { data: 'logID' },
            { data: 'user.username',
                defaultContent: 'Unknown User'},
            { data: 'action' },
            { data: 'level' },
            { data: 'targetTable' }
        ],
        layout: {
            topStart: {
                buttons: [
                    'excel'
                ]
            }
        }
    });
});