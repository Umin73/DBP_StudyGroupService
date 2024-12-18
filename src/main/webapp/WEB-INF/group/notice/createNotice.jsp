<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NOTICE FORM</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/notice.css" type="text/css">
</head>
<body>
	<jsp:include page="../../Header.jsp" />
	<jsp:include page="../../sidebar.jsp" />
    <div class="container">
        <h1>공지사항 작성</h1>
        <form action="submitNotice.do" method="post">
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" id="title" name="title" placeholder="제목을 입력하세요." required>
            </div>
            <div class="form-group">
                <label for="content">내용</label>
                <textarea id="content" name="content" rows="10" placeholder="내용을 입력하세요." required></textarea>
            </div>
            <div class="button-group">
                <button type="submit" class="button">작성 완료</button>
                <a href="viewNoticeList.jsp" class="button">목록으로</a>
            </div>
        </form>
    </div>
</body>
</html>
