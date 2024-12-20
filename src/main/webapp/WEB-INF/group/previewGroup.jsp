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

function goToList(targetUri) {
    window.location.href = targetUri;
}

</script>

<style>
.group-preview-container {
    width: 60%;
    margin: 0 auto;
    padding: 30px;
    text-align: center;
    background-color: #f9f9f9;
    border: 1px solid #ddd;
    border-radius: 10px;
}

.group-preview-container h2 {
    font-size: 1.8rem;
    color: #333;
    margin-bottom: 20px;
}

.button-container {
    display: flex;
    justify-content: center;
    gap: 20px;
    margin-top: 20px;
}

.button-container .btn {
    padding: 10px 20px;
    font-size: 1rem;
    border: none;
    border-radius: 5px; 
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.button-container .btn.btn-secondary {
    background-color: #ddd;
    color: #333; 
}

.button-container .btn.btn-secondary:hover {
    background-color: #bbb;
}

.button-container .btn.btn-primary {
    background-color: #007bff;
    color: #fff;
}

.button-container .btn.btn-primary:hover {
    background-color: #0056b3;
}

</style>

</head>
<body>
<jsp:include page="../Header.jsp" />
<jsp:include page="../sidebar.jsp" />
<div class="main-content">

	<c:if test="${not empty errorMessage}">
      	<script>
        	alert("${errorMessage}");
    	</script>
    </c:if>

	<div class="group-preview-container">
		<h2>[${group.groupName}]에 가입하시겠습니까?</h2>

	    <div class="button-container">
	    	<button class="btn btn-secondary" onclick="goToList('<c:url value="/group/viewAll"/>')">목록으로</button>
	        <button class="btn btn-primary" onclick="joinGroup('<c:url value="/group/join"/>', '${group.groupId}')">가입하기</button>
	    </div>
	</div>
</div>
</body>