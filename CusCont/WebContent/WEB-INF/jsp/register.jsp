<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<title>顧客登録画面</title>
</head>
<body>
<%@ include file="layout.jsp" %>
<div class="mx-auto" style="width: 300px;">
	<h1 class="mb-3" style="text-align: center">顧客登録画面</h1>
	<form action="/CusCont/SqlServlet" method="post">
		<input type="hidden" name="id" value="">
		<input type="hidden" name="cus_status" value="insert">
	  <div class="mb-3">
	    <label for="customerName" class="form-label">お客様名</label>
	    <input type="text" class="form-control" id="customerName" name="name">
	  </div>
	  <div class="mb-3">
	    <label for="address" class="form-label">住所</label>
	    <input type="text" class="form-control" id="address" name="address">
	  </div>
	  <div class="mb-3">
	    <label for="phone_num" class="form-label">電話番号</label>
	    <input type="text" class="form-control" id="phone_num" name="phone_num">
	  </div>
	  <button type="submit" class="btn btn-primary">登録</button>
	</form>

	<c:url value="/CustomerServlet" var="register"/>
	<div style="text-align:center;">
		<a href="${register}">顧客管理画面</a>
	</div>
</div>
</body>
</html>