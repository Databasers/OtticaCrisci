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
		<input type="file" accept=".img,.pdf" name="certificato">
		<input type="submit">
	</form>

</body>
</html>