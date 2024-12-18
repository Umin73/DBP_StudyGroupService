<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    function navigateToPage(targetUri) {
        const form = document.createElement('form');
        form.action = targetUri;
        form.method = "GET";
        document.body.appendChild(form);
        form.submit();
    }
</script>

<div class="sidebar">
    <ul>
        <li><a onclick="navigateToPage('<c:url value="/home"/>')">HOME</a></li>
        <li><a onclick="navigateToPage('<c:url value="/group/create"/>')">스터디 그룹 생성</a></li>
        <li><a onclick="navigateToPage('<c:url value="/group/viewAll"/>')">스터디 그룹 찾기</a></li>
        <li><a onClick="navigateToPage('<c:url value="/group/myGroup"/>')">참여 중인 스터디</a></li>
        <li><a href="myPage.jsp">MY PAGE</a></li>
    </ul>
</div>
