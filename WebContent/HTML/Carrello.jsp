<%@ page import="java.util.*" %>
<%@ page import="bean.*,it.unisa.model.*" %>
<!DOCTYPE html>

<html>

	<!-- Pagina in cui l'utente può rivedere l'ordine che sta per richiedere -->


<head>
<LINK rel="stylesheet" href="../CSS/Carrello.css" type="text/css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import = "it.unisa.model.Carrello, bean.Frame, java.util.List, servlet.GestioneCarrello" %>
<div class= "header">
<%@ include file="Header.jsp"%>
</div>

	<!-- 
		E' una tabella n * 1 dove son presenti gli articoli con foto, nome, prezzo
		Ogni riga ha al termine un pulsante "remove"
	-->
	
	
	
	
	<div id ="quadro">
	<table>
	<%
	int h = 0;
	Carrello<Frame> carrello;
	GestioneCarrello gestione = new GestioneCarrello();
	
		carrello=(Carrello<Frame>)request.getSession().getAttribute("carrello");
		if(carrello==null || carrello.getList().isEmpty()) { %>
		<p id="vuoto"><img  src="<%=request.getContextPath()%>/Immagini_Frame/CarrelloVuoto.jpg" width=140px>Non ci sono elementi nel tuo carrello. Compra qualcosa! <span id="ala">Luigi Crisci è scemo</span></p>
		<%}
		else{
		%>
			<%
			List<Frame> lista = carrello.getList();
			Frame[] elenco=lista.toArray(new Frame[0]);
			%>
			
			<% for(Frame oggetto: elenco){
			h += oggetto.getPrezzo();
			%>
			<tr>
				<td>
				<div class="product">
				<form method="post">
				<img class="prductImg" src = "<%=request.getContextPath()%>/Immagini_Frame/<%=oggetto.getUrlImmagine()%>" > 
				<div class="desc"><%=oggetto.getMarchio() + " " + oggetto.getModello() + " " + oggetto.getColore() + "\n"%></div>
				 <div class="destra"><%=oggetto.getPrezzo()%></div>
				 <button class="btn" type="submit" formaction="/OtticaCrisci/gestione?action=delCart&id=<%=oggetto.getId()%>">Rimuovi</button>
				 </form>
				 </div>
				 </td>
			</tr>
			
		<%
		
		}
		}
	%>
	
	</table>
	</div>
	
	<!-- 
		Aggiungere nel CSS il float a sinistra su id "Totale"
	-->
	<div id = "Totale">
		<div id = "Tot_Num">Totale carrello: <%=h%></div>
		<form method="post">
		<button type="submit" formaction="/OtticaCrisci/gestione?action=checkout">Conferma</button>
		</form>
	</div>
	

</body>
</html>