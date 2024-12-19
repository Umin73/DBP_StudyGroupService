<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>MEMBER LIST</title>

<style>
.table-container {
    max-height: 500px;
    overflow-y: auto;
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

<div class="table-container">
    <table class="member-table">
        <thead>
            <tr>
                <th>역할</th>
                <th>이름</th>
                <th>출석률</th>
                <th>퀴즈 제출률</th>
                <th>과제 제출률</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="member" items="${groupMember}">
                <tr>
                    <td>${member.role}</td>
                    <td>${member.name}</td>
                    <td>${member.attendanceRate}%</td>
                    <td>${member.quizRate}%</td>
                    <td>${member.assignRate }%</td>
                    <td>
                        <c:if test="${member.role != 'leader'}">
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