<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	function navigateToCreateQuiz(targetUri) {
		const form = document.createElement("form");
		form.action = targetUri;
		form.method = "GET";
		document.body.appendChild(form);
		form.submit();
	}
</script>
<html>
<head>
<meta charset="UTF-8">
<title>퀴즈</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/quiz.css" type="text/css">
</head>
<body>
	<jsp:include page="../Header.jsp" />
	<jsp:include page="../sidebar.jsp" />
	
	<div class="main-content">
    	<div class="quiz-main">
        	<div class="quiz-round-dropdown">
            	<label for="rounds">회차 선택:</label>
            	<select id="rounds">
                	<%-- 회차 정보를 서버에서 동적으로 로드 --%>
                	<c:forEach var="round" items="${rounds}">
                    	<option value="${round}">${round}회차</option>
                	</c:forEach>
            	</select>
        	</div>

        	<div class="create-quiz-button">
            	<button onclick="navigateToCreateQuiz('<c:url value='/quiz/create' />')">퀴즈 생성</button>
        	</div>

        	<div class="quiz-list-container">
            	<div class="quiz-header">
                	<div>회차</div>
                	<div>문제 제목</div>
                	<div>출제자</div>
                	<div>푼 사람</div>
                	<div>정답 비율</div>
                	<div>제출</div>
            	</div>
            	
            	<c:forEach var="quiz" items="${quizList}">
                	<div class="quiz-item">
                    	<div>${quiz.round}</div>
                    	<div>${quiz.title}</div>
                    	<div>${quiz.creator}</div>
                    	<div>${quiz.participants}</div>
                    	<div>${quiz.correctRate}%</div>
                    	<div>${quiz.submitted ? 'O' : 'X'}</div>
                	</div>
            	</c:forEach>
        	</div>
    	</div>
	</div>
</body>
</html>
