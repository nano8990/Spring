<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>battle_from</title>
<link
	href="${pageContext.request.contextPath}/resources/battle_form.css"
	rel="stylesheet" />
</head>

<body>
	<span id="resultSpan1">213</span>
	<br />
	<span id="resultSpan2">123</span>
</body>
<script>
	var inputGaugeList = "${gauge_list}";
	var splitResult = inputGaugeList.split("|");
	var myGauge = [];
	var enemyGauge = [];
	
	out.print(System.currentTimeMillis());
	Thread.sleep(100);

	out.print("<br>");
	out.print(System.currentTimeMillis());

	%>
	
	for (var i = 0; i < splitResult.length; i++) {
		myGauge.push(splitResult[i].split("&")[0]);
		enemyGauge.push(splitResult[i].split("&")[1]);
	}

	var i = 0;
	var timer = setInterval(function() {
		console.log("#".repeat(myGauge[i] % 10))
		document.getElementById("resultSpan1").innerHTML = '#'.repeat(Math.floor(myGauge[i] / 10));
		document.getElementById("resultSpan2").innerHTML = "#".repeat(Math.floor(enemyGauge[i] / 10));
		if (i < splitResult.length) {
			i++;	
		}
	}, 100);
	
	setTimeout(function() {
		clearInterval(timer);
	}, 100 * splitResult.length);

	console.log(myGauge);
	console.log(enemyGauge);
</script>
</html>

