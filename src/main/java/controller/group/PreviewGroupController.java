package controller.group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.StudyGroupDAO;
import model.domain.StudyGroup;

public class PreviewGroupController implements Controller {

    private StudyGroupDAO groupDao = new StudyGroupDAO();
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String groupId = request.getParameter("groupId");
        
        System.out.println("GroupId is " + groupId);
        
        if(groupId == null || groupId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "groupId가 제공되지 않았습니다.");
            return null;
        }
        
        StudyGroup group = groupDao.findGroupById(groupId);
        
        if(group == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 그룹을 찾을 수 없습니다.");
            return null;
        }
        
        request.setAttribute("group", group);
        
        
        return "/group/previewGroup.jsp";
    }

}
