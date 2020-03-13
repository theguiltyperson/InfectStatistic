<%@ page import="infectstatistic.pojo.Province" %>
<%@ page import="java.util.List" %>
<%@ page import="infectstatistic.constant.ProvinceName" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/3/7
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>新型冠状病毒疫情地图</title>
      <script src="js/echarts.js"></script>
      <script src="js/jquery-3.4.1.min.js"></script>
      <script src="js/china.js"></script>
      <link href="css/style.css" rel="stylesheet">
  </head>
  <body>
      <div id="main">
          <header>
              <div>
                  <h3 class="virus1">新型冠状病毒肺炎</h3>
                  <h1 class="virus2" data-text="疫情数据报告">疫情数据报告</h1>
              </div>
          </header>
          <div id="virusSummary">
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
          <!--<div>
              <input type="checkbox" id="input1" class="input1" value="on">
              <label id="choose" class="choose" for="input1"></label>
          </div>-->
          <script type="text/javascript">
              /*$(document).ready(function () {
                  $("#choose").click(function (){
                      //显示累计确诊
                      if($("#choose").attr("class") === "choose"){
                          $("#choose").attr("class","choose1");
                      }
                      //显示现有确诊
                      else{
                          $("#choose").attr("class","choose");
                      }
                  })
              })*/
          </script>
          <div id="chinaMap"></div>
          <script type="text/javascript">
              var myChart = echarts.init(document.getElementById('chinaMap'));
                  myChart.setOption({
                      // 数据提示框
                      tooltip: {
                          trigger: 'item', // item放到数据区域触发
                          formatter: '地区：{b}<br/>确诊：{c}' // 提示数据格式br表示换行，地图 : {a}（系列名称），{b}（区域名称），{c}（合并数值）, {d}（无）
                      },
                      // 工具栏
                      /*toolbox: {
                          show: true,
                          orient: 'horizontal',
                          left: 'right',
                          feature: {
                              dataView: {readOnly: false},
                              restore: {},
                              saveAsImage: {}
                          }
                      },*/

                      // 使用透明度来区分疫情严重情况
                      visualMap: {
                          type: 'piecewise',
                          pieces: [
                              {gt: 1500, color: 'darkred'},
                              {gt: 1000, lte: 1500, color: 'red', colorAlpha: 1},
                              {gt: 500, lte: 1000, color: 'red', colorAlpha: 0.8},
                              {gt: 300, lte: 500, color: 'red', colorAlpha: 0.6},
                              {gt: 100, lte: 300, color: 'red', colorAlpha: 0.4},
                              {gt: 50, lte: 100, color: 'red', colorAlpha: 0.3},
                              {gt: 0, lte: 50, color: 'red', colorAlpha: 0.2},
                              {lte: 0, color: 'white'}
                          ],
                      },


                      // 具体数据
                      series: [
                          {
                              name: '各省确诊病例', // 系列名称
                              type: 'map', // 系列类型，地图
                              map: 'china', // 要使用的地图，即上面注册的地图名称
                              //roam: true, // 开启鼠标缩放和平移漫游
                              label: { // 图形上的文本标签，地图默认显示数据名
                                  show: true,
                                  formatter: '{b}', // b是数据名，c是数据值
                                  fontSize: 12
                              },
                              data:[
                                  <%
                                  int Ip;
                                  for(int i = 0; i < ProvinceName.provinceSize; i++){
                                      //ip = 0;
                                      Ip = (int) request.getAttribute(ProvinceName.provinceName[i] + "Ip");
                                  %>
                                  {name: "<%=ProvinceName.provinceName[i]%>", value: "<%=Ip%>"},
                                  <%}%>
                              ]
                          }
                      ]
                  });

              //点击省份后跳转到具体疫情的页面
              myChart.on("click",function (params) {
                  window.location.href = "Servlet?flag=2&name="+params.name;
              },true)
          </script>
          <!--为折线图准备的DOM-->
          <div id="lineChart" style="width: 800px;height:400px; position: center; background-color: #ffffff; padding-top: 40px"></div>
          <script type="text/javascript">

              var name = "全国";
              // 基于准备好的dom，初始化echarts实例
              var Chart = echarts.init(document.getElementById('lineChart'),'infographic');

              Chart.setOption({
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
                        List<Province> country = (List<Province>) request.getAttribute("country");
                        String date;
                        int ip,totalIp = 0;
                        int sp,totalSp = 0;
                        int cure,totalCure = 0;
                        int dead,totalDead = 0;
                        for(Province province : country){
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
                            for(Province province : country){
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
                            for(Province province : country){
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
                            for(Province province : country){
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
                            for(Province province : country){
                                dead = province.getDead();
                                totalDead += dead;
                            %>
                              <%=totalDead%>,
                              <%}%>]
                      }
                  ]
              },true)
          </script>
          <footer style="margin-top: 50px">
              <div id="footer">
                  积极防护，保护自己，戴口罩，勤洗手
              </div>
          </footer>
      </div>
  </body>
</html>
