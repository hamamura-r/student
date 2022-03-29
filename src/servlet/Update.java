package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.studentDao;
import model.Student;


@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//編集画面からの保存リクエストがきたらこれを実行する
		
		//リクエストパラメータの情報を取得する
		request.setCharacterEncoding("UTF-8");
		String oldId = request.getParameter("oldId");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		if(id != null && id.length() != 0 && name != null && name.length() != 0) {
		//取得した情報を元に、生徒インスタンスを生成しておく
		Student student = new Student(id,name);		
		//studentDaoの更新メソッドを実行する
		studentDao studentDao = new studentDao();
		studentDao.update(student,oldId);
		}else {
			request.setAttribute("errorMsg", "修正時の入力項目が不足しています");
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//編集画面への遷移リクエストがきたらこれを実行する
		//リクエストパラメータにある、編集したい生徒のIDを取得する
		request.setCharacterEncoding("UTF-8");
		String updateId = request.getParameter("updateId");
		
		//取得したIDを使って、生徒情報をデータベースより取り出して、インスタンスを生成する
			//studentDaoクラスに、IDを指定して生徒情報を取り出すメソッドを記述しておく
		studentDao studentDao = new studentDao();
		Student student = studentDao.findOne(updateId);		
		
		//生成したインスタンスをリクエストスコープへ保存する
		request.setAttribute("updateStudent", student);
		//生徒情報を表示するページへフォワードする（studentView.jsp）
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/studentView.jsp");
		dispatcher.forward(request, response);
	}

}
