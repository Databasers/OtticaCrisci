<!DOCTYPE html>
<html>

	<!-- Pagina in cui l'utente può rivedere l'ordine che sta per richiedere -->


<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import = "it.unisa.model.Carrello, bean.Frame, java.util.List, servlet.GestioneCarrello" %>
	<%!	
	int h = 0;
	Carrello<Frame> carrello = new Carrello<>();
	GestioneCarrello gestione = new GestioneCarrello();
	%>

	<!-- 
		E' una tabella n * 1 dove son presenti gli articoli con foto, nome, prezzo
		Ogni riga ha al termine un pulsante "remove"
	-->
	
	<table>
		<!-- Inserire variabile globale per il totale -->
			<%
			List<Frame> lista = carrello.getList();
		while(!lista.isEmpty()){
			Frame oggetto = lista.remove(0);
			h += oggetto.getPrezzo();
			%>
			<tr>
				<td>
				<img src = <%= oggetto.getMarchio()%> > <%=""+ oggetto.getMarchio()  + " " + oggetto.getModello() + " " + oggetto.getColore() + "\n"%>
				 <%= oggetto.getPrezzo()%>
				 <button type="submit" formaction="/gestione?action=delCart&id="+ <%=oggetto.getId()%>>Rimuovi</button>
				 </td>
			</tr>
			
		<%
		}

	%>
	
	</table>
	
	
	<!-- 
		Aggiungere nel CSS il float a sinistra su id "Totale"
	-->
	<p id = "Totale">
		<%= h %>
	<!-- Creare funzione per chiamare il checkout dal pulsante -->
		<button type="submit" formaction="/gestione?action=checkout">Conferma</button>
	<p/>
	
	
	
</body>
</html>