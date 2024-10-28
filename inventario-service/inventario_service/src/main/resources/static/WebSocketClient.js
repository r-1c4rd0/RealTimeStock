let stompClient = null;

function connect() {
    let socket = new SockJS('/websocket-inventory');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Conectado: ' + frame);
        stompClient.subscribe('/topic/estoque', function (mensagem) {
            console.log('Atualização do Estoque: ' + JSON.parse(mensagem.body).produtoNome);
        });
    });
}

function sendMessage(produtoNome, quantidadeAtual) {
    stompClient.send("/app/ajuste-estoque", {}, JSON.stringify({
        'produtoNome': produtoNome,
        'quantidadeAtual': quantidadeAtual
    }));
}

connect();
