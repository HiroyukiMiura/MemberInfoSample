<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報登録システム</title>
</head>
<body>

	<form action="../servlet/menuServlet" method="post">
		<div class="signin cf" align="center">
			<div class="formtitle">会員登録システム</div>

			<p>
				<input type="submit" name="buttonName" value="会員情報新規登録" id="regist" />
			</p>
			<p>
				<input type="submit" name="buttonName" value="会員情報変更" id="update" />
			</p>
			<p>
				<input type="submit" name="buttonName" value="会員情報削除" id="delete" />
			</p>

		</div>
	</form>

</body>
</html>