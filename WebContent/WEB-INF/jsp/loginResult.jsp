<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<% //セッションスコープからインスタンスを取得して、ユーザー型にキャストしてユーザーインスタンスに代入する
	User loginUser = (User)session.getAttribute("loginUser");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン結果画面</title>
</head>
<body>
<h1>ログイン結果画面</h1>

<% if(loginUser != null){%>
<p>ログインに成功しました。</p>
<p>ようこそ、あなたのIDは<%=loginUser.getId() %>です。</p>
<a href = "/studentInformation/Main">メイン画面へ</a>
<% }else{ %>
<p>ログインに失敗しました。</p>
<a href = "/studentInformation/login.jsp">ログイン画面へ戻る</a>
<% } %>
</body>
</html>