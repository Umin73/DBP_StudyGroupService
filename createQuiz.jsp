<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.domain.User" %>
<script>
	function validateForm(event) {
		const form = document.querySelector("form");
		const quizTitle = document.getElementById("quizTitle").value.trim();
		const round = document.getElementById("round").value.trim();
		const type = document.getElementById("type").value.trim();
		const content = document.getElementById("content").value.trim();
		const answer = document.getElementById("answer").value.trim();
	
		if (!quizTitle || !round || !type || !content || !answer) {
			alert("모든 필드를 입력해주세요.");
			event.preventDefault();
			return false;
		}
		
		form.submit();
	}
	
	function checkLogin() {
		alert("로그인이 필요합니다.");
		window.location.href = "${pageContext.request.contextPath}/user/login/form";
	}
	
	function cancelQuiz() {
		if (confirm("퀴즈 생성을 취소하시겠습니까?")) {
			window.location.href = "${pageContext.request.contextPath}/quiz/list";
		}
	}
</script>
<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/quiz.css" type="text/css">
<title>퀴즈 생성</title>
</head>
<body>
<%
	User loginUser = (User) session.getAttribute("loginUser");
	String username = (loginUser != null) ? loginUser.getUsername() : "로그인이 필요합니다.";
	if (loginUser == null) {
	    %>
	    	    <script>
	    	    	checkLogin();
	    	    </script>
	    <%
	}
	String errorMessage = (String) request.getAttribute("errorMessage");
	if (errorMessage != null) {
%>
	<script>
		alert("<%= errorMessage %>");
	</script>
<%
	}
%>
	<jsp:include page="../Header.jsp" />
	<jsp:include page="../sidebar.jsp" />
	<div class="main-content">
		<form id="createQuizForm" action="${pageContext.request.contextPath}/quiz/list" method="post">
			<div class="quiz-create-container">
				<div class="quiz-creator-name">
					<span><%= username %></span>
				</div>
				<div class="quiz-fields">
					<label for="quizTitle">퀴즈 제목</label>
					<input type="text" id="quizTitle" name="quizTitle" placeholder="퀴즈 제목을 입력하세요">
					
					<label for="round">회차 선택</label>
					<select id="round" name="round">
						<option value="" disabled selected>회차 선택</option>
						<option value="1">1회차</option>
						<option value="2">2회차</option>
						<option value="other">기타</option>
					</select>
					
					<label for="type">유형 선택</label>
					<select id ="type" name="type">
						<option value="" disabled selected>유형 선택</option>
						<option value="multiple-choice">객관식</option>
						<option value="short-answer">주관식</option>
					</select>
					
					<label for="content">내용</label>
					<textarea id="content" name="content" placeholder="퀴즈 내용을 입력하세요"></textarea>
					
					<label for="answer">정답</label>
					<input type="text" id="answer" name="answer" placeholder="정답을 입력하세요">
				</div>
				
				<div class="quiz-buttons">
					<button type="button" onclick="cancelQuiz()">취소</button>
					<button type="submit" onclick="validateForm()">완료</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>