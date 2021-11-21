<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String user = (String)session.getAttribute("user"); %>
<nav class="navbar navbar-expand navbar-dark bg-light">
  <div class="container-fluid">
 	<ul class="navbar-nav">
  		<li class="nav-item">
    		ユーザー名：<%= user %>
  		</li>
	</ul>
	<ul class="nav justify-content-end">
  		<li class="nav-item">
    		<a class="nav-link active" aria-current="page" href="/CusCont/LogoutServlet">ログアウト</a>
  		</li>
	</ul>
  </div>
</nav>