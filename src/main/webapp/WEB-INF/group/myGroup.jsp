<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Group</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myGroup.css" type="text/css">
    <script>
        function showCategory(category) {
            const categories = document.querySelectorAll('.category-content');
            categories.forEach((content) => {
                content.style.display = 'none';
            });
            document.getElementById(category).style.display = 'block';
        }

        document.addEventListener('DOMContentLoaded', () => {
            showCategory('schedule'); // 기본으로 스터디 일정 표시
        });
        
        function navigateToPage(targetUri) {
            const form = document.createElement('form');
            form.action = targetUri;
            form.method = "GET";
            document.body.appendChild(form);
            form.submit();
        }
    </script>
</head>
<body>
	<jsp:include page="../Header.jsp" />
	<jsp:include page="../sidebar.jsp" />
    <div class="container">
        <!-- 상단 카테고리 탭 -->
        <div class="tabs">
            <button onclick="showCategory('schedule')">스터디 일정</button>
            <button onclick="navigateToPage('<c:url value="/notice/list"/>')">공지사항</button>
            <button onclick="showCategory('memebr')">멤버</button>
            <button onclick="navigateToPage('<c:url value="/assignment/list"/>')">과제</button>
            <button onclick="navigateToPage('<c:url value="/quiz/main"/>')">퀴즈</button>
        </div>
        
        <div id="schedule" class="category-content">
            <h2>스터디 일정</h2>
            <ul>
                <c:forEach var="item" items="${scheduleSummary}">
                    <li>${item}</li>
                </c:forEach>
            </ul>
        </div>
        <div id="notice" class="category-content" style="display: none;">
            <h2>공지사항</h2>
            <ul>
                <c:forEach var="item" items="${noticeSummary}">
                    <li>${item}</li>
                </c:forEach>
            </ul>
        </div>
        <div id="memebr" class="category-content" style="display: none;">
            <h2>멤버</h2>
            <jsp:include page="viewMemberList.jsp" />
        </div>
        <div id="assignment" class="category-content" style="display: none;">
            <h2>과제</h2>
            <ul>
                <c:forEach var="item" items="${assignmentSummary}">
                    <li>${item}</li>
                </c:forEach>
            </ul>
        </div>
        <div id="quiz" class="category-content" style="display: none;">
            <ul>
                <c:forEach var="item" items="${quizSummary}">
                    <li>${item}</li>
                </c:forEach>
            </ul>
        </div>
    </div>
</body>
</html>
