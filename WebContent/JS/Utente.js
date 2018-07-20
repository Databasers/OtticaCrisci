

function check(){
	var u = document.password.Nu.value;
	var d = document.password.Ri.value;
	if(u == d){
		var re = new XMLHttpRequest();
		re.open("GET","/GestioneUtente?action=modificaPassword&pwd="+u,true);
		re.setRequestHeader("connection", "close");
		re.send(null);
	}
	else{
		alert("La password non corrisponde");
		document.password.Nu.style = "background: red";
		document.password.Ri.style = "background: red";
	}
}

function cambia(a, b){
	$("#"+a).hide();
	$("#"+b).fadeIn();
}