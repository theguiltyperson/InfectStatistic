package infectstatistic.pojo;

public class Province {
    private String name;//省份名
    private String date;//日期
    private int ip;//感染
    private int sp;//疑似
    private int cure;//治愈
    private int dead;//死亡
    public Province(){
        this.name = null;
        this.ip = 0;
        this.sp = 0;
        this.cure = 0;
        this.dead = 0;
    }
    public Province(String name, int ip, int sp, int cure, int dead){
        this.name = name;
        this.ip = ip;
        this.sp = sp;
        this.cure = cure;
        this.dead = dead;
    }
    public static Province init(){//?????province??
        return new Province(null,0,0,0,0);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public int getIp() {
        return ip;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public int getSp() {
        return sp;
    }

    public void setCure(int cure) {
        this.cure = cure;
    }

    public int getCure() {
        return cure;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }

    public int getDead() {
        return dead;
    }

}
