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


	<!-- 
		E' una tabella n * 1 dove son presenti gli articoli con foto, nome, prezzo
		Ogni riga ha al termine un pulsante "remove"
	-->
	<%
	
	
	
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
				<td >
				<img src = "<%=oggetto.getUrlImmagine()%>" > <%=""+ oggetto.getMarchio()  + " " + oggetto.getModello() + " " + oggetto.getColore() + "\n"%>
				 <span class="prezzi"><%= oggetto.getPrezzo()%></span>
				 <button type="submit" formaction="https:localhost:8080/OtticaCrisci/gestione?action=delCart&id=<%=oggetto.getId()%>">Rimuovi</button>
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
		<span id = "Tot_Num"><%=h%></span>
	<!-- Creare funzione per chiamare il checkout dal pulsante -->
		<button type="submit" formaction="https:localhost:8080/OtticaCrisci/gestione?action=checkout">Conferma</button>
	<p/>
	
	
	
</body>
</html>