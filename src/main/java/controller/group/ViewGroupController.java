package controller.group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.domain.StudyGroup;
import model.service.GroupManager;

public class ViewGroupController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 헤더의 로그인/로그아웃 버튼용
        HttpSession session = request.getSession();
        boolean isLoggedIn = UserSessionUtils.hasLogined(session);
        request.setAttribute("isLoggedIn", isLoggedIn);
        
        GroupManager groupMan = GroupManager.getInstance();
        List<StudyGroup> groupList = groupMan.findAllGroups();
        
        request.setAttribute("groupList", groupList);
        
        return "/group/viewAllGroup.jsp";
    }

}
