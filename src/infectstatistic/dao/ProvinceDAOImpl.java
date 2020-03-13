package infectstatistic.dao;

import infectstatistic.pojo.Province;
import infectstatistic.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProvinceDAOImpl implements ProvinceDAO {

    @Override
    public Province get(String name, String date) {
        String sql = "SELECT * FROM province WHERE name = ? and date = ?";
        Province province = new Province();
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, date);
            ResultSet rs = ps.executeQuery();//�õ������
            int ip = rs.getInt("ip");
            int sp = rs.getInt("sp");
            int cure = rs.getInt("cure");
            int dead = rs.getInt("dead");
            province.setName(name);
            province.setDate(date);
            province.setIp(ip);
            province.setSp(sp);
            province.setCure(cure);
            province.setDead(dead);

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
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,date);
            ResultSet rs = ps.executeQuery(); //得到结果集
            while(rs.next()){
                Province province = new Province();
                String name = rs.getString("name");
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
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery(); //得到结果集
            while(rs.next()){
                Province province = new Province();
                String date = rs.getString("date");
                int ip = rs.getInt("ip");
                int sp = rs.getInt("sp");
                int cure = rs.getInt("cure");
                int dead = rs.getInt("dead");
                province.setName(name);
                province.setDate(date);
                province.setIp(ip);
                province.setSp(sp);
                province.setCure(cure);
                province.setDead(dead);
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
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery(); //得到结果集
            while(rs.next()){
                Province province = new Province();
                String name = rs.getString("name");
                String date = rs.getString("date");
                int ip = rs.getInt("ip");
                int sp = rs.getInt("sp");
                int cure = rs.getInt("cure");
                int dead = rs.getInt("dead");
                province.setName(name);
                province.setDate(date);
                province.setIp(ip);
                province.setSp(sp);
                province.setCure(cure);
                province.setDead(dead);
                list.add(province);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void ListAdd(ArrayList<Province> list, String date) {
        String sql = "insert into province(name,date,ip,sp,cure,dead)" +
                "values(?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            for (Province province : list) {
                ps.setString(1, province.getName());
                ps.setString(2, date);
                ps.setInt(3, province.getIp());
                ps.setInt(4, province.getSp());
                ps.setInt(5, province.getCure());
                ps.setInt(6, province.getDead());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void delete() {
        String sql = "delete from province";
        try (Connection c = DBUtil.getConnection()) {
            Statement st = c.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
