<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="managerBean.*,bean.Certificato"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	CertificatoManager x= new CertificatoManager();
	Certificato c;
	c=x.doRetrieveByKey("CRSLGU97P06F924H");
%>
<%= c.getcF() +" "+ c.getUrl()+" "+ c.isValido()%>

</body>
</html>