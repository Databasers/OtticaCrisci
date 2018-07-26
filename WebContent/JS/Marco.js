function pfunc(marchio){
	var a = $("div#peppe");
	a.slideUp("fast", function() {
		a.html($("div#"+ marchio).html() + "");
	});
	a.slideDown();
}
