var yearDisplay = $('#yearDisplay');
yearDisplay.text(getCurrentYear());

document.getElementById('increaseYear').addEventListener('click', function () {
    var yearDisplay = document.getElementById('yearDisplay');
    var currentYear = parseInt(yearDisplay.textContent);
    yearDisplay.textContent = currentYear + 1;
    drawChart(currentYear + 1);
});

document.getElementById('decreaseYear').addEventListener('click', function () {
    var yearDisplay = document.getElementById('yearDisplay');
    var currentYear = parseInt(yearDisplay.textContent);
    yearDisplay.textContent = currentYear - 1;
    drawChart(currentYear - 1);
});

function drawChart(year) {
    var revenues;
    var url = 'http://localhost:8080/api/v1/revenues?command=year&year=' + year;
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            revenues = data.data;
            var options = {

                axisX: {
                    interval: 1,
                    labelAutoFit: true,
                },

                data: [
                    {
                        type: "column",
                        dataPoints: [
                            {label: "Jan", y: revenues[1].totalRevenue},
                            {label: "Feb", y: revenues[2].totalRevenue},
                            {label: "Mar", y: revenues[3].totalRevenue},
                            {label: "Apr", y: revenues[4].totalRevenue},
                            {label: "May", y: revenues[5].totalRevenue},
                            {label: "Jun", y: revenues[6].totalRevenue},
                            {label: "Jul", y: revenues[7].totalRevenue},
                            {label: "Aug", y: revenues[8].totalRevenue},
                            {label: "Sep", y: revenues[9].totalRevenue},
                            {label: "Oct", y: revenues[10].totalRevenue},
                            {label: "Nov", y: revenues[11].totalRevenue},
                            {label: "Dec", y: revenues[12].totalRevenue}
                        ]
                    }
                ]
            };
            $("#titleYear").html("<h3>Doanh thu năm " + year + ": " + revenues[0].totalRevenue + " VNĐ &" + " Số vé: " + revenues[0].ticketSold + "</h3>");
            $("#chartContainer").CanvasJSChart(options);
        })
        .catch(error => {
            console.error('There was a problem with your fetch operation:', error);
        });
}

function revenueDates(dateStart, dateEnd) {
    console.log(dateStart, dateEnd)
    var url = 'http://localhost:8080/api/v1/revenues?command=dates&dateStart=' + dateStart + '&dateEnd=' + dateEnd;
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            let total = 0;
            let count = 0;
            let revenues = data.data;
            for (var i = 0; i < revenues.length; i++) {
                total += revenues[i].totalRevenue;
                count += revenues[i].ticketSold;
            }
            $("#dailyRevenue").html(total + " VNĐ" + " & Số vé: " + count);
            drawPieChart(dateStart, dateEnd);
        })
        .catch(error => {
            console.error('There was a problem with your fetch operation:', error);
        });
}


function getCurrentYear() {
    return new Date().getFullYear();
}

function drawPieChart(dateStart, dateEnd) {
    var url = 'http://localhost:8080/api/v1/revenues?command=dates&dateStart=' + dateStart + '&dateEnd=' + dateEnd;
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            var dataPoints = data.data.map(film => ({
                y: film.totalRevenue,
                label: film.revenueName + " Số vé: " + film.ticketSold
            }));
            var chart = new CanvasJS.Chart("chartContainer2", {
                theme: "light2", // "light1", "light2", "dark1", "dark2"
                // exportEnabled: true,
                animationEnabled: true,
                title: {
                    text: "Doanh thu phim hiện có"
                },
                data: [{
                    type: "pie",
                    startAngle: 25,
                    toolTipContent: "<b>{label}</b>: {y}",
                    indexLabelFontSize: 16,
                    indexLabel: "{label} - {y}",
                    dataPoints: dataPoints
                }]
            });
            chart.render();
        })
        .catch(error => {
            console.error('There was a problem with your fetch operation:', error);
        });
}


function getFirstAndLastDayOfMonth() {
    const currentDate = new Date();
    const firstDay = new Date(currentDate.getFullYear(), currentDate.getMonth(), 1);
    const lastDay = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 0);

    console.log(firstDay, lastDay);

    return {
        firstDay: firstDay.toISOString().split('T')[0],
        lastDay: lastDay.toISOString().split('T')[0]
    };
}

drawChart(getCurrentYear());
setTimeout(() => {
    revenueDates(getFirstAndLastDayOfMonth().firstDay, getFirstAndLastDayOfMonth().lastDay);
}, 500);

