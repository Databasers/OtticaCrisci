<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="managerBean.*,bean.Certificato,java.util.Collection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table>
<tr>
	<th>CodiceFiscale
	<th>URL
	<th>Valido
</tr>
<%
	CertificatoManager x= new CertificatoManager();
	Certificato c;
	try{
	c=x.doRetrieveByKey("CRSLGU97P06F924H");
	}catch(SQLException sql)
	{
		
	}
	
	
	
	Certificato save= new Certificato();
	save.setcF("CRSGPP78I52W687F");
	save.setUrl("www.wikipedia.it");
	save.setValido(true);
	x.doSave(save);
	
	
	Certificato update= new Certificato();
	update.setcF("CRSGPP78I52W687F");
	update.setUrl("www.yahoo.it");
	update.setValido(true);
	x.doUpdate(update);
	
	Collection<Certificato> all=x.doRetrieveAll("");
	
	for(Certificato e: all)
	{
%>
<tr>
	<td><%= e.getcF() %>
	<td><%= e.getUrl()%>
	<td><%= e.isValido()%>
</tr>
<% } %>
</table>

<br><br>
<h1>DOPO CANCELLAZIONE</h1>
<%
	boolean done=x.doDelete("CRSLGU97P06F924H");
	if(done==false){
%>
<p>Non è stato possibile cancellare l'elemento</p>
<%
	}
	else
	{	
%>
<table>
<tr>
	<th>CodiceFiscale
	<th>URL
	<th>Valido
</tr>
<%
		Collection<Certificato> all2=x.doRetrieveAll("");

		for(Certificato e: all2)
		{
%>
<tr>
	<td><%= e.getcF() %>
	<td><%= e.getUrl()%>
	<td><%= e.isValido()%>
	</tr>
<%		 } 
	}
%>
</table>


</body>
</html>