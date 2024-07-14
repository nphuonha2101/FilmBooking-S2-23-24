const formatJson = json => {
    if (json === null || json === undefined) {
        return 'No data available';
    }
    const jsonObj = JSON.parse(json);
    const formattedJSON = JSON.stringify(jsonObj, null, 2);
    const formattedJson = hljs.highlightAuto(formattedJSON).value;

    return `<pre class="text-start"><code class="language-javascript">${formattedJson}</code></pre>`;
}

const returnLogLevelBadge = (logLevel) => {
    if (logLevel === 'INFO') {
        return `<span class="badge bg-primary">${logLevel}</span>`;
    }
    if (logLevel === 'WARN') {
        return `<span class="badge bg-warning">${logLevel}</span>`;
    }
    if (logLevel === 'ALERT') {
        return `<span class="badge bg-danger">${logLevel}</span>`;
    }
}


$(function () {
    let selectLogId = 0;
    const table = $('#logTable').DataTable({
        ajax: {
            url: '/api/v1/logs?command=all',
            dataSrc: 'data'
        },
        columns: [
            {data: 'logID'},
            {data: 'username', defaultContent: 'Unknown User'},
            {data: 'reqIP'},
            {data: 'action'},
            {
                data: 'level',
                render: function (data) {
                    return returnLogLevelBadge(data);
                }
            },
            {data: 'targetTable'},
            {
                data: 'createdAt',
                render: function (data) {
                    if (data === null || data === undefined) {
                        return `<span class="text-secondary">No data</span>`;
                    }
                    return new Date(data).toLocaleString();
                },
            },
            {
                data: 'updatedAt',
                render: function (data) {
                    if (data === null || data === undefined) {
                        return `<span class="text-secondary">No data</span>`;
                    }
                    return new Date(data).toLocaleString();
                },
            },
            {
                data: null,
                defaultContent: '<button class="btn btn-outline-primary details-btn">Chi tiết</button>',
                orderable: false
            }
        ]
    });

    $('#logTable tbody').on('click', '.details-btn', function () {
        const data = table.row($(this).parents('tr')).data();

        $.ajax({
            url: `/api/v1/logs?command=id&id=${data.logID}`,
            type: 'GET',
            success: function (result) {
                // Xóa nội dung cũ của bảng
                $('#modal-body-content').empty();

                // Tạo các hàng mới với dữ liệu nhận được từ API
                const logDetails = result.data;
                selectLogId = logDetails.logID;

                const stringifiedBeforeValue = formatJson(logDetails.beforeValueJSON);

                const stringifiedAfterValue = formatJson(logDetails.afterValueJSON);


                const rows = `
                    <tr>
                        <th>LogID</th>
                        <td class="text-start">${logDetails.logID}</td>
                    </tr>
                    <tr>
                        <th>Username</th>
                        <td class="text-start">${logDetails.username}</td>
                    </tr>
                    <tr>
                        <th>Request IP</th>
                        <td class="text-start">${logDetails.reqIP}</td>
                    </tr>
                    <tr>
                        <th>Action</th>
                        <td class="text-start">${logDetails.action}</td>
                    </tr>
                    <tr>
                        <th>Level</th>
                        <td class="text-start">${returnLogLevelBadge(logDetails.level)}</td>
                    </tr>
                    <tr>
                        <th>Target Table</th>
                        <td class="text-start">${logDetails.targetTable}</td>
                    </tr>
                    <tr>
                        <th>Action Result</th>
                        <td class="text-start fw-bold">
                        ${logDetails.isActionSuccess === true ? `<span class="badge bg-success">Success</span>` : `<span class="badge bg-danger">Failed</span>`}
                        </td>
                    </tr>
                    <tr>
                        <th>Before Value</th>
                        <td class="text-start">${stringifiedBeforeValue}</td>
                    </tr>
                    <tr>
                        <th>After Value</th>
                        <td class="text-start">${stringifiedAfterValue}</td>
                    </tr>
                    <tr>
                        <th>Created At</th>
                        <td class="text-start">${logDetails.createdAt}</td>
                    </tr>
                    <tr>
                        <th>Updated At</th>
                        <td class="text-start">${logDetails.updatedAt}</td>
                    </tr>
                `;
                $('#modal-body-content').append(rows);

                // Hiển thị modal
                $('#detailsModal').modal('show');
            }
        });
    });

    $('#closeModalBtn').click(function () {
        $('#detailsModal').modal('hide');
        $('#modal-body-content').empty();

        $.ajax({
            url: '/admin/management/log',
            type: 'POST',
            data: {
                message: 'convert-level',
                logID: selectLogId
            },
            success: function (response) {
                console.log('Message sent successfully');
                $.ajax({
                    url: `/api/v1/logs?command=id&id=${selectLogId}`,
                    type: 'GET',
                    success: function (result) {
                        const logDetails = result.data;

                        const row = table.row(function (idx, data, node) {
                            return data.logID === selectLogId;
                        });

                        row.data(logDetails).draw();
                    },
                    error: function (xhr, status, error) {
                        console.error('Error updating row: ' + error);
                    }
                });
            },
            error: function (xhr, status, error) {
                console.error('Error sending message: ' + error);
            }
        });
    });
});