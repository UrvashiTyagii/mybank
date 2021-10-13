package dao;

import daolayer.DbConnector;
import models.User;

public class ActivateDao {

    public static void activateDao(User user){
        DbConnector.update(user);
    }
}
