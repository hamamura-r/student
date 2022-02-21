package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.LoginLogic;

import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインリクエストが送られてきたらpostメソッドを実行する
		
		//ログインリクエストのリクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass= request.getParameter("pass");
		
		//取得した値を用いてユーザーインスタンスを生成
		User user = new User(id,pass);
		
		//LoginLogicインスタンスを生成する。
		LoginLogic loginLogic = new LoginLogic();
		//インスタンスのメソッドでログイン可能かどうかを判定する
		boolean isLogin = loginLogic.execute(user);
		//ログイン可能なら、ユーザーインスタンスをセッションスコープに保存しておく
		if(isLogin) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user); 
		}
		//ログインの可否に関わらず、結果画面にフォワードする
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
        dispatcher.forward(request,response);
		
		
		
		
		
		
	}

}
