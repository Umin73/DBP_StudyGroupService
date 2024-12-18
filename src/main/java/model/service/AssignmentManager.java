package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.GroupServiceDAO;
import model.dao.StudyGroupDAO;
import model.domain.Assignment;
import model.domain.StudyGroup;

public class AssignmentManager {
    private static AssignmentManager assignMan = new AssignmentManager();
    private GroupServiceDAO assignmentDao;
    
    private AssignmentManager() {
        try {
            assignmentDao = new GroupServiceDAO();
        } catch (Exception e) {
        	System.err.println("SQL 오류: " + e.getMessage());
            System.err.println("오류 코드: " + ((SQLException) e).getErrorCode());
            e.printStackTrace();
        }
    }

    public static AssignmentManager getInstance() {
        return assignMan;
    }

    // 과제 생성
    public int create(Assignment assignment) throws Exception {
        // 과제 생성 관련 비즈니스 로직 추가 가능
        //if (assignmentDao.existingGroup(assignment.getAssignmentId()) == true)  {
        //    throw new IllegalArgumentException("과제 제목은 필수 입력 항목입니다.");
        //}
        return assignmentDao.createAssignment(assignment); // DAO를 통해 DB에 저장
    }

    // 과제 목록 조회
    //public List<Assignment> getAssignmentList() throws Exception {
    //    return assignmentDao.findAllAssignments(); // DAO를 통해 모든 과제 조회
    //}

    // 과제 상세 조회
    public Assignment getAssignmentById(String id) throws Exception {
        return assignmentDao.viewAssignment(id); // DAO를 통해 특정 과제 조회
    }

    // 과제 수정
    public boolean updateAssignment(Assignment assignment) throws Exception {
        if (assignment.getAssignmentId() != null) {
            throw new IllegalArgumentException("올바른 과제 ID가 필요합니다.");
        }
        return assignmentDao.updateAssignment(assignment); // DAO를 통해 과제 수정
    }

    // 과제 삭제
    public boolean deleteAssignment(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("올바른 과제 ID가 필요합니다.");
        }
        return assignmentDao.deleteAssignment(id); // DAO를 통해 과제 삭제
    }
}
