<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>生徒情報システム</title>
</head>
<body>
<h1>生徒情報システムログイン画面</h1>
<form action="/sudentInformation/Login" method="post">
ユーザーID：<input type="text" name="id"><br>
パスワード：<input type="password" name="pass"><br>
<input type="submit" value="ログイン">
</form>
</body>
</html>