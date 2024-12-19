<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NOTICE FORM</title>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/notice.css" type="text/css">
	<script>
	function navigateToPage(targetUri) {
        const form = document.createElement('form');
        form.action = targetUri;
        form.method = "GET";
        document.body.appendChild(form);
        form.submit();
    }
	
	function createNotice(event) {
		const form = document.getElementById("createNoticeForm");
		
		if(form.title.value.trim() == "") {
			alert("제목을 입력해주세요.");
			form.title.focus();
			event.preventDefault();
			return;
		}
		
		if(form.content.value.trim() == "") {
			alert("내용을 입력해주세요.");
			form.description.focus();
			event.preventDefault();
			return;
		}
		
		form.submit();
	}
	
	window.onload = function() {
        const successMessage = "<c:out value='${successMessage}' />";
        if (successMessage.trim() != "") {
            alert(successMessage);
            window.location.href = "<c:url value='/notice/view' />"; 
        }
    };
	</script>
</head>
<body>
	<jsp:include page="../../Header.jsp" />
	<jsp:include page="../../sidebar.jsp" />
    <div class="container">
        <h1>공지사항 작성</h1>
        <form id="createNoticeForm" action="<c:url value='/notice/view' />" method="post">
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" id="title" name="title" placeholder="제목을 입력하세요." required>
            </div>
            <div class="form-group">
                <label for="content">내용</label>
                <textarea id="content" name="content" rows="10" placeholder="내용을 입력하세요." required></textarea>
            </div>
            <div class="button-group">
                <button type="button" class="button" onClick="createNotice(event)">작성 완료</button>
                <a class="button" onclick="navigateToPage('<c:url value="/notice/list"/>')">목록으로</a>
            </div>
        </form>
    </div>
</body>
</html>
