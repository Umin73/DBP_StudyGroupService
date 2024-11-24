<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="UTF-8">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/login.css" type="text/css">

<script>
	function navigateToPage(targetUri) {
		const form = document.createElement("form");
		form.action = targetUri;
		form.method = "GET";
		document.body.appendChild(form);
		form.submit();
	}
</script>

<title>아이디 찾기 결과</title>
</head>
<body>
	<jsp:include page="../Header.jsp" />
	<jsp:include page="/html/Sidebar.html" />
	<div class="main-content">
		<div class="login-titleWrap">아이디 찾기 결과</div>
        <div class="contentWrap">
            <c:if test="${success}">
                <p class="centerText">찾으신 아이디는 <strong>${userId}</strong>입니다.</p>
                
                <div class="buttonContainer">
	                <div>
	        			<input type="button" class="sloginButton" value="비밀번호 찾기" onClick="navigateToPage('<c:url value='/user/findId' />')" />
	        		</div>
	        		
	        		<div>
	        			<input type="button" class="sloginButton" value="로그인" onClick="navigateToPage('<c:url value='/user/login/form' />')" />
	        		</div>

            	</div>
            </c:if>
            <c:if test="${!success}">
                <p class="centerText">${errorMessage}</p>
                
                <div class="buttonContainer">
	                <div>
	        			<input type="button" class="sloginButton" value="아이디 찾기" onClick="navigateToPage('<c:url value='/user/findId' />')" />
	        		</div>
	        		
	        		<div>
	        			<input type="button" class="sloginButton" value="회원가입" onClick="navigateToPage('<c:url value='/user/signup' />')" />
	        		</div>

            	</div>
            </c:if>

   		</div>
   	</div>
</body>
</html>