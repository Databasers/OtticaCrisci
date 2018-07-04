<!DOCTYPE html>
<%@page import="bean.Frame, java.util.List, servlet.GestioneCarrello"%>
<html>
<style>

</style>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<LINK rel="stylesheet" href="../CSS/Temp_FS.css" type="text/css">
<LINK rel="stylesheet" href="../CSS/TabellaFiltri.css" type="text/css">
</head>
<body>

	<aside class="filtri">
		Questo è un aside per i filtri
		<ol>
			<li><input type="checkbox" name="Filtro1" value="Marca1">Marca 1</li>
			<li><input type="checkbox" name="Filtro1" value="Marca2">Marca 2</li>
		</ol>
		<button class="ok">Conferma</button>
		
	</aside>
	<%! List<Frame> lista = new List<>;
		String select = "tabella";
		GestioneCarrello gestione = new GestioneCarrello();%>
	<%
	//Qui va cambiato, personalmente penserei ad un metodo che mostra solo da una posizione ad un altra in questo modo possiamo fare delle
	//"pagine" nel negozio che possono essere anche di lunghezza diverse in base a cosa preferisce l'utente. Tutto questo sarebbe fantastico
	//ma io di certo non tengo sbatta di mettermi a fare una cosa del genere da solo senza chiedere a voi, che poi sbaglio qualcosa e devo
	//riscrivere tutto, dio che palle la vita.
		if(select = "colonna"){%>
			<hr/>
			<%while (!lista.isEmpty()) {
				Frame x = lista.remove(0);
				%>
				<div id="sinistra" style="float: left">
				<img src = "<%=x.getId()%>"/> <p id = ><%=x.getMarchio() x.getModello() x.getMateriale() x.getColore())%></p>
				</div>
				<div id = "destra" style="float: right;">
				<p><%=x.getPrezzo()%></p>
				<button type="submit" formaction="/gestione?action=addCart&id=<%=x.getId()%>">Aggiungi al carrello</button>
				</div>
				<hr/>
<%			} 
		} 
		else if(select = "tabella"){%>
		<table>
			<tr>
			<%for(int i = 0; i< lista.size() - i; i++){
				Frame x = lista.remove(i);%>
				<div id="sinistra" style="float: left">
				<img src = "<%=x.getId()%>"/> <p id = ><%=x.getMarchio() x.getModello() x.getMateriale() x.getColore())%></p>
				</div>
				<div id = "destra" style="float: right;">
				<p><%=x.getPrezzo()%></p>
				<button type="submit" formaction="/gestione?action=addCart&id=<%=x.getId()%>">Aggiungi al carrello</button>
				</div>
			<%} %>
			</tr>
			<tr>
				<%while (!lista.isEmpty()) {
					Frame x = lista.remove(0);
					%>
					<div id="sinistra" style="float: left">
					<img src = "<%=x.getId()%>"/> <p id = ><%=x.getMarchio() x.getModello() x.getMateriale() x.getColore())%></p>
					</div>
					<div id = "destra" style="float: right;">
					<p><%=x.getPrezzo()%></p>
					<button type="submit" formaction="/gestione?action=addCart&id=<%=x.getId()%>">Aggiungi al carrello</button>
					</div>
					<hr/>
				<%}%> 
			</tr>
		</table>	
		<% }
		%>
	
	
	
	
	
	

</body>
</html>