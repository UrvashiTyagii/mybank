package models;

import io.ebean.Finder;

import javax.persistence.*;
import java.util.Date;
//import java.util.Date;

@Entity
@Table(name ="user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "email")
    private String email;

    @Column(name = "adhar_no")
    private String adharNo;

    @ManyToOne
    @JoinColumn(name = "status_id",referencedColumnName = "id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private User roleType;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "phone_no")
    private String phoneNo;




    public enum userRoleEnum {
        ADMIN(1, "admin"), CUSTOMER(2, "customer");

        private int id;
        private String role;


        public int getId() {
            return id;
        }

        public String getRole() {
            return role;
        }

        userRoleEnum(int id, String role) {
            this.id=id;
            this.role=role;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdharNo() {
        return adharNo;
    }

    public void setAdharNo(String adharNo) {
        this.adharNo = adharNo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getRoleType() {
        return roleType;
    }

    public void setRoleType(User roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public static final Finder<Integer, User> find = new Finder<Integer, User>(User.class);
    public User(){

    }

    public User(int id, String fname, String lname, Date dob, String email, String adharNo, Status status, User roleType, Date createdOn, String phoneNo) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.email = email;
        this.adharNo = adharNo;
        this.status = status;
        this.roleType = roleType;
        this.createdOn = createdOn;
        this.phoneNo = phoneNo;
    }
}