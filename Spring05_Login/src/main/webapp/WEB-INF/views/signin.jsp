<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
</head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/sign_in.css" />
<body>
  <div class="wrap">
    <div class="login_content">
      <div class="logo_sec">
        <h3>로그인 화면 입니다.</h3>
      </div>
      <form action="do_signin" method="post" class="login_sec">
        <input type="text" placeholder="id" class="login_input" name="id" required="required" />
        <input type="password" placeholder="password" class="login_input" name="password" required="required" />
        <input type="submit" class="login_btn" value="제출" />
      </form>
    </div>
  </div>
</body>
</html>
