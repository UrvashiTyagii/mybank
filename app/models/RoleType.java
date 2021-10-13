package models;


import io.ebean.Finder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "role_type")
public class RoleType {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public enum roleTypeEnum{
        ADMIN(1,"admin"), CUSTOMER(2,"customer");
        private int id;
        private String role;

        public int getId() {
            return id;
        }

        public String getRole() {
            return role;
        }

        roleTypeEnum(Integer id,String role){
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static final Finder<Integer,RoleType> find= new Finder<>(RoleType.class);
}
