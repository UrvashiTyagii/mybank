package dao;

import daolayer.DbConnector;
import models.Password;
import models.User;

import java.util.List;

public class UserDao {

public static void saveUser(User user){
    DbConnector.save(user);
}

public static void saveUserPassword(Password password){
    DbConnector.save(password);
}

public static void deleteUser(User user){
    DbConnector.delete(user);

}

public static void updateUser(User user){
    DbConnector.update(user);
}

public List<User> getList(Integer roleId)
{
    List<User> userList = User.find.query().where().eq("roleType.id",roleId).findList();
    return userList;
}


}
