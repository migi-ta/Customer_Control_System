package con;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		Boolean login_status = false;
		request.setAttribute("login_status",login_status);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	//ログイン処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String user_name = request.getParameter("user_name");
		String user_pass = request.getParameter("password");

		SqlExeStat login = new SqlExeStat();
		//ログインユーザのチェック
		List<User> login_user = new ArrayList<User>();
		login_user = login.check(user_name,user_pass);

		String u_name = login_user.get(0).getUser_name();
		String u_pass = login_user.get(0).getPassword();

		if(u_name.equals(user_name) && u_pass.equals(user_pass)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user_name);
			List<Customer> cus_all = new ArrayList<Customer>();
			cus_all = login.selectAll();
			request.setAttribute("cus_all",cus_all);

			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/customer_list.jsp");
			dispatcher.forward(request, response);
		} else {
			Boolean login_status = true;
			request.setAttribute("login_status",login_status);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}