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
    function navigateToPage(targetUri, groupId) {

        const form = document.createElement('form');
        form.action = targetUri;
        form.method = "POST";
        
        const input = document.createElement('input');
        input.type = "hidden";
        input.name = "groupId";
        input.value = groupId;
        form.appendChild(input);
        
        document.body.appendChild(form);
        form.submit();

    }

    </script>
    
	<style>
	.table-container {
	    max-height: 500px; /* 최대 높이 설정 */
	    overflow-y: auto; /* 슬라이드 바 생성 */
	}
	
	.member-table {
	    width: 100%;
	    border-collapse: collapse;
	}
	
	.member-table th, .member-table td {
	    padding: 10px;
	    text-align: center;
	    border: 1px solid #ddd;
	}
	
	.member-table th {
	    background-color: #f2f2f2;
	}
	
	.btn {
	    padding: 5px 10px;
	    border: none;
	    cursor: pointer;
	    border-radius: 5px;
	}
	
	.btn-leader {
	    background-color: #28a745;
	    color: white;
	}
	
	.btn-kick {
	    background-color: #dc3545;
	    color: white;
	}
	</style>
</head>
<body>
	<jsp:include page="../Header.jsp" />
	<jsp:include page="../sidebar.jsp" />
    <div class="container">
        <!-- 상단 카테고리 탭 -->
        <div class="tabs">
            <button onclick="showCategory('schedule')">스터디 일정</button>
            <button onclick="navigateToPage('<c:url value="/notice/list"/>')">공지사항</button>
            <button onclick="navigateToPage('<c:url value="/group/memberList"/>', '${groupId}')">멤버</button>
            <button onclick="navigateToPage('<c:url value="/assignment/list"/>')">과제</button>
            <button onclick="navigateToPage('<c:url value="/quiz/main"/>')">퀴즈</button>
        </div>

        <div id="member" class="category-content">
            <h2>멤버</h2>
				<div class="table-container">
				    <table class="member-table">
				        <thead>
				            <tr>
				                <th>역할</th>
				                <th>이름</th>
				                <th>출석률</th>
				                <th>퀴즈 제출률</th>
				                <th>과제 제출률</th>
				            </tr>
				        </thead>
				        <tbody>
				            <c:forEach var="member" items="${groupMember}">
				                <tr>
				                    <td>${member.role}</td>
				                    <td>${member.user.username}</td>
				                    <td>${member.attendanceRate}%</td>
				                    <td>${member.quizRate}%</td>
				                    <td>${member.assignRate }%</td>
				                </tr>
				            </c:forEach>
				        </tbody>
				    </table>
				</div>
        </div>

    </div>
</body>
</html>