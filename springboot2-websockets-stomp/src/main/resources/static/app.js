
var ws;
var currentUser = "";
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
        $("#frmMensaje").show();
        $("#frmLogin").hide();
    }
    else {
        $("#conversation").hide();
        $("#frmMensaje").hide();
        $("#frmLogin").show();
        
    }
    $("#saludos").html("");
}

function connect() {
	//connect to stomp where stomp endpoint is exposed
	var socket = new WebSocket("ws://localhost:8080/springboot2-websockets-stomp/websocketApp");
	ws = Stomp.over(socket);
	
	//username and password
	var loginHeaders = {
      login: $("#username").val(),
      passcode: $("#passwcode").val(),
      // additional header
      'client-id': 'my-client-id'
	};
	
	ws.connect(loginHeaders, function(frame) {
	
	  ws.subscribe("/topic/reply", function(msg) {
	    console.log("Incoming Message: ");
	    console.log(msg);
	    appendGreeting(msg.body);
	  });

	 setConnected(true);
	});
}

function appendGreeting(message) {
    $("#saludos").append("<tr><td> " + message + "</td></tr>");
}

function disconnect() {
	console.log(ws);
    if (ws != null) {
        //ws.close();
    	ws.disconnect(function() {
    		console.log("Desconectado!");
    	});
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
	var data = JSON.stringify({'name': $("#txtMensaje").val()});
	 ws.send("/app/message", {},data);
}



$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $("#conversation").hide();
    $("#frmMensaje").hide();
    
});

