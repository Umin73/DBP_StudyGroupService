package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.domain.StudyGroup;

public class StudyGroupDAO {
    private JDBCUtil jdbcUtil = null;
    
    public StudyGroupDAO() {          
        jdbcUtil = new JDBCUtil();
    }
    
    // 그룹 생성
    public int create(StudyGroup group, String userId) throws SQLException {
        
        String uuid = UUID.randomUUID().toString().replace("-", "");
        group.setGroupId(uuid);
        
        String sql = "INSERT INTO STUDYGROUP VALUES (?, ?, ?, ?, ?, ?, ?, ?)";     
        Object[] param = new Object[] {
                group.getGroupId(), group.getGroupName(), group.getGroupDescription(),
                group.getGoal(), group.getCategory(), group.getMaxMembers(), group.getCurrMembers(), userId};             
        jdbcUtil.setSqlAndParameters(sql, param);
        
        try {    
            int result = jdbcUtil.executeUpdate();
            
            // 그룹 생성 시 그룹 생성자는 리더로 설정
            // 멤버 추가
            String insertMemberSql = "INSERT INTO GROUPMEMBER (GROUP_ID, USER_ID, ROLE, ATTENDANCE_RATE, QUIZ_RATE, ASSIGN_RATE) VALUES (?, ?, ?, ?, ?, ?)";
            jdbcUtil.setSqlAndParameters(insertMemberSql, new Object[] { group.getGroupId(), userId, "leader", 0, 0, 0 });
            int rowsInserted = jdbcUtil.executeUpdate();
            if (rowsInserted == 0) {
                jdbcUtil.rollback();
                return -1;
            }
            
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {     
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }
        
        return 0;            
    }
    
    // 그룹 수정
    public int updateGroup(StudyGroup group) throws SQLException {
        String sql = "UPDATE STUDYGROUP "
                    + "SET groupname=?, goal=?, maxmember=?, leaderId=? "
                    + "WHERE groupId=?";
        String leaderId = group.getLeaderId();
        if (leaderId.equals("")) leaderId = null;
        Object[] param = new Object[] {
                group.getGroupName(), group.getGoal(), group.getMaxMembers()};
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
    
    /* 그룹장 변경
    public int updateLeader(StudyGroup group) {
        String sql = "UPDATE StudyGroup "
                    + "SET leaderId=? "
                    + "WHERE groupId=?";
        Object[] param = new Object[] {group.getLeaderId(), group.getGroupId()};                
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
    */
    
    // 그룹 삭제
    public int deleteGroup(String groupId) throws SQLException {
        String sql = "DELETE FROM StudyGroup WHERE groupId=?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {groupId});
        
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
    
    // 그룹 ID로 스터디 그룹의 세부 정보를 가져오기
    public StudyGroup findGroupById(String groupId) throws SQLException {
        String sql = "SELECT groupname, groupdescription, goal, category, currmember, maxmember "
                    + "FROM STUDYGROUP "
                    + "WHERE group_id=?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {groupId});
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                StudyGroup group = new StudyGroup(
                        groupId,
                        rs.getString("groupname"),
                        rs.getString("groupdescription"),
                        rs.getString("goal"),
                        rs.getString("category"),
                        rs.getInt("currmember"),
                        rs.getInt("maxmember"));
                return group;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
    
    // 전체 그룹 리스트
    public List<StudyGroup> findGroupList() throws SQLException {
        String sql = "SELECT group_id, groupname, groupdescription, category, currmember, maxmember FROM STUDYGROUP";
        jdbcUtil.setSqlAndParameters(sql, null);    // 파라미터가 없으므로 null로 설정

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<StudyGroup> groupList = new ArrayList<StudyGroup>();   // 스터디그룹들의 리스트 생성
            while (rs.next()) {
                StudyGroup group = new StudyGroup(
                        rs.getString("group_id"),
                        rs.getString("groupname"),
                        rs.getString("groupdescription"),
                        rs.getString("category"),
                        rs.getInt("currmember"),
                        rs.getInt("maxmember"));
                groupList.add(group);   // 생성된 객체를 리스트에 추가
            }
            return groupList;                    
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
    
    // 그룹 가입
    public String joinGroup(String groupId, String userId, String role) throws SQLException {
        // 중복 가입 확인
        String checkMemberSql = "SELECT COUNT(*) FROM GROUPMEMBER WHERE GROUP_ID = ? AND USER_ID = ?";
        jdbcUtil.setSqlAndParameters(checkMemberSql, new Object[] { groupId, userId });

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return "Duplicated";
            }

            // 그룹 인원 확인
            String groupCheckSql = "SELECT CURRMEMBER, MAXMEMBER FROM STUDYGROUP WHERE GROUP_ID=?";
            jdbcUtil.setSqlAndParameters(groupCheckSql, new Object[] { groupId });

            rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                int currMembers = rs.getInt("CURRMEMBER");
                int maxMembers = rs.getInt("MAXMEMBER");

                if (currMembers >= maxMembers) {
                    return "Full";
                }
            } else {
                return "Group not found.";
            }

            // 멤버 추가
            String insertMemberSql = "INSERT INTO GROUPMEMBER (GROUP_ID, USER_ID, ROLE, ATTENDANCE_RATE, QUIZ_RATE, ASSIGN_RATE) VALUES (?, ?, ?, ?, ?, ?)";
            jdbcUtil.setSqlAndParameters(insertMemberSql, new Object[] { groupId, userId, role, 0, 0, 0 });
            int rowsInserted = jdbcUtil.executeUpdate();
            if (rowsInserted == 0) {
                jdbcUtil.rollback();
                return "Failed to add member.";
            }

            // 현재 멤버 수 증가
            String updateMemberCountSql = "UPDATE STUDYGROUP SET CURRMEMBER = CURRMEMBER + 1 WHERE GROUP_ID=?";
            jdbcUtil.setSqlAndParameters(updateMemberCountSql, new Object[] { groupId });
            int rowsUpdated = jdbcUtil.executeUpdate();
            if (rowsUpdated == 0) {
                jdbcUtil.rollback();
                return "Failed to update member count.";
            }

            jdbcUtil.commit();
            return "Joined";
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
            return "An error occurred.";
        } finally {
            jdbcUtil.close();
        }
    }
  
  
    // 그룹 존재 여부
    public boolean existingGroup(String groupId) throws SQLException {
        String sql = "SELECT count(*) FROM STUDYGROUP WHERE group_id = ?";      
        jdbcUtil.setSqlAndParameters(sql, new Object[] {groupId});

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
    
    // 사용자가 가입한 그룹들 조회
    public List<StudyGroup> getUserGroups(String userId) throws SQLException {
        String sql = "SELECT SG.group_id, SG.groupname, SG.groupdescription, SG.goal, SG.category, " +
                "SG.currmember, SG.maxmember, SG.leader, " +
                "GM.role, GM.attendance_rate, GM.quiz_rate, GM.assign_rate " +
                "FROM STUDYGROUP SG " +
                "JOIN GROUPMEMBER GM ON SG.group_id = GM.group_id " +
                "WHERE TRIM(GM.user_id) = ?";
        
        System.out.println("userId" + userId);
        jdbcUtil.setSqlAndParameters(sql, new Object[]{userId});
        
        List<StudyGroup> groupList = new ArrayList<>();
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            System.out.println("~~~~");
            while (rs.next()) {
                // StudyGroup 객체 생성
                System.out.println("Generate StudyGroup Obj");
                StudyGroup group = new StudyGroup();
                group.setGroupId(rs.getString("GROUP_ID"));
                group.setGroupName(rs.getString("GROUPNAME"));
                group.setGroupDescription(rs.getString("GROUPDESCRIPTION"));
                group.setGoal(rs.getString("GOAL"));
                group.setCategory(rs.getString("CATEGORY"));
                group.setCurrMembers(rs.getInt("CURRMEMBER"));
                group.setMaxMembers(rs.getInt("MAXMEMBER"));
                group.setLeaderId(rs.getString("LEADER"));
                
                /*
                 * // GroupMember 객체 생성 GroupMember member = new GroupMember(
                 * rs.getString("GROUP_ID"), null, rs.getString("ROLE"),
                 * rs.getDouble("ATTENDANCE_RATE"), rs.getDouble("QUIZ_RATE"),
                 * rs.getDouble("ASSIGN_RATE"));
                 * 
                 * // 그룹에 멤버 추가 List<GroupMember> members = new ArrayList<>();
                 * members.add(member); group.setMembers(members);
                 */
                
                groupList.add(group);
            }
            return groupList;
        } finally {
            jdbcUtil.close();
        }
    }
    
}