package infectstatistic.dao;

import infectstatistic.pojo.Province;
import infectstatistic.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProvinceDAOImpl implements ProvinceDAO{

    //通过省份名和日期获取省份数据
    @Override
    public Province get(String name, String date) {
        String sql = "SELECT * FROM province WHERE name = ? and date = ?";
        Province province =new Province();
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,name);
            ps.setString(2,date);
            ResultSet rs = ps.executeQuery();//得到结果集
            int ip = rs.getInt("ip");
            int sp = rs.getInt("sp");
            int cure = rs.getInt("cure");
            int dead = rs.getInt("dead");
            province.setName(name);
            province.setIp(ip);
            province.setSp(sp);
            province.setCure(cure);
            province.setDead(dead);
            province.setDate(date);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return province;
    }

    //返回指定日期的所有省份数据
    @Override
    public List<Province> list(String date) {
        String sql = "SELECT * FROM province WHERE date = ?";
        List<Province> list = new ArrayList<>();
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,date);
            ResultSet rs = ps.executeQuery();
            String name;
            int ip,sp,cure,dead;
            while(rs.next()){
                Province province = new Province();
                name = rs.getString("name");
                ip = rs.getInt("ip");
                sp = rs.getInt("sp");
                cure = rs.getInt("cure");
                dead = rs.getInt("dead");
                date = rs.getString("date");
                province.setName(name);
                province.setIp(ip);
                province.setSp(sp);
                province.setCure(cure);
                province.setDead(dead);
                province.setDate(date);
                list.add(province);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //通过省份名获取省份数据
    @Override
    public List<Province> list(String name, boolean b) {
        String sql = "SELECT * FROM province WHERE name = ? ORDER BY date";
        List<Province> list = new ArrayList<>();
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            int ip,sp,cure,dead;
            String date;
            while(rs.next()){
                Province province = new Province();
                ip = rs.getInt("ip");
                sp = rs.getInt("sp");
                cure = rs.getInt("cure");
                dead = rs.getInt("dead");
                date = rs.getString("date");
                province.setName(name);
                province.setIp(ip);
                province.setSp(sp);
                province.setCure(cure);
                province.setDead(dead);
                province.setDate(date);
                list.add(province);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //列出所有省份数据
    @Override
    public List<Province> list() {
        String sql = "SELECT * FROM province ORDER BY date";
        List<Province> list = new ArrayList<>();
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            int ip,sp,cure,dead;
            String name,date;
            while(rs.next()){
                Province province = new Province();
                name = rs.getString("name");
                ip = rs.getInt("ip");
                sp = rs.getInt("sp");
                cure = rs.getInt("cure");
                dead = rs.getInt("dead");
                date = rs.getString("date");
                province.setName(name);
                province.setIp(ip);
                province.setSp(sp);
                province.setCure(cure);
                province.setDead(dead);
                province.setDate(date);
                list.add(province);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
