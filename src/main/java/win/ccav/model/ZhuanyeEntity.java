package win.ccav.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by paul on 2017/4/17.
 */
@Entity
@Table(name = "zhuanye", schema = "dbo", catalog = "zuihou")
public class ZhuanyeEntity {
    private int id;
    private String xuexiaomingcheng;
    private String zhuanyemingcheng;
    private String diqu;
    private String wenli;
    private String pici;
    private String shuobodian;
    private String xuekezhongdian;
    private BigDecimal fenshu2010;
    private BigDecimal fenshu2011;
    private BigDecimal fenshu2012;
    private BigDecimal fenshu2013;
    private BigDecimal fenshu2014;
    private BigDecimal fenshu2015;
    private BigDecimal fenshu2016;
    private BigDecimal yucefenshu2017;
    private Integer weici2011;
    private Integer weici2012;
    private Integer weici2013;
    private Integer weici2014;
    private Integer weici2015;
    private Integer weici2016;
    private Integer yuceweici2017;
    private Integer zhaoshenreshu2011;
    private Integer zhaoshenreshu2012;
    private Integer zhaoshenreshu2013;
    private Integer zhaoshenreshu2014;
    private Integer zhaoshenreshu2015;
    private Integer zhaoshenreshu2016;
    private Integer zhaoshenreshu2017;
    private Integer xuexiaoid;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "xuexiaomingcheng")
    public String getXuexiaomingcheng() {
        return xuexiaomingcheng;
    }

    public void setXuexiaomingcheng(String xuexiaomingcheng) {
        this.xuexiaomingcheng = xuexiaomingcheng;
    }

    @Basic
    @Column(name = "zhuanyemingcheng")
    public String getZhuanyemingcheng() {
        return zhuanyemingcheng;
    }

    public void setZhuanyemingcheng(String zhuanyemingcheng) {
        this.zhuanyemingcheng = zhuanyemingcheng;
    }

    @Basic
    @Column(name = "diqu")
    public String getDiqu() {
        return diqu;
    }

    public void setDiqu(String diqu) {
        this.diqu = diqu;
    }

    @Basic
    @Column(name = "wenli")
    public String getWenli() {
        return wenli;
    }

    public void setWenli(String wenli) {
        this.wenli = wenli;
    }

    @Basic
    @Column(name = "pici")
    public String getPici() {
        return pici;
    }

    public void setPici(String pici) {
        this.pici = pici;
    }

    @Basic
    @Column(name = "shuobodian")
    public String getShuobodian() {
        return shuobodian;
    }

    public void setShuobodian(String shuobodian) {
        this.shuobodian = shuobodian;
    }

    @Basic
    @Column(name = "xuekezhongdian")
    public String getXuekezhongdian() {
        return xuekezhongdian;
    }

    public void setXuekezhongdian(String xuekezhongdian) {
        this.xuekezhongdian = xuekezhongdian;
    }

    @Basic
    @Column(name = "fenshu2010")
    public BigDecimal getFenshu2010() {
        return fenshu2010;
    }

    public void setFenshu2010(BigDecimal fenshu2010) {
        this.fenshu2010 = fenshu2010;
    }

    @Basic
    @Column(name = "fenshu2011")
    public BigDecimal getFenshu2011() {
        return fenshu2011;
    }

    public void setFenshu2011(BigDecimal fenshu2011) {
        this.fenshu2011 = fenshu2011;
    }

    @Basic
    @Column(name = "fenshu2012")
    public BigDecimal getFenshu2012() {
        return fenshu2012;
    }

    public void setFenshu2012(BigDecimal fenshu2012) {
        this.fenshu2012 = fenshu2012;
    }

    @Basic
    @Column(name = "fenshu2013")
    public BigDecimal getFenshu2013() {
        return fenshu2013;
    }

    public void setFenshu2013(BigDecimal fenshu2013) {
        this.fenshu2013 = fenshu2013;
    }

    @Basic
    @Column(name = "fenshu2014")
    public BigDecimal getFenshu2014() {
        return fenshu2014;
    }

    public void setFenshu2014(BigDecimal fenshu2014) {
        this.fenshu2014 = fenshu2014;
    }

    @Basic
    @Column(name = "fenshu2015")
    public BigDecimal getFenshu2015() {
        return fenshu2015;
    }

    public void setFenshu2015(BigDecimal fenshu2015) {
        this.fenshu2015 = fenshu2015;
    }

    @Basic
    @Column(name = "fenshu2016")
    public BigDecimal getFenshu2016() {
        return fenshu2016;
    }

    public void setFenshu2016(BigDecimal fenshu2016) {
        this.fenshu2016 = fenshu2016;
    }

    @Basic
    @Column(name = "yucefenshu2017")
    public BigDecimal getYucefenshu2017() {
        return yucefenshu2017;
    }

    public void setYucefenshu2017(BigDecimal yucefenshu2017) {
        this.yucefenshu2017 = yucefenshu2017;
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
    @Column(name = "yuceweici2017")
    public Integer getYuceweici2017() {
        return yuceweici2017;
    }

    public void setYuceweici2017(Integer yuceweici2017) {
        this.yuceweici2017 = yuceweici2017;
    }

    @Basic
    @Column(name = "zhaoshenreshu2011")
    public Integer getZhaoshenreshu2011() {
        return zhaoshenreshu2011;
    }

    public void setZhaoshenreshu2011(Integer zhaoshenreshu2011) {
        this.zhaoshenreshu2011 = zhaoshenreshu2011;
    }

    @Basic
    @Column(name = "zhaoshenreshu2012")
    public Integer getZhaoshenreshu2012() {
        return zhaoshenreshu2012;
    }

    public void setZhaoshenreshu2012(Integer zhaoshenreshu2012) {
        this.zhaoshenreshu2012 = zhaoshenreshu2012;
    }

    @Basic
    @Column(name = "zhaoshenreshu2013")
    public Integer getZhaoshenreshu2013() {
        return zhaoshenreshu2013;
    }

    public void setZhaoshenreshu2013(Integer zhaoshenreshu2013) {
        this.zhaoshenreshu2013 = zhaoshenreshu2013;
    }

    @Basic
    @Column(name = "zhaoshenreshu2014")
    public Integer getZhaoshenreshu2014() {
        return zhaoshenreshu2014;
    }

    public void setZhaoshenreshu2014(Integer zhaoshenreshu2014) {
        this.zhaoshenreshu2014 = zhaoshenreshu2014;
    }

    @Basic
    @Column(name = "zhaoshenreshu2015")
    public Integer getZhaoshenreshu2015() {
        return zhaoshenreshu2015;
    }

    public void setZhaoshenreshu2015(Integer zhaoshenreshu2015) {
        this.zhaoshenreshu2015 = zhaoshenreshu2015;
    }

    @Basic
    @Column(name = "zhaoshenreshu2016")
    public Integer getZhaoshenreshu2016() {
        return zhaoshenreshu2016;
    }

    public void setZhaoshenreshu2016(Integer zhaoshenreshu2016) {
        this.zhaoshenreshu2016 = zhaoshenreshu2016;
    }

    @Basic
    @Column(name = "zhaoshenreshu2017")
    public Integer getZhaoshenreshu2017() {
        return zhaoshenreshu2017;
    }

    public void setZhaoshenreshu2017(Integer zhaoshenreshu2017) {
        this.zhaoshenreshu2017 = zhaoshenreshu2017;
    }

    @Basic
    @Column(name = "xuexiaoid")
    public Integer getXuexiaoid() {
        return xuexiaoid;
    }

    public void setXuexiaoid(Integer xuexiaoid) {
        this.xuexiaoid = xuexiaoid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZhuanyeEntity that = (ZhuanyeEntity) o;

        if (id != that.id) return false;
        if (xuexiaomingcheng != null ? !xuexiaomingcheng.equals(that.xuexiaomingcheng) : that.xuexiaomingcheng != null)
            return false;
        if (zhuanyemingcheng != null ? !zhuanyemingcheng.equals(that.zhuanyemingcheng) : that.zhuanyemingcheng != null)
            return false;
        if (diqu != null ? !diqu.equals(that.diqu) : that.diqu != null) return false;
        if (wenli != null ? !wenli.equals(that.wenli) : that.wenli != null) return false;
        if (pici != null ? !pici.equals(that.pici) : that.pici != null) return false;
        if (shuobodian != null ? !shuobodian.equals(that.shuobodian) : that.shuobodian != null) return false;
        if (xuekezhongdian != null ? !xuekezhongdian.equals(that.xuekezhongdian) : that.xuekezhongdian != null)
            return false;
        if (fenshu2010 != null ? !fenshu2010.equals(that.fenshu2010) : that.fenshu2010 != null) return false;
        if (fenshu2011 != null ? !fenshu2011.equals(that.fenshu2011) : that.fenshu2011 != null) return false;
        if (fenshu2012 != null ? !fenshu2012.equals(that.fenshu2012) : that.fenshu2012 != null) return false;
        if (fenshu2013 != null ? !fenshu2013.equals(that.fenshu2013) : that.fenshu2013 != null) return false;
        if (fenshu2014 != null ? !fenshu2014.equals(that.fenshu2014) : that.fenshu2014 != null) return false;
        if (fenshu2015 != null ? !fenshu2015.equals(that.fenshu2015) : that.fenshu2015 != null) return false;
        if (fenshu2016 != null ? !fenshu2016.equals(that.fenshu2016) : that.fenshu2016 != null) return false;
        if (yucefenshu2017 != null ? !yucefenshu2017.equals(that.yucefenshu2017) : that.yucefenshu2017 != null)
            return false;
        if (weici2011 != null ? !weici2011.equals(that.weici2011) : that.weici2011 != null) return false;
        if (weici2012 != null ? !weici2012.equals(that.weici2012) : that.weici2012 != null) return false;
        if (weici2013 != null ? !weici2013.equals(that.weici2013) : that.weici2013 != null) return false;
        if (weici2014 != null ? !weici2014.equals(that.weici2014) : that.weici2014 != null) return false;
        if (weici2015 != null ? !weici2015.equals(that.weici2015) : that.weici2015 != null) return false;
        if (weici2016 != null ? !weici2016.equals(that.weici2016) : that.weici2016 != null) return false;
        if (yuceweici2017 != null ? !yuceweici2017.equals(that.yuceweici2017) : that.yuceweici2017 != null)
            return false;
        if (zhaoshenreshu2011 != null ? !zhaoshenreshu2011.equals(that.zhaoshenreshu2011) : that.zhaoshenreshu2011 != null)
            return false;
        if (zhaoshenreshu2012 != null ? !zhaoshenreshu2012.equals(that.zhaoshenreshu2012) : that.zhaoshenreshu2012 != null)
            return false;
        if (zhaoshenreshu2013 != null ? !zhaoshenreshu2013.equals(that.zhaoshenreshu2013) : that.zhaoshenreshu2013 != null)
            return false;
        if (zhaoshenreshu2014 != null ? !zhaoshenreshu2014.equals(that.zhaoshenreshu2014) : that.zhaoshenreshu2014 != null)
            return false;
        if (zhaoshenreshu2015 != null ? !zhaoshenreshu2015.equals(that.zhaoshenreshu2015) : that.zhaoshenreshu2015 != null)
            return false;
        if (zhaoshenreshu2016 != null ? !zhaoshenreshu2016.equals(that.zhaoshenreshu2016) : that.zhaoshenreshu2016 != null)
            return false;
        if (zhaoshenreshu2017 != null ? !zhaoshenreshu2017.equals(that.zhaoshenreshu2017) : that.zhaoshenreshu2017 != null)
            return false;
        if (xuexiaoid != null ? !xuexiaoid.equals(that.xuexiaoid) : that.xuexiaoid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (xuexiaomingcheng != null ? xuexiaomingcheng.hashCode() : 0);
        result = 31 * result + (zhuanyemingcheng != null ? zhuanyemingcheng.hashCode() : 0);
        result = 31 * result + (diqu != null ? diqu.hashCode() : 0);
        result = 31 * result + (wenli != null ? wenli.hashCode() : 0);
        result = 31 * result + (pici != null ? pici.hashCode() : 0);
        result = 31 * result + (shuobodian != null ? shuobodian.hashCode() : 0);
        result = 31 * result + (xuekezhongdian != null ? xuekezhongdian.hashCode() : 0);
        result = 31 * result + (fenshu2010 != null ? fenshu2010.hashCode() : 0);
        result = 31 * result + (fenshu2011 != null ? fenshu2011.hashCode() : 0);
        result = 31 * result + (fenshu2012 != null ? fenshu2012.hashCode() : 0);
        result = 31 * result + (fenshu2013 != null ? fenshu2013.hashCode() : 0);
        result = 31 * result + (fenshu2014 != null ? fenshu2014.hashCode() : 0);
        result = 31 * result + (fenshu2015 != null ? fenshu2015.hashCode() : 0);
        result = 31 * result + (fenshu2016 != null ? fenshu2016.hashCode() : 0);
        result = 31 * result + (yucefenshu2017 != null ? yucefenshu2017.hashCode() : 0);
        result = 31 * result + (weici2011 != null ? weici2011.hashCode() : 0);
        result = 31 * result + (weici2012 != null ? weici2012.hashCode() : 0);
        result = 31 * result + (weici2013 != null ? weici2013.hashCode() : 0);
        result = 31 * result + (weici2014 != null ? weici2014.hashCode() : 0);
        result = 31 * result + (weici2015 != null ? weici2015.hashCode() : 0);
        result = 31 * result + (weici2016 != null ? weici2016.hashCode() : 0);
        result = 31 * result + (yuceweici2017 != null ? yuceweici2017.hashCode() : 0);
        result = 31 * result + (zhaoshenreshu2011 != null ? zhaoshenreshu2011.hashCode() : 0);
        result = 31 * result + (zhaoshenreshu2012 != null ? zhaoshenreshu2012.hashCode() : 0);
        result = 31 * result + (zhaoshenreshu2013 != null ? zhaoshenreshu2013.hashCode() : 0);
        result = 31 * result + (zhaoshenreshu2014 != null ? zhaoshenreshu2014.hashCode() : 0);
        result = 31 * result + (zhaoshenreshu2015 != null ? zhaoshenreshu2015.hashCode() : 0);
        result = 31 * result + (zhaoshenreshu2016 != null ? zhaoshenreshu2016.hashCode() : 0);
        result = 31 * result + (zhaoshenreshu2017 != null ? zhaoshenreshu2017.hashCode() : 0);
        result = 31 * result + (xuexiaoid != null ? xuexiaoid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ZhuanyeEntity{" +
                "id=" + id +
                ", xuexiaomingcheng='" + xuexiaomingcheng + '\'' +
                ", zhuanyemingcheng='" + zhuanyemingcheng + '\'' +
                ", diqu='" + diqu + '\'' +
                ", wenli='" + wenli + '\'' +
                ", pici='" + pici + '\'' +
                ", shuobodian='" + shuobodian + '\'' +
                ", xuekezhongdian='" + xuekezhongdian + '\'' +
                ", fenshu2010=" + fenshu2010 +
                ", fenshu2011=" + fenshu2011 +
                ", fenshu2012=" + fenshu2012 +
                ", fenshu2013=" + fenshu2013 +
                ", fenshu2014=" + fenshu2014 +
                ", fenshu2015=" + fenshu2015 +
                ", fenshu2016=" + fenshu2016 +
                ", yucefenshu2017=" + yucefenshu2017 +
                ", weici2011=" + weici2011 +
                ", weici2012=" + weici2012 +
                ", weici2013=" + weici2013 +
                ", weici2014=" + weici2014 +
                ", weici2015=" + weici2015 +
                ", weici2016=" + weici2016 +
                ", yuceweici2017=" + yuceweici2017 +
                ", zhaoshenreshu2011=" + zhaoshenreshu2011 +
                ", zhaoshenreshu2012=" + zhaoshenreshu2012 +
                ", zhaoshenreshu2013=" + zhaoshenreshu2013 +
                ", zhaoshenreshu2014=" + zhaoshenreshu2014 +
                ", zhaoshenreshu2015=" + zhaoshenreshu2015 +
                ", zhaoshenreshu2016=" + zhaoshenreshu2016 +
                ", zhaoshenreshu2017=" + zhaoshenreshu2017 +
                ", xuexiaoid=" + xuexiaoid +
                '}';
    }
}
