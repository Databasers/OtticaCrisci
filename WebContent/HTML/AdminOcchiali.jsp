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
		if(elencoN==null && elencoR==null){
			response.sendRedirect("/OtticaCrisci/GestioneAdmin?action=occhiali");
			return;
		}
	
	if(elencoN.isEmpty() && elencoR.isEmpty()){
		%><h3>Non ci sono ordini da gestire</h3>
	<%} 
	else{
	%>
	<table border="1px solid black">
		<%
		if(!elencoN.isEmpty()){
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
			<td><a href="/OtticaCrisci/GestioneAdmin?action=modOcchiali&spostamento=completato&tabella=occhiale_nuovo&code=<%=c.getId() %>"> Completato </a>
				<%if(c.getStato().equalsIgnoreCase("In lavorazione")){ %>
						<a href="/OtticaCrisci/GestioneAdmin?action=modOcchiali&spostamento=deposito&tabella=occhiale_nuovo&code=<%=c.getId() %>"> Deposito </a>				
				<%} else{ %>
						<a href="/OtticaCrisci/GestioneAdmin?action=modOcchiali&spostamento=laboratorio&tabella=occhiale_nuovo&code=<%=c.getId() %>"> Laboratorio </a>
					<%} %>
		</tr>
		<% }} %>
		<%
		if(!elencoR.isEmpty()){
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
			<td><%=c.getDataConsegna() %>
			<td><a href="/OtticaCrisci/GestioneAdmin?action=modOcchiali&spostamento=completato&tabella=occhiale_rotto&code=<%=c.getId() %>"> Completato </a>
				<%if(c.getStato().equalsIgnoreCase("In lavorazione")){ %>
						<a href="/OtticaCrisci/GestioneAdmin?action=modOcchiali&spostamento=deposito&tabella=occhiale_rotto&code=<%=c.getId() %>"> Deposito </a>				
				<%} else{ %>
						<a href="/OtticaCrisci/GestioneAdmin?action=modOcchiali&spostamento=laboratorio&tabella=occhiale_rotto&code=<%=c.getId() %>"> Laboratorio </a>
					<%} %>
		</tr>
		<% }}} %>
	</table>

</body>
</html>