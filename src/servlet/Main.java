package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Student;
import model.User;
import model.registerStudentLogic;


@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログイン後、メイン画面へのリクエストがきたらこれを実行
		
		//アプリケーションスコープから、登録済の生徒情報を取得する
		ServletContext application = this.getServletContext();
		//アプリケーションスコープ内のstudentリストのインスタンスを取得する。なければ生成してアプリケーションスコープへ保存する。
		List<Student> studentList = (List<Student>)application.getAttribute("studentList");
		if(studentList == null) {
			studentList = new ArrayList<>();
			application.setAttribute("studentList" , studentList);
		}

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
		if(name != null && name.length() != 0 && id != null && id.length() != 0) {
			//取得し生徒の氏名とIDを使って、Studentインスタンスを生成する
			Student student = new Student(name,id);
			
			//アプリケーションスコープから、生徒情報のリストを取得する
			ServletContext application = this.getServletContext();
			List<Student> studentList = (List<Student>)application.getAttribute("studentList");
			//生徒情報のリストに、Studentインスタンスを格納する
			
			//registerStudentLogicインスタンスのexecuteメソッドを使って、生徒情報リストにStudentインスタンスを追加する。
			registerStudentLogic registerStudentLogic = new registerStudentLogic();
			registerStudentLogic.execute(student , studentList);
		}else {
			request.setAttribute("errorMsg","入力項目が不足しています");
		}
		
		//メイン画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        dispatcher.forward(request,response);
		
	}

}
