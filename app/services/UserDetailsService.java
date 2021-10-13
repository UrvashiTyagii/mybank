package services;

import dto.UserDetailsRequest;
import dto.UserDetailsResponse;
import models.User;

import javax.inject.Singleton;

public class UserDetailsService {


    @Singleton
    public static UserDetailsResponse saveUserDetails(UserDetailsRequest userDetailsRequestdto) {


        //User user = User.find.byId(userDetailsRequestdto.getUserId());
        User user = User.find.byId(13);

        UserDetailsResponse userDetailsResponsedto = new UserDetailsResponse();
        userDetailsResponsedto.setUserName(user.getFname() + "" + user.getLname());
        userDetailsResponsedto.setUserId(user.getId());
        userDetailsResponsedto.setDob(user.getDob());
        userDetailsResponsedto.setEmail(user.getEmail());
        return userDetailsResponsedto;




    }




}
