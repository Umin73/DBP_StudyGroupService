package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.QuizDAO;
import model.domain.Quiz;
import model.domain.User;
import java.util.UUID;

public class QuizController implements Controller {

    private QuizDAO quizDAO = new QuizDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // GET 요청: 퀴즈 목록 조회
        if (request.getMethod().equalsIgnoreCase("GET")) {
            request.setAttribute("quizList", quizDAO.findAllQuizzes());
            return "/quiz/quizMain.jsp";
        }

        // POST 요청: 퀴즈 생성
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/user/login/form";
        }

        // 폼 데이터 가져오기
        String title = request.getParameter("title");
        String groupId = request.getParameter("groupId");    
        String section = request.getParameter("section");
        String questionId = request.getParameter("questionId");

        /*
        // 유효성 검사
        if (title == null || title.isEmpty() || groupId == null || groupId.isEmpty() ||
            section == null || section.isEmpty() || questionId == null || questionId.isEmpty()) {
            request.setAttribute("errorMessage", "...");
            return "/quiz/createQuiz.jsp";
        }
        */

        // 새로운 퀴즈 생성
        Quiz quiz = new Quiz(
                UUID.randomUUID().toString(), // 고유 ID 생성
                title,
                groupId,
                section,
                0.0, // 정답률 기본값
                0,   // 제출자 수 기본값
                "N", // 제출 여부 기본값
                questionId,
                loginUser
        );
        
        // DAO를 통해 저장
        boolean result = quizDAO.createQuiz(quiz) > 0;

        if (result) {
            return "redirect:/quiz/list";
        } else {
            request.setAttribute("error", "퀴즈 생성에 실패했습니다. 다시 시도해주세요.");
            return "/quiz/createQuiz.jsp";
        }
    }
}
