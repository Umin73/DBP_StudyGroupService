package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserManager;

public class FindIdController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String method = request.getMethod();
        if("GET".equalsIgnoreCase(method)) {
            return "/user/findId.jsp";
        }
        
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");    
            
        try {
            UserManager userMan = UserManager.getInstance();
            
            String userId = userMan.findUserId(username, phone);
            
            request.setAttribute("userId", userId);
            request.setAttribute("success", true);
         
        } catch(Exception e) {
            request.setAttribute("errorMessage", "입력한 정보와 일치하는 아이디를 찾을 수 없습니다.");
            request.setAttribute("success", false);
        }
        
        return "/user/findIdResult.jsp";
    }

}
