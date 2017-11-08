package win.ccav.model;

/**
 * Created by paul on 2017/4/19.
 */
public class SearchForm {
    private Double stuScore;
    private Double bodongzhi;
    private String shenfen;
    private String chengshi;
    private String wenli;
    private String banxuexingzhi;
    private String sort;
    private String pici;

    @Override
    public String toString() {
        return "SearchForm{" +
                "stuScore=" + stuScore +
                ", bodongzhi=" + bodongzhi +
                ", shenfen='" + shenfen + '\'' +
                ", chengshi='" + chengshi + '\'' +
                ", wenli='" + wenli + '\'' +
                ", banxuexingzhi='" + banxuexingzhi + '\'' +
                ", sort='" + sort + '\'' +
                ", pici='" + pici + '\'' +
                '}';
    }

    public String getPici() {
        return pici;
    }

    public void setPici(String pici) {
        this.pici = pici;
    }

    public Double getStuScore() {
        return stuScore;
    }

    public void setStuScore(Double stuScore) {
        this.stuScore = stuScore;
    }

    public Double getBodongzhi() {
        return bodongzhi;
    }

    public void setBodongzhi(Double bodongzhi) {
        this.bodongzhi = bodongzhi;
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
}
