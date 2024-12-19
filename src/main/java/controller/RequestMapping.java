package controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.group.CreateAssignmentController;
import controller.group.CreateGroupController;
import controller.group.JoinGroupController;
import controller.group.PreviewGroupController;
import controller.group.ViewGroupController;
import controller.group.ViewMyGroupListController;
import controller.quiz.CreateQuizController;
import controller.quiz.QuizAnswerController;
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
        
        // /mypage 
        mappings.put("/user/myPage", new ForwardController("/user/myPage.jsp"));
        
        // 그룹 생성
        mappings.put("/group/create", new ForwardController("/group/createGroup.jsp"));
        mappings.put("/group/create/process", new CreateGroupController());
        
        // 그룹 리스트 조회
        mappings.put("/group/viewAll", new ViewGroupController());
        
        // 사용자가 가입한 그룹 리스트 조회
        mappings.put("/group/viewMyAllGroup", new ViewMyGroupListController());
        
        //그룹 정보 조회
        mappings.put("/group/showGroup", new ForwardController("/group/showGroup.jsp"));
        mappings.put("/group/myGroup", new ForwardController("/group/myGroup.jsp"));
     
        mappings.put("/notice/list", new ForwardController("/group/notice/viewNoticeList.jsp"));
        mappings.put("/notice/view", new ForwardController("/group/notice/viewNotice.jsp"));
        mappings.put("/notice/create", new ForwardController("/group/notice/createNotice.jsp"));
        
        mappings.put("/assignment/list", new ForwardController("/group/assignment/viewAssignmentList.jsp"));
        mappings.put("/assignment/view", new ForwardController("/group/assignment/viewAssignment.jsp"));
        mappings.put("/assignment/create", new ForwardController("/group/assignment/createAssignment.jsp"));
        mappings.put("/assignment/create/form", new CreateAssignmentController());
        
        //퀴즈
        mappings.put("/quiz/main", new ForwardController("/quiz/quizMain.jsp"));
      
        // 그룹 미리보기
        mappings.put("/group/preview", new PreviewGroupController());        
        
        // 그룹 가입하기
        mappings.put("/group/join", new JoinGroupController());
        
        // 그룹 홈
        mappings.put("/group/home", new ForwardController("/group/groupPage.jsp"));
        
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
    
        // 퀴즈 답안 보기
        mappings.put("/quiz/viewAnswer", new QuizAnswerController());
    }

    public Controller findController(String uri) {  
        // 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}