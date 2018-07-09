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

<%
		Collection<OcchialeNuovo> elencoN=(Collection<OcchialeNuovo>)request.getAttribute("occhialiNuovi");
		Collection<OcchialeRotto> elencoR=(Collection<OcchialeRotto>)request.getAttribute("occhialiRotti");
		if(elencoN==null || elencoR==null){
			response.sendRedirect("/OtticaCrisci/GestioneAdmin?action=occhiali");
			return;
		}
		
	%>
	<table border="1px solid black">
		<%
		for(OcchialeNuovo c: elencoN){
		%>
		<tr>
			<td><%=c.getId() %>
			<%  if(c.getStato().equalsIgnoreCase("In lavorazione")){
			%>
			<td>Laboratorio
			<%}
			  else{
			  %>
			  <td>Deposito
			  <%} %>
			<td><%=c.getDataOrdine() %>
			<td><select name="spostamento"> 
				<option value="completato">completato 
				<%if(c.getStato().equalsIgnoreCase("In lavorazione")){ %>
						<option value="deposito" selected>In deposito				
				<%} else{ %>
						<option value="laboratorio" >In laboratorio
					<%} %>
					</select>
			<td><a href="/OtticaCrisci/GestioneAdmin?action=modOcchiali&tabella=occhiale_nuovo&code=<%=c.getId() %>">Modifica</a>
			<td>
		</tr>
		<% } %>
		<%
		for(OcchialeRotto c: elencoR){
		%>
		<tr>
			<td><%=c.getId() %>
			<%  if(c.getStato().equalsIgnoreCase("In lavorazione")){
			%>
			<td>Laboratorio
			<%}
			  else{
			  %>
			  <td>Deposito
			  <%} %>
			<td>
			<td><select name="spostamento"> 
				<option value="completato">completato 
				<%if(c.getStato().equalsIgnoreCase("In lavorazione")){ %>
						<option value="deposito" selected>In deposito				
				<%} else{ %>
						<option value="laboratorio" >In laboratorio
					<%} %>
					</select>
			<td><a href="/OtticaCrisci/GestioneAdmin?action=modOcchiali&tabella=occhiale_rotto&code=<%=c.getId() %>">Modifica</a>
			<td>
		</tr>
		<% } %>
	</table>

</body>
</html>