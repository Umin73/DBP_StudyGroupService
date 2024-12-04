<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	function navigateToLogin(targetUri) {
		const form = document.createElement('form');
		form.action = targetUri;
		form.method="GET";
		document.body.appendChild(form);
		form.submit();
	}
	
	function navigateToLogout(targetUri) {
		const form = document.createElement('form');
		form.action = targetUri;
		form.method = "POST";
		document.body.appendChild(form);
		form.submit();
	}
</script>

<div class="header">
    <div class="logo">STUDY</div>
    <div class="right-section">
        <input type="text" class="search" placeholder="스터디 그룹 찾아보기">
        
        <c:choose>
        	<c:when test="${isLoggedIn}">
        		<button class="login" onClick="navigateToLogout'<c:url value='/user/logout'/>')">Log Out</button>
        	</c:when>

        	<c:otherwise>
        		<button class="login" onClick="navigateToLogin('<c:url value='/user/login/form'/>')">Log In</button>
        	</c:otherwise>
        </c:choose>
    </div>
</div>