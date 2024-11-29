<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="UTF-8">

<script>
	function findId(event) {
		const form = document.querySelector("form");
		
		if(form.username.value.trim() == "") {
			alert("이름을 입력해주세요.");
			form.username.focus();
			event.preventDefault();
			return false;
		}
		
		const phoneExp = /^\d{2,3}\d{3,4}\d{4}$/;
		if (!phoneExp.test(form.phone.value)) {
			alert("전화번호 형식이 올바르지 않습니다.");
			form.phone.focus();
			event.preventDefault();
			return false;
		}
		
		form.submit();
	}
</script>

<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/login.css" type="text/css">

<title>아이디 찾기</title>
</head>
<body>
	<jsp:include page="../Header.jsp" />
	<jsp:include page="../sidebar.jsp" />
	<div class="main-content">
		<div class="login-titleWrap">아이디 찾기</div>
        <div class="contentWrap">
        	<form method="POST" action="<c:url value='/user/findId' />">
        	<div class="inputWrap">
        		<input type="text" class="input" name="username" placeholder="이름" required />
        	</div>
        	<div class="inputWrap">
        		<input type="text" class="input" name="phone" placeholder="휴대폰 번호를 '-' 없이 입력" required />
        	</div>
        	<div>
        		<input type="submit" class="loginButton" value="아이디 찾기" onClick="findId(event)" />
        	</div>
        	</form>
        </div>
        
	</div>
</body>
</html>