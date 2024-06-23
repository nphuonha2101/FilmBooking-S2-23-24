$(function() {
    $('#myTable').DataTable({
        ajax: {
            url: '/api/v1/logs?command=all',
            dataSrc: 'data'
        },
        columns: [
            { data: 'logID' },
            { data: 'user.username',
                defaultContent: 'Unknown User'},
            { data: 'action' },
            { data: 'level' },
            { data: 'targetTable' },
            {data: '<button >Details</button>'}
        ]
        // layout: {
        //     topStart: {
        //         buttons: [
        //             'excel'
        //         ]
        //     }
        // }
    });
});