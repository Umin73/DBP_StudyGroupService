package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@WebServlet(name="dispatcherSevlet", urlPatterns="/", loadOnStartup=1)
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    private RequestMapping rm;

    @Override
    public void init() throws ServletException {
        rm = new RequestMapping();
        rm.initMapping();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        logger.debug("Method : {}, Request URI : {}, ServletPath : {}", 
                request.getMethod(), request.getRequestURI(), request.getServletPath());

        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();

        // URL 중 servletPath에 대응되는 controller를 구함
        Controller controller = rm.findController(servletPath);

        if (controller == null) {
            logger.error("No controller found for URI: {}", servletPath);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No controller found for URI: " + servletPath);
            return; // 더 이상 진행하지 않음
        }

        try {
            // controller를 통해 request 처리 후, 이동할 uri를 반환받음
            String uri = controller.execute(request, response);

            if (uri == null) return; // Ajax 요청이 완료된 경우

            // 반환된 URI 처리
            logger.debug("Controller executed. Returning URI: {}", uri);

            if (uri.startsWith("redirect:")) {
                // redirection 지시
                String targetUri = contextPath + uri.substring("redirect:".length());
                response.sendRedirect(targetUri); // redirect to url
            } else if (uri.startsWith("/WEB-INF")) {
                // 이미 완전한 경로인 경우, 포워딩을 바로 실행
                RequestDispatcher rd = request.getRequestDispatcher(uri);
                rd.forward(request, response); // forward to the view page
            } else {
                // 상대 경로일 경우 /WEB-INF를 추가하여 포워딩
                String targetUri = "/WEB-INF" + uri;
                RequestDispatcher rd = request.getRequestDispatcher(targetUri);
                rd.forward(request, response); // forward to the view page
            }
        } catch (Exception e) {
            logger.error("Exception : {}", e);
            throw new ServletException(e.getMessage());
        }
    }
}
