<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="bean.*,it.unisa.model.*,managerBean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- E' un test per capire se, usando la classe Integer come tipo invece che int come tipo per l'interfaccia
productModel funziona tutto bene 

P.S. Funziona benissimo
-->


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	FornitoreManager fornitore= new FornitoreManager();
	Fornitore test= fornitore.doRetrieveByKey(123456);
	

%>
<br><br>
<p> Il nome del fornitore è <%=test.getNome() %></p>

</body>
</html>