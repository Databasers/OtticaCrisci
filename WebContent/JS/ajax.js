
function getXmlHttpRequest() {

	var xhr = false;
	var activeXoptions = new Array("Microsoft.XmlHttp", "MSXML4.XmlHttp",
			"MSXML3.XmlHttp", "MSXML2.XmlHttp", "MSXML.XmlHttp");

	try {
		xhr = new XMLHttpRequest();
		console.log("Get XML http request");
	} catch (e) {
	}

	if (!xhr) {
		var created = false;
		for (var i = 0; i < activeXoptions.length && !created; i++) {
			try {
				xhr = new ActiveXObject(activeXoptions[i]);
				created = true;
				console.log("Get ActiveXObject XML http request");
			} catch (e) {
			}
		}
	}
	return xhr;
}

function getReadyStateHandler(req, responseXmlHandler, id) {
	return function() {
		
		if (req.readyState == 1) {
			console.log("Server connection");
		} else if ( req.readyState == 2 ) {
			console.log("Send request");
		} else if ( req.readyState == 3 ) {
				console.log("Receive response");
		} else if (req.readyState == 4) {
			console.log("Request finished and response is ready");
			if (req.status == 200 || req.status == 304) {
				responseXmlHandler(req.responseXML, id);
			} else {
				console.log("Response error "+ req.status + " " + req.statusText);
			}
		} else {
		}
	};
}



function ajaxCall(id, url, callback, parameter) {
	var req = getXmlHttpRequest();
	try {
		req.onreadystatechange = getReadyStateHandler(req, callback, id);
		req.open('POST', url, true);
		req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		req.send("param=" + parameter);
	} catch (e1) {
	
	}
}



