<!DOCTYPE html>
<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@page import="org.apache.catalina.ant.SessionsTask"%>
<%@page import="bean.*"%>
<%@page import="java.util.*"%>
<%@page import="it.unisa.model.Carrello"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript" src="../JS/Utente.js"></script>
<script type="text/javascript" src="../JS/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../JS/ajax.js"></script>
</head>
<body>
	<%!SessioneUtente su = null;%>
	
	<% 
	su =(SessioneUtente) session.getAttribute("Utente");
		if(su==null)
		{
			response.sendRedirect("Login.jsp");
			return;
		}
		
		Collection<OcchialeNuovo> elencoN=(Collection<OcchialeNuovo>)request.getAttribute("OcchialiNuovi");
		Collection<OcchialeRotto> elencoR=(Collection<OcchialeRotto>)request.getAttribute("OcchialiRotti");
		Certificato c=(Certificato) request.getAttribute("certificato");
		if(elencoN==null || elencoR==null){
			response.sendRedirect("/OtticaCrisci/GestioneUtente?action=retrieve");
			return;
		}
	%>
	

	<div id = "Contenitore-menu">
		<div id = "Selettore">
			<ul>
				<li><span><button onclick="cambia('Ordini', 'Anagrafica')">Anagrafica</button></span></li>
				<li><span><button onclick="cambia('Anagrafica', 'Ordini')">Ordini</button></span></li>
			</ul>
		</div>
		
		
		
		<div id = "Ordini">
		<%if(!elencoN.isEmpty()) {%>
		<table>
			<%for(OcchialeNuovo e: elencoN){ %>
			<tr>
				<td>
				
				<p id="modAjax"> </p>
				<input type="button" onclick="ajaxCall('modAjax', '/OtticaCrisci/GestioneUtente?action=ajax', displayResults, '3-occhialeNuovo');">
				
				</td>
			</tr>
			<%} %>
		</table>
		<%}
		    if(!elencoR.isEmpty()){ %>
		   <table>
		   		<%for(OcchialeRotto e: elencoR){ %>
		   		<tr>
		   			<!-- Inserisci le cose in tabella e il pulsante per aprire il riepilogo -->
		   		</tr>
		   	<%} %>
		   </table>
		   <%}%>
			Cose che hanno a che fare con gli ordini
		</div>
		
		
		
		<div id = "Anagrafica">
			<p><!-- Nome utente, codice fiscale --></p>
			<form name = "password">
				
				<span>Cambio password</span><br>
				<span>Nuova password 	<input type ="text" name="Nu" value = "test"></span><br>
				<span>Ripeti			<input type ="text" name="Ri" value = "due"></span><br>
				
				<button type="button" onclick = "check()">Conferma</button> <!-- Mo vai in Utente.js -->
			</form> 
		</div>
	</div>

  
  <!-- Qui inizia la pagina di Servlet, dentro c'è un esempio di chiamata ajax -->
  
  <script type="text/javascript">

	function hideCertificato(){
		var form=document.getElementById("certificato");
		//Qui andrebbe nascosto il form di input
	}
</script>


<!-- Roba di AJAX -->
<script>
function displayResults(listXML, id) {
	try { 
		var obj = document.getElementById(id);		
		if(obj != null) {
			var rdfs = listXML.getElementsByTagName("IDLente")[0].firstChild.nodeValue; 
			obj.innerHTML =rdfs;
			console.log("Handle results");
		}
	} catch(e1) {
	}
	 
}
</script>


</head>
<body>

	 
	 <%
	 	Boolean aggiunto =(Boolean)request.getAttribute("certificatoInserito");
	 	if(aggiunto!=null)
	 		if(aggiunto){
	 			%>
	 			<h3>Certificato aggiunto</h3>
	 		<% }
	 		else{
	 			%>
	 			<h3>Certificato non aggiunto</h3>
	 		<% }
	 	%>


	<form id="certificato" action="http://localhost:8080/OtticaCrisci/GestioneUtente?action=addCertificato" method="post" enctype="multipart/form-data">
		<input type="file" accept=".jpg,.pdf" name="certificato">
		<input type="submit">
	</form>
	

	<!-- Questo pulsante prende dal server le informazioni su un occhiale nuovo,le salva in xml e le invia. Per usarlo
		inviare i parametri nella forma idOcchiale-occhialeNuovo 
		Basta semplicemente implementarlo per ogni ordine e collegarlo ad un <p>, cosa già fatta ma ovviamente da redendere
		meglio a schermo-->
	<br><br>
	<p id="modAjax"> </p>
	<input type="button" onclick="ajaxCall('modAjax', '/OtticaCrisci/GestioneUtente?action=ajax', displayResults, '3-occhialeNuovo');">

</body>
</html>









