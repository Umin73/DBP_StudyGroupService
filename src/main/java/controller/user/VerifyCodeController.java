package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.VerifyCodeManager;

public class VerifyCodeController implements Controller{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String userId = request.getParameter("user_id");
        String email = request.getParameter("email");
        String code = request.getParameter("verifyCode");
        
        System.out.println("email: " + email);
        System.out.println("code: " + code);
        System.out.println("stored code: " + VerifyCodeManager.getStoredCode(email));
        
        if(VerifyCodeManager.verifyCode(email, code)) {
            VerifyCodeManager.invalidateCode(email);
            
            request.setAttribute("user_id", userId);
            request.setAttribute("email", email);
            request.setAttribute("verifySuccess", true);
        } else {
            request.setAttribute("user_id", userId);
            request.setAttribute("email", email);
            request.setAttribute("errorMessage", "인증번호가 올바르지 않습니다.");
            request.setAttribute("verifySuccess", false);
        }
        return "/user/findPwResult.jsp";
    }

}
