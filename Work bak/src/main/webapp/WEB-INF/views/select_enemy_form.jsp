<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Select Enemy</title>

<link href="${pageContext.request.contextPath}/resources/select_enemy_form.css"
	rel="stylesheet" />
</head>

<body>
	<a href="create_character_form">뒤로가기</a>

	<table>
		<tr>
			<th>선택</th>
			<th>이름</th>
			<th>체력</th>
			<th>공격력</th>
			<th>방어력</th>
			<th>명중률</th>
			<th>방어율</th>
			<th>공격속도</th>
		</tr>
		${select_result}
	</table>
	<a href="battle_form">선전포고</a>

</body>

</html>