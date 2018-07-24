

function check(){
	var u = document.password.Nu.value;
	var d = document.password.Ri.value;
	if(u == d){

		console.log("Le password corrispondono");
		document.password.action="/OtticaCrisci/GestioneUtente?action=modificaPassword&passwordNuova="+u;
		console.log(u);
		document.password.submit();
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
	$("#modAjax").html("");

	}
