package win.ccav.model;

/**
 * Created by paul on 2017/6/22.
 */
public class NewSearchForm {
    private Double stuScore;
    private Double scoreBodong;
    private Integer stuWeici;
    private Integer weiciBodong;
    private String xName;
    private String zName;
    private String shenfen;
    private String chengshi;
    private String wenli;
    private String banxuexingzhi;
    private String sort;
    private String pici;
    private String leixing;

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    @Override
    public String toString() {
        return "NewSearchForm{" +
                "stuScore=" + stuScore +
                ", scoreBodong=" + scoreBodong +
                ", stuWeici=" + stuWeici +
                ", weiciBodong=" + weiciBodong +
                ", xName='" + xName + '\'' +
                ", zName='" + zName + '\'' +
                ", shenfen='" + shenfen + '\'' +
                ", chengshi='" + chengshi + '\'' +
                ", wenli='" + wenli + '\'' +
                ", banxuexingzhi='" + banxuexingzhi + '\'' +
                ", sort='" + sort + '\'' +
                ", pici='" + pici + '\'' +
                '}';
    }

    public Double getStuScore() {
        return stuScore;
    }

    public void setStuScore(Double stuScore) {
        this.stuScore = stuScore;
    }

    public Double getScoreBodong() {
        return scoreBodong;
    }

    public void setScoreBodong(Double scoreBodong) {
        this.scoreBodong = scoreBodong;
    }

    public Integer getStuWeici() {
        return stuWeici;
    }

    public void setStuWeici(Integer stuWeici) {
        this.stuWeici = stuWeici;
    }

    public Integer getWeiciBodong() {
        return weiciBodong;
    }

    public void setWeiciBodong(Integer weiciBodong) {
        this.weiciBodong = weiciBodong;
    }

    public String getxName() {
        return xName;
    }

    public void setxName(String xName) {
        this.xName = xName;
    }

    public String getzName() {
        return zName;
    }

    public void setzName(String zName) {
        this.zName = zName;
    }

    public String getShenfen() {
        return shenfen;
    }

    public void setShenfen(String shenfen) {
        this.shenfen = shenfen;
    }

    public String getChengshi() {
        return chengshi;
    }

    public void setChengshi(String chengshi) {
        this.chengshi = chengshi;
    }

    public String getWenli() {
        return wenli;
    }

    public void setWenli(String wenli) {
        this.wenli = wenli;
    }

    public String getBanxuexingzhi() {
        return banxuexingzhi;
    }

    public void setBanxuexingzhi(String banxuexingzhi) {
        this.banxuexingzhi = banxuexingzhi;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getPici() {
        return pici;
    }

    public void setPici(String pici) {
        this.pici = pici;
    }
}
