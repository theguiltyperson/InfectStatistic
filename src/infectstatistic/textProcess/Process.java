package infectstatistic.textProcess;
import infectstatistic.pojo.Province;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static infectstatistic.dao.ProvinceDAOImpl.ListAdd;
import static infectstatistic.dao.ProvinceDAOImpl.delete;


public class Process {
    /**
     * TODO
     * 存储log文件夹下的日期
     * @author hmx1
     * @version 1.0.0
     */
    public static String[] date ={"2020-01-19", "2020-01-20", "2020-01-21", "2020-01-22", "2020-01-23",
            "2020-01-24", "2020-01-25", "2020-01-26", "2020-01-27", "2020-01-28","2020-01-29",
            "2020-01-30", "2020-01-31", "2020-02-01", "2020-02-02"};
    /**
     * TODO
     * 正則匹配工具类
     * @author hmx1
     * @version 1.0.0
     */
    static class RegularMatch {

        /**
         * TODO
         * 处理：新增感染者
         * @author hmx1
         * @version 1.0.0
         */
        public static void addIp (Matcher m, ArrayList<Province> list) {
            if(!isListName(list, m.group(1))){
                Province pr = new Province(m.group(1),Integer.parseInt(m.group(2)),0,0,0);
                list.add(pr);
            }

            else{
                for(int j = 0; j < list.size(); j++){
                    if(list.get(j).getName().equals(m.group(1))){
                        list.get(j).setIp(Integer.parseInt(m.group(2)) + list.get(j).getIp());//修改该省份的感染患者人数
                    }
                }
            }
        }
        /**
         * TODO
         * 处理：新增疑似患者
         * @author hmx1
         * @version 1.0.0
         */
        public static void addSp (Matcher m, ArrayList<Province> list) {
            if(!isListName(list, m.group(1))){
                Province pr =new Province(m.group(1),0,Integer.parseInt(m.group(2)),0,0);
                list.add(pr);
            }
            else{
                for(int j = 0; j < list.size(); j++){
                    if(list.get(j).getName().equals(m.group(1))){
                        list.get(j).setSp(Integer.parseInt(m.group(2)) + list.get(j).getSp());//修改该省份的疑似患者人数
                    }
                }
            }
        }
        /**
         * TODO
         * 处理：死亡
         * @author hmx1
         * @version 1.0.0
         */
        public static void dead (Matcher m, ArrayList<Province> list) {
            if(!isListName(list, m.group(1))){
                System.out.println("该省份无感染患者，无法死亡！");
            }
            else{
                for(int j = 0; j < list.size(); j++){
                    if(list.get(j).getName().equals(m.group(1))){
                        list.get(j).setIp(list.get(j).getIp() - Integer.parseInt(m.group(2)));//修改该省份的感染患者人数
                        list.get(j).setDead(Integer.parseInt(m.group(2)) + list.get(j).getDead());//修改该省份的死亡人数
                    }
                }
            }
        }
        /**
         * TODO
         * 处理：治愈
         * @author hmx1
         * @version 1.0.0
         */
        public static void cure (Matcher m, ArrayList<Province> list) {
            if(!isListName(list, m.group(1))){
                System.out.println("该省份无感染患者，无法治愈！");
            }
            else{
                for(int j = 0; j < list.size(); j++){
                    if(list.get(j).getName().equals(m.group(1))){
                        list.get(j).setIp(list.get(j).getIp() - Integer.parseInt(m.group(2)));//修改该省份的感染患者人数
                        list.get(j).setCure(Integer.parseInt(m.group(2)) + list.get(j).getCure());//修改该省份的治愈人数
                    }
                }
            }
        }
        /**
         * TODO
         * 处理：疑似患者确诊
         * @author hmx1
         * @version 1.0.0
         */
        public static void spToIp (Matcher m, ArrayList<Province> list) {
            if (!isListName(list, m.group(1))) {
                System.out.println("该省份无疑似患者，无法确诊！");
            } else {
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).getName().equals(m.group(1))) {
                        list.get(j).setIp(Integer.parseInt(m.group(2)) + list.get(j).getIp());//修改该省份的感染患者人数
                        list.get(j).setSp(list.get(j).getSp() - Integer.parseInt(m.group(2)));//修改该省份的疑似患者人数
                    }
                }
            }
        }
        /**
         * TODO
         * 处理：排除疑似患者
         * @author hmx1
         * @version 1.0.0
         */
        public static void deleteSp(Matcher m, ArrayList<Province> list) {
            if(!isListName(list, m.group(1))){
                System.out.println("该省份无疑似患者，无法排除！");
            }
            else{
                for(int j = 0; j < list.size(); j++){
                    if(list.get(j).getName().equals(m.group(1))){
                        //修改该省份的疑似患者人数
                        list.get(j).setSp(list.get(j).getSp() - Integer.parseInt(m.group(2)));
                    }
                }
            }
        }
        /**
         * TODO
         * 对读取到的内容正则匹配
         * @author hmx1
         * @version 1.0.0
         */
        public static ArrayList<Province> match(String[] allContent){
            //RegularMatch regularMatch = null;
            ArrayList<Province> list = new ArrayList<>();
            try{
                for(int i = 0; i<allContent.length; i++){
                    BufferedReader br = new BufferedReader(new InputStreamReader
                            (new ByteArrayInputStream(allContent[i].getBytes())));
                    String s;
                    while((s = br.readLine()) != null){
                        Pattern p1 = Pattern.compile("(.*) 新增 感染患者 (\\d*)人");
                        Pattern p2 = Pattern.compile("(.*) 新增 疑似患者 (\\d*)人");
                        Pattern p3 = Pattern.compile("(.*) 死亡 (\\d*)人");
                        Pattern p4 = Pattern.compile("(.*) 治愈 (\\d*)人");
                        Pattern p5 = Pattern.compile("(.*) 疑似患者 确诊感染 (\\d*)人");
                        Pattern p6 = Pattern.compile("(.*) 排除 疑似患者 (\\d*)人");
                        Matcher m1 = p1.matcher(s);
                        Matcher m2 = p2.matcher(s);
                        Matcher m3 = p3.matcher(s);
                        Matcher m4 = p4.matcher(s);
                        Matcher m5 = p5.matcher(s);
                        Matcher m6 = p6.matcher(s);
                        //新增感染患者
                        while(m1.find()){
                            addIp(m1, list);
                        }
                        //新增疑似患者
                        while(m2.find()){
                            addSp(m2, list);
                        }
                        //死亡
                        while(m3.find()){
                            dead(m3, list);
                        }
                        //治愈
                        while(m4.find()){
                            cure(m4, list);
                        }
                        //疑似患者确诊感染
                        while(m5.find()){
                            spToIp(m5, list);
                        }
                        //排除疑似患者
                        while(m6.find()){
                            deleteSp(m6, list);
                        }
                    }
                }
                //计算全国情况
                int allIp = 0, allSp = 0, allCure = 0, allDead = 0;
                for(int i = 0; i < list.size(); i++){
                    allIp += list.get(i).getIp();
                    allSp += list.get(i).getSp();
                    allCure += list.get(i).getCure();
                    allDead += list.get(i).getDead();
                }
                Province pr = new Province("全国", allIp, allSp, allCure, allDead );
                list.add(pr);
            }catch (Exception e){
                e.printStackTrace();
            }
            return list;
        }
    }
    /**
     * TODO
     * 读取目录下指定日期前的所有日志内容
     * @author hmx1
     * @version 1.0.0
     */
    public static String[] readFile(String path,String date) throws ParseException, IOException {
        List allFilePath = getFileName(path,date);
        String[] allContent = new String[allFilePath.size()];
        for (int i = 0; i < allFilePath.size(); i++){
            File file = new File(String.valueOf(allFilePath.get(i)));
            StringBuilder result = new StringBuilder();
            // 构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String s;
            // 使用readLine方法，一次读一行，并忽略注释行
            while ((s = br.readLine()) != null && !s.startsWith("//")) {
                result.append(System.lineSeparator() + s);
            }
            br.close();
            allContent[i] = result.toString();
        }
        return allContent;
    }
    /**
     * TODO
     * 获取文件夹中，在date日期及以前的文件名
     * @author hmx1
     * @version 1.0.0
     */
    @SuppressWarnings("unchecked")
    public static List getFileName(String path, String date) throws ParseException {
        File file = new File(path);
        List listLocal = new ArrayList<>();
        if (file != null) {
            File[] f = file.listFiles();
            if (f != null) {
                for (File value : f) {
                    String str = String.valueOf(value);
                    String str1 = str.substring(str.length() - 18, str.length() - 8);
                    String str2 = str.substring(str.length() - 8);
                    if (str2.matches(".log.txt") && str1.equals(date)) {
                        listLocal.add(value);
                    }
                }
            }
        }
        return listLocal;
    }

    /**
     * TODO
     * 判断list中是否含有指定Name属性的province
     * @author hmx1
     * @version 1.0.0
     */
    public static boolean isListName(ArrayList<Province> list, String name){
        for(int i = 0; i < list.size(); i++){
            if (list.get(i).getName().equals(name)){
                return true;
            }
        }
        return false;
    }
