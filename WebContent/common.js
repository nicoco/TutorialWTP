function getXMLHTTP(){
var xhr=null;
if(window.XMLHttpRequest){ // Firefox et autres
xhr = new XMLHttpRequest();
}
else if(window.ActiveXObject){ // Internet Explorer
try {
xhr = new ActiveXObject("Msxml2.XMLHTTP");
}
catch (e) {
try {
xhr = new ActiveXObject("Microsoft.XMLHTTP");
}
catch (e1) {
xhr = null;
}
}
}
else { // XMLHttpRequest non supporté par le navigateur
alert("Votre navigateur ne supporte pas les objets XMLHTTPRequest...");
}
return xhr;
}

  
  
function sendXhrLogin()
{
	var method   = "GET"; 
	var filename = 'LoginServlet';
	var data=null;

	var s1       = document.forms[0].elements["login"].value;
	var s2       = document.forms[0].elements["pass"].value;
	filename += "?login=" + s1 + "&pass=" + s2;

	var xhr = getXMLHTTP();
	xhr.open(method, filename, true);
	
	xhr.onreadystatechange = function() { 
		if(xhr.readyState == 4) {
			var response = xhr.responseText;
			var MonParagraphe = document.getElementById("comment");
			MonParagraphe.innerHTML = response;
			if(response.substr(0,2)=="OK") {
				MonParagraphe.style.color = "green";
				history.go(0);
			} else {
				MonParagraphe.style.color = "red";
			}
		}
	}
	xhr.send(data);
}