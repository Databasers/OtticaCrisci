

function check(){
	var u = document.password.Nu.value;
	var d = document.password.Ri.value;
	if(u == d){
		var re = new XMLHttpRequest();
		re.open("get","/GestioneUtente?action=modificaPassword&pwd="+u,true);
		
	}
	else{
		alert("La password non corrisponde");
		document.password.Nu.style = "background: red";
		document.password.Ri.style = "background: red";
	}
}

function cambia(a, b){
	$("#"+a).fadeOut();
	$("#"+b).fadeIn();
	
	}