<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>회원가입</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/login.css" type="text/css">

<script>
	function signUp(event, targetUri) {
		const form = document.querySelector("form");

		if (form.userId.value.trim() == "") {
			alert("ID를 입력해주세요.");
			form.userId.focus();
			event.preventDefault();
			return false;
		}
		
		if (form.password.value.trim() == "") {
			alert("비밀번호를 입력해주세요.");
			form.password.focus();
			event.preventDefault();
			return false;
		}

		if (form.password.value != form.password2.value) {
			alert("비밀번호가 일치하지 않습니다.");
			form.password2.focus();
			event.preventDefault();
			return false;
		}

		if (form.username.value.trim() == "") {
			alert("이름을 입력해주세요.");
			form.username.focus();
			event.preventDefault();
			return false;
		}

		const emailExp = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
		if (!emailExp.test(form.email.value)) {
			alert("이메일 형식이 올바르지 않습니다.");
			form.email.focus();
			event.preventDefault();
			return false;
		}

		const phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
		if (!phoneExp.test(form.phone.value)) {
			alert("전화번호 형식이 올바르지 않습니다.");
			form.phone.focus();
			event.preventDefault();
			return false;
		}

		form.submit();
		
		navigateToLogIn('<c:url value='/user/login/form'/>');
	}
	
	function navigateToLogIn(targetUri) {
		form.action = targetUri;
		form.submit();
	}
</script>
</head>

<body>
	<jsp:include page="../Header.jsp" />
	<jsp:include page="/html/Sidebar.html" />
	<div class="main-content">
		<div class="titleWrap">회원가입</div>
		<div class="contentWrap">
			<form method="POST" action="<c:url value='/user/signup' />">
				<div class="inputTitle">아이디</div>
				<div class="inputWrap">
					<input type="text" class="input" name="userId" required />
				</div>
				
				<div class="inputTitle">비밀번호</div>
				<div class="inputWrap">
					<input type="password" class="input" name="password" required />
				</div>
				
				<div class="inputTitle">비밀번호 확인</div>
				<div class="inputWrap">
					<input type="password" class="input" name="password2" required />
				</div>
				
				<div class="inputTitle">이름</div>
				<div class="inputWrap">
					<input type="text" class="input" name="username" required />
				</div>
				
				<div class="inputTitle">전화번호</div>
				<div class="inputWrap">
					<input type="text" class="input" name="phone" required />
				</div>
				
				<div class="inputTitle">이메일</div>
				<div class="inputWrap">
					<input type="text" class="input" name="email" required />
				</div>
				
				<div>
					<input type="button" class="loginButton" value="회원가입" onClick="signUp(event)" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>
