<!DOCTYPE html>
<%@page import="org.apache.catalina.ant.SessionsTask"%>
<%@page import="bean.*"%>
<%@page import="java.util.*"%>
<%@page import="it.unisa.model.Carrello"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<LINK rel="stylesheet" href="<%=request.getContextPath()%>/CSS/Utento.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/JS/Utente.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/JS/jquery-3.3.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/JS/ajax.js"></script>
</head>
<body>
<div class= "header">
<%@ include file="Header.jsp"%>
</div>


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
		<table id = "Selettore">
			<tr>
				<td><span><button onclick="cambia('Ordini', 'Anagrafica')">Anagrafica</button></span></td>
				<td><span><button onclick="cambia('Anagrafica', 'Ordini')">Ordini</button></span></td>
			</tr>
		</table>
		
		 
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
						<input id="file" type="file" accept=".jpg,.pdf" name="certificato">
						<input type="submit">
					</form>
				</div>
	 		<% }
	 	%>
		
		<hr>
		
		<div id = "Ordini" style="display: none;">
		<%if(!elencoN.isEmpty()) {%>
		<h4>Occhiali nuovi</h4>
		<table>
			<%for(OcchialeNuovo e: elencoN){ %>
			<tr>
				<td>
				
				<p >Occhiale Nuovo: <%=e.getStato()%> <%=e.getDataOrdine()%> <%=e.getPrezzo()%> </p>
				<input type="button" onclick="ajaxCall('modAjax', '/OtticaCrisci/GestioneUtente?action=ajax', displayResults, '<%=e.getId()%>-occhialeNuovo');">
				Mostra di più</input>
				
			<hr>
				</td>
			</tr>
			<%} %>
		</table>
		<%}
		    if(!elencoR.isEmpty()){ %>
		    <h4>Occhiali rotti</h4>
		   <table>
		   		<%for(OcchialeRotto e: elencoR){ %>
		   		<tr>
		   			<td>
				
						<p>Occhiale rotto: <%=e.getStato()%> <%=e.getTipoDanno()%> <%=e.getPrezzo()%>  </p>
					</td>
		   		</tr>
		   	<%} %>
		   </table>
		   <%}%>
		</div>
		
		
		<p id="modAjax"><p>
		
		
		
		<div id = "Anagrafica">
			<h4><%=su.getNome()%> <%=su.getCognome()%></h4>
			<form name = "password" method="post">
				<% Boolean pass=(Boolean) request.getSession().getAttribute("passwordCambiata");
				if(pass!=null){
				if(pass){%>
				<h3>PasswordCambiata</h3>
				<%}
				else {%>
				<h3>Password non cambiata</h3>
				<%} 
				request.getSession().removeAttribute("passwordCambiata");}%>
				<h2>Cambio password</h2><br>
				<span>Nuova password<br><input type ="password" name="Nu" placeholder ="test" style="background-color: #d1d5d8"></span><br>
				<span>Ripeti		<br><input type ="password" name="Ri" placeholder ="test" style="background-color: #d1d5d8"></span><br>
				
				<button type="button" onclick = "check()">Conferma</button> <!-- Mo vai in Utente.js -->
			</form> 
		</div>
	</div>
	<br>
  
 <script type="text/javascript">

	function hideCertificato(){
		$("#certificato").hide();
	}
</script>

<!-- Roba di AJAX -->
<script>
function displayResults(listXML, id) {
	try { 
		var obj = document.getElementById(id);		
		var tag =["URLImmagine", "PrezzoF", "Modello", "Colore", "PesoF", "MaterialeF", "Marchio", "Diottria"];
		
		if(obj != null) {
			var rdfs = listXML.getElementsByTagName(tag[0])[0].firstChild.nodeValue; 
			obj.innerHTML ="<img style='width:120px; height:120px' src =<%=request.getContextPath()%>/Immagini_Frame/" +rdfs +">";
			for( var i = 1; i < tag.length; i++){
				var rdfs = listXML.getElementsByTagName(tag[i])[0].firstChild.nodeValue;
				obj.innerHTML += " " + rdfs;
				console.log(rdfs);
			}
			console.log("Handle results");
		}
	} catch(e1) {
	}
	 
}
</script>



	

	


</body>
</html>









