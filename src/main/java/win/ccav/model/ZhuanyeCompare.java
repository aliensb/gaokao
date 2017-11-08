package win.ccav.model;

import javax.persistence.*;

/**
 * Created by paul on 2017/4/22.
 */
@Entity
@Table(name = "zhuanye", schema = "dbo", catalog = "zuihou")
public class ZhuanyeCompare {
    private int id;
    private String zhuanyemingcheng;
    private Integer xuexiaoid;
    private Double yucefenshu2017;
    @Basic
    @Column(name = "yucefenshu2017")
    public Double getYucefenshu2017() {
        return yucefenshu2017;
    }

    public void setYucefenshu2017(Double yucefenshu2017) {
        this.yucefenshu2017 = yucefenshu2017;
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
    @Column(name = "zhuanyemingcheng")
    public String getZhuanyemingcheng() {
        return zhuanyemingcheng;
    }

    public void setZhuanyemingcheng(String zhuanyemingcheng) {
        this.zhuanyemingcheng = zhuanyemingcheng;
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

        ZhuanyeCompare that = (ZhuanyeCompare) o;

        if (id != that.id) return false;
        if (zhuanyemingcheng != null ? !zhuanyemingcheng.equals(that.zhuanyemingcheng) : that.zhuanyemingcheng != null)
            return false;
        if (xuexiaoid != null ? !xuexiaoid.equals(that.xuexiaoid) : that.xuexiaoid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (zhuanyemingcheng != null ? zhuanyemingcheng.hashCode() : 0);
        result = 31 * result + (xuexiaoid != null ? xuexiaoid.hashCode() : 0);
        return result;
    }
}
