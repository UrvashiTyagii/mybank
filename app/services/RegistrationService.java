package services;

import dao.UserDao;
import dto.RegistrationRequest;
import models.Password;
import models.Status;
import models.User;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;


@Singleton // only one instance will be created in allover the project when we inject this class

public class RegistrationService {

    //@Inject
    private final UserDao userDao;


    private final UserDao userDao1;
    // instead of creating more than one referenced variables of the dao file
    // we can call it by constructor by declaring referenced variables as public final Daofilename referencedvariablename;

    @Inject
    public RegistrationService(UserDao userDao, UserDao userDao1) {
        this.userDao = userDao;
        this.userDao1 = userDao1;
    }

  /*  public void save(){
        User user = new User();
        user.setFname("shista");
        user.setLname("sngh");
        user.setCreatedOn(new Date());
        user.setAdharNo("990005858268784");
        user.setPhoneNo("5196065799");
        user.setDob(new Date());
        user.setEmail("shrstasngh@gmail.com");



        Status status= Status.find.query().where()
                .eq("id",1)
                .findOne();
        user.setStatus(status);

        RoleType roleType=RoleType.find.query().where()
                        .eq("id",1)
                                .findOne();
        user.setRoleType(roleType);
        userDao.saveUser(user);

    } *

   */

    public void save(RegistrationRequest requestDto){

        User user=new User();
        user.setFname(requestDto.getCustomerFirstName());
        user.setLname(requestDto.getCustomerLastName());
        user.setAdharNo(requestDto.getAadharNumber());
        user.setDob(requestDto.getDob());
        user.setEmail(requestDto.getEmail());
        user.setPhoneNo(requestDto.getPhoneNumber());
        user.setCreatedOn(new Date());
        Password password=new Password();

        password.setPassword(requestDto.getPassword());
        password.setCreatedOn(new Date());
        password.setUserId(user);
        password.setConfirmpassword(requestDto.getConfirmPassword());
        Status status=Status.find.byId(Status.statusEnum.ACTIVE.getId());
        user.setStatus(status);
        User user1=User.find.byId(User.userRoleEnum.CUSTOMER.getId());
        user.setRoleType(user1);
        userDao.saveUser(user);
        userDao.saveUserPassword(password);
    }



    public void update(){
        User user = User.find.query().where()
                .eq("email","urvashityagi@gmail.com")
                .eq("id",2)
                .findOne()
                ;

       // List<User> userList = User.find.query().where()
            //    .eq("email","abc@Wgmail.com")
             //   .eq("id",1);
               // .in()
              // .notIn()
             // .orderBy()
            // .findList();

        user.setFname("Urvanshi");
        user.setLname("tyagii");
        user.setCreatedOn(new Date());
        user.setAdharNo("898768567018784");
        user.setPhoneNo("7650006548");
        //user.setDob(new Date());
        user.setEmail("urvashi.tyagi@gmail.com");
        userDao.deleteUser(user);

    }




}
