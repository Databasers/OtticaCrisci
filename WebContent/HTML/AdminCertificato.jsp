<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="bean.*,managerBean.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!-- Esempio gestione della parte dei certificati -->

	<%
		Collection<Certificato> elenco=(Collection<Certificato>)request.getAttribute("certificati");
		if(elenco==null){
			response.sendRedirect("/OtticaCrisci/GestioneAdmin?action=certificato");
			return;
		}
		
	%>
	<table border="1px solid black">
		<%
		for(Certificato c: elenco){
		%>
		<tr>
			<td><%=c.getcF() %>
			<td><%=c.getUrl() %>
			<td><%=c.isValido() %>
			<td><select name="valido"> <option value="true">true <option value="false" selected>false</select>
			<td><a href="/OtticaCrisci/GestioneAdmin?action=modCertificato&code=<%=c.getcF() %>">modifica</a>
			<td>
		</tr>
		<% } %>
	</table>
	
	<hr>
	<br>
	<form action="/OtticaCrisci/GestioneAdmin?action=cliente" method="post">
	<p>Cerca un cliente <input type="text" name="cf" max="16"> <input type="submit"> <% String trovatoT=(String)request.getAttribute("trovato");
																				if(trovatoT!=null){
																					%><%=trovatoT %><% 
																					boolean trovato=Boolean.parseBoolean(trovatoT);
																					if(trovato){ %>
																						<font color="red">Non esiste un cliente con codice fiscale uguale</font>
																				   <%} 
																					else{ %>
																						<font color="red">Non esiste un cliente con codice fiscale uguale</font>
																					<% } 
																						}%>
																				  </p>
	</form>
	
	<% Certificato c=(Certificato)request.getAttribute("certificatoCliente"); 
	   if(c!=null){
	%>
	<table border="1px solid black">
		<tr>
			<td><%=c.getcF() %>
			<td><%=c.getUrl() %>
			<td><%=c.isValido() %>
			<td><select name="valido"> <option value="true">true <option value="false" selected>false</select>
			<td><a href="/OtticaCrisci/GestioneAdmin?action=modCertificato&code=<%=c.getcF() %>">modifica</a>
			<td>
		</tr>
		<% } %>
	</table>


</body>
</html>