<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>NOTICE</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/notice.css" type="text/css">
</head>
<body>
	<jsp:include page="../../Header.jsp" />
	<jsp:include page="../../sidebar.jsp" />
    <div class="container">
        <h1>공지사항</h1>
        <div class="notice-detail">
            <p><strong>제목:</strong> ${notice.title}</p>
            <p><strong>작성일:</strong> ${notice.date}</p>
            <hr>
            <div class="content">
                ${notice.content}
            </div>
        </div>

        <div class="comments-section">
            <h2>댓글</h2>
            <c:if test="${not empty comments}">
                <ul class="comments-list">
                    <c:forEach var="comment" items="${comments}">
                        <li>
                            <p><strong>${comment.writer}</strong> (${comment.date})</p>
                            <p>${comment.content}</p>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
            <c:if test="${empty comments}">
                <p>아직 댓글이 없습니다.</p>
            </c:if>
        </div>

        <div class="comment-form">
            <h3>댓글 작성</h3>
            <form action="addComment.do" method="post">
                <input type="hidden" name="noticeId" value="${notice.id}">
                <textarea name="content" rows="4" placeholder="댓글을 입력하세요." required></textarea>
                <button type="submit" class="button">댓글 작성</button>
            </form>
        </div>

        <div class="button-group">
            <a href="viewNoticeList.jsp" class="button">목록으로</a>
        </div>
    </div>
</body>
</html>
