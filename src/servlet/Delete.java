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


@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストスコープから削除依頼のきた生徒のIDを取得する
		request.setCharacterEncoding("UTF-8");
		String deleteId = request.getParameter("deleteId");
		
		//削除メソッドの実行
		studentDao dao = new studentDao();
		dao.delete(deleteId);
		
		//データベースからStudentのリストを取得する
		List<Student> studentList = dao.findAll();	
		//データベースから取得した生徒情報をリクエストスコープに保存しておく
		request.setAttribute("studentList", studentList);
				
		//main.jspへフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
        dispatcher.forward(request,response);
        
	}

}
