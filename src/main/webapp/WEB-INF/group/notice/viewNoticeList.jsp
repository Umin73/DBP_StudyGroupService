<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>NOTICE LIST</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/notice.css" type="text/css">
	<script>
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
	<jsp:include page="../../Header.jsp" />
	<jsp:include page="../../sidebar.jsp" />
    <div class="container">
        <h1>공지사항</h1>
         <div class="right">
            <button class="button" onclick="navigateToPage('<c:url value="/notice/create"/>')">작성하기</button>
        </div>
        <table class="notice-table">
            <thead>
                <tr>
                    <th>순번</th>
                    <th>제목</th>
                    <th>작성일</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="notice" items="${noticeList}">
                    <tr>
                        <td>${notice.id}</td>
                        <td>
                            <a href="viewNotice.jsp?id=${notice.id}">
                                ${notice.title}
                            </a>
                        </td>
                        <td>${notice.date}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
