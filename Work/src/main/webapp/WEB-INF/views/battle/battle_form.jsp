<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
				<title>Battle</title>
			</head>
			<style>
				div .myCharacter {
					float: left;
				}
				div .enemyCharacter {
					float: right;
				}
				div .button {
					display: none;
				}
				div .myStatus {
					float: left;
					position: relative;
				}
				div .enemyStatus {
					float: right;
					position: relative;
				}
				.character {
					display: block;
					height: 350;
					top: 120;
					position: absolute;
				}
			</style>
			<body>
				<div class="myCharacter">

					<div class="myStatus">
						체력
						<br/>
						<progress id="myHp" value="${my_current_hp}" max="${my_max_hp}"></progress>
						<br/>
						공격게이지
						<br/>
						<progress id="myGauge" value="${my_first_gauge}" max="100"></progress>
					</div>
					<div>
						<img src="resources/images/${my_character}.png" id="myCharacter" class="character" height="350px"/>
					</div>
				</div>
				<div class="enemyCharacter">

					<div class="enemyStatus">
						체력
						<br/>
						<progress id="enemyHp" value="${enemy_current_hp}" max="${enemy_max_hp}"></progress>
						<br/>
						공격게이지
						<br/>
						<progress id="enemyGauge" value="${enemy_first_gauge}" max="100"></progress>
					</div>
					<div>
						<img src="resources/images/${enemy_character}.png" id="enemyCharacter" class="character" height="350px"/>
					</div>
				</div>
				<div class="button">
					<input type="button" id="turn" value="턴 진행" onclick="turn()"/>
				</div>
			</body>
			<script src="http://code.jquery.com/jquery-latest.min.js"></script>
			<script>

				var DELAY_SEC = 100;

				$("#turn").hide();
				$("#myCharacter").animate({
					'left': 0, 'top': 120
				}, 0);
				$("#enemyCharacter").animate({
					'right': 0, 'top': 120
				}, 0);

				var myMaxHp = '${my_max_hp}';
				var enemyMaxHp = '${enemy_max_hp}';

				var inputGaugeList = '${gauge_list}';
				var splitResult = inputGaugeList.split("|");
				var myGauge = [];
				var enemyGauge = [];

				for (var i = 0; i < splitResult.length; i++) {
					myGauge.push(splitResult[i].split("&")[0]);
					enemyGauge.push(splitResult[i].split("&")[1]);
				}

				function gaugeUp(i) {
					if (i < splitResult.length) {
						setTimeout(
							function () {
								$("#myGauge").val(myGauge[i]);
								$("#enemyGauge").val(enemyGauge[i]);
								i++;
								gaugeUp(i);
							},
							i == 0
								? 0
								: DELAY_SEC
						);
					} else {
						var nMyGauge = Number($("#myGauge").val());
						var nEnemyGauge = Number($("#enemyGauge").val());
						if (nMyGauge >= 100) {
							attack("#myCharacter");
						}
						if (nEnemyGauge >= 100) {
							attack("#enemyCharacter");
						}
						$("#turn").click();
					}
				}

				function hpDown(i, j, giveDamage, receiveDamage) {
					if (i < giveDamage || j < receiveDamage) {
						setTimeout(
							function () {
								if (i < giveDamage) {
									$("#enemyHp").val(Number($("#enemyHp").val() - 1));
									i++;
								}
								if (j < receiveDamage) {
									$("#myHp").val(Number($("#myHp").val() - 1));
									j++;
								}
								hpDown(i, j, giveDamage, receiveDamage);
							},
							i == 0 || j == 0
								? 0
								: DELAY_SEC
						);
					}
				}

				gaugeUp(0);

				function damage(myelement) {
					if (myelement == "#myCharacter") {
						$(myelement).animate({
							'left': 10, 'top': 120
						}, 10).animate({
							'left': -20, 'top': 120
						}, 10).animate({
							'left': 10, 'top': 120
						}, 10);
					} else {
						$(myelement).animate({
							'right': 10, 'top': 120
						}, 10).animate({
							'right': -20, 'top': 120
						}, 10).animate({
							'right': 10, 'top': 120
						}, 10);
					}
				};

				function attack(myelement) {
					if (myelement == "#myCharacter") {
						$(myelement).animate({
							'left': 100, 'top': 120
						}, 50, function () {
							$(myelement).animate({
								'left': 0, 'top': 120
							}, 50);
						});
					} else {
						$(myelement).animate({
							'right': 100, 'top': 120
						}, 50, function () {
							$(myelement).animate({
								'right': 0, 'top': 120
							}, 50);
						});
					}
				}

				function turn() {
					$.ajax({
						url: 'do_attack',
						type: 'POST',
						data: {},
						success: function (response) {
							if (response.result == 'success') {
								if (response.battleResult == 'going') {
									var giveDamage = Number(response.giveDamage);
									var receiveDamage = Number(response.receiveDamage);

									if (giveDamage > 0) {
										damage("#enemyCharacter");
									}
									if (receiveDamage > 0) {
										damage("#myCharacter");
									}

									hpDown(0, 0, giveDamage, receiveDamage);

									inputGaugeList = response.gaugeList;
									splitResult = inputGaugeList.split("|");
									myGauge = [];
									enemyGauge = [];
									for (var i = 0; i < splitResult.length; i++) {
										myGauge.push(splitResult[i].split("&")[0]);
										enemyGauge.push(splitResult[i].split("&")[1]);
									}
									gaugeUp(0);

								} else if (response.battleResult == 'tie') {
									$("#myHp").val(0);
									$("#enemyHp").val(0);
									setTimeout(function () {
										alert("비겼습니다.");
										location.href = '/com';
									}, 100);
								} else if (response.battleResult == 'win') {
									$("#enemyHp").val(0);
									setTimeout(function () {
										alert("이겼습니다.");
										location.href = '/com';
									}, 100);
								} else if (response.battleResult == 'lose') {
									$("#myHp").val(0);
									setTimeout(function () {
										alert("졌습니다.");
										location.href = '/com';
									}, 100);
								} else {
									console.log("Battle Result Exception");
								}
							} else if (response.result == 'Session Problem') {
								console.log("Session Problem");
							} else {
								console.log("Result Exception");
							}
						}
					});
				}
			</script>
		</html>
