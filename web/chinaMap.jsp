<%--
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
          <div>
              <input type="checkbox" id="input1" class="input1" value="on">
              <label id="choose" class="choose" for="input1"></label>
          </div>
          <script type="text/javascript">
              $(document).ready(function () {
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
              })
          </script>
          <div id="chinaMap"></div>
          <script type="text/javascript">
              var myChart = echarts.init(document.getElementById('chinaMap'));
              $.get('province_total.json').done(function (data) {
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

                      // visualMap默认是连续映射，可以设置为分段型，对于分布范围广的数据
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
                              data: [
                                  {name: data['name'][0], value: data['value'][0]},
                                  {name: data['name'][1], value: data['value'][1]},
                                  {name: data['name'][2], value: data['value'][2]},
                                  {name: data['name'][3], value: data['value'][3]},
                                  {name: data['name'][4], value: data['value'][4]},
                                  {name: data['name'][5], value: data['value'][5]},
                                  {name: data['name'][6], value: data['value'][6]},
                                  {name: data['name'][7], value: data['value'][7]},
                                  {name: data['name'][8], value: data['value'][8]},
                                  {name: data['name'][9], value: data['value'][9]},
                                  {name: data['name'][10], value: data['value'][10]},
                                  {name: data['name'][11], value: data['value'][11]},
                                  {name: data['name'][12], value: data['value'][12]},
                                  {name: data['name'][13], value: data['value'][13]},
                                  {name: data['name'][14], value: data['value'][14]},
                                  {name: data['name'][15], value: data['value'][15]},
                                  {name: data['name'][16], value: data['value'][16]},
                                  {name: data['name'][17], value: data['value'][17]},
                                  {name: data['name'][18], value: data['value'][18]},
                                  {name: data['name'][19], value: data['value'][19]},
                                  {name: data['name'][20], value: data['value'][20]},
                                  {name: data['name'][21], value: data['value'][21]},
                                  {name: data['name'][22], value: data['value'][22]},
                                  {name: data['name'][23], value: data['value'][23]},
                                  {name: data['name'][24], value: data['value'][24]},
                                  {name: data['name'][25], value: data['value'][25]},
                                  {name: data['name'][26], value: data['value'][26]},
                                  {name: data['name'][27], value: data['value'][27]},
                                  {name: data['name'][28], value: data['value'][28]},
                                  {name: data['name'][29], value: data['value'][29]},
                                  {name: data['name'][30], value: data['value'][30]},
                                  {name: data['name'][31], value: data['value'][31]},
                                  {name: data['name'][32], value: data['value'][32]},
                                  {name: data['name'][33], value: data['value'][33]}
                              ],
                          }
                      ]
                  });
              });
              /*$("#chinaMap").click(function () {
                  window.location.href="concreteInfectStatistic.jsp";
              })*/
              //点击省份后跳转到具体疫情的页面
              myChart.on("click",function (params) {
                  window.location.href = "Servlet?flag=2&name="+params.name;

              })
          </script>
          <footer style="margin-top: 50px">
              <div id="footer">
                  积极防护，保护自己，戴口罩，勤洗手
              </div>
          </footer>
      </div>
  </body>
</html>
