package win.ccav.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by paul on 2017/4/17.


 这个类与XuexiaoEntity这个类的区别就是在与对专业面的加载上，这是在查询这个类的时候，就会将这个类对应的专业的set集合查询出来属于非懒加载
 @@author paul
 @@see 只能用于针对一个id来查询一个学校的具体信息！！
 */
@Entity
@Table(name = "xuexiao", schema = "dbo", catalog = "zuihou")
public class XuexiaoEntity {
    private int id;
    private String zhaoshengdaima;
    private String xuexiaomingcheng;
    private String diqu;
    private String chengshi;
    private String banxuexingzhi;
    private String lisu;
    private String jibie;
    private String leixing;
    private String paiming;
    private String wenli;
    private String pici;
    private Integer zhaoshenrenshu2016;
    private Integer zhaoshenrenshu2015;
    private Integer zhaoshenrenshu2014;
    private Integer zhaoshenrenshu2013;
    private Integer zhaoshenrenshu2012;
    private Integer zhaoshenrenshu2011;
    private Long fenshu2010;
    private Long fenshu2011;
    private Long fenshu2012;
    private Long fenshu2013;
    private Long fenshu2014;
    private Long fenshu2015;
    private Long fenshu2016;
    private Integer weici2011;
    private Integer weici2012;
    private Integer weici2013;
    private Integer weici2014;
    private Integer weici2015;
    private Integer weici2016;
    private Integer zhaoshengrenshu2017;

    private Integer yuceweici2017;
    private Long yucefenshu2017;
    private String zhaoshenguize;
    @Basic
    @Column(name = "zhaoshenguize")
    public String getZhaoshenguize() {
        return zhaoshenguize;
    }

    public void setZhaoshenguize(String zhaoshenguize) {
        this.zhaoshenguize = zhaoshenguize;
    }

    private Set<ZhuanyeEntity> zhuanyeEntities=new HashSet<ZhuanyeEntity>();
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="xuexiaoid")
    public Set<ZhuanyeEntity> getZhuanyeEntities() {
        return zhuanyeEntities;
    }

    public void setZhuanyeEntities(Set<ZhuanyeEntity> zhuanyeEntities) {
        this.zhuanyeEntities = zhuanyeEntities;
    }
    //    private Set<ZhuanyeEntity> zhuanyeEntitySet=new HashSet<ZhuanyeEntity>();
