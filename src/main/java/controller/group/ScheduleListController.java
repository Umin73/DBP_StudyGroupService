package controller.group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.GroupManager;

public class ScheduleListController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
