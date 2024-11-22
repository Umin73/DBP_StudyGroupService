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
    
    public User findUser(String userId) throws SQLException, UserNotFoundException{
        User user = userDao.findUser(userId);
        
        if(user == null) {
            throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
        }
        
        return user;
    }
    
    public boolean login(String userId, String password) 
            throws SQLException, UserNotFoundException, PasswordMismatchException{
        User user = findUser(userId);

        if (user == null) {
            throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
        }

        if (!user.matchPassword(password)) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }

        System.out.println("로그인 성공: " + userId);
        
        return true;
    }
}
