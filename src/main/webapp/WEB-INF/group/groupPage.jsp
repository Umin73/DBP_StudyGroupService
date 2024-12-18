<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>GROUP HOME</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel=stylesheet href="${pageContext.request.contextPath}/css/viewGroup.css" type="text/css">

<script>

</script>

</head>
<body>
<jsp:include page="../Header.jsp" />
<jsp:include page="../sidebar.jsp" />

<c:if test="${not empty success}">
	<script>
    	alert("${success}");
   	</script>
</c:if>

<p>그룹 페이지</p>

</body>
</html>