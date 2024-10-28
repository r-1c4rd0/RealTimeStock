package com.inventario.websocket

import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller

@Controller
class EstoqueWebSocketController(private val messagingTemplate: SimpMessagingTemplate) {

    // Envia a mensagem para todos os clientes conectados ao tópico "/topic/estoque"
    @SendTo("/topic/estoque")
    fun enviarAtualizacaoEstoque(estoqueAtualizado: String): String {
        return estoqueAtualizado
    }

    // Método para enviar manualmente a mensagem via SimpMessagingTemplate
    fun enviarAlertaEstoqueBaixo(produtoNome: String) {
        val mensagem = "O produto $produtoNome está com estoque baixo!"
        messagingTemplate.convertAndSend("/topic/estoque", mensagem)
    }
}