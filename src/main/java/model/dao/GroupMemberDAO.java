package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.domain.GroupMember;
import model.domain.User;
import model.service.UserManager;

public class GroupMemberDAO {
    private JDBCUtil jdbcUtil = null;
    private UserManager userMan;
    
    public GroupMemberDAO() {
        jdbcUtil = new JDBCUtil();
        userMan = UserManager.getInstance();
    }
    
    public List<GroupMember> findMembersByGroupId(String groupId) throws SQLException {
        
        String sql = "SELECT GROUP_ID, USER_ID, ROLE, ATTENDANCE_RATE, QUIZ_RATE, ASSIGN_RATE "
                + "FROM GROUPMEMBER "
                + "WHERE GROUP_ID = ?";
        
        jdbcUtil.setSqlAndParameters(sql, new Object[]{groupId});
        List<GroupMember> memberList = new ArrayList<>();
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                
                String userId = rs.getString("USER_ID");
                User user = userMan.findUserByUserId(userId);
                
                GroupMember member = new GroupMember();
                member.setGroupId(rs.getString("GROUP_ID"));
                member.setUser(user);
                member.setRole(rs.getString("ROLE"));
                member.setAttendanceRate(rs.getDouble("ATTENDANCE_RATE"));
                member.setQuizRate(rs.getDouble("QUIZ_RATE"));
                member.setAssignRate(rs.getDouble("ASSIGN_RATE"));

                memberList.add(member);
            }
        } finally {
            jdbcUtil.close();
        }
        
        System.out.println("size of memberList : " + memberList.size());
        
        return memberList;
    }
}
