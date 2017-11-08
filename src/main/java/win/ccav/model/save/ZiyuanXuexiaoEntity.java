package win.ccav.model.save;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by paul on 2017/4/22.
 */
@Entity
@Table(name = "ziyuan_xuexiao", schema = "dbo", catalog = "zuihou")
public class ZiyuanXuexiaoEntity {
    private int id;
    private int xuexiaoid;
    private String xuexiaomingcheng;
    private Integer orderx;
    private Integer stuid;
    private Set<ZiyuanZhuanyeEntity> ziyuanZhuanyeEntities;

    public ZiyuanXuexiaoEntity(int xuexiaoid, String xuexiaomingcheng, Integer orderx, Set<ZiyuanZhuanyeEntity> ziyuanZhuanyeEntities) {
        this.xuexiaoid = xuexiaoid;
        this.xuexiaomingcheng = xuexiaomingcheng;
        this.orderx = orderx;
        this.ziyuanZhuanyeEntities = ziyuanZhuanyeEntities;
    }

    public ZiyuanXuexiaoEntity() {

    }
    @Basic
    @Column(name = "orderx")
    public Integer getOrderx() {
        return orderx;
    }

    public void setOrderx(Integer orderx) {
        this.orderx = orderx;
    }


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="zy_xuexiaoid")
    @OrderBy("orderx ASC")
    public Set<ZiyuanZhuanyeEntity> getZiyuanZhuanyeEntities() {
        return ziyuanZhuanyeEntities;
    }

    public void setZiyuanZhuanyeEntities(Set<ZiyuanZhuanyeEntity> ziyuanZhuanyeEntities) {
        this.ziyuanZhuanyeEntities = ziyuanZhuanyeEntities;
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
    @Column(name = "xuexiaoid")

    public int getXuexiaoid() {
        return xuexiaoid;
    }

    public void setXuexiaoid(int xuexiaoid) {
        this.xuexiaoid = xuexiaoid;
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
    @Column(name = "stuid")
    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZiyuanXuexiaoEntity that = (ZiyuanXuexiaoEntity) o;

        if (id != that.id) return false;
        if (xuexiaoid != that.xuexiaoid) return false;
        if (xuexiaomingcheng != null ? !xuexiaomingcheng.equals(that.xuexiaomingcheng) : that.xuexiaomingcheng != null)
            return false;
        if (stuid != null ? !stuid.equals(that.stuid) : that.stuid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + xuexiaoid;
        result = 31 * result + (xuexiaomingcheng != null ? xuexiaomingcheng.hashCode() : 0);
        result = 31 * result + (stuid != null ? stuid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ZiyuanXuexiaoEntity{" +
                "id=" + id +
                ", xuexiaoid=" + xuexiaoid +
                ", xuexiaomingcheng='" + xuexiaomingcheng + '\'' +
                ", orderx=" + orderx +
                ", stuid=" + stuid +
                ", ziyuanZhuanyeEntities=" + ziyuanZhuanyeEntities +
                '}';
    }
}
