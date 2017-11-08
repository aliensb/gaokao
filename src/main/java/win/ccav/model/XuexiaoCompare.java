//package win.ccav.model;
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * Created by paul on 2017/4/22.
// */
//@Entity
//@Table(name = "xuexiao", schema = "dbo", catalog = "zuihou")
//public class XuexiaoCompare {
//    private int id;
//    private String xuexiaomingcheng;
//    private String diqu;
//    private String chengshi;
//    private String banxuexingzhi;
//    private String lisu;
//    private String jibie;
//    private String leixing;
//    private String paiming;
//    private String wenli;
//    private String pici;
//    private String zhaoshengdaima;
//    @Id
//    @Column(name = "zhaoshengdaima")
//    public String getZhaoshengdaima() {
//        return zhaoshengdaima;
//    }
//
//    public void setZhaoshengdaima(String zhaoshengdaima) {
//        this.zhaoshengdaima = zhaoshengdaima;
//    }
//

//
//    public XuexiaoCompare() {
//    }
//
//    public XuexiaoCompare(int id, String xuexiaomingcheng, String diqu, String chengshi, String banxuexingzhi, String lisu, String jibie, String leixing, String paiming, String wenli, String pici) {
//        this.id = id;
//        this.xuexiaomingcheng = xuexiaomingcheng;
//        this.diqu = diqu;
//        this.chengshi = chengshi;
//        this.banxuexingzhi = banxuexingzhi;
//        this.lisu = lisu;
//        this.jibie = jibie;
//        this.leixing = leixing;
//        this.paiming = paiming;
//        this.wenli = wenli;
//        this.pici = pici;
//    }
//
//    @Id
//    @Column(name = "id")
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @Basic
//    @Column(name = "xuexiaomingcheng")
//    public String getXuexiaomingcheng() {
//        return xuexiaomingcheng;
//    }
//
//    public void setXuexiaomingcheng(String xuexiaomingcheng) {
//        this.xuexiaomingcheng = xuexiaomingcheng;
//    }
//
//    @Basic
//    @Column(name = "diqu")
//    public String getDiqu() {
//        return diqu;
//    }
//
//    public void setDiqu(String diqu) {
//        this.diqu = diqu;
//    }
//
//    @Basic
//    @Column(name = "chengshi")
//    public String getChengshi() {
//        return chengshi;
//    }
//
//    public void setChengshi(String chengshi) {
//        this.chengshi = chengshi;
//    }
//
//    @Basic
//    @Column(name = "banxuexingzhi")
//    public String getBanxuexingzhi() {
//        return banxuexingzhi;
//    }
//
//    public void setBanxuexingzhi(String banxuexingzhi) {
//        this.banxuexingzhi = banxuexingzhi;
//    }
//
//    @Basic
//    @Column(name = "lisu")
//    public String getLisu() {
//        return lisu;
//    }
//
//    public void setLisu(String lisu) {
//        this.lisu = lisu;
//    }
//
//    @Basic
//    @Column(name = "jibie")
//    public String getJibie() {
//        return jibie;
//    }
//
//    public void setJibie(String jibie) {
//        this.jibie = jibie;
//    }
//
//    @Basic
//    @Column(name = "leixing")
//    public String getLeixing() {
//        return leixing;
//    }
//
//    public void setLeixing(String leixing) {
//        this.leixing = leixing;
//    }
//
//    @Basic
//    @Column(name = "paiming")
//    public String getPaiming() {
//        return paiming;
//    }
//
//    public void setPaiming(String paiming) {
//        this.paiming = paiming;
//    }
//
//    @Basic
//    @Column(name = "wenli")
//    public String getWenli() {
//        return wenli;
//    }
//
//    public void setWenli(String wenli) {
//        this.wenli = wenli;
//    }
//
//    @Basic
//    @Column(name = "pici")
//    public String getPici() {
//        return pici;
//    }
//
//    public void setPici(String pici) {
//        this.pici = pici;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        XuexiaoCompare that = (XuexiaoCompare) o;
//
//        if (id != that.id) return false;
//        if (xuexiaomingcheng != null ? !xuexiaomingcheng.equals(that.xuexiaomingcheng) : that.xuexiaomingcheng != null)
//            return false;
//        if (diqu != null ? !diqu.equals(that.diqu) : that.diqu != null) return false;
//        if (chengshi != null ? !chengshi.equals(that.chengshi) : that.chengshi != null) return false;
//        if (banxuexingzhi != null ? !banxuexingzhi.equals(that.banxuexingzhi) : that.banxuexingzhi != null)
//            return false;
//        if (lisu != null ? !lisu.equals(that.lisu) : that.lisu != null) return false;
//        if (jibie != null ? !jibie.equals(that.jibie) : that.jibie != null) return false;
//        if (leixing != null ? !leixing.equals(that.leixing) : that.leixing != null) return false;
//        if (paiming != null ? !paiming.equals(that.paiming) : that.paiming != null) return false;
//        if (wenli != null ? !wenli.equals(that.wenli) : that.wenli != null) return false;
//        if (pici != null ? !pici.equals(that.pici) : that.pici != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id;
//        result = 31 * result + (xuexiaomingcheng != null ? xuexiaomingcheng.hashCode() : 0);
//        result = 31 * result + (diqu != null ? diqu.hashCode() : 0);
//        result = 31 * result + (chengshi != null ? chengshi.hashCode() : 0);
//        result = 31 * result + (banxuexingzhi != null ? banxuexingzhi.hashCode() : 0);
//        result = 31 * result + (lisu != null ? lisu.hashCode() : 0);
//        result = 31 * result + (jibie != null ? jibie.hashCode() : 0);
//        result = 31 * result + (leixing != null ? leixing.hashCode() : 0);
//        result = 31 * result + (paiming != null ? paiming.hashCode() : 0);
//        result = 31 * result + (wenli != null ? wenli.hashCode() : 0);
//        result = 31 * result + (pici != null ? pici.hashCode() : 0);
//        return result;
//
//    }
//
//    @Override
//    public String toString() {
//        return "XuexiaoCompare{" +
//                "id=" + id +
//                ", xuexiaomingcheng='" + xuexiaomingcheng + '\'' +
//                ", diqu='" + diqu + '\'' +
//                ", chengshi='" + chengshi + '\'' +
//                ", banxuexingzhi='" + banxuexingzhi + '\'' +
//                ", lisu='" + lisu + '\'' +
//                ", jibie='" + jibie + '\'' +
//                ", leixing='" + leixing + '\'' +
//                ", paiming='" + paiming + '\'' +
//                ", wenli='" + wenli + '\'' +
//                ", pici='" + pici + '\'' +
//                ", zhuanyeCompares=" + zhuanyeCompares +
//                '}';
//    }
//}


