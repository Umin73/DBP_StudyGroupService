package controller.group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.domain.Notice;
import model.service.NoticeManager;

public class CreateNoticeController implements Controller{
    private static final Logger log = LoggerFactory.getLogger(CreateNoticeController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    		 Notice notice = new Notice(
    				 null, request.getParameter("title"),
    				 request.getParameter("content"),
    				 null, null, null);		
        
		try {
			NoticeManager manager = NoticeManager.getInstance();
			manager.createNotice(notice);
			
	    	log.debug("Create Notice : {}", notice);
	        return "redirect:/notice/view";	// 성공 시 뷰 화면으로 redirect
	        
		} catch (Exception e) {		// 예외 발생 시 입력화면 forwarding
            request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("notice", notice);
			return "/notice/create.jsp";
		}
    }

}
