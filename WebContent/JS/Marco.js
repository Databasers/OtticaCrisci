function pfunc(marchio){
	var a = $("div#peppe");
	a.slideUp("slow", function() {
		a.html($("div#"+ marchio).html() + "");
	});
	a.slideDown();
}