package win.ccav.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by paul on 2017/4/22.
 */
@Entity
@Table(name = "xuexiao", schema = "dbo", catalog = "zuihou")
public class XuexiaoCompare implements Comparable{

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
    private Double yucefenshu2017;
//    public int compareTo(XuexiaoCompare arg0) {
//        return this.getYucefenshu2017().compareTo(arg0.getYuceweici2017());
//    }

    @Basic
    @Column(name = "yuceweici2017")
    public Double getYuceweici2017() {
        return yuceweici2017;
    }

    public void setYuceweici2017(Double yuceweici2017) {
        this.yuceweici2017 = yuceweici2017;
    }

    private Double yuceweici2017;
    @Basic
    @Column(name = "yucefenshu2017")
    public Double getYucefenshu2017() {
        return yucefenshu2017;
    }

    public void setYucefenshu2017(Double yucefenshu2017) {
        this.yucefenshu2017 = yucefenshu2017;
    }

    private Set<ZhuanyeCompare> zhuanyeCompares=new HashSet<ZhuanyeCompare>();
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="xuexiaoid")
    public Set<ZhuanyeCompare> getZhuanyeCompares() {
        return zhuanyeCompares;
    }

    public void setZhuanyeCompares(Set<ZhuanyeCompare> zhuanyeCompares) {
        this.zhuanyeCompares = zhuanyeCompares;
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
    @Column(name = "zhaoshengdaima")
    public String getZhaoshengdaima() {
        return zhaoshengdaima;
    }

    public void setZhaoshengdaima(String zhaoshengdaima) {
        this.zhaoshengdaima = zhaoshengdaima;
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
    @Column(name = "diqu")
    public String getDiqu() {
        return diqu;
    }

    public void setDiqu(String diqu) {
        this.diqu = diqu;
    }

    @Basic
    @Column(name = "chengshi")
    public String getChengshi() {
        return chengshi;
    }

    public void setChengshi(String chengshi) {
        this.chengshi = chengshi;
    }

    @Basic
    @Column(name = "banxuexingzhi")
    public String getBanxuexingzhi() {
        return banxuexingzhi;
    }

    public void setBanxuexingzhi(String banxuexingzhi) {
        this.banxuexingzhi = banxuexingzhi;
    }

    @Basic
    @Column(name = "lisu")
    public String getLisu() {
        return lisu;
    }

    public void setLisu(String lisu) {
        this.lisu = lisu;
    }

    @Basic
    @Column(name = "jibie")
    public String getJibie() {
        return jibie;
    }

    public void setJibie(String jibie) {
        this.jibie = jibie;
    }

    @Basic
    @Column(name = "leixing")
    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    @Basic
    @Column(name = "paiming")
    public String getPaiming() {
        return paiming;
    }

    public void setPaiming(String paiming) {
        this.paiming = paiming;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XuexiaoCompare that = (XuexiaoCompare) o;

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
        return result;
    }

    @Override
    public int compareTo(Object o) {
        o=(XuexiaoCompare)o;
        return this.getYucefenshu2017().compareTo(((XuexiaoCompare) o).getYucefenshu2017());
    }
}
