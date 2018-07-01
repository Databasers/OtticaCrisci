<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!-- TEST PER CONTROLLARE SE FUNZIONAVA IL LOGIN -->

<%
	String x= (String)request.getAttribute("Done");
	System.out.print("Il valore e': " + x);
	if(x!=null && x=="falso"){
	%>
	
	<p><font color="red">Errore nel login</font></p> 	
	<% }%>
	
<form action="http://localhost:8080/OtticaCrisci/GestioneLogin" method="Post">
	<input type="text" placeholder="username" name="username"><br>
	<input type="password" placeholder="passowrd" name="password"><br>
	<input type="submit">

</form>




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