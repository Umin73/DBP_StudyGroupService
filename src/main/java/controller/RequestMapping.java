package controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.FindIdController;
import controller.user.LoginController;
import controller.user.LogoutController;
import controller.user.SignUpController;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("/index.jsp"));
        /* mappings.put("/home", new ForwardController("/home.jsp")); */
        mappings.put("/header", new ForwardController("/Header.jsp"));
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/findId/result", new ForwardController("/user/findIdResult.jsp"));
        
        mappings.put("/home", new HomeController());
        mappings.put("/user/login", new LoginController()); 
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/signup", new SignUpController());
        mappings.put("/user/findId", new FindIdController());
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}