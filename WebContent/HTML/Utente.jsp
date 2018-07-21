<!DOCTYPE html>
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
	
		Collection<OcchialeNuovo> elencoN=(Collection<OcchialeNuovo>)request.getSession().getAttribute("OcchialiNuovi");
		Collection<OcchialeRotto> elencoR=(Collection<OcchialeRotto>)request.getSession().getAttribute("OcchialiRotti");
		Certificato c=(Certificato) request.getAttribute("certificato");
		if(elencoN==null || elencoR==null){
			response.sendRedirect("/OtticaCrisci/GestioneUtente?action=retrieve");
			return;
		}
		else{
			request.getSession().removeAttribute("OcchialiNuovi");
			request.getSession().removeAttribute("OcchialiRotti");
			System.out.println("Elimino gli elementi dalla sessione");
		}
		Cookie[] x= request.getCookies();
		for(Cookie e: x){
			System.out.println(e.getName()+": "+e.getValue()+" età massima: "+e.getMaxAge());
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
		Occhiali nuovi
		<table>
			<%for(OcchialeNuovo e: elencoN){ %>
			<tr>
				<td>
				
				<p ><%=e.getStato()%> <%=e.getDataOrdine()%> <%=e.getPrezzo()%> </p>
				<input type="button" onclick="ajaxCall('modAjax', '/OtticaCrisci/GestioneUtente?action=ajax', displayResults, '<%=e.getId()%>-occhialeNuovo');">
				Mostra di più</input>
				
				</td>
			</tr>
			<%} %>
		</table>
		<%}
		    if(!elencoR.isEmpty()){ %>
		    Occhiali rotti
		   <table>
		   		<%for(OcchialeRotto e: elencoR){ %>
		   		<tr>
		   			<td>
				
						<p><%=e.getStato()%> <%=e.getTipoDanno()%> <%=e.getPrezzo()%>  </p>
						<input type="button" onclick="ajaxCall('modAjax', '/OtticaCrisci/GestioneUtente?action=ajax', displayResults, '<%=e.getId()%>-occhialeRotto');">
						Mostra di più</input>
					</td>
		   		</tr>
		   	<%} %>
		   </table>
		   <%}%>
		</div>
		<p id="modAjax"><p>
		
		
		
		<div id = "Anagrafica">
			<p><!-- Nome utente, codice fiscale --></p>
			<form name = "password" method="post">
				<% Boolean pass=(Boolean) request.getSession().getAttribute("passwordCambiata");
				if(pass!=null){
				if(pass){%>
				<h3>PasswordCambiata</h3>
				<%}
				else {%>
				<h3>Password non cambiata</h3>
				<%} 
				request.getSession().removeAttribute("passowrdCambiata");}%>
				<span>Cambio password</span><br>
				<span>Nuova password 	<input type ="text" name="Nu" value = "test"></span><br>
				<span>Ripeti			<input type ="text" name="Ri" value = "due"></span><br>
				
				<button type="button" onclick = "check()">Conferma</button> <!-- Mo vai in Utente.js -->
			</form> 
		</div>
	</div>
	<br>
  
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
		var tag =["URLImmagine" ,"IDFrame", "PrezzoF", "Modello", "Colore", "PesoF", "MaterialeF", "Marchio", "Diottria"]
		
		if(obj != null) {
			var rdfs = listXML.getElementsByTagName(tag[0])[0].firstChild.nodeValue; 
			obj.innerHTML ="<img src =" +rdfs +">";
			for( var i = 1; i < tag.length; i++){
				var rdfs = listXML.getElementsByTagName(tag[i])[0].firstChild.nodeValue;
				obj.innerHTML += " " + rdfs;
			}
			console.log("Handle results");
		}
	} catch(e1) {
	}
	 
}
</script>



	 
	 <%
	 	Certificato cert=(Certificato) request.getSession().getAttribute("certificato");
	 System.out.println("Zona certificato");
	 	if(cert!=null){
	 		if(cert.isValidato() && cert.isValido()){
	 			%>
	 			<h3>Certificato aggiunto</h3>
	 		<%}
	 		if(!cert.isValido() && !cert.isValidato()){
	 			%>
	 			<h3>Certificato in attesa di validazione</h3>
	 		<% }
	 		if(!cert.isValido() && cert.isValidato()){
	 		%>
	 		<div><h3>Inserisci certificato</h3>
					<form id="certificato" action="/OtticaCrisci/GestioneUtente?action=addCertificato" method="post" enctype="multipart/form-data">
						<input type="file" accept=".jpg,.pdf" name="certificato">
						<input type="submit">
						<label><font color="red"> Il certificato non è stato accettato</font></label>
					</form>
				</div>
	 		<% }
	 	}
	 		else{
	 			%>
	 			<div><h3>Inserisci certificato</h3>
					<form id="certificato" action="/OtticaCrisci/GestioneUtente?action=addCertificato" method="post" enctype="multipart/form-data">
						<input type="file" accept=".jpg,.pdf" name="certificato">
						<input type="submit">
					</form>
				</div>
	 		<% }
	 	%>

	


</body>
</html>









