package model;

public class User {
	//ユーザーのIDとパスワードを定義
	private String id;
	private String pass;
	//コンストラクタでIDとパスワードを設定する
	public User() {}
	public User(String id,String pass) {
		this.id = id;
		this.pass = pass;
	}
	//IDとパスワードを取得するメソッド
	public String getId() {return id;}
	public String getPass() {return pass;}
}
