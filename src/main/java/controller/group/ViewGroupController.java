package controller.group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.StudyGroup;
import model.service.GroupManager;

public class ViewGroupController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        GroupManager groupMan = GroupManager.getInstance();
        List<StudyGroup> groupList = groupMan.findAllGroups();
        
        request.setAttribute("groupList", groupList);
        
        return "/group/viewAllGroup.jsp";
    }

}
