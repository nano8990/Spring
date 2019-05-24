<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<form action="/spring1/select" method="get">
		<input type="submit" value="조회" />
	</form>
	<form action="/spring1/input" method="get">
		<input type="submit" value="입력" />
	</form>
</body>
</html>