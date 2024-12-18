<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ASSIGNMENT LIST</title>
    <link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/assignment.css" type="text/css">
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
        <h1>과제</h1>
        
        <!-- 생성하기 버튼 -->
        <div class="right">
            <button class="button" onclick="navigateToPage('<c:url value="/assignment/create"/>')">생성하기</button>
        </div>

        <!-- 과제 리스트 테이블 -->
        <table class="assignment-table">
            <thead>
                <tr>
                    <th>순번</th>
                    <th>제목</th>
                    <th>작성일</th>
                    <th>마감기한</th>
                    <th>제출여부</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="assignment" items="${assignmentList}">
                    <tr>
                        <td>${assignment.id}</td>
                        <td>
                            <a href="viewAssignment.jsp?id=${assignment.id}">
                                ${assignment.title}
                            </a>
                        </td>
                        <td>${assignment.createdDate}</td>
                        <td>${assignment.dueDate}</td>
                        <td>${assignment.isSubmitted ? '제출 완료' : '미제출'}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
