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
    
    // 그룹 멤버리스트용
    public User findUserByUserId(String userId) throws SQLException {
        User user = userDao.findUser(userId);
        System.out.println("username is " + user.getUsername());
        
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

        return true;
    }
    
    public String findUserId(String username, String phone) throws SQLException {
        String userId = userDao.findUserId(username, phone);
        
        System.out.println("아이디는 " + userId);
        
        if(userId == null) {
            throw new SQLException("사용자를 찾을 수 없습니다.");
        }
        
        return userId;
    }
}
