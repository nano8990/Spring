<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="${pageContext.request.contextPath}/resources/update_pw.css"
	rel="stylesheet" />
</head>
<body><center>
<h1>비밀번호 변경</h1></center>
	<form action="do_update_pw" method="get">
	<center>
  	PW: <input type="password" placeholder="비밀번호를 입력해주세요"name="passwd" value=""> <br>
  	 	<input type="submit" name="" value="Change"></center></form>
</body>
</html>