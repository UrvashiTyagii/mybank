package models;

import io.ebean.Finder;

import javax.persistence.*;
import java.util.Date;


@Entity(name = "password")
public class Password {

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User userId;

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "password")
    private String password;

    @Column(name = "confirmpassword")
    private String confirmpassword;

    @Column(name = "created_on")
    private Date createdOn;

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public static final Finder<Integer, Password> find = new Finder<>(Password.class);
}
