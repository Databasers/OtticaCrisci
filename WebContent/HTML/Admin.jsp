<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/CSS/Admin.css" type="text/css"
	media="all">
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
						<form method="post" action="/OtticaCrisci/GestioneAdmin?action=frame">
							<fieldset>
							    <legend>Inserisci nuovo frame</legend>
							   
							    <input type="text" name="colore" value="colore">
							    <br> <br>
							    <input type="text" name="peso" value="peso">
							    <br><br>
							    <input type="text" name="materiale" value="materiale">
							    <br> <br>
							    <input type="text" name="prezzo" value="prezzo">
							    <br> <br>
							    <input type="text" name="PartitaIva" value="PartitaIva">
							    <br> <br>
							    <input type="text" name="marchio" value="marchio">
							     <br> <br>
							    <input type="text" name="modello" value="modello">
							     <br> <br>
							    <input type="text" name="peso" value="Link">
							     <br> <br>
							    <input type="submit" value="Inserisci Frame!" style="background-color:#008ae6">
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


</body>
</html>
