<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계정 생성</title>
  <style>

  </style>
  <link href="${pageContext.request.contextPath}/resources/check_identify.css"
	rel="stylesheet" />
</head>
<body>
  <div class="wrap">
    <div class="create_content">
      <div class="create_sec">
      <h3>계정 생성 </h3>
      </div>
   <form action="do_create_account" method="get" class="create_sec">
   <center>
 	<input type="text" placeholder="아이디를 입력해주세요" value="" class="create_input" required="required" name="id">
  	<input type="password" placeholder="비밀번호를 입력해주세요" value="" class="create_input" required="required" name="passwd1">
 	<input type="password" placeholder="비밀번호를 입력해주세요" value="" class="create_input" required="required" name="passwd2">
 	<input type="submit" class="create_btn" value="생성">
  </form>

 </div>
  </div>
  

</body>
</html>