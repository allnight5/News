var socket = new SockJS('/ws'); // 서버와 WebSocket 연결
var stompClient = Stomp.over(socket); // STOMP 클라이언트 생성

function connect() {
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function (message) {
            showMessage(message.body);
        });
    });
}

function disconnect() {
    stompClient.disconnect();
    console.log("Disconnected");
}

function sendMessage() {
    var message = document.getElementById('message').value;
    stompClient.send("/app/chat", {}, message);
}

function showMessage(message) {
    var p = document.createElement('p');
    p.appendChild(document.createTextNode(message));
    document.getElementById('messages').appendChild(p);
}