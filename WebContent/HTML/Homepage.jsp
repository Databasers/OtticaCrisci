<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%@page import="bean.Frame, java.util.List, servlet.GestioneCarrello, java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../CSS/Homepage.css" type="text/css" media="all">
<title>OtticaCrisci, ottica otticante</title>
</head>
<body>
<div class= "header">
<%@ include file="Header.jsp"%>
</div>

<div class="container">
	<div class="mySlides">
		<img class= "myslidesimg" src="../ottico.jpg" style="width:100%">
		<h1 class="myslidestext">Ottica Crisci, la soluzione per i tuoi occhi!</h1>
		<button class="button display-slide" onclick=>Vai al negozio!</button>
	</div>
	<div class="mySlides">
		<img class= "myslidesimg" src="../marche.jpg" style="width:100%">
		<h1 class="myslidestext">I migliori marchi, per tutti i gusti!</h1>
		<button class="button display-slidedue" onclick= >Guarda i nostri marchi!</button>
	</div>
  

	<button class="button display-left" onclick="plusDivs(-1)">&#10094;</button>
	<button class="button display-right" onclick="plusDivs(1)">&#10095;</button>
</div>

<br>

<h1 class="title"> I nostri consigli per la tua moda!</h1>

<%
		int count = 0;
		Collection<Frame> elenco = (Collection<Frame>) request.getSession().getAttribute("Frame");
		if(elenco==null){
			response.sendRedirect("/OtticaCrisci/GestioneNegozio?action=retrieveForHome");
			return;
		}
		else
			request.getSession().removeAttribute("Frame");
	%>
	
<div class= "container-advices">
	<%  if(elenco.isEmpty()){
		%><p>Non ci sono frame</p>
		<div class= "product"> 
					<img class= "productImg" src= "../marche.jpg" style= "width:100%; ">
					<h3> Bello<%-- f.getModello() --%></h3> 
					<h3> GenteRicca<%-- f.getMarchio() --%></h3>
					<h3> MoltoRosso<%-- f.getColore() --%></h3>
					<h3> 23<%-- f.getPrezzo() --%> Euro</h3>
					<button type="submit" formaction="/gestione?action=addCart&id=<%--f.getId()--%>">Aggiungi al carrello</button>
		</div> <%
		
	}
	else{
		Frame[] el = elenco.toArray(new Frame[0]);
		GestioneCarrello gc = new GestioneCarrello();
		for( Frame f: el){
			if(count<3){
			//if(){ *gestione frame se ci sono cookies
			//}else{
			%>
				<div class= "product"> 
					<img class= "productImg" src= <%= f.getUrlImmagine() %> alt="Img" style= "width:100%">
					<h3> <%-- f.getModello() --%></h3>
					<h3> <%-- f.getMarchio() --%></h3>
					<h3><%-- f.getColore() --%></h3>
					<h3><%-- f.getPrezzo() --%> Euro</h3>
					<button type="submit" formaction="/gestione?action=addCart&id=
					<%--f.getId()--%>">Aggiungi al carrello</button>
				</div>
			<% 
			count +=1;
			}
		}
	}
	
			%>

</div>

<h1 class="goToStore" style="color: #00000" > <a href="Store.jsp" style="color: #000000" > Clicca qui per molti altri modelli</a> </h1>


<script>
var m = 1;
var slideIndex = 1;
var myVar;
showDivs(slideIndex);
//myVar = setTimeout(carousel, 5000);
function plusDivs(n) {
	clearTimeout(myVar);
  showDivs(slideIndex += n);
  
  //myVar = setTimeout(carousel, 5000);
}
/*function carousel() {
	
    plusDivs(m);  
    m=m*(-1);
    myVar = setTimeout(carousel, 5000); 
}*/
function showDivs(n) {
  var i;
  var x = document.getElementsByClassName("mySlides");
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