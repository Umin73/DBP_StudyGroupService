<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>사이드바</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
</head>
<body>
    <div class="sidebar">
        <ul>
            <li><a href="home.jsp">HOME</a></li>
            <li><a href="createGroup.jsp">스터디 그룹 생성</a></li>
            <li><a href="findGroup.jsp">스터디 그룹 찾기</a></li>
            <li><a class="active" href="${pageContext.request.contextPath}/user/mygroup">참여 중인 스터디</a></li>
            <li><a href="myPage.jsp">MY PAGE</a></li>
        </ul>
    </div>
</body>
</html>