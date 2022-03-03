package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Student;

public class studentDao {
	//データベース接続に使用する情報をここで定義する
	private final String JDBC_URL = "jdbc:mysql://localhost/students" ;
	private final String DB_USER = "root";
	private final String DB_PASS = "hamamura1989";
	
	
	//ここから生徒情報登録メソッド
	public boolean register(Student student) {
		//データベースに接続(マネージャで道インスタンスを作る)
		try{
			//JDBCドライバ？クラスをプロジェクトにロードしておく？
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS);		
			//Select文を作っておく（道で命令文インスタンスを作る）
			String sql = "insert into personal (id,name) values(? , ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//「？」に入る内容を指定する
			pStmt.setString(1 , student.getStudentId());
			pStmt.setString(2 , student.getStudentName());
			
			
			//命令文で結果インスタンスを取得する（ここでは、追加した行数が取得される）
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}		
		}catch(SQLException | ClassNotFoundException e) {
					e.printStackTrace();
					return false;
		}
		return true;
	}
	
	
	//ここから生徒情報取得メソッド
	public List<Student> findAll(){
		
		//取得したデータを格納するリストを作っておく
		List<Student> studentList = new ArrayList<>();
		
		
		//ここから取得処理
		try{
			//JDBCドライバ？クラスをプロジェクトにロードしておく？
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS);
			//Select文を作っておく（道で命令文インスタンスを作る）
			String sql = "select ID,NAME from personal";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//select文を実行して結果を取得する
			ResultSet rs = pStmt.executeQuery();
			
			//取得した結果をリストに格納する
			while(rs.next()) {
				//取得した値を変数に代入
				String studentId = rs.getString("ID");
				String studentName = rs.getString("NAME");
				
				//Studentインスタンスを生成する
				Student student = new Student(studentId, studentName);
				//Studentインスタンスをリストに格納する
				studentList.add(student);
			}
			
		}catch(SQLException | ClassNotFoundException e) {
					e.printStackTrace();
					return null;
		}
		return studentList;
		
	}
	
	
	//ここから生地情報削除メソッド
	public boolean delete(String deleteId) {
		//データベースに接続(マネージャで道インスタンスを作る)
		try{
			//JDBCドライバ？クラスをプロジェクトにロードしておく？
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS);		
			//Delete文を作っておく（道で命令文インスタンスを作る）
			String sql = "delete from personal where id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1,deleteId);
			
			//命令文で削除を実行する
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}		
		}catch(SQLException | ClassNotFoundException e) {
					e.printStackTrace();
					return false;
		}
		return true;
	}
		
	
	
	
}