<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/login.css" type="text/css">
<title>로그인</title>
</head>
<body>
	<jsp:include page="/html/Header.html" />
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
	</div>
</body>
</html>