package controller.group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.StudyGroupDAO;

public class JoinGroupController implements Controller{

    private StudyGroupDAO groupDao = new StudyGroupDAO();
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // 헤더의 로그인/로그아웃 버튼용
        HttpSession session = request.getSession();
        boolean isLoggedIn = UserSessionUtils.hasLogined(session);
        request.setAttribute("isLoggedIn", isLoggedIn);
        
        String groupId = request.getParameter("groupId");
        String userId = (String) request.getSession().getAttribute("userId");
        
        if (userId == null) {
            request.setAttribute("errorMessage", "로그인이 필요합니다.");
            return "/user/loginForm.jsp";
        }
        
        try {
            String result = groupDao.joinGroup(groupId, userId, "member");
            System.out.println("result is " + result);
            
            if (result.equals("Joined")) {
                // 성공 시 그룹페이지로 리다이렉트
                return "redirect:/home";
            } else if(result.equals("Duplicated")) {
                request.setAttribute("errorMessage", "이미 가입된 그룹입니다.");
                return "/group/previewGroup.jsp";
            } else if (result.equals("Full")) {
                // 그룹이 이미 가득 찬 경우
                request.setAttribute("errorMessage", "그룹이 이미 최대 인원에 도달했습니다.");
                return "/group/previewGroup.jsp";
            } else {
                request.setAttribute("errorMessage", "그룹 가입에 실패했습니다.");
                return "/group/previewGroup.jsp";
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "시스템 오류가 발생했습니다. 다시 시도해주세요.");
            return "/group/previewGroup.jsp";
        }
    }

}
