'use strict';

var stompClient = connect();

function connect() {

	var socket = new SockJS('/topic/public');
	
    socket.onopen = function () {
        console.log('open');
    };
    
    socket.onmessage = function (e) {
        var textdata = e.data;
        console.log('message', textdata);
        
        $("#messageList").append("<tr><td>" + textdata + "</td></tr>");
    };

   
}


function connectionSuccess() {

    stompClient.subscribe('/topic/public', function (message) {
        showGreeting(JSON.parse(message.body).content);
    });
}


function showGreeting(message) {
    $("#messageList").append("<tr><td>" + message + "</td></tr>");
}


function onMessageReceived(payload) {

	var message = JSON.parse(payload.body);

	var messageElement = document.createElement('li');
	messageElement.classList.add('message-data');

	var element = document.createElement('i');
	var text = document.createTextNode(message.sender[0]);
	element.appendChild(text);

	messageElement.appendChild(element);

	var usernameElement = document.createElement('span');
	var usernameText = document.createTextNode(message.sender);
	usernameElement.appendChild(usernameText);
	messageElement.appendChild(usernameElement);


	var textElement = document.createElement('p');
	var messageText = document.createTextNode(message.content);
	textElement.appendChild(messageText);

	messageElement.appendChild(textElement);

	document.querySelector('#messageList').appendChild(messageElement);
	document.querySelector('#messageList').scrollTop = document
	.querySelector('#messageList').scrollHeight;

}