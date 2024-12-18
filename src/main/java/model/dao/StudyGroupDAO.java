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
    public int create(StudyGroup group) throws SQLException {
        
        String uuid = UUID.randomUUID().toString().replace("-", "");
        group.setGroupId(uuid);
        
        String sql = "INSERT INTO STUDYGROUP VALUES (?, ?, ?, ?, ?, ?, ?)";     
        Object[] param = new Object[] {
                group.getGroupId(), group.getGroupName(), group.getGroupDescription(),
                group.getGoal(), group.getCategory(), group.getMaxMembers(), group.getCurrMembers()};             
        jdbcUtil.setSqlAndParameters(sql, param);
        
        try {    
            int result = jdbcUtil.executeUpdate();
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
    public String joinGroup(String groupId, String userId) throws SQLException {
        // 중복 가입 확인
        String checkMemberSql = "SELECT COUNT(*) FROM GROUPMEMBER WHERE GROUP_ID = ? AND USER_ID = ?";
        jdbcUtil.setSqlAndParameters(checkMemberSql, new Object[] { groupId, userId });

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return "User is already a member of this group.";
            }

            // 그룹 인원 확인
            String groupCheckSql = "SELECT CURRMEMBER, MAXMEMBER FROM STUDYGROUP WHERE GROUP_ID=?";
            jdbcUtil.setSqlAndParameters(groupCheckSql, new Object[] { groupId });

            rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                int currMembers = rs.getInt("CURRMEMBER");
                int maxMembers = rs.getInt("MAXMEMBER");
                System.out.println("현재 멤버 수 = " + currMembers);
                System.out.println("최대 멤버 수 = " + maxMembers);

                if (currMembers >= maxMembers) {
                    return "Group is full.";
                }
            } else {
                return "Group not found.";
            }

            // 멤버 추가
            String insertMemberSql = "INSERT INTO GROUPMEMBER (GROUP_ID, USER_ID) VALUES (?, ?)";
            jdbcUtil.setSqlAndParameters(insertMemberSql, new Object[] { groupId, userId });
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
    
}