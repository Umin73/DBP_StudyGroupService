package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.UserDAO;

public class ChangePwController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String user_id = request.getParameter("user_id");
        String newPw = request.getParameter("newPassword");
        
        System.out.println("user_id: " + user_id);
        System.out.println("newPassword: " + newPw);
        
        UserDAO userDao = new UserDAO();
        try {
            boolean result = userDao.updatePassword(user_id, newPw);
            
            if(result) {
                request.setAttribute("successMessage", "비밀번호가 성공적으로 변경되었습니다.");
                return "/user/loginForm.jsp";
            } else {
                request.setAttribute("errorMessage", "비밀번호 변경에 실패했습니다.");
                return "/user/findPwResult.jsp";
            } 
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "오류가 발생했습니다. 다시 시도해주세요.");
            return "/user/findPwResult.jsp";
        }
    }

    
}
