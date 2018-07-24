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
			<td>
			<form method="post">
				<select id="selezione">
				<option value="true">true
				<option value="false">false 
				</select>
				<input type="number" min="0" max="10" id="num" value="0"> 
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
		console.log("Entro nella funzione");
		var obj = document.getElementById(id);		
		var tag =["Esito","CodiceFiscale","Url","Valido", "Validato"];
		var esito = listXML.getElementsByTagName(tag[0])[0].firstChild.nodeValue;
		if(esito=="false")
			obj.innerHTML="Questo utente non ha inserito certificati";
		else
		if(obj != null) {
			console.log("Dopo il controlloc su obj");
			var table=document.createElement("table");
			var tr=document.createElement("tr");
			for(var i=0;i<3;i++){
				var td=document.createElement("td");
				if(i==0){
					var codiceFiscale = listXML.getElementsByTagName(tag[1])[0].firstChild.nodeValue;
					td.appendChild(document.createTextNode(codiceFiscale));
				}
				if(i==1){
					var url=listXML.getElementsByTagName(tag[2])[0].firstChild.nodeValue;
					td.appendChild(document.createTextNode(url));
				}
				if(i==2){
					var form=document.createElement("form");
					form.setAttribute("method","post");
					//Gradazione
					var gradazione=document.createElement("input");
					gradazione.type="number";
					gradazione.setAttribute("id","num");
					gradazione.setAttribute("min","0");
					gradazione.setAttribute("max","10");
					gradazione.setAttribute("value","0");
					
					//Valido
					var valido=listXML.getElementsByTagName(tag[3])[0].firstChild.nodeValue;
					var selectValido=document.createElement("select");
					selectValido.setAttribute("id","selezione");
						 var optionValidoFalse=document.createElement("option");
						 	optionValidoFalse.setAttribute("value","false");
						 	optionValidoFalse.innerHTML="false";
						 var optionValidoTrue=document.createElement("option");
						 	optionValidoTrue.setAttribute("value","true");
						 	optionValidoTrue.innerHTML="true";
						if(valido=="true")
							optionValidoTrue.setAttribute("selected","selected");
						else
							optionValidoFalse.setAttribute("selected","selected");
					selectValido.appendChild(optionValidoTrue);
					selectValido.appendChild(optionValidoFalse);
					
					//Bottone
					var bottone=document.createElement("input");
					bottone.setAttribute("type","button");
					bottone.setAttribute("onClick","check(this)");
					
					form.appendChild(selectValido);
					form.appendChild(gradazione);
					form.appendChild(bottone);
					td.appendChild(form);
				}	
				tr.appendChild(td);
			}
			table.appendChild(tr);
			obj.appendChild(table);
		}	
		console.log("Handle results");
	} catch(e1) {
	}
}

</script>

<div id="cliente">
	<p >Cerca un cliente <input id="pcliente" type="text" name="cf" max="16"> <input type="button" onclick="FindCliente(this)">
	<div id="modAjax">

</div>
</div>
</body>
</html>
