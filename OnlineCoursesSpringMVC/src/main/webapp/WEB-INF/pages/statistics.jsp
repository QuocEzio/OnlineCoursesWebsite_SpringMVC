


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<style>

    .container {
        max-width: 800px;
        margin: 0 auto;
        background-color: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    h1 {
        color: #333;
        text-align: center;
    }
    .chart-container {
        margin-top: 20px;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    th, td {
        padding: 10px;
        border: 1px solid #ddd;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
</style>


<div class="container">
    <h1>Monthly Revenue Statistics</h1>

    <div class="chart-container">
        <canvas id="revenueChart"></canvas>
    </div>

    <table>
        <thead>
            <tr>
                <th>Year</th>
                <th>Month</th>
                <th>Revenue</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="entry" items="${monthlyRevenue}">
                <tr>
                    <td>${entry[0]}</td>
                    <td>${entry[1]}</td>
                    <td><fmt:formatNumber value="${entry[2]}" type="currency" currencySymbol="VND" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script>
    var ctx = document.getElementById('revenueChart').getContext('2d');
    var chart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: [
    <c:forEach var="entry" items="${monthlyRevenue}">
                '${entry[0]}-${entry[1]}',
    </c:forEach>
                            ],
                            datasets: [{
                                    label: 'Revenue (VND)',
                                    data: [
    <c:forEach var="entry" items="${monthlyRevenue}">
        ${entry[2]},
    </c:forEach>
                                    ],
                                    backgroundColor: 'rgba(75, 192, 192, 0.6)',
                                            borderColor: 'rgba(75, 192, 192, 1)',
                                    borderWidth: 1
                                }]
                        },
                        options: {
                            responsive: true,
                            scales: {
                                y: {
                                    beginAtZero: true,
                                    title: {
                                        display: true,
                                        text: 'Revenue (VND)'
                                    },
                                    ticks: {
                                        callback: function (value, index, values) {
                                            return new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(value);
                                        }
                                    }
                                },
                                x: {
                                    title: {
                                        display: true,
                                        text: 'Year-Month'
                                    }
                                }
                            },
                            plugins: {
                                tooltip: {
                                    callbacks: {
                                        label: function (context) {
                                            let label = context.dataset.label || '';
                                            if (label) {
                                                label += ': ';
                                            }
                                            if (context.parsed.y !== null) {
                                                label += new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(context.parsed.y);
                                            }
                                            return label;
                                        }
                                    }
                                }
                            }
                        }
                    });
</script>

