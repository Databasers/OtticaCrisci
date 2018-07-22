<!DOCTYPE html>
<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@page import="java.util.LinkedList"%>
<%@page import="bean.Frame, java.util.List, servlet.GestioneCarrello, java.util.*"%>
<html>
<style>

</style>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<LINK rel="stylesheet" href="../CSS/Temp_FS.css" type="text/css">
<LINK rel="stylesheet" href="../CSS/TabellaFiltri.css" type="text/css">
<LINK rel="stylesheet" href="../CSS/Store.css" type="text/css">

</head>
<body>

	<div class= "header">
<%@ include file="Header.jsp"%>
</div>
	<%
		Collection<Frame> elenco=(Collection<Frame>)request.getSession().getAttribute("Frame");
		if(elenco==null){
			response.sendRedirect("/OtticaCrisci/GestioneNegozio?action=retrieve");
			return;
		}
		else
			request.getSession().removeAttribute("Frame");
	%>

<!--  
	<aside class="filtri">
		Questo è un aside per i filtri
		<ol>
			<li><input type="checkbox" name="Filtro1" value="Marca1">Marca 1</li>
			<li><input type="checkbox" name="Filtro1" value="Marca2">Marca 2</li>
		</ol>
		<button class="ok">Conferma</button>
		
	</aside>
	-->
	
	<%--  if(elenco.isEmpty()){
		--%><p>Non ci sono frame</p> <%--
	}
	else{
		Frame[] lista=elenco.toArray(new Frame[0]);
		String select = "colonna";
		GestioneCarrello gestione = new GestioneCarrello();
		
		--%>
			
			<%--for(Frame x: lista) {
				
				--%>
			<% for (int i =0; i<8; i++){ %>
				
				 <div class= "product"> 
					<img class= "productImg" src=  "../marche.jpg"<%-- x.getUrlImmagine() --%> alt="Img" style= "width:33%">
					<div class= "description">
						<h3>mamma <%-- x.getModello() --%></h3>
						<h3>bella <%-- x.getMarchio() --%></h3>
						<h3>rossa<%-- x.getColore() --%></h3>
						<h3>costosa in<%-- x.getPrezzo() --%> Euro</h3>
						
						<button type="submit" formaction="/gestione?action=addCart&id=
						<%--x.getId()--%>">Aggiungi al carrello</button>
					</div>
				</div>
				
<%			} %>
		
		
		
		<%--
		else if(select == "tabella"){%>
		<table width = 100%>
			<tr>
				<td width=50%>
			<% int h = lista.length/2;
			for(int i = 0; i< h; i++){
				Frame x = lista[i];%>
				<div id="sinistra" style="float: left">
				<img src = "<%=x.getId()%>"/> <p id = ><%=x.getMarchio()%> <%=x.getModello()%> <%=x.getMateriale()%> <%=x.getColore()%></p>
				</div>
				<div id = "destra" style="float: right;">
				<p><%=x.getPrezzo()%></p>
				<button type="submit" formaction="/gestione?action=addCart&id=<%=x.getId()%>">Aggiungi al carrello</button>
				</div>
			<% } %>
				<td>
				<%for(int i=h; i<lista.length; i++) {
					Frame x = lista[i];
					%>
					<div id="sinistra" style="float: left">
					<img src = "<%=x.getId()%>"/> <p id = ><%=x.getMarchio()%> <%=x.getModello()%> <%=x.getMateriale()%> <%=x.getColore()%></p>
					</div>
					<div id = "destra" style="float: right;">
					<p><%=x.getPrezzo()%></p>
					<button type="submit" formaction="/gestione?action=addCart&id=<%=x.getId()%>">Aggiungi al carrello</button>
					</div>
					<hr/>
				<%}%> 
			</tr>
		</table>	
		<% } --%>
		<%-- }--%>
	
	
	
	
	
	

</body>
</html>