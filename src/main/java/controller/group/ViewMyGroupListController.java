package controller.group;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.domain.StudyGroup;
import model.service.GroupManager;

public class ViewMyGroupListController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // 헤더의 로그인/로그아웃 버튼용
        HttpSession session = request.getSession();
        boolean isLoggedIn = UserSessionUtils.hasLogined(session);
        request.setAttribute("isLoggedIn", isLoggedIn);
        
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
