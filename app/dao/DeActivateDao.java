package dao;

import daolayer.DbConnector;
import models.User;

public class DeActivateDao {
    public static void deActivateDao(User user){
        DbConnector.update(user);
    }
}
