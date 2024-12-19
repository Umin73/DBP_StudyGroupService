package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.user.UserSessionUtils;
import model.domain.StudyGroup;
import model.service.GroupManager;

public class HomeController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        
        boolean isLoggedIn = UserSessionUtils.hasLogined(session);
        request.setAttribute("isLoggedIn", isLoggedIn);
        
        GroupManager groupMan = GroupManager.getInstance();
        String userId = (String) request.getSession().getAttribute("userId");
        
        List<StudyGroup> myGroupList = groupMan.getUserGroups(userId);
        if (myGroupList.size() > 4) {
            myGroupList = myGroupList.subList(0, 4);
        }
        request.setAttribute("myGroupList", myGroupList);
        
        List<StudyGroup> groupList = groupMan.findAllGroups();
        if (groupList.size() > 4) {
            groupList = groupList.subList(0, 4);
        }
        request.setAttribute("groupList", groupList);
        
        return "/home.jsp";
    }

}
