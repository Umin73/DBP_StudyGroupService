package model.domain;

public class GroupMember {
    private String groupId;
    private User user;
    private String role;
    private double attendanceRate;
    private double quizRate;
    private double assignRate;

    public GroupMember(String groupId, User user, String role, double attendanceRate, double quizRate, double assignRate) {
        this.groupId = groupId;
        this.user = user;
        this.role = role;
        this.attendanceRate = attendanceRate;
        this.quizRate = quizRate;
        this.assignRate = assignRate;
    }

    public GroupMember() { }

    public double getAssignRate() {
        return assignRate;
    }

    public void setAssignRate(double assignRate) {
        this.assignRate = assignRate;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getAttendanceRate() {
        return attendanceRate;
    }

    public void setAttendanceRate(double attendanceRate) {
        this.attendanceRate = attendanceRate;
    }

    public double getQuizRate() {
        return quizRate;
    }

    public void setQuizRate(double quizRate) {
        this.quizRate = quizRate;
    }
}