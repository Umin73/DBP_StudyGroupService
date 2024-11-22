<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
	function login(event) {
		const form = document.querySelector("form");
		
		if(form.userId.value.trim() == "") {
			alert("아이디를 입력해주세요.");
			form.userId.focus();
			event.preventDefault();
			return false;
		}
		
		if(form.password.value.trim() == "") {
			alert("비밀번호를 입력해주세요.");
			form.password.focus();
			event.preventDefault();
			return false;
		}
		form.submit();
	}

	function navigateToSignUp(targetUri) {
		const form = document.createElement("form");
		form.action = targetUri;
		form.method = "GET";
		document.body.appendChild(form);
		form.submit();
	}
</script>

<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/login.css" type="text/css">
<title>로그인</title>
</head>
<body>
	<jsp:include page="../Header.jsp" />
	<jsp:include page="/html/Sidebar.html" />
	<div class="main-content">
		<div class="login-titleWrap">로그인</div>
        <div class="contentWrap">
        	<form method="POST" action="<c:url value='/user/login' />">
        	<div class="inputWrap">
        		<input type="text" class="input" name="userId" placeholder="아이디" required />
        	</div>
        	<div class="inputWrap">
        		<input type="password" class="input" name="password" placeholder="비밀번호" required />
        	</div>
        	<div>
        		<input type="submit" class="loginButton" value="로그인" onClick="login(event)" />
        	</div>
        	</form>
        </div>
        <div class="signUpAndFindWrap">
        	<div class="sButtonWrap">
        		<button class="sButton">아이디 찾기</button>
        	</div>
        	<div class="sButtonWrap">
        		<button class="sButton">비밀번호 찾기</button>
        	</div>
        	<div class="sButtonWrap">
        		<button class="sButton" onClick="navigateToSignUp('<c:url value='/user/signup' />')">회원가입</button>
        	</div>
        </div>
	</div>
</body>
</html>