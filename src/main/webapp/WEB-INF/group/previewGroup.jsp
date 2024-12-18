<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>GROUP PREVIEW</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/viewGroup.css" type="text/css">


<script>
function joinGroup(targetUri, groupId) {
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

	<div>
		${group.groupName }
	</div>

	<button onclick="joinGroup('<c:url value="/group/join"/>', '${group.groupId}')">그룹 가입하기</button>
</body>