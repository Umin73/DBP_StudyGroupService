<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/home.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/viewGroup.css" type="text/css">

<script>
function navigateToGroupPage(targetUri, groupId) {
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

<title>HOME</title>
</head>
<body>
	<jsp:include page="Header.jsp" />
	<jsp:include page="sidebar.jsp" />
	<div class="main-content">

	<c:if test="${not empty success}">
      	<script>
        	alert("${success}");
    	</script>
    </c:if>

		<!-- 광고 -->
    	<section class="main-ad">
    		<img src="img/dbp_mainpage_ad1.png" class="ad-img">
    	</section>
    	
    	<div class="study-container">
            <!-- 참여 중인 스터디 -->
            <section class="active-study">
                <h2>참여 중인 스터디</h2>
                <div class="group-container">
			        <c:forEach var="group" items="${myGroupList}">
 			            <div class="group-card" 
						     data-category="${group.category}"
						     onclick="navigateToGroupPage('<c:url value="/group/myGroup"/>', '${group.groupId}')">
						    <div class="group-category">${group.category}</div>
						    <div class="group-name">${group.groupName}</div>
						    <div class="group-description">${group.groupDescription}</div>
						    <div class="group-size">참여 인원 ${group.currMembers}/${group.maxMembers}</div>
						</div>
			        </c:forEach>
   				</div>
            </section>

            <!-- 새로운 스터디 -->
            <section class="new-study">
                <h2>새로운 스터디</h2>
                <div class="study-box">
                	<c:forEach var="group" items="${groupList}">
	                    <div class="study-item"
	                    		onclick="navigateToGroupPage('<c:url value="/group/preview"/>', '${group.groupId}')">
	                        <div class="study-info">${group.groupName }</div>
	                        <div class="details">${group.groupDescription}</div>
	                    </div>
                    </c:forEach>
                </div>
            </section>
        </div>
	</div>
</body>
</html>