<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User , model.Student , java.util.List" %>
<%
//セッションスコープ保存されているログインユーザー情報を取得する
User loginUser = (User)session.getAttribute("loginUser");
%>

<%
//リクエストスコープの生徒情報を取得する
List<Student> studentList = (List<Student>)request.getAttribute("studentList");
//リクエストスコープにエラーメッセージがあれば取得する
String errorMsg = (String)request.getAttribute("errorMsg");

//削除したときに、削除処理依頼がきた生徒のIDがリクエストパラメータとしてきているので、それを取得しておく
request.setCharacterEncoding("UTF-8");
String deleteId = request.getParameter("deleteId");
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

<!-- 生徒登録の入力項目不足のときのエラー表示 -->
<% if(errorMsg != null){ %>
	<p><%=errorMsg %></p>
<% }%>

<!-- 削除処理をしたときの一時的な表示 -->
<%if(deleteId != null){ %>
ID:<%=deleteId%>の生徒を削除しました。<br>
<%} %>

<!-- 登録済の生徒リストを表示 -->
<% for(Student student : studentList){ %>
	<form action = "/studentInformation/Update"  method = "post" style="display:inline-block">
	ID：<%=student.getStudentId() %>　氏名：<%=student.getStudentName() %>さん　
	<input type="hidden" name="updateId" value=<%=student.getStudentId()%>>
	<input type="submit" value="編集">
	</form>
	<form action = "/studentInformation/Delete"  method = "post" style="display:inline-block">
	<input type="hidden" name="deleteId" value=<%=student.getStudentId()%>>
	<input type="submit" value="削除">
	</form>
	<br>
<% }%>
</body>
</html>