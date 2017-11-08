package win.ccav.model;

import javax.persistence.*;

/**
 * Created by john on 2017/10/30.
 */
@Entity
@Table(name = "user_choose_details", schema = "dbo", catalog = "zuihou")
public class UserChooseDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @Basic
    private String xuexiaomingcheng;
    @Column
    @Basic
    private Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getXuexiaomingcheng() {
        return xuexiaomingcheng;
    }

    public void setXuexiaomingcheng(String xuexiaomingcheng) {
        this.xuexiaomingcheng = xuexiaomingcheng;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public UserChooseDetails() {
    }

    public UserChooseDetails(String xuexiaomingcheng, Integer userid) {
        this.xuexiaomingcheng = xuexiaomingcheng;
        this.userid = userid;
    }
}
