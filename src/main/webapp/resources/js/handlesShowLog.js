$(function() {
    const table = $('#myTable').DataTable({
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
            {data: null,
            defaultContent: '<button class="btn btn-danger details-btn">Details</button>',
                orderable: false}
        ]
        // layout: {
        //     topStart: {
        //         buttons: [
        //             'excel'
        //         ]
        //     }
        // }
    });

    $('#myTable tbody').on('click', '.details-btn', function() {
        const data = table.row($(this).parents('tr')).data();
        $.ajax({
            url: `/api/v1/logs/${data.logID}`,
            type: 'GET',
            success: function(result) {

                $('#detailsModal').modal('show');
            }
        });
    });
});