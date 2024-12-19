package controller.quiz;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.domain.Quiz;
import model.domain.User;
import model.service.QuizManager;

public class CreateQuizController implements Controller {
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // 헤더의 로그인/로그아웃 버튼용
        HttpSession session = request.getSession();
        boolean isLoggedIn = UserSessionUtils.hasLogined(session);
        request.setAttribute("isLoggedIn", isLoggedIn);

        try {
            Random random = new Random();
            String quizId = String.format("%018d", Math.abs(random.nextLong() % 1000000000000000000L));
            String quizTitle = request.getParameter("quizTitle");
            String section = request.getParameter("section");
            String type = request.getParameter("type");
            String content = request.getParameter("content");
            String answer = request.getParameter("answer");
            
            // 퀴즈 생성 시 입력하진 않지만 필요한 파라미터들..
            String groupId = request.getParameter("groupId");
            //String questionId = request.getParameter("questionId");
            
            session = request.getSession();
            User loginUser = (User) session.getAttribute("loginUser");
            
            // 그룹아이디 없을때.. 일단 테스트용으로 만듦
            if (groupId == null || groupId.isEmpty()) {
                groupId = "3d085fa1c83a41b9a19cda7a41cb19d7";
            }
            
            User creator = (loginUser != null) ? loginUser : new User("test1", "1234", "name1", "email@gmail.com", "01012345678");
            

            // Quiz 객체 생성
            Quiz quiz = new Quiz(
                    quizId, // 고유 ID 생성
                    quizTitle,
                    groupId,
                    section,
                    0.0, // 정답률 기본값
                    0,   // 제출자 수 기본값
                    "N", // 제출 여부 기본값
                    //questionId,
                    //loginUser
                    creator
            );
            
            // QuizManager 인스턴스를 통해 그룹 생성
            QuizManager quizMan = QuizManager.getInstance();
            quizMan.create(quiz);
            
            // 성공 
            request.setAttribute("successMessage", "퀴즈가 생성되었습니다.");
            return "redirect:/quiz/list";
        } catch (Exception e) {
            request.setAttribute("errorMessage", "예기치 않은 오류가 발생했습니다. 다시 시도해주세요.");
            return "/quiz/createQuiz.jsp";
        }
        
    }
}