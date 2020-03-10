package infectstatistic.servlet;

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
            provinceDAO =new ProvinceDAOImpl();
            List<Province> local = provinceDAO.list(name,true);//获取当地每天数据
            int ip = 0,sp = 0,cure = 0,dead = 0;
            //累加当地每天的数据
            for (Province province : local) {
                //System.out.println(province.getIp()+" "+province.getSp());
                ip += province.getIp();
                sp += province.getSp();
                cure += province.getCure();
                dead += province.getDead();
            }
            req.setAttribute("name",name);
            req.setAttribute("totalip",ip);
            req.setAttribute("totalsp",sp);
            req.setAttribute("totalcure",cure);
            req.setAttribute("totaldead",dead);
            req.setAttribute("totalname",name);
            req.setAttribute("local",local);
            req.getRequestDispatcher("concreteInfectStatistic.jsp").forward(req,resp);
        }
        //否则跳转到第一个页面
        else{



            req.getRequestDispatcher("chinaMap.jsp").forward(req,resp);
        }


    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
