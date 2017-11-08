package win.ccav.model.save;

import javax.persistence.*;

/**
 * Created by paul on 2017/4/22.
 */
@Entity
@Table(name = "ziyuan_zhuanye", schema = "dbo", catalog = "zuihou")
public class ZiyuanZhuanyeEntity {
    private int id;
    private int zhuanyeid;
    private String zhuanyemingcheng;
    private Integer zyXuexiaoid;
    private Integer orderx;

    public ZiyuanZhuanyeEntity(int zhuanyeid, String zhuanyemingcheng,Integer orderx) {
        this.zhuanyeid = zhuanyeid;
        this.zhuanyemingcheng = zhuanyemingcheng;
        this.orderx=orderx;
    }

    public ZiyuanZhuanyeEntity() {
    }
    @Basic
    @Column(name = "orderx")
    public Integer getOrderx() {
        return orderx;
    }

    public void setOrderx(Integer orderx) {
        this.orderx = orderx;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "zhuanyeid")
    public int getZhuanyeid() {
        return zhuanyeid;
    }

    public void setZhuanyeid(int zhuanyeid) {
        this.zhuanyeid = zhuanyeid;
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
    @Column(name = "zy_xuexiaoid")
    public Integer getZyXuexiaoid() {
        return zyXuexiaoid;
    }

    public void setZyXuexiaoid(Integer zyXuexiaoid) {
        this.zyXuexiaoid = zyXuexiaoid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZiyuanZhuanyeEntity that = (ZiyuanZhuanyeEntity) o;

        if (id != that.id) return false;
        if (zhuanyeid != that.zhuanyeid) return false;
        if (zhuanyemingcheng != null ? !zhuanyemingcheng.equals(that.zhuanyemingcheng) : that.zhuanyemingcheng != null)
            return false;
        if (zyXuexiaoid != null ? !zyXuexiaoid.equals(that.zyXuexiaoid) : that.zyXuexiaoid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + zhuanyeid;
        result = 31 * result + (zhuanyemingcheng != null ? zhuanyemingcheng.hashCode() : 0);
        result = 31 * result + (zyXuexiaoid != null ? zyXuexiaoid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ZiyuanZhuanyeEntity{" +
                "id=" + id +
                ", zhuanyeid=" + zhuanyeid +
                ", zhuanyemingcheng='" + zhuanyemingcheng + '\'' +
                ", zyXuexiaoid=" + zyXuexiaoid +
                ", orderx=" + orderx +
                '}';
    }
}
