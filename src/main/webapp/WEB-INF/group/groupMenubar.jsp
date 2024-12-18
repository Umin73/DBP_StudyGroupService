<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
.group-menu ul {
    display: flex;
    justify-content: space-around;
    list-style: none;
    padding: 10px;
    background-color: #f5f5f5;
}

.group-menu ul li a {
    text-decoration: none;
    color: black;
    font-weight: bold;
}

.group-menu ul li a:hover {
    color: #007bff;
}
</style>

<div class="group-menu">
    <ul>
        <li><a href="${pageContext.request.contextPath}/group/schedule.jsp">스터디 일정 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/group/manageMembers.jsp">멤버 관리</a></li>
        <li><a href="${pageContext.request.contextPath}/group/viewMemberList.jsp">멤버 목록</a></li>
        <li><a href="${pageContext.request.contextPath}/group/assignments.jsp">과제 목록</a></li>
        <li><a href="${pageContext.request.contextPath}/group/quiz.jsp">퀴즈</a></li>
    </ul>
</div>