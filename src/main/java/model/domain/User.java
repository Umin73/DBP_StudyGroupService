package model.domain;

public class User {
    
    private Long id;
    private String userId;
    private String password;
    private String username;
    private String email;
    private String phone;

    public User() { }       // 기본 생성자
    
    public User(String userId, String password, String username, String email, String phone) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }
    
    public User(String userId, String username, String email, String phone) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean matchPassword(String password) {
        if(password == null) return false;
        return this.password.equals(password);
    }
    
    public boolean isSameUser(String userId) {
        return this.userId.equals(userId);
    }
    
    @Override
    public String toString() {
        return "User ["+"id=" + id + ", userId=" + userId + ", password=" + password + ", username=" + username + ", email=" + email + ", phone="
                + phone + "]";
    }
}