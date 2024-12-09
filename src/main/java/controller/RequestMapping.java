package controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.group.CreateGroupController;
import controller.group.ViewGroupController;
import controller.quiz.CreateQuizController;
import controller.quiz.QuizController;
import controller.user.ChangePwController;
import controller.user.FindIdController;
import controller.user.LoginController;
import controller.user.LogoutController;
import controller.user.SendEmailController;
import controller.user.SignUpController;
import controller.user.VerifyCodeController;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
        // 각 uri에 대응되는 controller 객체를 생성 및 저장
        
        // 기본 경로 및 공통
        mappings.put("/", new ForwardController("/index.jsp"));
        mappings.put("/header", new ForwardController("/Header.jsp"));
        mappings.put("/sidebar", new ForwardController("/sidebar.jsp"));
        mappings.put("/home", new HomeController());
        
        // 회원가입
        mappings.put("/user/signup", new SignUpController());
        
        // 로그인 및 로그아웃
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController()); 
        mappings.put("/user/logout", new LogoutController());
        
        // 아이디 찾기
        mappings.put("/user/findId", new FindIdController());
        mappings.put("/user/findId/result", new ForwardController("/user/findIdResult.jsp"));
       
        // 비밀번호 찾기
        mappings.put("/user/findPw", new SendEmailController());
        mappings.put("/user/verifyCode", new VerifyCodeController());
        mappings.put("/user/changePw", new ChangePwController());
    
        // 그룹 생성
        mappings.put("/group/create", new ForwardController("/group/createGroup.jsp"));
        mappings.put("/group/create/process", new CreateGroupController());
        
        // 그룹 리스트 조회
        mappings.put("/group/viewAll", new ViewGroupController());
        
        // 참여중인 그룹 상세페이지
        mappings.put("/user/myGroup", new ForwardController("/user/myGroup.jsp"));
        
        // 퀴즈 리스트 조회
        mappings.put("/quiz/list", new QuizController());
        
        // 퀴즈 생성
        mappings.put("/quiz/create", new ForwardController("/quiz/createQuiz.jsp"));
        mappings.put("/quiz/create/process", new CreateQuizController());
        
        // 퀴즈 풀기
        //mappings.put("/quiz/answer", new QuizController());
        mappings.put("/quiz/answer", new ForwardController("/quiz/quizAnswer.jsp"));
    }

    public Controller findController(String uri) {  
        // 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}