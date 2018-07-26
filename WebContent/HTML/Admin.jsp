<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/CSS/Admin.css" type="text/css"
	media="all">
	<script type="text/javascript" src="../OtticaCrisci/JS/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../OtticaCrisci/JS/ajax.js"></script>
<title>Admin Page</title>
</head>
<body>
<div class= "header">
<%@ include file="Header.jsp"%>
</div>

<%!SessioneUtente su = null;%>
	
	<% 
	su =(SessioneUtente) session.getAttribute("Utente");
		if(su==null)
		{
			response.sendRedirect("Login.jsp");
			return;
		}
		
	String label=(String) request.getSession().getAttribute("label");%>
<div class="well-profile">
            <div class="Anagrafica">
                
                    <h2><%= su.getNome() %> <%=su.getCognome() %></h2>
                    <p><strong>Codice Fiscale </strong> <%=su.getcF()%> </p>
                    
             </div>
             
             <div class="Funzioni">
		
					<div class="func2">
						<form name= "insert" method="post" action="/OtticaCrisci/GestioneAdmin?action=frame">
							<fieldset>
							    <legend>Inserisci nuovo frame</legend>
							   
							    <input type="text" name="colore" placeholder ="colore">
							    <br> <br>
							    <input type="text" name="peso" placeholder ="0">
							    <br><br>
							    <input type="text" name="materiale" placeholder ="materiale">
							    <br> <br>
							    <input type="text" name="prezzo" placeholder ="0">
							    <br> <br>
							    <input type="text" name="marchio" placeholder ="marchio">
							     <br> <br>
							    <input type="text" name="modello" placeholder ="modello">
							     <br> <br>
							    <input type="text" name="link" placeholder ="Link">
							     <br> <br>
							    <button type="button" onclick = "check()" style="background-color:#008ae6">Inserisci Frame!</button> 
							 </fieldset>
														
						</form>
					</div>
				<div class="Function_list">
					<form method="post">
						<input type="submit" formaction="/OtticaCrisci/GestioneAdmin?action=cambio&tabella=certificato" value="Controlla certificati">
						<input type="submit" formaction="/OtticaCrisci/GestioneAdmin?action=cambio&tabella=occhiali" value="Gestisci occhiali">
					</form>
				</div>
				<div class="zonafunzioni">
				<% if(label!=null && label.equalsIgnoreCase("certificato")){%>
					<div class="func">
						<%@ include file="AdminCertificato.jsp"%>
					</div>
				<% }else if(label!=null && label.equalsIgnoreCase("occhiali")){%>
					<div class="func">
						<%@ include file="AdminOcchiali.jsp"%>
					</div>
					<% }%>
				</div>
            </div>
            
</div>

<script>
function check(){
	var u = document.insert.peso.value;
	var d = document.insert.prezzo.value;
	console.log(u);
	console.log(d);
	if(isInt(u) && isInt(d)){

		console.log("interi");
		
		document.insert.submit();
	}
	else{
		alert("Inseriti valori errati, necessari interi");
		document.insert.peso.style = "background: red";
		document.insert.prezzo.style = "background: red";
		;
	}
}
function isInt(value) {
	var x;
	  if (isNaN(value)) {
	    return false;
	  }
	  x = parseFloat(value);
	  return (x | 0) === x;
	}
</script>
</body>
</html>
