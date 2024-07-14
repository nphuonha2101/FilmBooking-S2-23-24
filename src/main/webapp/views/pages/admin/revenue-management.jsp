<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Raindrop
  Date: 09/07/2024
  Time: 15:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:choose>
    <c:when test="${empty sessionScope.lang || sessionScope.lang eq 'default'}">
        <fmt:setLocale value="default"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${sessionScope.lang}"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="properties.messageAdmin" var="adminMsg"/>
<section class="section justify-content-start d-flex flex-column align-items-center">

    <jsp:include page="/views/components/admin-panel.jsp"/>
    <div class="container">
        <div class="row">
            <div class="col-12 text-center mb-4">
                <h2><fmt:message bundle="${adminMsg}" key="revenueManagement"/></h2>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div id="titleYear"></div>
                <div id="chartContainer" style="height: 370px; width: 100%;"></div>
                <div class="year-selector d-flex justify-content-center align-items-center">
                    <button id="decreaseYear" class="btn btn-secondary">-</button>
                    <span id="yearDisplay" class="mx-3">2024</span>
                    <button id="increaseYear" class="btn btn-secondary">+</button>
                </div>

            </div>
            <div class="col">
                <div id="chartContainer2" style="height: 370px; width: 100%;"></div>
                <div id="dailyRevenueDisplay">Doanh thu ngày: <span id="dailyRevenue"></span></div>
                <div class="form-group row">
                    <label for="dateStart" class="col-form-label col-sm-3">Chọn ngày bắt đầu: </label>
                    <div class="col-sm-3">
                        <input type="date" id="dateStart" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="dateStart" class="col-form-label col-sm-3">Chọn ngày ket thuc: </label>
                    <div class="col-sm-3">
                        <input type="date" id="dateEnd" class="form-control">
                    </div>
                </div>
                <button id="okButton" class="btn btn-primary mb-2" onclick="revenueDates($('#dateStart').val(), $('#dateEnd').val())">OK</button>

            </div>
        </div>
    </div>
    <script>
        var yearDisplay = $('#yearDisplay');
        yearDisplay.text(getCurrentYear());

        document.getElementById('increaseYear').addEventListener('click', function() {
            var yearDisplay = document.getElementById('yearDisplay');
            var currentYear = parseInt(yearDisplay.textContent);
            yearDisplay.textContent = currentYear + 1;
            drawChart(currentYear + 1);
        });

        document.getElementById('decreaseYear').addEventListener('click', function() {
            var yearDisplay = document.getElementById('yearDisplay');
            var currentYear = parseInt(yearDisplay.textContent);
            yearDisplay.textContent = currentYear - 1;
            drawChart(currentYear - 1);
        });
        function drawChart(year) {
            var revenues ;
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
                    console.log(revenues);
                    var options = {

                        axisX: {
                            interval: 1,
                            labelAutoFit: true,
                        },

                        data: [
                            {
                                type: "column",
                                dataPoints: [
                                    { label: "Jan",  y: revenues[1]  },
                                    { label: "Feb", y: revenues[2]  },
                                    { label: "Mar", y: revenues[3]  },
                                    { label: "Apr",  y: revenues[4]  },
                                    { label: "May",  y: revenues[5]  },
                                    { label: "Jun",  y: revenues[6]  },
                                    { label: "Jul",  y: revenues[7]  },
                                    { label: "Aug",  y: revenues[8]  },
                                    { label: "Sep",  y: revenues[9]  },
                                    { label: "Oct",  y: revenues[10]  },
                                    { label: "Nov",  y: revenues[11]  },
                                    { label: "Dec",  y: revenues[12]  }
                                ]
                            }
                        ]
                    };
                    $("#titleYear").html("<h3>Doanh thu năm " + year + ": " + revenues[0] + "</h3>");
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
                    console.log(data);
                    $("#dailyRevenue").html(data);
                })
                .catch(error => {
                    console.error('There was a problem with your fetch operation:', error);
                });
        }
        drawChart(getCurrentYear());

        function getCurrentYear() {
            return new Date().getFullYear();
        }

        function fetchAndRenderFilmChartData() {
            var url = 'http://localhost:8080/api/v1/revenues?command=films';
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
                        label: film.filmName
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
                    text: "Doanh thu phim"
                },
                data: [{
                    type: "pie",
                    startAngle: 25,
                    toolTipContent: "<b>{label}</b>: {y}%",
                    indexLabelFontSize: 16,
                    indexLabel: "{label} - {y}%",
                    dataPoints: dataPoints
                }]
            });
            chart.render();
        }
        fetchAndRenderFilmChartData();

    </script>
</section>