<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>그룹 생성</title>
    <link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
	<link rel=stylesheet href="${pageContext.request.contextPath}/css/createGroup.css" type="text/css">

	<script>
		function createGroup(event) {
			const form = document.getElementById("createGroupForm");
			
			if(form.groupName.value.trim() == "") {
				alert("그룹명을 입력해주세요.");
				form.groupName.focus();
				event.preventDefault();
				return false;
			}
			
			if(form.groupGoal.value.trim() == "") {
				alert("목표를 입력해주세요.");
				form.groupGoal.focus();
				event.preventDefault();
				return false;
			}
			
			if(form.groupDescription.value.trim() == "") {
				alert("설명을 입력해주세요.");
				form.groupDescription.focus();
				event.preventDefault();
				return false;
			}
			
			if(form.groupCategory.value.trim() == "") {
				alert("카테고리를 선택해주세요.");
				form.groupCategory.focus();
				event.preventDefault();
				return false;
			}
			
			if(form.groupSize.value.trim() == "") {
				alert("인원수를 입력해주세요.");
				form.groupSize.focus();
				event.preventDefault();
				return false;
			}
			
			form.submit();
		}
		
		window.onload = function() {
	        const successMessage = "<c:out value='${successMessage}' />";
	        if (successMessage) {
	            alert(successMessage);
	            window.location.href = "<c:url value='/group/viewAll' />"; // viewAllGroup.jsp로 이동
	        }
	    };
	</script>

</head>
<body>
	<jsp:include page="../Header.jsp" />
	<jsp:include page="../sidebar.jsp" />
    <div class="form-container">
        <form id="createGroupForm" action="<c:url value='/group/create/process' />" method="POST">
            <!-- 그룹명 -->
            <div class="form-group">
                <label for="groupName">그룹명: </label>
                <input type="text" id="groupName" name="groupName" placeholder="그룹명을 입력해주세요." required>
            </div>
            <!-- 목표 -->
            <div class="form-group">
                <label for="groupGoal">목표: </label>
                <input type="text" id="groupGoal" name="groupGoal" placeholder="목표를 입력해주세요." required>
            </div>
            <!-- 설명 -->
            <div class="form-group">
                <label for="groupDescription">설명: </label>
                <textarea id="groupDescription" name="groupDescription" placeholder="그룹에 대한 설명을 입력해주세요." rows="4"></textarea>
            </div>
            <!-- 카테고리 -->
            <div class="form-group">
                <label for="groupCategory">카테고리: </label>
                <select id="groupCategory" name="groupCategory" required>
                    <option value="">카테고리 선택</option>
                    <option value="어학">어학</option>
                    <option value="프로그래밍">프로그래밍</option>
                    <option value="취업">취업</option>
                    <option value="자격증">자격증</option>
                    <option value="임용">임용</option>
                    <option value="기타">기타</option>
                </select>
            </div>
            <!-- 인원 수 -->
            <div class="form-group">
                <label for="groupSize">인원 수: </label>
                <input type="number" id="groupSize" name="groupSize" placeholder="1" min="1" required>
            </div>
            <!-- 제출 버튼 -->
            <div class="form-group">
                <button type="button" onClick="createGroup(event)">생성하기</button>
            </div>
        </form>
    </div>
</body>
</html>