package model.service;

import java.sql.SQLException;

import model.dao.UserDAO;
import model.domain.User;

public class UserManager {
    private static UserManager userMan = new UserManager();
    private UserDAO userDao;
    
    private UserManager() {
        try {
            userDao = new UserDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static UserManager getInstance() {
        return userMan;
    }
    
    public int create(User user) throws SQLException, ExistingUserException {
        if(userDao.existingUser(user.getUserId()) == true) {
            throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
        }
        return userDao.createUser(user);
    }
}
