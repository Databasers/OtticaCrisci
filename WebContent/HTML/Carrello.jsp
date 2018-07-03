<!DOCTYPE html>
<%@page import="it.unisa.model.Carrello"%>
<html>

	<!-- Pagina in cui l'utente può rivedere l'ordine che sta per richiedere -->


<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import = "it.unisa.model.Carrello, bean.Frame, java.util.List" %>
<%!	
	int h = 0;
	Carrello<Frame> carrello; %>

	<!-- 
		E' una tabella n * 1 dove son presenti gli articoli con foto, nome, prezzo
		Ogni riga ha al termine un pulsante "remove"
	-->
	
	<table>
		<td id = "Riga">
		<!-- Inserire variabile globale per il totale -->
			<%
			List<Frame> lista = carrello.getList();
		while(!lista.isEmpty()){
			Frame oggetto = lista.remove(0);
			h += oggetto.getPrezzo();
			%>
			<tr>
				<img src = <%= oggetto.getMarchio()%>> <%=""+ oggetto.getMarchio()  + " " + oggetto.getModello() + " " + oggetto.getColore() + "\n"%>
				 <%= oggetto.getPrezzo()%>
				<button class = "rim">Rimuovi</button>  
			<tr/>
			
		<%
		}

	%>
		</td>
	</table>
	
	
	<!-- 
		Aggiungere nel CSS il float a sinistra su id "Totale"
	-->
	<p id = "Totale">
		<%= h %>
	<!-- Creare funzione per chiamare il checkout dal pulsante -->
		<button ="Checkout">Conferma</button>
	<p/>
	
	
	
</body>
</html>