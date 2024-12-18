<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>과제 상세 보기</title>
    <link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/assignment.css" type="text/css">
</head>
<body>
	<jsp:include page="../../Header.jsp" />
	<jsp:include page="../../sidebar.jsp" />
    <div class="container">
        <h1>과제 상세 정보</h1>
        
        <!-- 과제 상세 정보 -->
        <div class="assignment-detail">
            <p><strong>제목:</strong> ${assignment.title}</p>
            <p><strong>작성일:</strong> ${assignment.createdDate}</p>
            <p><strong>마감기한:</strong> ${assignment.dueDate}</p>
            <p><strong>제출 상태:</strong> ${assignment.isSubmitted ? "제출 완료" : "미제출"}</p>
            <hr>
            <p class="content"><strong>설명:</strong></p>
            <p class="content">${assignment.description}</p>
            <hr>
            <p class="content"><strong>내용:</strong></p>
            <p class="content">${assignment.content}</p>
        </div>

        <!-- 버튼 그룹 -->
        <div class="button-group">
            <c:if test="${!assignment.isSubmitted}">
                <!-- 과제 제출 버튼 -->
                <form action="submitAssignment.do" method="post" class="inline-form">
                    <input type="hidden" name="assignmentId" value="${assignment.id}">
                    <button type="submit" class="button">과제 제출</button>
                </form>
            </c:if>
            <a href="'<c:url value="/assignment/list"/>'" class="button">목록으로</a>
        </div>
    </div>
</body>
</html>
