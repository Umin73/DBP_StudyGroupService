package controller.quiz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.dao.QuizDAO;
import model.domain.Quiz;

public class QuizAnswerController implements Controller {
    private QuizDAO quizDAO = new QuizDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String quizId = request.getParameter("quizId");
        Quiz quiz = quizDAO.findQuizById(quizId);

        if (quiz == null) {
            request.setAttribute("errorMessage", "퀴즈를 찾을 수 없습니다.");
            return "/quiz/quizMain.jsp";
        }

        // 퀴즈와 관련된 답안을 가져오는 로직 추가 필요
        request.setAttribute("quiz", quiz);
        request.setAttribute("answer", quizDAO.findAnswerByQuizId(quizId)); // 예시
        return "/quiz/quizAnswer.jsp";
    }
}
