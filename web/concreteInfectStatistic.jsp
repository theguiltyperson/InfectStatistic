<%@ page import="infectstatistic.pojo.Province" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/3/8
  Time: 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新型冠状病毒疫情趋势</title>
    <script src="js/echarts.js"></script>
    <script src="js/infographic.js"></script>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
    <div id="main" style="background-color: #ffffff">

        <div style="margin-bottom: 10px">
            <h2><%=request.getAttribute("name")%></h2>
        </div>
        <div id="virusSummary" style="background-color: #e3e6eb; margin-bottom: 30px">
            <%
                int totalip = (int) request.getAttribute("totalip");
                int totalsp = (int) request.getAttribute("totalsp");
                int totalcure = (int) request.getAttribute("totalcure");
                int totaldead = (int) request.getAttribute("totaldead");
            %>
            <div id="ip">
                <div class="name">确诊</div>
                <div class="data"><%=totalip%></div>
            </div>
            <div id="sp">
                <div class="name">疑似</div>
                <div class="data"><%=totalsp%></div>
            </div>
            <div id="cure">
                <div class="name">治愈</div>
                <div class="data"><%=totalcure%></div>
            </div>
            <div id="dead">
                <div class="name">死亡</div>
                <div class="data"><%=totaldead%></div>
            </div>
        </div>
        <!--为折线图准备的DOM-->
        <div id="lineChart" style="width: 800px;height:450px; position: center"></div>
        <footer style="margin-top: 50px">
            <div id="footer">
                积极防护，保护自己，戴口罩，勤洗手
            </div>
        </footer>
    </div>
    <script type="text/javascript">

        var name = "<%=request.getParameter("name")%>";

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('lineChart'),'infographic');

            myChart.setOption({
                //设置标题
                title: {
                    text: name + '疫情趋势'
                },
                //数据提示框
                tooltip: {
                    trigger: 'axis',
                },
                legend: {
                    data: ['确诊','疑似','治愈','死亡']
                },
                xAxis: {
                    data:[<%
                        List<Province> local = (List<Province>) request.getAttribute("local");
                        String date;
                        int ip,totalIp = 0;
                        int sp,totalSp = 0;
                        int cure,totalCure = 0;
                        int dead,totalDead = 0;
                        for(Province province : local){
                            date = province.getDate();
                        %>
                        "<%=date%>",
                        <%}%>]
                },
                yAxis: {},
                series: [
                    {
                        name: '确诊',
                        type: 'line',
                        data:[<%
                            for(Province province : local){
                                ip = province.getIp();
                                totalIp += ip;
                            %>
                            <%=totalIp%>,
                            <%}%>]
                    },
                    {
                        name: '疑似',
                        type: 'line',
                        data:[<%
                            for(Province province : local){
                                sp = province.getSp();
                                totalSp += sp;
                            %>
                            <%=totalSp%>,
                            <%}%>]
                    },
                    {
                        name: '治愈',
                        type: 'line',
                        data:[<%
                            for(Province province : local){
                                cure = province.getCure();
                                totalCure += cure;
                            %>
                            <%=totalCure%>,
                            <%}%>]
                    },
                    {
                        name: '死亡',
                        type: 'line',
                        data:[<%
                            for(Province province : local){
                                dead = province.getDead();
                                totalDead += dead;
                            %>
                            <%=totalDead%>,
                            <%}%>]
                    }
                ]
            })
    </script>
</body>
</html>
