<%@ page import="java.util.*" %>
<%@ page import="bean.*,it.unisa.model.*" %>
<!DOCTYPE html>
<html>

	<!-- Pagina in cui l'utente può rivedere l'ordine che sta per richiedere -->


<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import = "it.unisa.model.Carrello, bean.Frame, java.util.List, servlet.GestioneCarrello" %>


	<!-- 
		E' una tabella n * 1 dove son presenti gli articoli con foto, nome, prezzo
		Ogni riga ha al termine un pulsante "remove"
	-->
	<%
	Cookie[] x=request.getCookies();
	for(Cookie e: x){
		%><p>Il cookie <%=e.getName() %> vale <%=e.getValue() %></p> <% 
	}
	
	
	int h = 0;
	Carrello<Frame> carrello;
	GestioneCarrello gestione = new GestioneCarrello();
	
		carrello=(Carrello<Frame>)request.getSession().getAttribute("carrello");
		if(carrello==null) { %>
		<p>Non ci sono elementi</p> 
		<%}
		else{
		%>
	<table>
		<!-- Inserire variabile globale per il totale -->
			<%
			List<Frame> lista = carrello.getList();
			Frame[] elenco=lista.toArray(new Frame[0]);
		for(Frame oggetto: elenco){
			h += oggetto.getPrezzo();
			%>
			<tr>
				<td>
				<img src = <%= oggetto.getMarchio()%> > <%=""+ oggetto.getMarchio()  + " " + oggetto.getModello() + " " + oggetto.getColore() + "\n"%>
				 <%= oggetto.getPrezzo()%>
				 <a href="/OtticaCrisci/gestione?action=delCart&id=<%=oggetto.getId()%>">Rimuovi</a>
				 </td>
			</tr>
			
		<%
		}
		}
	%>
	
	</table>
	
	
	<!-- 
		Aggiungere nel CSS il float a sinistra su id "Totale"
	-->
	<p id = "Totale">
		<%= h %>
	<!-- Creare funzione per chiamare il checkout dal pulsante -->
		<a href="/OtticaCrisci/gestione?action=checkout">Conferma</a>
	<p/>
	
	
	
</body>
</html>