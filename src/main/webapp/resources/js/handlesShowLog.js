$(function () {
    $('#logTable').DataTable({
        ajax: {
            url: '/api/v1/logs?command=all',
            dataSrc: 'data'
        },
        columns: [
            {data: 'logID'},
            {
                data: 'user.username',
                defaultContent: 'Unknown User'
            },
            {data: 'reqIP'},
            {data: 'action'},
            {data: 'level'},
            {data: 'targetTable'},
            {
                data: 'createdAt',
                defaultContent: 'Unknown Date'
            },
            {
                data: 'updatedAt',
                defaultContent: 'Unknown Date'
            }

        ]
    });
});