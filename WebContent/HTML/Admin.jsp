<%@ page import="bean.*,managerBean.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%	String label=(String)request.getAttribute("label");
	if(label==null){
		%> <%@ include file="AdminCertificato.jsp" %>
	<% } 
	else{
		%><p><%=label %></p> <% 
		if(label.equalsIgnoreCase("certificato")){
		%>
			<%@ include file="AdminCertificato.jsp" %>
	
	<%	}
		} %> 
	
	
	
	
	
	
	