<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<style>
form {
	margin: 0 auto;
	padding: 10px;
	background: #eee;
}

label {
	display: inline-block;
	width: 200px;
	text-align: center;
}

input {
	display: inline-block;
	width: 300px;
}

form span {
	display: block;
	padding: 10px;
}

input[type="submit"] {
	display: inline-block;
	margin: 5px 0px 0px 140px;
}
</style>
<body>
	<form action="/inputscore/insert" method="get">
		<span><label>이름</label><input type="text" name="name" /></span>
		<span><label>중간고사점수</label><input type="number" name="score" /></span>
		<input type="submit" value="데이터 전송">
	</form>
	<span>${query_result}</span>
</body>
</html>