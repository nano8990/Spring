<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
  <style>

  </style>
  <link href="${pageContext.request.contextPath}/resources/login_in.css"
	rel="stylesheet" />
</head>
<body>

 <div class="wrap">
    <div class="login_content">
      <div class="logo_sec">
        <h3>로그인 해주세요</h3>
      </div>
     <form class="login_sec" action="do_login" method="get">
		<input type="text" placeholder="아이디를 입력해주세요" class="login_input" required="required" name="id" />
  		<input type="password" placeholder="비밀번호를 입력해주세요" class="login_input" required="required" name="passwd" />
  	 	<input type="submit" class="login_btn" value="Login" />
	 </form>
    </div>
  </div>
</body>
</html>