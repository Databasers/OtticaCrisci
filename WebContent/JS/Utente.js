function check(){
	var u = document.password.Nu.value;
	var d = document.password.Ri.value;
	if(u == d){
		//EXEChecazzo ci scrivo qua?
	}
	else{
		alert("La password non corrisponde");
		document.password.Nu.style = "background: red";
		document.password.Ri.style = "background: red";
	}
}