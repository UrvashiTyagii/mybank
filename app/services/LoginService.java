package services;


import dto.LoginRequest;
import dto.LoginResponse;
import models.Password;
import models.User;

import javax.inject.Singleton;

@Singleton
public class LoginService {


    public static LoginResponse validateCredentials(LoginRequest loginRequestDto){
        LoginResponse loginResponseDto= new LoginResponse();


        try {
        User user= User.find.byId(loginRequestDto.getCustomerId());
        if (user==null)
        {
            System.out.println("doesnt exist!");

            loginResponseDto.setStatus(404);
            loginResponseDto.setMessage("User id doesnt exist!");
            return loginResponseDto;
        }

        Password userPassword=Password.find.query().where().eq("userId.id",user.getId())
                .eq("password",loginRequestDto.getCustomerPassword()).findOne();

        if (userPassword==null)
        {
            System.out.println("Incorrect password for user Id" + loginRequestDto.getCustomerId());

            loginResponseDto.setStatus(404);
            loginResponseDto.setMessage("Incorrect Password!");
            return loginResponseDto;
        }
        if(user.getRoleType().getId()==1)
        {
           loginResponseDto.setRoleType(1);
           loginResponseDto.setStatus(1);
           return loginResponseDto;
        }
        if (user.getRoleType().getId()== 2)
        {
            loginResponseDto.setRoleType(2);
            loginResponseDto.setStatus(1);
            return loginResponseDto;
        }
        }




        catch (Exception exception)
        {
         System.out.println("some exception"+exception.getStackTrace().toString());


            loginResponseDto.setStatus(500);
            loginResponseDto.setMessage("Some exception occured");

            return loginResponseDto;
        }
        System.out.println("LogIn Successful for user Id "+ loginRequestDto.getCustomerId());

        loginResponseDto.setStatus(1);

        User userRole=User.find.byId(loginRequestDto.getCustomerId());
        loginResponseDto.setRoleType(User.userRoleEnum.CUSTOMER.getId());
        loginResponseDto.setMessage("LogIn Successful!");
        return loginResponseDto;



    }

}
