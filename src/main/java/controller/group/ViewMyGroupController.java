package controller.group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.StudyGroup;
import model.service.GroupManager;

public class ViewMyGroupController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GroupManager groupMan = GroupManager.getInstance();
        String groupId = request.getParameter("groupId");
        
        if(groupId == null || groupId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "groupId가 제공되지 않았습니다.");
            return null;
        }
        
        StudyGroup group = groupMan.findGroup(groupId);
        
        if(group == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 그룹을 찾을 수 없습니다.");
            return null;
        }
        
        System.out.println("그룹 이름은 " + group.getGroupName());
        
        request.setAttribute("group", group);
        return "/group/myGroup.jsp";
    }

}
