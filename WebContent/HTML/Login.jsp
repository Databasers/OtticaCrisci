<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Login</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/CSS/Logino.css" type="text/css" media="all">
<script type="text/javascript" src="<%=request.getContextPath()%>/JS/jquery-3.3.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/JS/ajax.js"></script>
</head>
<body>
<div class= "header">
<%@ include file="Header.jsp"%>
</div>


<%
	String x= (String)request.getAttribute("Done");
	if(x!=null && x=="falso"){
	%>
	
	<p><font color="red">Errore nel login</font></p> 	
	<% }
	request.removeAttribute("Done");
	%>
<div class= "container">
	
	<div class="choice">
		<button class="button display-log" onclick="plusDivs(-1)">Login</button>
		<button class="button display-reg" onclick="plusDivs(1)">Registrati!</button>
	</div>
	<div class="reg-log">
		<form id="login"  method="Post">
			<input type="text" class="desc" placeholder="codicefiscale" name="username"><br>
			<input type="password" class="desc" placeholder="password" name="password"><br>
			<input type="submit" class="btn" formaction="http://localhost/OtticaCrisci/GestioneLogin?action=login">
			<input type="submit" class="btn" formaction="http://localhost/OtticaCrisci/GestioneLogin?action=loginAdmin" value="Login as Admin">

		</form>	
	</div>

	<div class="reg-log">
		<%
			String registered= (String)request.getAttribute("alreadyRegistered");
			if(registered!=null && registered=="true"){
		%>
		<h3><font color="red">Questo utente esiste già</font></h3>
		<% }
			request.removeAttribute("alreadyRegistered");
		%>
		<form name = "registro" method = "Post">
			<input type="text" class="desc" placeholder="Codice Fiscale" id="codicefiscale"><br>
			<input type="text" class="desc" placeholder="Nome" id="nome"><br>
			<input type="text" class="desc" placeholder="Cognome" id="cognome"><br>
			<input type="password" class="desc" placeholder="password" id="password"><br>
			<input type="button" class="btnbello" onclick="check()" value ="Invio">

		<script>
		function check(){
			var u = document.getElementById("nome").value;
			var d = document.getElementById("cognome").value;
			var e = document.getElementById("password").value;
			var x = document.getElementById("codicefiscale").value;
			
			if(u== "" || d== "" || e== "" || x== ""){
				alert("Inserimento errato");
				if(u == ""){
					$("input#nome").css("background-color", "red");
				}	
				if(d == ""){
					$("input#cognome").css("background-color", "red");
				}
				if(e == ""){
					$("input#password").css("background-color", "red");
				}
				if(x == ""){
					$("input#codicefiscale").css("background-color", "red");
				}
		}
			else {
				document.registro.action = "/OtticaCrisci/GestioneLogin?action=registrazione&nome="+u+"&cognome="+d+"&password="+e+"&codicefiscale="+x;
				document.registro.submit();
				
			}
			
		}
		</script>


	
		</form>
	</div>

</div>
<script>

var slideIndex = 1;
var actual = "login";
showDivs(slideIndex);

function plusDivs(n) {
	
	if(n==1 && actual == "login")
	{
 		showDivs(slideIndex += n);
 		actual="reg";
	}
	
	if(n==-1 && actual == "reg")
	{
		showDivs(slideIndex += n);
		actual="login";
	}
  
}
function showDivs(n) {
	  var i;
	  var x = document.getElementsByClassName("reg-log");
	  if (n > x.length) {slideIndex = 1}    
	  if (n < 1) {slideIndex = x.length}
	  for (i = 0; i < x.length; i++) {
	     x[i].style.display = "none";  
	  }
	  x[slideIndex-1].style.display = "block";  
	}
	
</script>

</body>
</html>