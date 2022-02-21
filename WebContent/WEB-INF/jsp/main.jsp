<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User , model.Student , java.util.List" %>
<%
//セッションスコープ保存されているログインユーザー情報を取得する
User loginUser = (User)session.getAttribute("loginUser");
%>
<%
//アプリケーションスコープ保存されているつぶやき情報を取得する
List<Student> studentList = (List<Student>)application.getAttribute("studentList");
%>
<%
//リクエストスコープにエラーメッセージがあれば取得する
String errorMsg = (String)request.getAttribute("errorMsg");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メイン画面</title>
</head>
<body>
<h1>メイン画面</h1>

<!-- ログイン情報確認 -->
<p>アカウントID「<%=loginUser.getId() %>」でログイン中</p>
<p><a href="/studentInformation/Logout">ログアウト</a></p>

<!-- 生徒の登録フォーム -->
<form action="/studentInformation/Main" method="post"> 
氏名：<input type="text" name="name"><br>
ID：<input type="text" name="id"><br>
<input type="submit" value="IDと氏名を登録">
</form>

<!-- 項目不足のときのエラー表示 -->
<% if(errorMsg != null){ %>
	<p><%=errorMsg %></p>
<% }%>

<!-- 生徒リストを表示 -->
<% for(Student student : studentList){ %>
	<p>
	ID：<%=student.getStudentId() %>　,	氏名：<%=student.getStudentName() %>さん
	</p>
<% }%>
</body>
</html>