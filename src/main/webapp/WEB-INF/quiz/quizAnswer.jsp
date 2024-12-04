<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	//전달받은 퀴즈 ID
	String quizId = request.getParameter("quizId");

	// QuizDAO를 통해 해당 퀴즈 데이터 로드
	model.dao.QuizDAO quizDAO = new model.dao.QuizDAO();
	model.domain.Quiz quiz = quizDAO.findQuizById(quizId);

	if (quiz == null) {
    	out.println("<h2>존재하지 않는 퀴즈입니다.</h2>");
    	return;
	}
</script>
<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/quiz.css" type="text/css">
<title>퀴즈 풀기</title>
</head>
<body>
	<jsp:include page="../Header.jsp" />
	<jsp:include page="../sidebar.jsp" />
	
	<div class="main-content">
		<div class="quiz-container">
			<h1>${quiz.title}</h1>
			<p>문제: ${quiz.getQuestions().get(0).getQuestionText()}</p>
			<form action="submitAnswer.jsp" method="post">
            	<input type="hidden" name="quizId" value="${quiz.quizId}">
            	<label for="answer">답안을 입력하세요:</label>
            	<input type="text" id="answer" name="answer" required>
            	<button type="submit" class="submit-btn">제출</button>
        	</form>
        </div>
	</div>
</body>
</html>