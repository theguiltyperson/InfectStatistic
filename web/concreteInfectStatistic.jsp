<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/3/8
  Time: 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>xx省疫情</title>
    <script src="js/echarts.js"></script>
    <script src="js/infographic.js"></script>
</head>
<body>
    <!--为折线图准备的DOM-->
    <div id="lineChart" style="width: 600px;height:400px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('lineChart'),'infographic');
        var option = {
            //设置标题
            title: {
                text: 'xx省xx趋势'
            },
            //数据提示框
            tooltip: {
                trigger: 'axis',
            },
            legend: {
                data: [{
                    name: 'xx',
                }]
            },
            xAxis: {
                data: ["2.11","2.12","2.13"]
            },
            yAxis: {},
            series: [{
                name: 'xx',
                type: 'line',
                data: [50,90,66]

            }]
        };
        myChart.setOption(option);
    </script>
</body>
</html>
