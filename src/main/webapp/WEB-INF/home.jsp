<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/home.css" type="text/css">
<title>HOME</title>
</head>
<body>
	<jsp:include page="Header.jsp" />
	<jsp:include page="sideBar.jsp" />
	<div class="main-content">
	
		<!-- 광고 -->
    	<section class="main-ad">
    		<img src="img/dbp_mainpage_ad1.png" class="ad-img">
    	</section>
    	
    	<div class="study-container">
            <!-- 참여 중인 스터디 -->
            <section class="active-study">
                <h2>참여 중인 스터디</h2>
                <div class="study-box">
                    <div class="study-item">
                        <div class="study-info">스터디 그룹명</div>
                        <div class="tasks">과제 1</div>
                        <div class="tasks">과제 2</div>
                        <div class="quiz">퀴즈 1</div>
                        <div class="quiz">퀴즈 2</div>
                    </div>
                </div>
                
                <div class="study-box">
                   	<div class="study-item">
                        <div class="study-info">스터디 그룹명</div>
                        <div class="tasks">과제 1</div>
                        <div class="tasks">과제 2</div>
                        <div class="quiz">퀴즈 1</div>
                    </div>
                </div>
            </section>

            <!-- 새로운 스터디 -->
            <section class="new-study">
                <h2>새로운 스터디</h2>
                <div class="study-box">
                    <div class="study-item">
                        <div class="study-info">Spring 김영한 강의 스터디</div>
                        <div class="details">강의 듣고 리뷰하면서 스터디 하실 분 구해요~~</div>
                    </div>
                    <div class="study-item">
                        <div class="study-info">코테 스터디</div>
                        <div class="details">백준 플레 수준정도까지 코테스터디 하실분 (백준 골드5 이상)</div>
                    </div>
                    <div class="study-item">
                    	<div class="study-info">우테코 준비 스터디</div>
                    	<div class="details">우테코우테코</div>
                    </div>
                    <div class="study-item">
                    	<div class="study-info">무슨무슨 면접스터디</div>
                    	<div class="details">면접면접면접~~~~~~~~~~</div>
                    </div>
                </div>
            </section>
        </div>
	</div>
</body>
</html>