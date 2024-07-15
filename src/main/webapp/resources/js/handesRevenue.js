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
            revenues = data;
            console.log(data);
            var options = {

                axisX: {
                    interval: 1,
                    labelAutoFit: true,
                },

                data: [
                    {
                        type: "column",
                        dataPoints: [
                            {label: "Jan", y: revenues[1].filmRevenue},
                            {label: "Feb", y: revenues[2].filmRevenue},
                            {label: "Mar", y: revenues[3].filmRevenue},
                            {label: "Apr", y: revenues[4].filmRevenue},
                            {label: "May", y: revenues[5].filmRevenue},
                            {label: "Jun", y: revenues[6].filmRevenue},
                            {label: "Jul", y: revenues[7].filmRevenue},
                            {label: "Aug", y: revenues[8].filmRevenue},
                            {label: "Sep", y: revenues[9].filmRevenue},
                            {label: "Oct", y: revenues[10].filmRevenue},
                            {label: "Nov", y: revenues[11].filmRevenue},
                            {label: "Dec", y: revenues[12].filmRevenue}
                        ]
                    }
                ]
            };
            $("#titleYear").html("<h3>Doanh thu năm " + year + ": " + revenues[0].filmRevenue + " VNĐ &" + " Số vé: " + revenues[0].ticketSold + "</h3>");
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
            for (var i = 0; i < data.length; i++) {
                total += data[i].filmRevenue;
                count += data[i].ticketSold;
            }
            $("#dailyRevenue").html(total + " VNĐ" + " & Số vé: " + count);
            fetchAndRenderFilmChartData(dateStart, dateEnd);
        })
        .catch(error => {
            console.error('There was a problem with your fetch operation:', error);
        });
}


function getCurrentYear() {
    return new Date().getFullYear();
}

function fetchAndRenderFilmChartData(dateStart, dateEnd) {
    var url = 'http://localhost:8080/api/v1/revenues?command=dates&dateStart=' + dateStart + '&dateEnd=' + dateEnd;
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            var dataPoints = data.map(film => ({
                y: film.filmRevenue,
                label: film.filmName + " Số vé: " + film.ticketSold
            }));
            renderFilmChart(dataPoints);
        })
        .catch(error => {
            console.error('There was a problem with your fetch operation:', error);
        });
}

function renderFilmChart(dataPoints) {
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