//
//    public Set<ZhuanyeEntity> getZhuanyeEntitySet() {
//        return zhuanyeEntitySet;
//    }
//    @OneToMany
//    @JoinColumn(name="xuexiaoid")
//    public void setZhuanyeEntitySet(Set<ZhuanyeEntity> zhuanyeEntitySet) {
//        this.zhuanyeEntitySet = zhuanyeEntitySet;
//    }

    public XuexiaoEntity(String diqu) {
        this.diqu = diqu;
    }
    public XuexiaoEntity(int id, String chengshi){
        this.chengshi=chengshi;
    }
    public XuexiaoEntity() {
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "zhaoshengdaima", columnDefinition="nvarchar(255)")
    public String getZhaoshengdaima() {
        return zhaoshengdaima;
    }

    public void setZhaoshengdaima(String zhaoshengdaima) {
        this.zhaoshengdaima = zhaoshengdaima;
    }

    @Basic
    @Column(name = "xuexiaomingcheng", columnDefinition="nvarchar(255)")
    public String getXuexiaomingcheng() {
        return xuexiaomingcheng;
    }

    public void setXuexiaomingcheng(String xuexiaomingcheng) {
        this.xuexiaomingcheng = xuexiaomingcheng;
    }

    @Basic
    @Column(name = "diqu", columnDefinition="nvarchar(255)")
    public String getDiqu() {
        return diqu;
    }

    public void setDiqu(String diqu) {
        this.diqu = diqu;
    }

    @Basic
    @Column(name = "chengshi", columnDefinition="nvarchar(255)")
    public String getChengshi() {
        return chengshi;
    }

    public void setChengshi(String chengshi) {
        this.chengshi = chengshi;
    }

    @Basic
    @Column(name = "banxuexingzhi", columnDefinition="nvarchar(255)")
    public String getBanxuexingzhi() {
        return banxuexingzhi;
    }

    public void setBanxuexingzhi(String banxuexingzhi) {
        this.banxuexingzhi = banxuexingzhi;
    }

    @Basic
    @Column(name = "lisu", columnDefinition="nvarchar(255)")
    public String getLisu() {
        return lisu;
    }

    public void setLisu(String lisu) {
        this.lisu = lisu;
    }

    @Basic
    @Column(name = "jibie", columnDefinition="nvarchar(255)")
    public String getJibie() {
        return jibie;
    }

    public void setJibie(String jibie) {
        this.jibie = jibie;
    }

    @Basic
    @Column(name = "leixing", columnDefinition="nvarchar(255)")
    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    @Basic
    @Column(name = "paiming", columnDefinition="nvarchar(255)")
    public String getPaiming() {
        return paiming;
    }

    public void setPaiming(String paiming) {
        this.paiming = paiming;
    }

    @Basic
    @Column(name = "wenli", columnDefinition="nvarchar(255)")
    public String getWenli() {
        return wenli;
    }

    public void setWenli(String wenli) {
        this.wenli = wenli;
    }

    @Basic
    @Column(name = "pici", columnDefinition="nvarchar(255)")
    public String getPici() {
        return pici;
    }

    public void setPici(String pici) {
        this.pici = pici;
    }

    @Basic
    @Column(name = "zhaoshenrenshu2016")
    public Integer getZhaoshenrenshu2016() {
        return zhaoshenrenshu2016;
    }

    public void setZhaoshenrenshu2016(Integer zhaoshenrenshu2016) {
        this.zhaoshenrenshu2016 = zhaoshenrenshu2016;
    }

    @Basic
    @Column(name = "zhaoshenrenshu2015")
    public Integer getZhaoshenrenshu2015() {
        return zhaoshenrenshu2015;
    }

    public void setZhaoshenrenshu2015(Integer zhaoshenrenshu2015) {
        this.zhaoshenrenshu2015 = zhaoshenrenshu2015;
    }

    @Basic
    @Column(name = "zhaoshenrenshu2014")
    public Integer getZhaoshenrenshu2014() {
        return zhaoshenrenshu2014;
    }

    public void setZhaoshenrenshu2014(Integer zhaoshenrenshu2014) {
        this.zhaoshenrenshu2014 = zhaoshenrenshu2014;
    }

    @Basic
    @Column(name = "zhaoshenrenshu2013")
    public Integer getZhaoshenrenshu2013() {
        return zhaoshenrenshu2013;
    }

    public void setZhaoshenrenshu2013(Integer zhaoshenrenshu2013) {
        this.zhaoshenrenshu2013 = zhaoshenrenshu2013;
    }

    @Basic
    @Column(name = "zhaoshenrenshu2012")
    public Integer getZhaoshenrenshu2012() {
        return zhaoshenrenshu2012;
    }

    public void setZhaoshenrenshu2012(Integer zhaoshenrenshu2012) {
        this.zhaoshenrenshu2012 = zhaoshenrenshu2012;
    }

    @Basic
    @Column(name = "zhaoshenrenshu2011")
    public Integer getZhaoshenrenshu2011() {
        return zhaoshenrenshu2011;
    }

    public void setZhaoshenrenshu2011(Integer zhaoshenrenshu2011) {
        this.zhaoshenrenshu2011 = zhaoshenrenshu2011;
    }

    @Basic
    @Column(name = "fenshu2010")
    public Long getFenshu2010() {
        return fenshu2010;
    }

    public void setFenshu2010(Long fenshu2010) {
        this.fenshu2010 = fenshu2010;
    }

    @Basic
    @Column(name = "fenshu2011")
    public Long getFenshu2011() {
        return fenshu2011;
    }

    public void setFenshu2011(Long fenshu2011) {
        this.fenshu2011 = fenshu2011;
    }

    @Basic
    @Column(name = "fenshu2012")
    public Long getFenshu2012() {
        return fenshu2012;
    }

    public void setFenshu2012(Long fenshu2012) {
        this.fenshu2012 = fenshu2012;
    }

    @Basic
    @Column(name = "fenshu2013")
    public Long getFenshu2013() {
        return fenshu2013;
    }

    public void setFenshu2013(Long fenshu2013) {
        this.fenshu2013 = fenshu2013;
    }

    @Basic
    @Column(name = "fenshu2014")
    public Long getFenshu2014() {
        return fenshu2014;
    }

    public void setFenshu2014(Long fenshu2014) {
        this.fenshu2014 = fenshu2014;
    }

    @Basic
    @Column(name = "fenshu2015")
    public Long getFenshu2015() {
        return fenshu2015;
    }

    public void setFenshu2015(Long fenshu2015) {
        this.fenshu2015 = fenshu2015;
    }

    @Basic
    @Column(name = "fenshu2016")
    public Long getFenshu2016() {
        return fenshu2016;
    }

    public void setFenshu2016(Long fenshu2016) {
        this.fenshu2016 = fenshu2016;
    }

    @Basic
    @Column(name = "weici2011")
    public Integer getWeici2011() {
        return weici2011;
    }

    public void setWeici2011(Integer weici2011) {
        this.weici2011 = weici2011;
    }

    @Basic
    @Column(name = "weici2012")
    public Integer getWeici2012() {
        return weici2012;
    }

    public void setWeici2012(Integer weici2012) {
        this.weici2012 = weici2012;
    }

    @Basic
    @Column(name = "weici2013")
    public Integer getWeici2013() {
        return weici2013;
    }

    public void setWeici2013(Integer weici2013) {
        this.weici2013 = weici2013;
    }

    @Basic
    @Column(name = "weici2014")
    public Integer getWeici2014() {
        return weici2014;
    }

    public void setWeici2014(Integer weici2014) {
        this.weici2014 = weici2014;
    }

    @Basic
    @Column(name = "weici2015")
    public Integer getWeici2015() {
        return weici2015;
    }

    public void setWeici2015(Integer weici2015) {
        this.weici2015 = weici2015;
    }

    @Basic
    @Column(name = "weici2016")
    public Integer getWeici2016() {
        return weici2016;
    }

    public void setWeici2016(Integer weici2016) {
        this.weici2016 = weici2016;
    }

    @Basic
    @Column(name = "zhaoshengrenshu2017")
    public Integer getZhaoshengrenshu2017() {
        return zhaoshengrenshu2017;
    }

    public void setZhaoshengrenshu2017(Integer zhaoshengrenshu2017) {
        this.zhaoshengrenshu2017 = zhaoshengrenshu2017;
    }

    @Basic
    @Column(name = "yuceweici2017")
    public Integer getYuceweici2017() {
        return yuceweici2017;
    }

    public void setYuceweici2017(Integer yuceweici2017) {
        this.yuceweici2017 = yuceweici2017;
    }

    @Basic
    @Column(name = "yucefenshu2017")
    public Long getYucefenshu2017() {
        return yucefenshu2017;
    }

    public void setYucefenshu2017(Long yucefenshu2017) {
        this.yucefenshu2017 = yucefenshu2017;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XuexiaoEntity that = (XuexiaoEntity) o;

        if (id != that.id) return false;
        if (zhaoshengdaima != null ? !zhaoshengdaima.equals(that.zhaoshengdaima) : that.zhaoshengdaima != null)
            return false;
        if (xuexiaomingcheng != null ? !xuexiaomingcheng.equals(that.xuexiaomingcheng) : that.xuexiaomingcheng != null)
            return false;
        if (diqu != null ? !diqu.equals(that.diqu) : that.diqu != null) return false;
        if (chengshi != null ? !chengshi.equals(that.chengshi) : that.chengshi != null) return false;
        if (banxuexingzhi != null ? !banxuexingzhi.equals(that.banxuexingzhi) : that.banxuexingzhi != null)
            return false;
        if (lisu != null ? !lisu.equals(that.lisu) : that.lisu != null) return false;
        if (jibie != null ? !jibie.equals(that.jibie) : that.jibie != null) return false;
        if (leixing != null ? !leixing.equals(that.leixing) : that.leixing != null) return false;
        if (paiming != null ? !paiming.equals(that.paiming) : that.paiming != null) return false;
        if (wenli != null ? !wenli.equals(that.wenli) : that.wenli != null) return false;
        if (pici != null ? !pici.equals(that.pici) : that.pici != null) return false;
        if (zhaoshenrenshu2016 != null ? !zhaoshenrenshu2016.equals(that.zhaoshenrenshu2016) : that.zhaoshenrenshu2016 != null)
            return false;
        if (zhaoshenrenshu2015 != null ? !zhaoshenrenshu2015.equals(that.zhaoshenrenshu2015) : that.zhaoshenrenshu2015 != null)
            return false;
        if (zhaoshenrenshu2014 != null ? !zhaoshenrenshu2014.equals(that.zhaoshenrenshu2014) : that.zhaoshenrenshu2014 != null)
            return false;
        if (zhaoshenrenshu2013 != null ? !zhaoshenrenshu2013.equals(that.zhaoshenrenshu2013) : that.zhaoshenrenshu2013 != null)
            return false;
        if (zhaoshenrenshu2012 != null ? !zhaoshenrenshu2012.equals(that.zhaoshenrenshu2012) : that.zhaoshenrenshu2012 != null)
            return false;
        if (zhaoshenrenshu2011 != null ? !zhaoshenrenshu2011.equals(that.zhaoshenrenshu2011) : that.zhaoshenrenshu2011 != null)
            return false;
        if (fenshu2010 != null ? !fenshu2010.equals(that.fenshu2010) : that.fenshu2010 != null) return false;
        if (fenshu2011 != null ? !fenshu2011.equals(that.fenshu2011) : that.fenshu2011 != null) return false;
        if (fenshu2012 != null ? !fenshu2012.equals(that.fenshu2012) : that.fenshu2012 != null) return false;
        if (fenshu2013 != null ? !fenshu2013.equals(that.fenshu2013) : that.fenshu2013 != null) return false;
        if (fenshu2014 != null ? !fenshu2014.equals(that.fenshu2014) : that.fenshu2014 != null) return false;
        if (fenshu2015 != null ? !fenshu2015.equals(that.fenshu2015) : that.fenshu2015 != null) return false;
        if (fenshu2016 != null ? !fenshu2016.equals(that.fenshu2016) : that.fenshu2016 != null) return false;
        if (weici2011 != null ? !weici2011.equals(that.weici2011) : that.weici2011 != null) return false;
        if (weici2012 != null ? !weici2012.equals(that.weici2012) : that.weici2012 != null) return false;
        if (weici2013 != null ? !weici2013.equals(that.weici2013) : that.weici2013 != null) return false;
        if (weici2014 != null ? !weici2014.equals(that.weici2014) : that.weici2014 != null) return false;
        if (weici2015 != null ? !weici2015.equals(that.weici2015) : that.weici2015 != null) return false;
        if (weici2016 != null ? !weici2016.equals(that.weici2016) : that.weici2016 != null) return false;
        if (zhaoshengrenshu2017 != null ? !zhaoshengrenshu2017.equals(that.zhaoshengrenshu2017) : that.zhaoshengrenshu2017 != null)
            return false;
        if (yuceweici2017 != null ? !yuceweici2017.equals(that.yuceweici2017) : that.yuceweici2017 != null)
            return false;
        if (yucefenshu2017 != null ? !yucefenshu2017.equals(that.yucefenshu2017) : that.yucefenshu2017 != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (zhaoshengdaima != null ? zhaoshengdaima.hashCode() : 0);
        result = 31 * result + (xuexiaomingcheng != null ? xuexiaomingcheng.hashCode() : 0);
        result = 31 * result + (diqu != null ? diqu.hashCode() : 0);
        result = 31 * result + (chengshi != null ? chengshi.hashCode() : 0);
        result = 31 * result + (banxuexingzhi != null ? banxuexingzhi.hashCode() : 0);
        result = 31 * result + (lisu != null ? lisu.hashCode() : 0);
        result = 31 * result + (jibie != null ? jibie.hashCode() : 0);
        result = 31 * result + (leixing != null ? leixing.hashCode() : 0);
        result = 31 * result + (paiming != null ? paiming.hashCode() : 0);
        result = 31 * result + (wenli != null ? wenli.hashCode() : 0);
        result = 31 * result + (pici != null ? pici.hashCode() : 0);
        result = 31 * result + (zhaoshenrenshu2016 != null ? zhaoshenrenshu2016.hashCode() : 0);
        result = 31 * result + (zhaoshenrenshu2015 != null ? zhaoshenrenshu2015.hashCode() : 0);
        result = 31 * result + (zhaoshenrenshu2014 != null ? zhaoshenrenshu2014.hashCode() : 0);
        result = 31 * result + (zhaoshenrenshu2013 != null ? zhaoshenrenshu2013.hashCode() : 0);
        result = 31 * result + (zhaoshenrenshu2012 != null ? zhaoshenrenshu2012.hashCode() : 0);
        result = 31 * result + (zhaoshenrenshu2011 != null ? zhaoshenrenshu2011.hashCode() : 0);
        result = 31 * result + (fenshu2010 != null ? fenshu2010.hashCode() : 0);
        result = 31 * result + (fenshu2011 != null ? fenshu2011.hashCode() : 0);
        result = 31 * result + (fenshu2012 != null ? fenshu2012.hashCode() : 0);
        result = 31 * result + (fenshu2013 != null ? fenshu2013.hashCode() : 0);
        result = 31 * result + (fenshu2014 != null ? fenshu2014.hashCode() : 0);
        result = 31 * result + (fenshu2015 != null ? fenshu2015.hashCode() : 0);
        result = 31 * result + (fenshu2016 != null ? fenshu2016.hashCode() : 0);
        result = 31 * result + (weici2011 != null ? weici2011.hashCode() : 0);
        result = 31 * result + (weici2012 != null ? weici2012.hashCode() : 0);
        result = 31 * result + (weici2013 != null ? weici2013.hashCode() : 0);
        result = 31 * result + (weici2014 != null ? weici2014.hashCode() : 0);
        result = 31 * result + (weici2015 != null ? weici2015.hashCode() : 0);
        result = 31 * result + (weici2016 != null ? weici2016.hashCode() : 0);
        result = 31 * result + (zhaoshengrenshu2017 != null ? zhaoshengrenshu2017.hashCode() : 0);
        result = 31 * result + (yuceweici2017 != null ? yuceweici2017.hashCode() : 0);
        result = 31 * result + (yucefenshu2017 != null ? yucefenshu2017.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "XuexiaoEntity{" +
                "id=" + id +
                ", zhaoshengdaima='" + zhaoshengdaima + '\'' +
                ", xuexiaomingcheng='" + xuexiaomingcheng + '\'' +
                ", diqu='" + diqu + '\'' +
                ", chengshi='" + chengshi + '\'' +
                ", banxuexingzhi='" + banxuexingzhi + '\'' +
                ", lisu='" + lisu + '\'' +
                ", jibie='" + jibie + '\'' +
                ", leixing='" + leixing + '\'' +
                ", paiming='" + paiming + '\'' +
                ", wenli='" + wenli + '\'' +
                ", pici='" + pici + '\'' +
                ", zhaoshenrenshu2016=" + zhaoshenrenshu2016 +
                ", zhaoshenrenshu2015=" + zhaoshenrenshu2015 +
                ", zhaoshenrenshu2014=" + zhaoshenrenshu2014 +
                ", zhaoshenrenshu2013=" + zhaoshenrenshu2013 +
                ", zhaoshenrenshu2012=" + zhaoshenrenshu2012 +
                ", zhaoshenrenshu2011=" + zhaoshenrenshu2011 +
                ", fenshu2010=" + fenshu2010 +
                ", fenshu2011=" + fenshu2011 +
                ", fenshu2012=" + fenshu2012 +
                ", fenshu2013=" + fenshu2013 +
                ", fenshu2014=" + fenshu2014 +
                ", fenshu2015=" + fenshu2015 +
                ", fenshu2016=" + fenshu2016 +
                ", weici2011=" + weici2011 +
                ", weici2012=" + weici2012 +
                ", weici2013=" + weici2013 +
                ", weici2014=" + weici2014 +
                ", weici2015=" + weici2015 +
                ", weici2016=" + weici2016 +
                ", zhaoshengrenshu2017=" + zhaoshengrenshu2017 +
                ", yuceweici2017=" + yuceweici2017 +
                ", yucefenshu2017=" + yucefenshu2017 +
                '}';
    }
}
