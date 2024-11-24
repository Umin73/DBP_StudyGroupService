<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>그룹 생성</title>
    <link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
	<link rel=stylesheet href="${pageContext.request.contextPath}/css/createGroup.css" type="text/css">
</head>
<body>
	<jsp:include page="../Header.jsp" />
	<jsp:include page="../sideBar.jsp" />
    <div class="form-container">
        <form action="<c:url value='/group/create' />" method="post">
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
                    <option value="Technology">어학</option>
                    <option value="Health">프로그래밍</option>
                    <option value="Education">취업</option>
                    <option value="Sports">자격증</option>
                    <option value="Entertainment">임용</option>
                    <option value="etc">기타</option>
                </select>
            </div>
            <!-- 인원 수 -->
            <div class="form-group">
                <label for="groupSize">인원 수: </label>
                <input type="number" id="groupSize" name="groupSize" placeholder="1" min="1" required>
            </div>
            <!-- 제출 버튼 -->
            <div class="form-group">
                <button type="submit">생성하기</button>
            </div>
        </form>
    </div>
</body>
</html>
