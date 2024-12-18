<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Assignment</title>
    <link rel=stylesheet href="${pageContext.request.contextPath}/css/index.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/assignment.css" type="text/css">
	<script>
		function createAssignment(event) {
			const form = document.getElementById("createAssignmentForm");
			
			if(form.title.value.trim() == "") {
				alert("제목을 입력해주세요.");
				form.title.focus();
				event.preventDefault();
				return;
			}
			
			if(form.description.value.trim() == "") {
				alert("설명을 입력해주세요.");
				form.description.focus();
				event.preventDefault();
				return;
			}
			
			if(form.deadline.value.trim() == "") {
				alert("마감일을 입력해주세요.");
				form.deadline.focus();
				event.preventDefault();
				return;
			}
			
			if(form.createDate.value.trim() == "") {
				alert("작성일을 입력해주세요.");
				form.createDate.focus();
				event.preventDefault();
				return;
			}
			
			form.submit();
		}
		
		window.onload = function() {
	        const successMessage = "<c:out value='${successMessage}' />";
	        if (successMessage.trim() != "") {
	            alert(successMessage);
	            window.location.href = "<c:url value='/assignment/view' />"; 
	        }
	    };
	</script>
</head>
<body>
	<jsp:include page="../../Header.jsp" />
	<jsp:include page="../../sidebar.jsp" />
    <form id="createAssignmentForm" action="<c:url value='/assignment/create/form' />" method="post"> 
        <!-- Title Field -->
        <div>
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required>
        </div>

        <!-- Description Field -->
        <div>
            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4" required></textarea>
        </div>

        <!-- Deadline Field -->
        <div>
            <label for="deadline">Deadline:</label>
            <input type="datetime-local" id="deadline" name="deadline" required>
        </div>

        <!-- Create Date Field (Auto-filled with current date) -->
        <div>
            <label for="createDate">Create Date:</label>
            <input type="datetime-local" id="createDate" name="createDate" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(new java.util.Date()) %>">
        </div>

        <!-- Submit Button -->
        <div>
            <button type="button" onClick="createAssignment(event)">생성하기</button>
        </div>
    </form>
</body>
</html>
