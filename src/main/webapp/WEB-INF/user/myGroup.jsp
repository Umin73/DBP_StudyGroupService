<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>참여중인 스터디</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/mygroup.css" type="text/css">
</head>
<body>
	<jsp:include page="../Header.jsp" />
	<jsp:include page="../sidebar.jsp" />
	
	<div class="main-content">
		<div class="study-menu">
			<button onclick="location.href='schedule.jsp'">스터디 일정 관리</button>
			<button onclick="location.href='memberManage.jsp'">멤버 관리</button>
			<button onclick="location.href='memberList.jsp'">멤버 목록</button>
			<button onclick="location.href='assignmentList.jsp'">과제 목록</button>
			<button onclick="location.href='${pageContext.request.contextPath}/quiz/list'">퀴즈</button>
		</div>
	</div>
</body>
</html>