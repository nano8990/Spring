<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계정생성</title>
  <style>

  </style>
  <link href="${pageContext.request.contextPath}/resources/account.css"
	rel="stylesheet" />
</head>
<body>
<div class="center">
<form action="duplicate_check" method="get">
   <center>
   <input type="text" name = "key_number" placeholder="주민번호 6자리만 입력해주세요" class="a" required="required" /> 
   <input type="submit" class="btn" value="중복검사" /><br />
</center>
</form>

</div>
</body>
</html>