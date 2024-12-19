package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.user.UserSessionUtils;

public class ForwardController implements Controller {
    private String forwardUrl;

    public ForwardController(String forwardUrl) {
        if (forwardUrl == null) {
            throw new NullPointerException("forwardUrl is null. 이동할 URL을 입력하세요.");
        }
        this.forwardUrl = "/WEB-INF" + forwardUrl;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        // 헤더의 로그인/로그아웃 버튼용
        HttpSession session = req.getSession();
        boolean isLoggedIn = UserSessionUtils.hasLogined(session);
        req.setAttribute("isLoggedIn", isLoggedIn);
        
        return forwardUrl;
    }
}
