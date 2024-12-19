<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>MY GROUPS</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/viewGroup.css" type="text/css">

<script>

function navigateToPage() {
	
}

</script>

</head>
<body>

<jsp:include page="../Header.jsp" />
<jsp:include page="../sidebar.jsp" />

<div class="container">

<!-- 그룹 리스트 -->
    <div class="group-container">
        <c:forEach var="group" items="${myGroupList}">
            <div class="group-card" 
			     data-category="${group.category}"
			     >
			    <div class="group-category">${group.category}</div>
			    <div class="group-name">${group.groupName}</div>
			    <div class="group-description">${group.groupDescription}</div>
			    <div class="group-size">참여 인원 ${group.currMembers}/${group.maxMembers}</div>
			</div>
        </c:forEach>
    </div>
</div>
</body>
</html>