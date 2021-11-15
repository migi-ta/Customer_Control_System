package con;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SqlServlet
 */
@WebServlet("/SqlServlet")
public class SqlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String command = request.getParameter("command");

		if(command.equals("REGISTER")) {
			RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
			dispatcher.forward(request, response);
		}

		int id = Integer.parseInt(request.getParameter("id"));

		SqlExeStat sql = new SqlExeStat();
		switch(command) {
		case "UPDATE":
			List<Customer> cus_info = sql.selectOne(id);
			request.setAttribute("cus_info", cus_info);
			RequestDispatcher dispatcher1 =
					request.getRequestDispatcher("WEB-INF/jsp/update.jsp");
			dispatcher1.forward(request, response);
		break;

		case "DELETE":
			sql.delete(id);
			List<Customer> cus_all = sql.selectAll();
			request.setAttribute("cus_all", cus_all);
			RequestDispatcher dispatcher2 =
					request.getRequestDispatcher("WEB-INF/jsp/customer_list.jsp");
			dispatcher2.forward(request, response);
		break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		//パラメータ取得
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone_num = request.getParameter("phone_num");
		String cus_status = request.getParameter("cus_status");

		SqlExeStat sql = new SqlExeStat();

		switch(cus_status) {
		//顧客情報登録処理
		case "insert":
			sql.insert(name,address,phone_num);
			break;
		//顧客情報更新処理
		case "update":
			int id = Integer.parseInt(request.getParameter("id"));
			sql.update(id,name,address,phone_num);
			break;
		}

		//顧客情報取得
		List<Customer> list = sql.selectAll();
		request.setAttribute("cus_all", list);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/customer_list.jsp");
		dispatcher.forward(request, response);
	}
}
