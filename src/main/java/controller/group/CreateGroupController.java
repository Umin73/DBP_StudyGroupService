package controller.group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.StudyGroupDAO;
import model.domain.StudyGroup;
import model.service.ExistingGroupException;
import model.service.GroupManager;

public class CreateGroupController implements Controller {
    
    private StudyGroupDAO groupDao = new StudyGroupDAO();
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // 헤더의 로그인/로그아웃 버튼용
        HttpSession session = request.getSession();
        boolean isLoggedIn = UserSessionUtils.hasLogined(session);
        request.setAttribute("isLoggedIn", isLoggedIn);
        

        try {
            // String groupId = request.getParameter("groupId"); // 그룹 생성자 ID
            String groupName = request.getParameter("groupName");
            String groupGoal = request.getParameter("groupGoal");
            String groupDescription = request.getParameter("groupDescription");
            String groupCategory = request.getParameter("groupCategory");
            String groupSizeStr = request.getParameter("groupSize"); // null 처리 가능성 확인
            
            String userId = (String) request.getSession().getAttribute("userId");
            
            if (userId == null) {
                request.setAttribute("error", "로그인이 필요합니다.");
                return "/user/loginForm.jsp";
            }
            
            int groupSize = 1; // 기본값
            if (groupSizeStr != null && !groupSizeStr.isEmpty()) {
                groupSize = Integer.parseInt(groupSizeStr);
            }
            
            // StudyGroup 객체 생성
            StudyGroup group = new StudyGroup(groupName, groupDescription, groupGoal, groupCategory, groupSize, userId);
            
            // GroupManager 인스턴스를 통해 그룹 생성
            GroupManager groupMan = GroupManager.getInstance();
            groupMan.create(group, userId);
            
            // 성공 
            request.setAttribute("successMessage", "그룹이 생성되었습니다.");
            return "/group/createGroup.jsp";
        } catch (ExistingGroupException e) {
            // 그룹 ID가 이미 존재하는 경우
            request.setAttribute("createFailed", true);
            request.setAttribute("exception", e);
            return "/group/createGroup.jsp";
        } catch (Exception e) {
            // 기타 예외 처리
            request.setAttribute("errorMessage", "예기치 않은 오류가 발생했습니다. 다시 시도해주세요.");
            return "/group/createGroup.jsp";
        }
        
    }
}