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
<script type="text/javascript" src="../JS/jquery-3.3.1.js"></script>
</head>
<body>
	<%!SessioneUtente su = null;%>
	
	<% su =(SessioneUtente) session.getAttribute("utente");%>
	
	
	<div id = "Contenitore-menu">
		<div id = "Selettore">
			<ul>
				<li><span><button onclick="cambia('Ordini', 'Anagrafica')">Anagrafica</button></span></li>
				<li><span><button onclick="cambia('Anagrafica', 'Ordini')">Ordini</button></span></li>
			</ul>
		</div>
		
		
		
		<div id = "Ordini" class="Tab" style="display: none;">
		<table>
			<tr>
		
			</tr>
		</table>
			Cose che hanno a che fare con gli ordini
		</div>
		<div id = "Anagrafica" class="Tab">
			<p><!-- Nome utente, codice fiscale --></p>
			<form name = "password">
				
				<span>Cambio password</span><br>
				<span>Nuova password 	<input type ="text" name="Nu" value = "test"></span><br>
				<span>Ripeti			<input type ="text" name="Ri" value = "due"></span><br>
				
				<button type="button" onclick = "check()">Conferma</button> <!-- Mo vai in Utente.js -->
			</form> 
		</div>
	</div>

</body>
</html>