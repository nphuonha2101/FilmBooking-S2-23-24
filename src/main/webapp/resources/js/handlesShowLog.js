$(function() {
    $('#myTable').DataTable({
        ajax: {
            url: '/api/v1/logs?command=all', // Thay thế 'API_URL' bằng URL của API của bạn
            dataSrc: '' // Sử dụng nếu dữ liệu trả về là một mảng được gói trong một đối tượng
        },
        columns: [
            {
                data: null,
                render: function(data, type, row) {
                    return '<input type="checkbox" name="select[]" value="' + data.logID + '">';
                }
            },
            { data: 'logID' },
            { data: 'user.username',
                defaultContent: 'Unknown User'},
            { data: 'action' },
            { data: 'level' },
            { data: 'targetTable' }
        ]
    });
});