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
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//顧客登録画面への遷移
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		SqlExeStat sql = new SqlExeStat();
		String command = request.getParameter("command");
		int id = Integer.parseInt(request.getParameter("id"));

		switch(command) {
		case "UPDATE":
			List<Customer> cus_info = sql.selectOne(id);
			request.setAttribute("cus_info", cus_info);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/update.jsp");
			dispatcher.forward(request, response);
		break;

		case "DELETE":
			sql.delete(id);
			RequestDispatcher dispatcher_list1 =
					request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
			dispatcher_list1.forward(request, response);
		break;

		default:
			RequestDispatcher dispatcher_list =
				request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
			dispatcher_list.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		//パラメータ取得
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone_num = request.getParameter("phone_num");
		String cus_status = request.getParameter("cus_status");

		SqlExeStat sql = new SqlExeStat();

		switch(cus_status) {
		case "insert":
			//sql.insert(name,address,phone_num);
			break;
		case "update":
			sql.update(id,name,address,phone_num);
		}

		//顧客情報取得
		List<Customer> list = sql.selectAll();
		request.setAttribute("customer_data", list);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/customer_list.jsp");
		dispatcher.forward(request, response);
	}
}
