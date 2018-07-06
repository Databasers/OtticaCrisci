<!DOCTYPE html>
<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@page import="org.apache.catalina.ant.SessionsTask"%>
<%@page import="bean.SessioneUtente"%>
<%@page import="bean.Frame"%>
<%@page import="it.unisa.model.Carrello"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript" src="../JS/Utente.js"></script>
</head>
<body>
	<%!SessioneUtente su = null;%>
	
	<% su =(SessioneUtente) session.getAttribute("utente");%>
	
	
	
	<div id = "Contenitore-menu">
		<div id = "Selettore">
			<ul>
				<li><span><a href="Marchi.html">Anagrafica</a></span></li>
				<li><span><a href="Marchi.html">Ordini</a></span></li>
				<li><span><a href="Marchi.html">Riparazioni</a></span></li>
			</ul>
		</div>
		
		
		
		<div id = "Ordini">
			<!-- Inserire script per ritrovare gli ordini e stamparli -->
		</div>
		<div id = "Anagrafica">
			<p><!-- Nome utente, codice fiscale --></p>
			<form name = "password">
				
				<span>Cambio password</span><br>
				<span>Nuova password	<input type ="text" name="Nu" value = "test"></span><br>
				<span>Ripeti			<input type ="text" name="Ri" value = "due"></span><br>
				
				<button type="button" onclick = "check()">Conferma</button> <!-- Mo vai in Utente.js -->
			</form> 
		</div>
		<div id = "Riparazione">
			<p> Contattaci per riparare i tuoi occhiali o vieni in negozio!</p>
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
			var rdfs = listXML.getElementsByTagName("IDOcchiale")[0].firstChild.nodeValue; 
			obj.innerHTML =rdfs;
			console.log("Handle results");
		}
	} catch(e1) {
	}
	 
}
</script>
<script type="text/javascript" src="ajax.js"></script>


</head>
<body>

	<!-- 
		Manco questa abbiamo visto per motivi randomici, si accettano proposte
	 -->
	 
	 <%
	 	Boolean aggiunto =(Boolean)request.getAttribute("certificatoInserito");
	 	if(aggiunto!=null)
	 		if(aggiunto){
	 			%>
	 			<h3>Certificato aggiunto</h3>
	 		<% }
	 		else{
	 			%>
	 			<h3>Certificatoo non aggiunto</h3>
	 		<% }
	 	%>


	<form id="certificato" action="http://localhost:8080/OtticaCrisci/GestioneUtente?action=addCertificato" method="post" enctype="multipart/form-data">
		<input type="file" accept=".jpg,.pdf" name="certificato">
		<input type="submit">
	</form>
	

	<br><br>
	<p id="modAjax"> </p>
	<input type="button" onclick="ajaxCall('modAjax', '/OtticaCrisci/GestioneUtente?action=ajax', displayResults, '1-occhialeNuovo');">

</body>
</html>