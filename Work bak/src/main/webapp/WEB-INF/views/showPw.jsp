<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="login">로그인페이지가기</a> <br>
<a href="updatePw">비밀번호 바꾸기</a>
</body>

<script>
var searchResult = "${pw_result}";
alert("당신의 비밀번호는 " + searchResult+ " 입니다");
</script>
</html>