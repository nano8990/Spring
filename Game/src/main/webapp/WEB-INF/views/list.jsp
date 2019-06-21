<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">

<head>
  <meta charset="utf-8" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/list.css" />
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/list.js"></script>
</head>

<body>
  <div id="wrap">
    <div class="top_button_section">
      <a href="login" class="top_button">로그인</a>
      <a href="join" class="top_button">회원 가입</a>
      <a href="my_card" class="top_button">내 카드 보기</a>
    </div>
    <ul class="player_list">
		${select_result}
    </ul>
    <div class="select_section">
      <form>
        <input type="text" name="select1" />
        <input type="text" name="select2" />
        <input type="submit" value="대전 시작" />
      </form>
    </div>
  </div>
</body>

</html>