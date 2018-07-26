<!DOCTYPE html>
<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@page import="java.util.LinkedList"%>
<%@page import="bean.Frame, java.util.List, servlet.GestioneCarrello, java.util.*"%>
<html>
<style>

</style>
<head>
<meta charset="ISO-8859-1">
<title>Occhiali da vista e da sole</title>
<LINK rel="stylesheet" href="<%=request.getContextPath()%>/CSS/Temp_FS.css" type="text/css">
<LINK rel="stylesheet" href="<%=request.getContextPath()%>/CSS/TabellaFiltri.css" type="text/css">
<LINK rel="stylesheet" href="<%=request.getContextPath()%>/CSS/Store.css" type="text/css">

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
	
	<%  if(elenco.isEmpty()){
		%><p>Non ci sono frame</p> <%
	}
	else{
		
		Frame[] lista=elenco.toArray(new Frame[0]);
		GestioneCarrello gestione = new GestioneCarrello();
		
		
			
			for(Frame x: lista) {
				System.out.println(x.getUrlImmagine());
				%>
			
				
				 <div class= "product"> 
					<img class= "productImg" src= "<%=request.getContextPath()%>/Immagini_Frame/<%= x.getUrlImmagine() %>" alt="Img" style= "width:33%">
					<div class= "description">
						<div class = "desc">
							<h3> <%= x.getModello() %> <%= x.getColore()%></h3>
							<h3> <%= x.getMarchio() %></h3>
							<h3><%= x.getPrezzo() %> Euro</h3>
						</div>
						<div class = "btn">
							<form method= "post">
							<button type="submit" formaction="/OtticaCrisci/gestione?action=addCart&id=
							<%=x.getId()%>">Aggiungi al carrello</button>
							</form>
						</div>
					</div>
				</div>
				
<%			} 
		}%>
		
	

</body>
</html>