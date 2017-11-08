package win.ccav.model.save;

import win.ccav.model.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by paul on 2017/4/22.
 */
@Entity
@Table(name = "student", schema = "dbo", catalog = "zuihou")
public class StudentEntity {
    private int id;
    private String sname;
    private double score;
    private String wenli;
//    private User user;
//    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinColumn(name = "id")
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
    private String userName;
    @Basic
    @Column(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWenli() {
        return wenli;
    }

    public void setWenli(String wenli) {
        this.wenli = wenli;
    }

    private Set<ZiyuanXuexiaoEntity> ziyuanXuexiaoEntities=new HashSet<ZiyuanXuexiaoEntity>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="stuid")
    @OrderBy("orderx ASC")
    public Set<ZiyuanXuexiaoEntity> getZiyuanXuexiaoEntities() {
        return ziyuanXuexiaoEntities;
    }

    public void setZiyuanXuexiaoEntities(Set<ZiyuanXuexiaoEntity> ziyuanXuexiaoEntities) {
        this.ziyuanXuexiaoEntities = ziyuanXuexiaoEntities;
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
    @Column(name = "sname")
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Basic
    @Column(name = "score")
    public double getScore() {
        return (double)Math.round(score*10)/10;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity that = (StudentEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.score, score) != 0) return false;
        if (sname != null ? !sname.equals(that.sname) : that.sname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (sname != null ? sname.hashCode() : 0);
        temp = Double.doubleToLongBits(score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id=" + id +
                ", sname='" + sname + '\'' +
                ", score=" + score +
                ", ziyuanXuexiaoEntities=" + ziyuanXuexiaoEntities +
                '}';
    }
}
