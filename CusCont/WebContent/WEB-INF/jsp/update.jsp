<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="con.Customer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% List<Customer> cus_info = (List<Customer>)request.getAttribute("cus_info");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<title>顧客情報変更画面</title>
</head>
<body>
<div class="mx-auto" style="width: 300px;">
	<h1 class="mb-3" style="text-align: center">顧客情報変更</h1>
	<form action="/CusCont/SqlServlet" method="post">
		<input type="hidden" name="cus_status" value="update">
		<input type="hidden" name="id" value="<%= cus_info.get(0).getId() %>">
	  <div class="mb-3">
	    <label class="form-label" for="customerName">お客様名</label>
	    <input type="text" name="name" class="form-control" id="customerName"  value="<%= cus_info.get(0).getName() %>">
	  </div>
	  <div class="mb-3">
	    <label class="form-label" for="address">住所</label>
	    <input type="text" name="address" class="form-control" id="address" value="<%= cus_info.get(0).getAddress() %>">
	  </div>
	  <div class="mb-3">
	    <label class="form-label" for="phone_num">電話番号</label>
	    <input type="text" name="phone_num" class="form-control" id="phone_num" value="<%= cus_info.get(0).getPhone_num() %>">
	  </div>

	  <button type="submit" class="btn btn-primary">更新</button>
	</form>
</div>
</body>
</html>