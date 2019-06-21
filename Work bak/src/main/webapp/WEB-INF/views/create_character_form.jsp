<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>케릭터 생성</title>
  <link rel="stylesheet" type="text/css" href="main.css" />
</head>
<style>
</style>

<body>
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <a href="yes_session_form">목록으로</a>
  <br><br>
  <form action="do_create_character" method="get">

    <div name="jobSection">
      <label>직업선택</label><br>
      1<input type="radio" name="selectJob" checked>
      2<input type="radio" name="selectJob">
      3<input type="radio" name="selectJob">
      4<input type="radio" name="selectJob">
      5<input type="radio" name="selectJob">
    </div>

    <div name="statusSection">
      <label>스탯선택</label>
      <br><br>
      <label>체　　력　</label>
      <input type="text" name="status" style="text-align:center; width:30px" readonly />
      <input type="button" name="up" value="+" />
      <input type="button" name="down" value="-" />
      <br>
      <label>공 격 력　</label>
      <input type="text" name="status" style="text-align:center; width:30px" readonly />
      <input type="button" name="up" value="+" />
      <input type="button" name="down" value="-" />
      <br>
      <label>방 어 력　</label>
      <input type="text" name="status" style="text-align:center; width:30px" readonly />
      <input type="button" name="up" value="+" />
      <input type="button" name="down" value="-" />
      <br>
      <label>명 중 률　</label>
      <input type="text" name="status" style="text-align:center; width:30px" readonly />
      <input type="button" name="up" value="+" />
      <input type="button" name="down" value="-" />
      <br>
      <label>방 어 율　</label>
      <input type="text" name="status" style="text-align:center; width:30px" readonly />
      <input type="button" name="up" value="+" />
      <input type="button" name="down" value="-" />
      <br>
      <label>치명확률　</label>
      <input type="text" name="status" style="text-align:center; width:30px" readonly />
      <input type="button" name="up" value="+" />
      <input type="button" name="down" value="-" />
      <br>
      <label>치명피해　</label>
      <input type="text" name="status" style="text-align:center; width:30px" readonly />
      <input type="button" name="up" value="+" />
      <input type="button" name="down" value="-" />
      <br>
      <label>공격속도　</label>
      <input type="text" name="status" style="text-align:center; width:30px" readonly />
      <input type="button" name="up" value="+" />
      <input type="button" name="down" value="-" />
      <br><br>
      <label>남은 스탯　</label>
      <input type="text" name="remainStatus" style="text-align:center; width:30px" readonly />
      <br>
    </div>
    <input type="submit" value="생성" />
  </form>

  <script>
    var maxStatus = 30;
    var totalStat = maxStatus;
    $("input[name=status]").val(0);
    $("input[name=remainStatus]").val(totalStat);

    $("input[name=up]").click(function() {
      var selectedIndex = $("input[name=up]").index(this);
      if (totalStat > 0) {
        var statusValue = Number($("input[name=status]:eq(" + selectedIndex + ")").val()) + 1;
        $("input[name=status]:eq(" + selectedIndex + ")").val(statusValue);
        totalStat--
        $("input[name=remainStatus]").val(totalStat);
      }
    })

    $("input[name=down]").click(function() {
      var selectedIndex = $("input[name=down]").index(this);
      if ((totalStat < 30) && (Number($("input[name=status]:eq(" + selectedIndex + ")").val()) > 0)) {
        var statusValue = Number($("input[name=status]:eq(" + selectedIndex + ")").val()) - 1;
        $("input[name=status]:eq(" + selectedIndex + ")").val(statusValue);
        totalStat++
        $("input[name=remainStatus]").val(totalStat);
      }
    })
  </script>
</body>

</html>