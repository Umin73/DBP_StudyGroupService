package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.domain.User;

public class UserDAO {
    private JDBCUtil jdbcUtil = null;

    
    public UserDAO() {
        jdbcUtil = new JDBCUtil();
    }


    public int createUser(User user) throws SQLException {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?, ?)";
        Object[] param = new Object[]{
            user.getUserId(), 
            user.getPassword(), 
            user.getUsername(), 
            user.getEmail(), 
            user.getPhone()
        };
        jdbcUtil.setSqlAndParameters(sql, param);
        
        try {               
            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {     
            jdbcUtil.commit();
            jdbcUtil.close();
        }       
        return 0;   
    }
    
    public int updateUser(User user) throws SQLException {
        String sql = "UPDATE USERS SET password = ?, username = ?, email = ?, phone = ? WHERE user_id = ?";
        Object[] param = new Object[] {
                user.getPassword(), user.getUsername(), user.getEmail(), user.getPhone(), user.getUserId()};
        jdbcUtil.setSqlAndParameters(sql, param);
        
        try {               
            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }       
        return 0;
    }
    
    public int deleteUser(String userId) throws SQLException {
        String sql = "DELETE FROM USERS WHERE user_id=?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});
        
        try {               
            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }       
        return 0;
    }
    
    public User findUser(String userId, boolean close) throws SQLException {
        String sql = "SELECT password, username, email, phone FROM USERS WHERE TRIM(USER_ID) = ?";
        System.out.println("Executing SQL: " + sql + " with userId=[" + userId.trim() + "]");
        
        jdbcUtil.setSqlAndParameters(sql, new Object[]{userId.trim()});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                return new User(
                        userId,
                        rs.getString("password"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(close)
                jdbcUtil.close();
        }
        System.out.println("User not found: " + userId);
        return null;
    }
    
    public String findUserId(String username, String phone) throws SQLException {
        String sql = "SELECT user_id FROM USERS WHERE username = ? AND phone = ?";
        System.out.println("Executing SQL: " + sql + " [username: " + username + ", phone: " + phone + "]");
        
        jdbcUtil.setSqlAndParameters(sql, new Object[] { username, phone });

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                System.out.println("Query Success: User ID found.");
                return rs.getString("user_id");
            } else {
                System.out.println("Query Failure: No matching user.");
            }
        } catch (Exception ex) {
            System.out.println("Query Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }

    
    public List<User> findAllUser() throws SQLException {
        String sql = "SELECT user_id, password, username, email, phone " 
                   + "FROM USERS "
                   + "ORDER BY user_id";
        jdbcUtil.setSqlAndParameters(sql, null);
                    
        try {
            ResultSet rs = jdbcUtil.executeQuery();      
            List<User> userList = new ArrayList<User>();
            while (rs.next()) {
                User user = new User(
                    rs.getString("user_id"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone")); 
                userList.add(user);
            }       
            return userList;                    
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
    
    public User loginUser(String userId, String password) throws SQLException {
        String sql = "SELECT id, username, email, phone FROM USERS WHERE user_id=? AND password=?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {userId, password});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                return new User(
                        userId,
                        password,
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("phone"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
    
    
    
    public boolean existingUser(String userId) throws SQLException {
        String sql = "SELECT count(*) FROM USERS WHERE user_id=?";      
        jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return (count == 1 ? true : false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return false;
    }
    
    public boolean existingUser(String userId, String username, String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM USERS WHERE user_id = ? AND username = ? AND email = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { userId, username, email });
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if(rs.next()) {
                int count = rs.getInt(1);
                return (count == 1 ? true : false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return false;
    }
    
    public boolean updatePassword(String user_id, String newPassword) throws SQLException {
        String sql = "UPDATE USERS SET password = ? WHERE user_id = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {newPassword, user_id});
        
        try {
            int result = jdbcUtil.executeUpdate();
            return result > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return false;
    }


    public String findEmailByUserIdAndUsername(String userId, String username) throws SQLException {
        String sql = "SELECT email FROM USERS WHERE user_id = ? AND username = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { userId, username });

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
}