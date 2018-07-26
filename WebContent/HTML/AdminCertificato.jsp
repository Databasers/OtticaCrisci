<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="bean.*,managerBean.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="Admin.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
		var td=tr.firstChild;
		console.log(td);
		cf=td.textContent;
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
					</select> <input type="number" min="0" max="10" id="num" value="0">
					<input type="button" onclick="check(this)" value="mamma">
				</form>
		</tr>
		<% } %>
	</table>

	
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
			var table=$("<table></table>");
			console.log("Dopo jquery");
			var tr=$("<tr></tr>");
			for(var i=0;i<3;i++){
				var td=$("<td></td>");
				if(i==0){
					var codiceFiscale = listXML.getElementsByTagName(tag[1])[0].firstChild.nodeValue;
					td.append(document.createTextNode(codiceFiscale));
					console.log("Dopo append di codice fiscale");
				}
				if(i==1){
					var url=listXML.getElementsByTagName(tag[2])[0].firstChild.nodeValue;
					td.append($("<label></label>").text(url));
					console.log("Dopo append di url");
				}
				if(i==2){
					var form=$("<form></form>");
					form.attr("method","post");
					console.log("Dopo form");
					
					//Gradazione
					var gradazione=$("<input></input>");
					gradazione.attr({
						"type" : "number",
						"id" : "num",
						"min" : "0",
						"max" : "10",
						"value" : "0"
					});
					console.log("Dopo gradazione");
					
					//Valido
					var valido=listXML.getElementsByTagName(tag[3])[0].firstChild.nodeValue;
					var selectValido=$("<select></select>");
					selectValido.attr("id","selezione");
						 var optionValidoFalse=$("<option></option>");
						 	optionValidoFalse.attr("value","false");
						 	optionValidoFalse.text("false");
						 var optionValidoTrue=$("<option></option>");
						 	optionValidoTrue.attr("value","true");
						 	optionValidoTrue.text("true");
						if(valido=="true")
							optionValidoTrue.attr("selected","selected");
						else
							optionValidoFalse.attr("selected","selected");
					selectValido.append(optionValidoTrue);
					selectValido.append(optionValidoFalse);
					console.log("Dopo select");

					
					//Bottone
					var bottone=$("<input></input>")
					bottone.attr("type","button");
					bottone.attr("value","check");
					bottone.attr("onClick","check(this)");
					
					form.append(selectValido);
					form.append(gradazione);
					form.append(bottone);
					td.append(form);
				}	
				tr.append(td);
			}
			table.append(tr);
			$("#modAjax").append(table);
		}	
		console.log("Handle results");
	} catch(e1) {
	}
}

</script>

	<div id="cliente">
		<p>
			Cerca un cliente <input id="pcliente" type="text" name="cf" max="16">
			<input type="button" onclick="FindCliente(this)" value="cerca">
		<div id="modAjax"></div>
	</div>
</body>
</html>
