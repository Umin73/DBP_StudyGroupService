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
</script>

<div class="header">
    <div class="logo">STUDY</div>
    <div class="right-section">
        <input type="text" class="search" placeholder="스터디 그룹 찾아보기">
        <button class="login" onClick="navigateToLogin('<c:url value='/user/login/form'/>')">Log In</button>
    </div>
</div>