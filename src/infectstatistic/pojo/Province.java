package infectstatistic.pojo;

public class Province {
    private String name;//ʡ����
    private String date;//����
    private int ip;//��Ⱦ
    private int sp;//����
    private int cure;//����
    private int dead;//����

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
