<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="con.Customer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% List<Customer> customer_list = (List<Customer>)request.getAttribute("customer_data");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<title>顧客管理画面</title>
</head>
<body>
<div class="mx-auto text-center" style="width: 70%;">
	<h2 class="text-center mb-3">顧客管理一覧</h2>
	<table class="table table-striped">
		<thead>
			 <tr>
			 	<th scope="col">ID</th>
			 	<th scope="col">お客様名</th>
			 	<th scope="col">住所</th>
			 	<th scope="col">電話番号</th>
			 	<th scope="col"></th>
			 </tr>
		</thead>
		<tbody>
			 <% for(Customer cus : customer_list){ %>
			 <tr>
			 	<td><%= cus.getId() %></td>
			 	<td><%= cus.getName() %></td>
			 	<td><%= cus.getAddress() %></td>
			 	<td><%= cus.getPhone_num() %></td>
			 	<td>
 			 		<c:set var="data" value="<%= cus.getId() %>" />
				 	<c:url value="/CustomerServlet" var="update">
				 		<c:param name="command" value="UPDATE"/>
				 		<c:param name="id" value="${data}"/>
				 	</c:url>
				 	<a href="${update}">更新</a>
				 	|
				 	<c:url value="/CustomerServlet" var="delete">
				 		<c:param name="command" value="DELETE"/>
				 		<c:param name="id" value="${data}"/>
				 	</c:url>
				 	<a href="${delete}">削除</a>
			 	</td>
			 </tr>
			 <% } %>
		</tbody>
	</table>
	<c:url value="/CustomerServlet" var="register">
		<c:param name="command" value="REGISTER"/>
	</c:url>
	<a href="${register}">顧客登録画面</a>
</div>
</body>
</html>