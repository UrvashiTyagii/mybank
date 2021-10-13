package models;


import io.ebean.Finder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "status")
public class Status {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public static Status getInstanceOf(int id) {
        Status status=new Status();
        status.setId(id);
        return status;
    }

    public enum statusEnum{
        ACTIVE(1,"active"), INACTIVE(2,"inactive");

        private int id;
        private String statusName;

        public int getId() {
            return id;
        }

        public String getStatusName() {
            return statusName;
        }

        statusEnum(Integer id, String statusName){
            this.id=id;
            this.statusName= statusName;
        }

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getName(Status status) {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Finder<Integer, Status> find = new Finder<>(Status.class);



}
