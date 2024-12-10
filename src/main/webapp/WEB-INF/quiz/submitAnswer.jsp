<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	// 전달받은 퀴즈 ID와 사용자 답안
	String quizId = request.getParameter("quizId");
	String userAnswer = request.getParameter("answer");
	
	if (quizId == null || userAnswer == null || quizId.isEmpty() || userAnswer.isEmpty()) {
        // 필수 파라미터가 없을 경우
		request.setAttribute("error", "유효하지 않은 요청입니다.");
        response.sendRedirect("/quiz/list");
        return;
        
        /* response.sendRedirect("quizMain.jsp?error=missing_parameters");
        return; */
    }

	// 데이터베이스에서 정답 확인
	boolean isCorrect = QuizDAO.checkAnswer(quizId, userAnswer);
	
	// 제출 상태와 정답 비율 업데이트
	QuizDAO.updateQuizStatus(quizId, isCorrect);

	// 퀴즈 목록으로 리다이렉트
	response.sendRedirect("quizMain.jsp");
</script>