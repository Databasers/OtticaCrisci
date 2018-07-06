<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

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