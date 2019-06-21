<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>데이터 입력</title>
<link href="${pageContext.request.contextPath}/resources/input.css"
	rel="stylesheet" />
</head>

<body>
	<a href="">목록으로</a>
	<form action="update_data" method="get">
		<input type="hidden" name="idx" value="${idx}" />
		<label>id</label> <input type="text" placeholder="ID" name="id" value="${id}" />
		<label>Password</label> <input type="password" placeholder="Password" name="password" value="${password}" />
		<label>이름</label> <input type="text" placeholder="이름" name="name" value="${name}" />
		<label>주소</label> <input type="text" placeholder="주소" name="address" value="${address}" />
		<label>생일</label> <input type="text" placeholder="생일" name="birthday" value="${birthday}" />
		<label>좋아하는 색상</label> <input type="text" placeholder="좋아하는 색상" name="favorite_color" value="${favorite_color}" />
		<input type="submit" value="입력 완료" />
	</form>
</body>

</html>
