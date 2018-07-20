

function check(){
	var v = document.password.Ve.value;
	var u = document.password.Nu.value;
	var d = document.password.Ri.value;
	if(u == d){
		var re = new XMLHttpRequest();
		re.open("POST","https:localhost:8080/OtticaCrisci/GestioneUtente?action=modificaPassword&passwordNuova="+u+"&passwordVecchia=",true);
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