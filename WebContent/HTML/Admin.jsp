
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="../OtticaCrisci/JS/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../OtticaCrisci/JS/ajax.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%!SessioneUtente su = null;%>
	
	<% 
	su =(SessioneUtente) session.getAttribute("Utente");
		if(su==null)
		{
			response.sendRedirect("Login.jsp");
		}
	%>
	
	
	<!-- 
		ANTONIO
		if che verifica che valore ha "label" (in sessione) e selezione quale pagina fare include file="Admin*.jsp" (come sotto)   
		dato che devono esserci anche i bottoni che cambiano, ogni bottone fagli modificare label in sessione e ricarica la pagina, così funziona sempre e lo fai una volta sola	
	
	 -->	
	
	<%@ include file="AdminCertificato.jsp" %>

<!--  
	 <script>
	 	function nascondi(a){
	 		var ogg = ['Frame', 'Ordini', 'Certificati', 'Utenti'];
	 		for(i=0; i < ogg.length; i++)}
	 			$("#"+ogg[i]).hide();
	 		}
	 		$("#"+a).fadeIn();
	 	}
	 </script>
	 
	 
	 
	 <table>
	 	<tr>
	 		<td><div><button onclick="nascondi('Frame')">Nuovo Frame</button></div></td>		
	 		<td><div><button onclick="nascondi('Ordini')">Ordini</button></div></td>
	 		<td><div><button onclick="nascondi('Certificati')">Certificati</button></div></td>
	 		<td><div><button onclick="nascondi('Utenti')">Utenti</button></div></td>
	 	</tr>
	 </table>
	 
	 <div><h1></h1>
	 
	 </div>
	 
	 
	<div id="Frame"><h2>Inserire Nuovi Frame</h2>
	<p>dati del frame<br/>
		<input type="submit" src ="newFrame()"></p>
	</div>
	
	<div id ="Ordini" style="display:none"><h2>Ordini in arrivo</h2>
	<p>Questo sarà un ordine</p>
	</div>
	
	<div id ="Certificati" style="display:none"><h2>Certificati in arrivo</h2></div>
	
	
	<div id="Utenti" style="display:none"><h2>Utenti</h2></div>
-->
</body>
