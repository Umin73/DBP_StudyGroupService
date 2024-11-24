package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.UserDAO;
import model.dao.VerifyCodeManager;
import model.service.FindPwService;

public class SendEmailController implements Controller{
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getMethod();
        if("GET".equalsIgnoreCase(method)) {
            return "/user/findPw.jsp";
        }
        
        UserDAO userDao = new UserDAO();
        FindPwService findPwService = new FindPwService();
        
        String userId = request.getParameter("user_id");
        String username = request.getParameter("username");
        
        try {
            String email = userDao.findEmailByUserIdAndUsername(userId, username);
            if(email == null) {
                request.setAttribute("errorMessage", "입력하신 정보에 해당하는 사용자가 없습니다.");
                return "/user/findPw.jsp";
            }
            
            // 인증코드 생성 및 이메일 전송
            String verificationCode = findPwService.generateRandomCode();
            VerifyCodeManager.generateCode(email, verificationCode);
            findPwService.sendEmail(email, verificationCode);
            
            // 이메일 발송 후 findPwResult로 이동
            request.setAttribute("user_id", userId);
            request.setAttribute("email", email);
            request.setAttribute("successMessage", "이메일로 인증번호가 발송되었습니다.");
            return "/user/findPwResult.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "오류가 발생했습니다. 다시 시도해주세요.");
            return "/user/findPw.jsp";
        }
    }
    
}
