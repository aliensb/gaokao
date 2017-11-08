package win.ccav.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by paul on 2017/4/17.
 */
@Entity
@Table(name = "users", schema = "dbo", catalog = "zuihou")
public class User {

    @Transient
    private String vilidate;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @Basic
    private String username;
    @Column
    @Basic
    private String password;
    @Column(name="registdate")
    @Basic
    private Date registDate;
    @Column
    @Basic
    private int valide;

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getValide() {
        return valide;
    }

    public void setValide(int valide) {
        this.valide = valide;
    }

    public String getVilidate() {
        return vilidate;
    }
    public void setVilidate(String vilidate) {
        this.vilidate = vilidate;
    }

}
