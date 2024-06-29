
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
            { data: 'targetTable' }
        ]
    });
});