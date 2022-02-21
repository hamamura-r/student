package model;

public class LoginLogic {
	//ユーザーインスタンスを生成して、そのパスワードがあっていれば真とする
	public boolean execute(User user) {
		if(user.getPass().equals("1234")) {return true;}
		return false;
	}
}
