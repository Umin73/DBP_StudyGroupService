<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css">

    <script>
        function validateVerification(event) {
            const verifyForm = document.getElementById("verifyForm");
            const verifyCode = verifyForm.verifyCode.value.trim();

            if (verifyCode === "") {
                alert("인증번호를 입력해주세요.");
                verifyForm.verifyCode.focus();
                event.preventDefault();
                return false;
            }

            verifyForm.submit();
        }

        function validatePasswordChange(event) {
            const changePwForm = document.getElementById("changePwForm");
            const newPassword = changePwForm.newPassword.value.trim();
            const confirmPassword = changePwForm.newPassword2.value.trim();

            if (newPassword === "") {
                alert("새 비밀번호를 입력해주세요.");
                changePwForm.newPassword.focus();
                event.preventDefault();
                return false;
            }

            if (newPassword !== confirmPassword) {
                alert("비밀번호가 일치하지 않습니다.");
                changePwForm.newPassword2.focus();
                event.preventDefault();
                return false;
            }

            changePwForm.submit();
        }
        
        function showPasswordChangedAlert() {
            alert("비밀번호가 변경되었습니다.");
        }
    </script>

    <title>비밀번호 변경</title>
</head>
<body>
    <jsp:include page="../Header.jsp" />
    <jsp:include page="/html/Sidebar.html" />

    <div class="main-content">
        <div class="login-titleWrap">비밀번호 변경</div>

        <c:if test="${not empty errorMessage}">
            <script>
                alert("${errorMessage}");
            </script>
        </c:if>

        <!-- 인증번호 확인 -->
        <c:if test="${!verifySuccess}">
        	<div class="contentWrap">
	            <p class="smallText">계정에 등록된 이메일로 전송된 인증번호를 입력해주세요.</p>
	        </div>
        
        	<div class="contentWrap">
	            <form id="verifyForm" method="POST" action="<c:url value='/user/verifyCode' />">
	                <input type="hidden" name="user_id" value="${user_id}" />
	                <input type="hidden" name="email" value="${email}" />
	                <div class="inputWrapInline">
	                    <input type="text" class="input" name="verifyCode" placeholder="인증번호" required />
	                    <input type="button" class="checkButton" value="인증 확인" onClick="validateVerification(event)" />
	                </div>
	            </form>
	        </div>
        </c:if>

        <!-- 비밀번호 변경 -->
        <c:if test="${verifySuccess}">
        	<div class="contentWrap">
	            <p class="smallText">새로운 비밀번호를 입력해주세요.</p>
	        </div>
        
            <div class="contentWrap">
                <form id="changePwForm" method="POST" action="<c:url value='/user/changePw' />">
                    <input type="hidden" name="user_id" value="${user_id}" />
                    <div class="inputWrap">
                        <input type="password" class="input" name="newPassword" placeholder="새로운 비밀번호" required />
                    </div>
                    <div class="inputWrap">
                        <input type="password" class="input" name="newPassword2" placeholder="비밀번호 확인" required />
                    </div>
                    <div>
                        <input type="button" class="loginButton" value="비밀번호 변경" onClick="validatePasswordChange(event)" />
                    </div>
                </form>
            </div>
        </c:if>
    </div>
</body>
</html>
