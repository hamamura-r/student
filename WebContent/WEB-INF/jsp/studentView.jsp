<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- studentクラスをインポートしておく -->
<%@ page import = "model.Student" %>
<!-- リクエストスコープの生徒インスタンスを取得しておく --> 
<% Student student = (Student)request.getAttribute("updateStudent");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>生徒情報編集画面</title>
</head>
<body>
<h1>生徒情報編集画面</h1>
<p>修正したい項目のみ再入力してください</p>
<form action="/studentInformation/Update">
<input type="hidden" name="oldId" value="<%= student.getStudentId()%>">
<input type="text" name="id" value="<%=student.getStudentId() %>">
<input type="text" name="name" value="<%=student.getStudentName() %>">
<input type="submit" value="保存">
</form>
<a href = "/studentInformation/Main">編集を中止してメイン画面へ戻る</a>
<!-- 生徒情報を表示する。
ただし、それぞれの情報をフォームのインプットにして、
各value値に登録済のデータが初期状態で表示されるようにする。
最後に「保存」ボタンでデータベースへ再登録する処理を記述
すでにある新規登録とは別に、更新のメソッドを使う。

あと、「戻る」ボタンも作成する。
これを押すと、ログイン画面へ戻る。つまり、「main.java」にとばせばよい。-->


</body>
</html>