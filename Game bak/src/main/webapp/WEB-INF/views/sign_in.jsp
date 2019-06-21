<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">

<head>
<title>rune</title>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/sign_in.css" />
</head>

<body>
  <div class="wrap">
    <div class="login_content">
      <div class="logo_sec">
        <h3>회원 센터</h3>
      </div>
      <form class="login_sec">
        <input type="text" placeholder="아이디" class="login_input" required="required" />
        <input type="password" placeholder="비밀번호" class="login_input" required="required" />
        <input type="submit" class="login_btn" value="로그인" />
      </form>
    </div>
  </div>
</body>

</html>