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
                $('#logID').text(result.logID);
                $('#username').text(result.user.username);
                $('#action').text(result.action);
                $('#level').text(result.level);
                $('#targetTable').text(result.targetTable);
                $('#beforeValue').text(result.beforeValue);
                $('#afterValue').text(result.afterValue);
                $('#createdAt').text(result.createdAt);
                $('#updatedAt').text(result.updatedAt);
                $('#detailsModal').modal('show');
            },
            error: function(xhr, status, error) {
                alert('Error fetching log details: ' + xhr.responseText);
            }
        });
    });
});