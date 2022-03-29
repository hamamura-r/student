package model;

import java.io.Serializable;

public class Student implements Serializable{
	//生徒の情報を定義
	private String studentName;
	private String studentId;
	
	//コンストラクタを定義
	public Student() {}
	public Student(String id,String name) {
		this.studentName = name;
		this.studentId = id;
	}
	
	//生徒名とパスワードの取得メソッドを定義
	public String getStudentName() {return studentName;}
	public String getStudentId() {return studentId;}
	
	//生徒名とパスワードの再登録メソッドを定義
	public void setStudentName(String name) {studentName = name; }
	public void setStudentId(String id) {studentId = id; }
}
