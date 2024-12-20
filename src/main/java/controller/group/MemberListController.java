package controller.group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.domain.GroupMember;
import model.service.GroupManager;

public class MemberListController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // 헤더의 로그인/로그아웃 버튼용
        HttpSession session = request.getSession();
        boolean isLoggedIn = UserSessionUtils.hasLogined(session);
        request.setAttribute("isLoggedIn", isLoggedIn);
        
        GroupManager groupMan = GroupManager.getInstance();
        String groupId = request.getParameter("groupId");
        
        request.setAttribute("groupId", groupId);
        
        if(groupId == null || groupId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "groupId가 제공되지 않았습니다.");
            return null;
        }
        
        List<GroupMember> groupMembers = groupMan.getGroupMemberList(groupId);
        
        if (groupMembers == null || groupMembers.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "멤버를 찾을 수 없습니다.");
            return null;
        }
        request.setAttribute("groupMember", groupMembers);
        
        return "/group/viewMemberList.jsp";
    }

}
