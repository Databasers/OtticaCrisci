<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
<div class="well profile">
            <div class="Anagrafica">
                
                    <h2>Nicole Pearson</h2>
                    <p><strong>Codice Fiscale </strong> 12355sads77 </p>
                    <p><strong>Telefono </strong> 1235577 </p>
                    
             </div>
             <div class="Funzioni">
            	<div class="Function_list">
					<form method="post">
						<input type="submit" formaction="/OtticaCrisci/GestioneAdmin?action=cambio&tabella=certificato" value="certificato">
						<input type="submit" formaction="/OtticaCrisci/GestioneAdmin?action=cambio&tabella=occhiali" value="occhiali">
					</form>
				</div>
				<div class="zonafunzioni">
				
					<div class="func2">
						<form method="post" action="/OtticaCrisci/GestioneAdmin?action=frame">
							<input type="submit" value="e manda">
						</form>
					</div>
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
