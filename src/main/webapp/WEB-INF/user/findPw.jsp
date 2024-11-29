<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <script>
        function validateForm(event) {
            const form = document.querySelector("form");

            if (form.user_id.value.trim() === "") {
                alert("아이디를 입력해주세요.");
                form.user_id.focus();
                event.preventDefault();
                return false;
            }

            if (form.username.value.trim() === "") {
                alert("이름을 입력해주세요.");
                form.username.focus();
                event.preventDefault();
                return false;
            }

            form.submit();
        }
    </script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css">
    <title>비밀번호 찾기</title>
</head>
<body>
    <jsp:include page="../Header.jsp" />
	<jsp:include page="../sidebar.jsp" />
    <div class="main-content">
        <div class="login-titleWrap">비밀번호 찾기</div>
        
        <c:if test="${not empty errorMessage}">
            <p class="errorMessage">${errorMessage}</p>
        </c:if>

        <div class="contentWrap">
            <p class="smallText">계정에 등록된 이메일로 인증번호를 전송해드립니다.</p>
        </div>

        <div class="contentWrap">
            <form method="POST" action="<c:url value='/user/findPw' />">
                <div class="inputWrap">
                    <input type="text" class="input" name="user_id" placeholder="아이디" required />
                </div>
                <div class="inputWrap">
                    <input type="text" class="input" name="username" placeholder="이름" required />
                </div>

                <div>
                    <input type="submit" class="loginButton" value="비밀번호 찾기" onClick="validateForm(event)" />
                </div>
            </form>
        </div>
    </div>
</body>
</html>
