package controller.group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.StudyGroup;
import model.service.GroupManager;

public class ViewMyGroupListController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        GroupManager groupMan = GroupManager.getInstance();
        String userId = (String) request.getSession().getAttribute("userId");
       
        if (userId == null) {
            request.setAttribute("error", "로그인이 필요합니다.");
            return "/user/loginForm.jsp";
        }
        
        List<StudyGroup> myGroupList = groupMan.getUserGroups(userId);
        
        request.setAttribute("myGroupList", myGroupList);
        
        return "/group/viewMyAllGroup.jsp";
    }

}
