<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <style>

  </style>
  <link href="${pageContext.request.contextPath}/resources/input.css"
	rel="stylesheet" />
</head>
<body>
<form action="do_search_id" method="get">
	<center>주민번호: <input type="text" placeholder="6자리만 입력해주세요" name="key_number" value=""><br>
  <input type="submit" value="아이디 확인"> 
  <a href="search_pw">비밀번호 찾기</a>
</center>
</form>
	
</body>
</html>