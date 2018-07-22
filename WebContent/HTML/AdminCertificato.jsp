<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="bean.*,managerBean.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="Admin.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
	function check(bottone){
		var valido;
		var gradazione;
		var cf;
		var form=bottone.parentNode;
		valido=form.selezione;
		valido=valido.options[valido.selectedIndex].value;
		var td=form.parentNode;
		var tr=td.parentNode;
		var celle=tr.cells;
		cf=celle[0].firstChild.nodeValue
		gradazione=form.num.value;
		form.action="/OtticaCrisci/GestioneAdmin?action=modCertificato&valido="+valido+"&gradazione="+gradazione+"&code="+cf;
		console.log("/OtticaCrisci/GestioneAdmin?action=modCertificato&valido="+valido+"&gradazione="+gradazione+"&code="+cf);
		form.submit();
	}

</script>

</head>
<body>

<!-- Esempio gestione della parte dei certificati -->

	<%
		Collection<Certificato> elenco=(Collection<Certificato>)request.getAttribute("certificati");
		if(elenco==null){
			response.sendRedirect("/OtticaCrisci/GestioneAdmin?action=certificato");
			return;
		}

	%>

	<table border="1px solid black">
		<%
		for(Certificato c: elenco){
		%>
		<tr>
			<td><%=c.getcF() %>
			<td><a href="<%=c.getUrl() %>"><%=c.getUrl() %></a>
			<!--  <td><a href="/OtticaCrisci/GestioneAdmin?action=modCertificato&valido=true&code=<%--=c.getcF() %>">Valido</a>
			<td><a href="/OtticaCrisci/GestioneAdmin?action=modCertificato&valido=false&code=<%=c.getcF() --%>">Non Valido</a> -->
			<td>
			<form method="post">
				<select id="selezione">
				<option value="true">true
				<option value="false">false 
				</select>
				<input type="number" min="0" max="10" id="num" placeholder="0"> 
				<input type="button" onclick="check(this)">
			</form>
		</tr>
		<% } %>
	</table>

	<hr>
	<br>


<script type="text/javascript">
function FindCliente(testo){
    var x=document.getElementById("pcliente");
   	console.log(x.value);
   	ajaxCall('modAjax', '/OtticaCrisci/GestioneAdmin?action=ajax', displayResults, x.value);
}

function displayResults(listXML, id) {
	try { 
		var obj = document.getElementById(id);		
		var tag =["Esito","Nome" ,"Cognome", "Valido", "Validato", "CodiceFiscale"];
		var esito = listXML.getElementsByTagName(tag[0])[0].firstChild.nodeValue;
		if(esito=="false")
			obj.innerHTML="Questo utente non ha inserito certificati";
		else
		if(obj != null) {
			for( var i = 0; i < tag.length; i++){
				var rdfs = listXML.getElementsByTagName(tag[i])[0].firstChild.nodeValue;
				obj.innerHTML += " " + rdfs;
				//Inserisci qui i tag <a href> per la modificca dei certificati uguali a quelli sopra
				
			}
			
		}
		console.log("Handle results");
	} catch(e1) {
	}
}

</script>

<div id="cliente">
	<p >Cerca un cliente <input id="pcliente" type="text" name="cf" max="16"> <input type="button" onclick="FindCliente(this)">
	<p id="modAjax">

</div>

</body>
</html>
