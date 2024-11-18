<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
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
        	<form>
        	<div class="inputWrap">
        		<input type="text" class="input" name="userId" placeholder="아이디" required />
        	</div>
        	<div class="inputWrap">
        		<input type="password" class="input" name="password" placeholder="비밀번호" required />
        	</div>
        	<div>
        		<input type="submit" class="loginButton" value="로그인" />
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