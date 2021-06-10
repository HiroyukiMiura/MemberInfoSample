<%@page import="jp.co.aforce.beans.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!-- タグライブラリの使用を宣言する（必要に応じて） -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="jp.co.aforce.beans.MemberBean"%>

<!doctype html>
<html>
<head>

<!-- 文字エンコーディングの指定 -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<!-- jsファイルの読み込み -->
<script src="js/common.js"></script>

<!-- 生年月日表示用Java処理 -->
<%
	MemberBean memberBean = (MemberBean) request.getAttribute("memberBean");
	int birthYearData = 0;
	int birthMonthData = 0;
	int birthDayData = 0;

	if (memberBean != null) {

		if (memberBean.getBirthYear() != null) {
			birthYearData = Integer.valueOf(memberBean.getBirthYear());
		}

		if (memberBean.getBirthMonth() != null) {
			birthMonthData = Integer.valueOf(memberBean.getBirthMonth());
		}

		if (memberBean.getBirthDay() != null) {
			birthDayData = Integer.valueOf(memberBean.getBirthDay());
		}
	}
%>

<title>会員情報登録システム</title>
</head>
<body>


	<div class="form" align="center">

		<span class="emsg">${memberBean.emsg}</span> <span class="commsg">${memberBean.commsg}</span>

		<div class="signin cf" align="center">
			<div class="formtitle">会員情報削除画面</div>

			<form action="../servlet/deleteServlet" method="post">

				<p>
					会員番号
					<input type="text" name="memberNo"
						value="${memberBean.memberNo}">
					<input type="submit" name="buttonName" value="表示" />
				</p>

				<table border="1">
					<tbody>
						<tr>
							<td>名前</td>
							<td><input type="text" name="name"
								value="${memberBean.name}" disabled></td>
						</tr>
						<tr>
							<td>年齢</td>
							<td><input type="text" name="age" value="${memberBean.age}" disabled></td>
						</tr>
						<tr>
							<td>生年月日</td>
							<td><select name="birthYear" disabled>
									<%
										out.println("<option value=" + "" + ">" + "" + "</option>");
										for (int i = 1920; i <= 2020; i++) {
											if (birthYearData == i) {
												out.println("<option value=" + i + " selected = \"selected\">" + i + "</option>");
											} else {
												out.println("<option value=" + i + ">" + i + "</option>");
											}
										}
									%>
							</select>年 <select name="birthMonth" disabled>

									<%
										out.println("<option value=" + "" + ">" + "" + "</option>");
										for (int i = 1; i <= 12; i++) {
											if (birthMonthData == i) {
												out.println("<option value=" + i + " selected = \"selected\">" + i + "</option>");
											} else {
												out.println("<option value=" + i + ">" + i + "</option>");
											}
										}
									%>

							</select>月 <select name="birthDay" disabled>
									<%
										out.println("<option value=" + "" + ">" + "" + "</option>");
										for (int i = 1; i <= 31; i++) {
											if (birthDayData == i) {
												out.println("<option value=" + i + " selected = \"selected\">" + i + "</option>");
											} else {
												out.println("<option value=" + i + ">" + i + "</option>");
											}
										}
									%>

							</select>日</td>
						</tr>


					</tbody>
				</table>

				<input type="submit" name="buttonName" value="戻る" /> <input
					type="submit" name="buttonName" value="削除" id="regist"
					onclick="return showConfirmDialog('削除')" />

			</form>

		</div>
	</div>

</body>
</html>