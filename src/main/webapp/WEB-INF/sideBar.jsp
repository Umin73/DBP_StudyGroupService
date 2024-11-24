<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	function createGroup(targetUri) {
		const form = document.createElement('form');
		form.action = targetUri;
		form.method="GET";
		document.body.appendChild(form);
		form.submit();
	}
</script>

<div class="sidebar">
	<ul>
		<li><a href="home.jsp">HOME</a></li>
		<li><a class="createGroup" onClick="createGroup('<c:url value='group/create/form'/>')">스터디 그룹 생성</a></li>
		<li><a href="findGroup.jsp">스터디 그룹 찾기</a></li>
		<li><a href="myGroup.jsp">참여 중인 스터디</a></li>
		<li><a href="myPage.jsp">MY PAGE</a></li>
	</ul>
</div>

