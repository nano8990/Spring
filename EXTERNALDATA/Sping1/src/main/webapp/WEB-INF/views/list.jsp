<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style>
table {width: 100%}
td, th {border: 1px solid #EEE; text-align: center; padding: 10px 20px;}
th {background: #000; color: #FFF}
a {background: #000; color: #FFF}
</style>
</head>
<body>
	<table>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>중간고사</th>
			<th>기말고사</th>
		</tr>
		${list_result}
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>중간고사</th>
			<th>기말고사</th>
		</tr>
	</table>
	<br>
	<a href="home">입력화면으로 가기</a>
</body>
</html>