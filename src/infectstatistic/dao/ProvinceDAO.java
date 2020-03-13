package infectstatistic.dao;

import infectstatistic.pojo.Province;

import java.util.ArrayList;
import java.util.List;

public interface ProvinceDAO {
    Province get(String name, String date);//通过省份名和日期获取省份数据
    List<Province> list(String date);//返回指定日期的所有省份数据
    List<Province> list(String name, boolean b);//通过省份名获取省份数据
    List<Province> list();//列出所有省份数据
    //static void ListAdd(ArrayList<Province> list, String date);//将处理好的数据添加到数据库
}
