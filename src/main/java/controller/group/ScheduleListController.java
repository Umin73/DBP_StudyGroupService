package controller.group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.GroupManager;

public class ScheduleListController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // 헤더의 로그인/로그아웃 버튼용
        HttpSession session = request.getSession();
        boolean isLoggedIn = UserSessionUtils.hasLogined(session);
        request.setAttribute("isLoggedIn", isLoggedIn);
        
        GroupManager groupMan = GroupManager.getInstance();
        String groupId = request.getParameter("groupId");
        
        if(groupId == null || groupId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "groupId가 제공되지 않았습니다.");
            return null;
        }

        // 스터디 일정 생성시 컨트롤러 처리 구현 필요
        
        return "/group/scheduleList.jsp";
    }

}
