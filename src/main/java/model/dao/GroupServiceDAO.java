package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.domain.Assignment;
import model.domain.Notice;

/*
 * 스터디그룹내의 공지사항, 과제 보기, 추가, 수정, 삭제 수행하는 DAO클래스 
 **/

public class GroupServiceDAO {
    private JDBCUtil jdbcUtil = null;
    
    public GroupServiceDAO() {
    	jdbcUtil = new JDBCUtil();
    }

    // 공지사항 작성
    public int createNotice(Notice notice) {
        String sql = "INSERT INTO Notice (noticeId, title, content, createDate, groupId) VALUES (?, ?, ?, ?)";
        Object[] param = new Object[] {
        	notice.getNoticeId(), 
        	notice.getTitle(), 
        	notice.getContent(), 
        	notice.getCreateDate(), 
        	notice.getGroupId()
        };
        
        
        try {
            jdbcUtil.setSqlAndParameters(sql, param);
            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return 0;
    }

    // 공지사항 보기
    public Notice viewNotice(int noticeId) {
        String sql = "SELECT * FROM Notice WHERE noticeId = ?";
        Notice notice = null;
        try {
            jdbcUtil.setSqlAndParameters(sql, new Object[]{noticeId});
            ResultSet rs = jdbcUtil.executeQuery();

            if (rs.next()) {
                notice = new Notice(
                        rs.getString("noticeId"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getDate("createdDate"),
                        rs.getString("groupId")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return notice;
    }

    // 공지사항 삭제
    public boolean deleteNotice(int noticeId) {
        String sql = "DELETE FROM Notice WHERE noticeId = ?";
        try {
            jdbcUtil.setSqlAndParameters(sql, new Object[]{noticeId});
            int result = jdbcUtil.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return false;
    }

    // 공지사항 수정
    public boolean updateNotice(Notice notice) {
        String sql = "UPDATE Notice SET title = ?, content = ? WHERE noticeId = ?";
        try {
            jdbcUtil.setSqlAndParameters(sql, new Object[]{
                    notice.getTitle(), notice.getContent(), notice.getNoticeId()
            });
            int result = jdbcUtil.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return false;
    }

    // 과제 생성
    public int createAssignment(Assignment assignment) {
        String sql = "INSERT INTO Assignment (assignmentId, title, description, deadline, groupId) VALUES (?, ?, ?, ?)";
        Object[] param = new Object[] {
        	assignment.getAssignmentId(),
        	assignment.getTitle(), 
        	assignment.getDescription(), 
        	assignment.getDeadline(), 
        	assignment.getGroupId()
        };
        
        try {
            jdbcUtil.setSqlAndParameters(sql, param);
            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return 0;
    }

    // 과제 보기
    public Assignment viewAssignment(int assignmentId) {
        String sql = "SELECT * FROM Assignment WHERE assignmentId = ?";
        Assignment assignment = null;
        try {
            jdbcUtil.setSqlAndParameters(sql, new Object[]{assignmentId});
            ResultSet rs = jdbcUtil.executeQuery();

            if (rs.next()) {
                assignment = new Assignment(
                        rs.getString("assignmentId"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("deadline"),
                        rs.getString("groupId")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return assignment;
    }

    // 과제 삭제
    public boolean deleteAssignment(int assignmentId) {
        String sql = "DELETE FROM Assignment WHERE assignmentId = ?";
        try {
            jdbcUtil.setSqlAndParameters(sql, new Object[]{assignmentId});
            int result = jdbcUtil.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return false;
    }

    // 과제 수정
    public boolean updateAssignment(Assignment assignment) {
        String sql = "UPDATE Assignment SET title = ?, description = ?, deadline = ? WHERE assignmentId = ?";
        Object[] param = new Object[] {
        	assignment.getTitle(), 
        	assignment.getDescription(), 
        	assignment.getDeadline(), 
        	assignment.getAssignmentId()
        };
        try {
            jdbcUtil.setSqlAndParameters(sql, param);
            int result = jdbcUtil.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return false;
    }

    // 과제 제출
    public boolean submitAssignment(int assignmentId, int studentId, String submission) {
        String sql = "INSERT INTO Submission (assignmentId, studentId, submissionDate, content) VALUES (?, ?, NOW(), ?)";
         try {
            jdbcUtil.setSqlAndParameters(sql, new Object[]{assignmentId, studentId, submission});
            int result = jdbcUtil.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return false;
    }
}

