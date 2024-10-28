package com.inventario.websocket

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class WebSocketMessageHandler {

    @MessageMapping("/ajuste-estoque")
    @SendTo("/topic/estoque")
    fun ajustarEstoque(produto: MensagemEstoque): MensagemEstoque {
        // Lógica de ajuste de estoque
        // Retorna a nova informação de estoque para ser enviada a todos os clientes
        return produto
    }
}
