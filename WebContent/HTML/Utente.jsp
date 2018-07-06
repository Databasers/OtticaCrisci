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

</body>
</html>