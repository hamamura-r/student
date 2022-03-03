package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.studentDao;
import model.Student;
import model.User;


@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログイン後、メイン画面へのリクエストがきたらこれを実行
		
		//データベースからStudentのリストを取得する
		studentDao dao = new studentDao();
		List<Student> studentList = dao.findAll();	
		//データベースから取得した生徒情報をリクエストスコープに保存しておく
		request.setAttribute("studentList", studentList);

		//セッションスコープを取得する。
		HttpSession session = request.getSession();
		//セッションスコープ内のloginUserインスタンスを取得する。なければ。トップ画面へリダイレクトする。あれば、メイン画面へフォワードする。
		User loginUser = (User) session.getAttribute("loginUser");
		if(loginUser == null) {
			response.sendRedirect("/studentInformation/login.jsp");
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
	        dispatcher.forward(request,response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//生徒情報の登録リクエストが送られてきたらここを実行する
		
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		
		//入力項目に不備がないか確認
		if(name != null && name.length() != 0 && id!= null && id.length() != 0) {
			//取得した生徒の氏名とIDを使って、Studentインスタンスを生成する
			Student student = new Student(id,name);
			//Daoインスタンスを生成して、データベースに生徒情報を登録する
			studentDao dao = new studentDao();
			dao.register(student);
			
		}else {
			request.setAttribute("errorMsg","入力項目が不足しています");
		}
		
		//データベースからStudentのリストを取得する
		studentDao dao = new studentDao();
		List<Student> studentList = dao.findAll();	
		//データベースから取得した生徒情報をリクエストスコープに保存しておく
		request.setAttribute("studentList", studentList);
		
		//メイン画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        dispatcher.forward(request,response);
		
	}

}
