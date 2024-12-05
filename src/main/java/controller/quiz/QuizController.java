package controller.quiz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
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
            request.setAttribute("quizList", quizDAO.findQuizList());
            return "/quiz/quizMain.jsp";
        }

        // GET 요청: 특정 퀴즈 조회 및 퀴즈 풀이 페이지로 이동
        if (request.getMethod().equalsIgnoreCase("GET") && request.getParameter("quizId") != null) {
            String quizId = request.getParameter("quizId");
            Quiz quiz = quizDAO.findQuizById(quizId);
            if (quiz == null) {
                request.setAttribute("error", "존재하지 않는 퀴즈입니다.");
                return "/quiz/quizMain.jsp";
            }
            request.setAttribute("quiz", quiz);
            return "/quiz/quizAnswer.jsp";
        }

        return "redirect:/quiz/list";
        
        /*
         * // POST 요청: 퀴즈 생성 HttpSession session = request.getSession(); User loginUser
         * = (User) session.getAttribute("loginUser");
         * 
         * //if (loginUser == null) { return "redirect:/user/login/form"; }
         * 
         * // 폼 데이터 가져오기 String title = request.getParameter("title"); String groupId =
         * request.getParameter("groupId"); String section =
         * request.getParameter("section"); String questionId =
         * request.getParameter("questionId");
         * 
         * 
         * // 유효성 검사 if (title == null || title.isEmpty() || groupId == null ||
         * groupId.isEmpty() || section == null || section.isEmpty() || questionId ==
         * null || questionId.isEmpty()) { request.setAttribute("errorMessage", "...");
         * return "/quiz/createQuiz.jsp"; }
         * 
         * 
         * // 그룹아이디 없을때.. 일단 테스트용으로 만듦 if (groupId == null || groupId.isEmpty()) {
         * groupId = "default"; }
         * 
         * User creator = (loginUser != null) ? loginUser : new User("guest", null,
         * "Guest User", null, null);
         * 
         * 
         * // 새로운 퀴즈 생성 Quiz quiz = new Quiz( UUID.randomUUID().toString(), // 고유 ID 생성
         * title, groupId, section, 0.0, // 정답률 기본값 0, // 제출자 수 기본값 "N", // 제출 여부 기본값
         * questionId, //loginUser creator );
         * 
         * // DAO를 통해 저장 boolean result = quizDAO.createQuiz(quiz) > 0;
         * 
         * if (result) { return "redirect:/quiz/list"; } else {
         * request.setAttribute("error", "퀴즈 생성에 실패했습니다. 다시 시도해주세요."); return
         * "/quiz/createQuiz.jsp"; }
         */
    }
}
