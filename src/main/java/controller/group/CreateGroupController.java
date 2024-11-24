package controller.group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.StudyGroup;
import model.service.ExistingGroupException;
import model.service.GroupManager;

public class CreateGroupController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String groupId = request.getParameter("groupId"); // 그룹 생성자 ID
		int size = Integer.parseInt(request.getParameter("groupSize"));
		//Group 객체 생성 
		StudyGroup group = new StudyGroup(
			request.getParameter("groupName"), 
			request.getParameter("groupGoal"), 
			request.getParameter("groupDescription"),
			request.getParameter("groupCategroy"), 
			size
			);

        try {
            // GroupManager 인스턴스를 통해 그룹 생성
        	GroupManager groupMan = GroupManager.getInstance();
            groupMan.create(group);
            
            // 성공 시 그룹 view 페이지로 리디렉션
            return "redirect:/group/view";
        } catch (ExistingGroupException e) {
            // 그룹 ID가 이미 존재하는 경우
            request.setAttribute("createFailed", true);
            request.setAttribute("exception", e);
            request.setAttribute("group", group);
            return "/group/createGroup.jsp";
        } catch (Exception e) {
            // 기타 예외 처리
            request.setAttribute("errorMessage", "예기치 않은 오류가 발생했습니다. 다시 시도해주세요.");
            return "/group/createGroup.jsp";
        }
		
	}
}
