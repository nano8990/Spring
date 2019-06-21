<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
	<title>Modify Page</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/input.css" />
<body>
	<a href="">목록으로</a>

	<form action="modify_data" method="post">
	<label>ID</label><input type="text" name="id" placeholder="ID" value="${id}" />
	<label>현재 Password</label><input type="password" name="origin_password" placeholder="Password" />
	<br />
	<label>Password</label><input type="password" name="password1" placeholder="Password" />
	<label>Password 확인</label><input type="password" name="password2" placeholder="Password" />
	<br />
	<label>이름</label><input type="text" name="name" placeholder="이름" value="${name}" />
	<label>나이</label><input type="text" name="age" placeholder="나이" value="${age}" />
	<label>성별</label><input type="text" name="sex" placeholder="성별" value="${sex}" />
	<input type="submit" value="Submit" />
	</form>

</body>
</html>
