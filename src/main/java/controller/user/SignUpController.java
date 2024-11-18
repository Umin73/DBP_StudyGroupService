package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.domain.User;
import model.service.ExistingUserException;
import model.service.UserManager;

public class SignUpController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(SignUpController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        UserManager userMan = UserManager.getInstance();
        
        // GET: 회원가입 폼 요청
        if(request.getMethod().equals("GET")) {
            return "/user/signUp.jsp";
        }
        
        // POST: 회원가입 수행
        User user = new User(
                request.getParameter("userId"),
                request.getParameter("password"),
                request.getParameter("username"),
                request.getParameter("email"),
                request.getParameter("phone"));
        
        try {
            userMan.create(user);
            return "redirect:/user/login/form";
        } catch(ExistingUserException e) {
            request.setAttribute("registerFailed", true);
            request.setAttribute("exception", e);
            request.setAttribute("user", user);
            return "/user/signUp.jsp";
        }
    }
}

