<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>GROUPS</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/viewGroup.css" type="text/css">

<script>

function filterGroups(category) {
    const allCards = document.querySelectorAll(".group-card");
    allCards.forEach(card => {
        const cardCategory = card.getAttribute("data-category");
        card.style.display = (category === "전체" || cardCategory === category) ? "block" : "none";
    });

    // 활성화된 카테고리 표시
    const tabs = document.querySelectorAll(".category-tab");
    tabs.forEach(tab => tab.classList.remove("active"));
    document.getElementById(category).classList.add("active");
}

function checkGroupId(targetUri, groupId) {
    console.log(groupId);
    
    const form = document.createElement('form');
    form.action = targetUri;
    form.method = "POST";
    
    const input = document.createElement('input');
    input.type = "hidden";
    input.name = "groupId";
    input.value = groupId;
    form.appendChild(input);
    
    document.body.appendChild(form);
    form.submit();
}



</script>

</head>
<body>

<jsp:include page="../Header.jsp" />
<jsp:include page="../sidebar.jsp" />

<!-- 카테고리 탭 -->
<div class="category-tabs">
        <div id="전체" class="category-tab active" onclick="filterGroups('전체')">전체</div>
        <div id="어학" class="category-tab" onclick="filterGroups('어학')">어학</div>
        <div id="프로그래밍" class="category-tab" onclick="filterGroups('프로그래밍')">프로그래밍</div>
        <div id="취업" class="category-tab" onclick="filterGroups('취업')">취업</div>
        <div id="자격증" class="category-tab" onclick="filterGroups('자격증')">자격증</div>
        <div id="임용" class="category-tab" onclick="filterGroups('임용')">임용</div>
        <div id="기타" class="category-tab" onclick="filterGroups('기타')">기타</div>
    </div>

<!-- 그룹 리스트 -->
    <div class="group-container">
        <c:forEach var="group" items="${groupList}">
            <div class="group-card" 
			     data-category="${group.category}"
			     onclick="checkGroupId('<c:url value="/group/preview"/>', '${group.groupId}')">
			    <div class="group-category">${group.category}</div>
			    <div class="group-name">${group.groupName}</div>
			    <div class="group-description">${group.groupDescription}</div>
			    <div class="group-size">참여 인원 ${group.currMembers}/${group.maxMembers}</div>
			</div>
        </c:forEach>
    </div>

</body>
</html>