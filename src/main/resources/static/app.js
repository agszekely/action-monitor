var stompClient = null;

function connect() {
    var socket = new SockJS('/actionmonitor');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        
        stompClient.subscribe('/message/dbevent', function (greeting) {
            showGreeting(greeting.body);
        });
        
        $("#messages").show();
    });
}

function showGreeting(message) {
	console.log(message)
	var json = JSON.parse(message);
    $("#greetings").prepend("<tr><td>" + json.content + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#greetings").html("");
    $("#messages").hide();
    
    connect();
    
});