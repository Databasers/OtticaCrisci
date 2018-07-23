<%@page import="bean.SessioneUtente"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../CSS/header.css" type="text/css" media="all">
</head>
<body>
	<div class="header_top">
	
		<!--  <div class = "ricerca">
		
		<input type="search" id="search" name="search" class="txt" size="20">
		<input type="submit" class="btn" value="Search" onClick="searchFunction()">
		</div>-->
		
		<div class="logo">
			<a href="Homepage.jsp" class="header"><img class ="logo" src="../OC.png"
				alt="Logo"> </a>
		</div>

		<%
    	SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
   	 if ( su != null) {
    %>
    	<div class="logout">
		<a href="Utente.jsp" class="welcome header">Welcome : <%=su.getNome()%> </a>
		<br>
		<a href="/OtticaCrisci/GestioneLogin?action=logout" class="header">Logout</a>
		</div>
		<%}else { %>

		<div class="login">
			<a href="Login.jsp" class="header">Login</a>
		</div>
		<% } %>
		
	</div>
	<div class="header_list">
		<ul>
			<li><a href="Homepage.jsp" class="header">Home</a></li>
			<li><a href="Store.jsp" class="header">Store</a></li>
			<li><a href="Marchi.html" class="header">Marchi</a></li>
			<li><a href="Carrello.jsp" class="header">Carrello</a></li>
		</ul>
	</div>


</body>
</html>