//
//    /**
//     * TODO
//     * 比较日期前后，time1日期比time2前则返回true，否则返回false
//     * @author hmx1
//     * @version 1.0.0
//     */
//    public static boolean isBefore(String time1, String time2) throws ParseException {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd");
//        Date date1 =  simpleDateFormat.parse(time1);
//        Date date2 =  simpleDateFormat.parse(time2);
//        return !date1.after(date2);
//    }

    /**
     * TODO
     * 连接数据库操作
     * @author hmx1
     * @version 1.0.0
     */
    public static void connectMysql() throws IOException, ParseException {
        for (int i=0; i<date.length; i++){
            String[] allContent = readFile("src/infectstatistic/log",date[i]);
            ArrayList<Province> province = RegularMatch.match(allContent);
            ListAdd(province, date[i]);
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
       delete();//清空province表
       connectMysql();

//        String[] allContent = readFile("src/infectstatistic/log","2020-01-23");
//        for (int i=0; i<allContent.length; i++){
//            System.out.println(allContent[i]);
//        }
//        //RegularMatch.match(readFile("src/infectstatistic/log","2020-01-23"));
//        for (Province province : RegularMatch.match( readFile("src/infectstatistic/log","2020-01-23"))){
//            System.out.println(province.getName()+
//                    " 感染"+province.getIp()+
//                    " 疑似"+province.getSp()+
//                    " 治愈"+province.getCure()+
//                    " 死亡"+province.getDead()
//            );
//        }
//        List name = getFileName("src/infectstatistic/log","2020-01-23");
//        for (int i=0; i<name.size(); i++){
//            System.out.println(name.get(i));
//        }
    }
}
