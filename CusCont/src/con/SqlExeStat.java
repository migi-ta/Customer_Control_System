package con;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlExeStat {

	final String url = "jdbc:mysql://localhost:3306/customer_db";
	final String user = "root";
	final String password  = "pumaamup7176";

	//select処理
	public List<Customer> selectAll() {
		Customer cus;
		List<Customer> list = new ArrayList<Customer>();
		String sql = "select * from customer_tb";

		try(Connection conn = DriverManager.getConnection(url,user,password)){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					cus = new Customer();
					cus.setId(rs.getInt("id"));
					cus.setName(rs.getString("name"));
					cus.setAddress(rs.getString("address"));
					cus.setPhone_num(rs.getString("phone_num"));
					list.add(cus);
				}
				System.out.println(DriverManager.getDriver(url));
		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.getStackTrace();
		}
		return list;
	}

	//select処理(指定した顧客情報を取得)
	public List<Customer> selectOne(int id) {
		Customer cus;
		List<Customer> list = new ArrayList<Customer>();
		String sql = "select * from customer_tb "
				+ "where id = ?";

		try(Connection conn = DriverManager.getConnection(url,user,password)){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				cus = new Customer();
				cus.setId(rs.getInt("id"));
				cus.setName(rs.getString("name"));
				cus.setAddress(rs.getString("address"));
				cus.setPhone_num(rs.getString("phone_num"));
				list.add(cus);
			}
		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.getStackTrace();
		}
		return list;
	}

	//insert処理
	public void insert(String name, String address, String phone_num) {
		String sql = "insert into "
				 + "customer_tb(name,address,phone_num) "
				 + "values (?,?,?)";
		try(Connection conn = DriverManager.getConnection(url,user,password)) {
			conn.setAutoCommit(false);
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				stmt.setString(1, name);
				stmt.setString(2, address);
				stmt.setString(3, phone_num);
				stmt.executeUpdate();
				conn.commit();
			} catch(Exception e) {
				conn.rollback();
				System.out.println("データが正しく処理されなかったため、rollbackを実行しました");
				throw e;
			}
		} catch(SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		}
	}

	//update処理
	public void update(int id ,String name, String address, String phone_num) {
		String sql = "update customer_tb "
				+ "set name = ?, address = ?, phone_num = ? "
				+ "where id = ?";
		try(Connection conn = DriverManager.getConnection(url,user,password)){
			conn.setAutoCommit(false);
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				stmt.setInt(4, id);
				stmt.setString(1, name);
				stmt.setString(2, address);
				stmt.setString(3, phone_num);
				stmt.executeUpdate();
				conn.commit();
			} catch(Exception e) {
				conn.rollback();
				System.out.println("データが正しく処理されなかったため、rollbackを実行しました");
				throw e;
			}
		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		}
	}

	//delete処理
	public void delete(int id) {
		String sql = "delete from customer_tb "
				+ "where id = ?";
		try(Connection conn = DriverManager.getConnection(url,user,password)){
			conn.setAutoCommit(false);
			try(PreparedStatement stmt = conn.prepareStatement(sql)){
				stmt.setInt(1, id);
				stmt.executeUpdate();
				conn.commit();
			} catch(Exception e) {
				conn.rollback();
				System.out.println("データが正しく処理されなかったため、rollbackを実行しました");
				throw e;
			}
		} catch (SQLException e) {
			System.out.println("SQLの例外が発生しました");
			e.printStackTrace();
		}
	}
}
