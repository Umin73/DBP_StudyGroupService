package controller.group;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.domain.Assignment;
import model.service.AssignmentManager;

public class CreateAssignmentController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CreateAssignmentController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 과제 객체 생성: 사용자가 입력한 제목, 설명, 마감일 파라미터로 Assignment 객체 생성
    	Assignment assignment = null;
    	Date dateDeadline = null;
    
        try {
        	String assignId = UUID.randomUUID().toString();
        	String title = request.getParameter("title");
        	String description = request.getParameter("description");
        	String deadline = request.getParameter("deadline");     
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");         // 문자열 -> Date        
        	dateDeadline = formatter.parse(deadline);
        	Date createDate = null; //임시
        	String submit = null;  //임시
        	
        	// 세션에서 groupId 가져오기
            HttpSession session = request.getSession();
            String groupId = (String) session.getAttribute("groupId");

            // groupId가 세션에 없으면 오류 처리
            /*if (groupId == null) {
                log.error("groupId not found in session");
                request.setAttribute("errorMessage", "그룹 정보가 없습니다. 다시 로그인해 주세요.");
                return "redirect:/user/login/from"; // 로그인 페이지로 리다이렉트
            }*/
            if(groupId == null) {   //test용 임시값
            	groupId = "3d085fa1c83a41b9a19cda7a41cb19d7";
            }
           
        	assignment = new Assignment(assignId, title, description, dateDeadline, groupId, createDate, submit);
        	
            // AssignmentManager의 인스턴스를 통해 과제를 생성하는 메서드 호출
            AssignmentManager manager = AssignmentManager.getInstance();
            manager.create(assignment);

            // 과제 생성 성공 시
            request.setAttribute("successMessage", "과제가 생성되었습니다.");
            return "redirect:/assignment/view";  
            
        } catch (ParseException e) { //날짜 형식 오류 
        	log.error("Invalid date format: {}", dateDeadline, e);
            request.setAttribute("errorMessage", "날짜 형식이 올바르지 않습니다.");
            return "/group/assignment/createAssignment.jsp"; 
        } catch (Exception e) {  // 예외가 발생한 경우 처리
            // 예외 발생 시 입력 폼으로 포워딩하여 실패 메시지와 과제 객체 전달
        	log.error("Assignment creation failed", e);
            request.setAttribute("creationFailed", true);
            request.setAttribute("exception", e);
            request.setAttribute("assignment", assignment);
            return "/group/assignment/createAssignment.jsp";  // 실패 시 과제 생성 폼으로 돌아가서 오류 메시지 표시
        }
    }
}
