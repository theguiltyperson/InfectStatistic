package infectstatistic.servlet;

import infectstatistic.constant.ProvinceName;
import infectstatistic.dao.ProvinceDAO;
import infectstatistic.dao.ProvinceDAOImpl;
import infectstatistic.pojo.Province;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

@WebServlet(urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flag = req.getParameter("flag");
        ProvinceDAO provinceDAO =new ProvinceDAOImpl();
        //flag=2时跳转到第二个页面
        if(flag != null && flag.equals("2")){
            String name = req.getParameter("name");
            String date = req.getParameter("Date");
            provinceDAO =new ProvinceDAOImpl();
            List<Province> local = provinceDAO.list(name,true);//获取当地每天数据
            int ip = 0,sp = 0,cure = 0,dead = 0;
            if(date != null){
                int year,month,day;
                int curYear = Integer.parseInt(date.substring(0,4));
                int curMonth = Integer.parseInt(date.substring(5,7));
                int curDay = Integer.parseInt(date.substring(8,10));
                for (Province province : local){
                    year = Integer.parseInt(province.getDate().substring(0,4));
                    month = Integer.parseInt(province.getDate().substring(5,7));
                    day = Integer.parseInt(province.getDate().substring(8,10));
                    //日期不超过的累加
                    if(year <= curYear){
                        if(month <= curMonth){
                            if(day <= curDay){
                                ip +=province.getIp();
                                sp += province.getSp();
                                cure += province.getCure();
                                dead += province.getDead();
                            }
                        }
                    }
                }
            }
            else{
                //累加当地每天的数据
                for (Province province : local) {
                    ip += province.getIp();
                    sp += province.getSp();
                    cure += province.getCure();
                    dead += province.getDead();
                }
            }
            req.setAttribute("name",name);
            req.setAttribute("totalip",ip);
            req.setAttribute("totalsp",sp);
            req.setAttribute("totalcure",cure);
            req.setAttribute("totaldead",dead);
            req.setAttribute("local",local);
            //resp.setContentType("text/html;charset=UTF-8");
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            req.getRequestDispatcher("concreteInfectStatistic.jsp").forward(req,resp);
        }
        //否则跳转到第一个页面
        else{
            String date = req.getParameter("Date");
            provinceDAO =new ProvinceDAOImpl();
            List<Province> country = provinceDAO.list("全国",true);//获取全国每天数据
            int ip = 0,sp = 0,cure = 0,dead = 0;
            if(date != null){
                int year,month,day;
                int curYear = Integer.parseInt(date.substring(0,4));
                int curMonth = Integer.parseInt(date.substring(5,7));
                int curDay = Integer.parseInt(date.substring(8,10));
                for(Province province : country){
                    year = Integer.parseInt(province.getDate().substring(0,4));
                    month = Integer.parseInt(province.getDate().substring(5,7));
                    day = Integer.parseInt(province.getDate().substring(8,10));
                    //日期不超过的累加
                    if(year <= curYear){
                        if(month <= curMonth){
                            if(day <= curDay){
                                ip +=province.getIp();
                                sp += province.getSp();
                                cure += province.getCure();
                                dead += province.getDead();
                            }
                        }
                    }
                }
                String name;
                int eachIp;
                //查找每个省份的每天的确诊数据累加到一个数值上
                for(int i = 0; i < ProvinceName.provinceSize; i++) {
                    name = ProvinceName.provinceName[i];
                    eachIp = 0;
                    List<Province> each = provinceDAO.list(name, true);//按省份名获取省份每天数据
                    for(Province province : each){
                        year = Integer.parseInt(province.getDate().substring(0,4));
                        month = Integer.parseInt(province.getDate().substring(5,7));
                        day = Integer.parseInt(province.getDate().substring(8,10));
                        if(year <= curYear){
                            if(month <= curMonth){
                                if(day <= curDay){
                                    eachIp += province.getIp();
                                }
                            }
                        }
                    }
                    req.setAttribute(name+"Ip",eachIp);
                }

            }
            else{
                //累加全国每天的数据
                for (Province province : country) {
                    ip += province.getIp();
                    sp += province.getSp();
                    cure += province.getCure();
                    dead += province.getDead();
                }
                String name;
                int eachIp;
                //查找每个省份的每天的确诊数据累加到一个数值上
                for(int i = 0; i < ProvinceName.provinceSize; i++) {
                    name = ProvinceName.provinceName[i];
                    eachIp = 0;
                    List<Province> each = provinceDAO.list(name, true);//按省份名获取省份每天数据
                    for(Province province : each){
                        eachIp += province.getIp();
                    }
                    req.setAttribute(name+"Ip",eachIp);
                }
            }
            req.setAttribute("totalip",ip);
            req.setAttribute("totalsp",sp);
            req.setAttribute("totalcure",cure);
            req.setAttribute("totaldead",dead);
            req.setAttribute("country",country);
            //resp.setContentType("text/html;charset=UTF-8");
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            req.getRequestDispatcher("chinaMap.jsp").forward(req,resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
