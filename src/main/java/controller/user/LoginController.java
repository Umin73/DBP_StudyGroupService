package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.UserManager;

public class LoginController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        
        try {
            UserManager userMan = UserManager.getInstance();
            userMan.login(userId, password);
            
            HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);

            return "redirect:/home";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("loginFailed", true);
            request.setAttribute("exception", e);
            
            return "/user/loginForm.jsp";
        }
    }

}
