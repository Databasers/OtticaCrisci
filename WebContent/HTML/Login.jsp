<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">

div#registrazione{
	display: block;
	float: right;
}

</style>

</head>
<body>

<!-- TEST PER CONTROLLARE SE FUNZIONAVA IL LOGIN -->

<%
	String x= (String)request.getAttribute("Done");
	System.out.print("Il valore e': " + x);
	if(x!=null && x=="falso"){
	%>
	
	<p><font color="red">Errore nel login</font></p> 	
	<% }
	request.removeAttribute("Done");
	%>

<div id="login">
<form id="login" action="http://localhost:8080/OtticaCrisci/GestioneLogin?action=login" method="Post">
	<input type="text" placeholder="username" name="username"><br>
	<input type="password" placeholder="passowrd" name="password"><br>
	<input type="submit">

</form>
</div>


<div id="registrazione">
<h3>Registrazione</h3><br>
<%
	String registered= (String)request.getAttribute("alreadyRegistered");
	System.out.print("Il valore di registered e': " + registered);
	if(registered!=null && registered=="true"){
	%>
	<h3><font color="red">Questo utente esiste gi�</font></h3>
<% }
	request.removeAttribute("alreadyRegistered");
%>
<form  action="http://localhost:8080/OtticaCrisci/GestioneLogin?action=registrazione" method="Post">
	<input type="text" placeholder="Codice Fiscale" name="codicefiscale"><br>
	<input type="text" placeholder="Nome" name="nome"><br>
	<input type="text" placeholder="Cognome" name="cognome"><br>
	<input type="password" placeholder="passowrd" name="password"><br>
	<input type="submit">


</form>
</div>



	<!-- 
	Due form, separati da una linea verticale al centro
	 -->
	 
	 <!-- SINISTRA
	 Scritta Login centrale e, sotto, form per login
	  -->
	 
	 <!-- Destra
	 Scritta Registrati centrale e, sotto, form per registrazione
	 (non so se inserire qui il campo per l'inserimento del certificato)
	 	  --> 

</body>
</html>