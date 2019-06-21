<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="login">로그인페이지가기</a>
<a href="search_pw">비밀번호찾기</a>
</body>

<script>
var searchResult = "${id_result}";
alert("당신의 아이디는 " + searchResult+ " 입니다");
</script>
</html>