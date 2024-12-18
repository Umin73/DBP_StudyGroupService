package controller.group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.StudyGroupDAO;

public class JoinGroupController implements Controller{

    private StudyGroupDAO groupDao = new StudyGroupDAO();
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String groupId = request.getParameter("groupId");
        String userId = (String) request.getSession().getAttribute("userId");
        
        if (userId == null) {
            request.setAttribute("error", "로그인이 필요합니다.");
            return "/user/loginForm.jsp";
        }
        
        try {
            String result = groupDao.joinGroup(groupId, userId);
            System.out.println("result is " + result);
            
            if (result.equals("Joined")) {
                // 성공 시 그룹페이지로 리다이렉트
                request.setAttribute("success", "그룹에 성공적으로 가입되었습니다.");
                request.setAttribute("groupId", groupId);
                return "/group/groupPage.jsp";
            } else if (result.equals("Full")) {
                // 그룹이 이미 가득 찬 경우
                request.setAttribute("error", "그룹이 이미 최대 인원에 도달했습니다.");
                return "/group/previewGroup.jsp"; // 그룹 상세 페이지로 이동
            } else {
                request.setAttribute("error", "그룹 가입에 실패했습니다.");
                return "/group/previewGroup.jsp";
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "시스템 오류가 발생했습니다. 다시 시도해주세요.");
            return "/group/previewGroup.jsp";
        }
    }

}
