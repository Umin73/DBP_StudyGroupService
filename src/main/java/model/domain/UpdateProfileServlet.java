package model.domain;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션에서 로그인된 사용자 ID 가져오기
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.getWriter().write("로그인이 필요합니다.");
            response.sendRedirect(request.getContextPath() + "/loginForm.jsp");
            return;
        }

        String userId = (String) session.getAttribute("userId");
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        // 유효성 검사
        if (username == null || username.trim().isEmpty()) {
            response.getWriter().write("사용자 이름을 입력해주세요.");
            return;
        }

        if (email == null || email.trim().isEmpty()) {
            response.getWriter().write("이메일을 입력해주세요.");
            return;
        }

        if (phone == null || phone.trim().isEmpty()) {
            response.getWriter().write("전화번호를 입력해주세요.");
            return;
        }

        UserDAO userDAO = new UserDAO();
        try {
            User existingUser = userDAO.findUser(userId, true);
            if (existingUser == null) {
                response.getWriter().write("사용자를 찾을 수 없습니다.");
                return;
            }

            // 기존 비밀번호 유지
            String password = existingUser.getPassword();

            // User 객체 생성
            User updatedUser = new User(userId, password, username, email, phone);

            // 업데이트 수행
            int result = userDAO.updateUser(updatedUser);
            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/mypage.jsp");
            } else {
                response.getWriter().write("업데이트 실패. 다시 시도해주세요.");
            }
        } catch (SQLException e) {
            response.getWriter().write("데이터베이스 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }
}
