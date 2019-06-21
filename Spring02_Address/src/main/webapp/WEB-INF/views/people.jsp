<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Input Student</title>
<style>
form {
	width: 100%;
	padding: 10px;
	border: 1px solid #eee;
	margin: 10px auto;
	width: 95%;
}

form label {
	display: inline-block;
	width: 100%;
	padding: 8px 0px 0px 10px;
	background: #eee;
	border-radius: 5px 0px;
}

form input {
	width: 100%;
	display: inline-block;
	margin: 0px 0px 20px 0px;
	padding: 5px 0px;
}

form input[type="submit"] {
	width: 50%;
	display: block;
	margin: 10px auto;
	background: #aa9999;
	color: #fff;
	border: 0px;
	padding: 10px;
}

</style>
</head>

<body>
	<form action="insert_data" method="get">
		<label>이름</label>
		<input type="text" placeholder="이름입력" name="name" />
		<label>좋아하는 색</label>
		<input type="text" placeholder="색 입력" name="favoriteColor" />
		<input type="submit" value="입력 완료" />
	</form>
</body>

</html>
