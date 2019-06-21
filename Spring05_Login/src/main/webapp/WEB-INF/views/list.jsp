<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
	<title>List Page</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/list.css" />
<body>
	<a href="insert">데이터 입력하기</a>
	<a href="signout">로그아웃</a>

	<table>
		<tr>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>
			<th>수정하기</th>
			<th>삭제하기</th>
		</tr>
		${select_result}
	</table>
</body>
</html>
