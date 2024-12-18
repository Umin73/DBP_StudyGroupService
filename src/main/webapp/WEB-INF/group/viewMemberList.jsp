<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>MEMBER LIST</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/viewGroup.css" type="text/css">

</head>
<body>

<jsp:include page="../Header.jsp" />
<jsp:include page="../sidebar.jsp" />
<jsp:include page="groupMenubar.jsp" />

<div class="table-container">
    <table class="member-table">
        <thead>
            <tr>
                <th>역할</th>
                <th>이름</th>
                <th>출석률</th>
                <th>퀴즈 제출률</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="member" items="${memberList}">
                <tr>
                    <td>${member.role}</td>
                    <td>${member.name}</td>
                    <td>${member.attendanceRate}%</td>
                    <td>${member.quizRate}%</td>
                    <td>
                        <c:if test="${member.role != '리더'}">
                            <button class="btn btn-leader">리더 변경</button>
                            <button class="btn btn-kick">추방</button>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>