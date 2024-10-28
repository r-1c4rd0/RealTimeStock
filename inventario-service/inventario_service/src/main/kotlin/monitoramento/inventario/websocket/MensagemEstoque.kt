package com.inventario.websocket

data class MensagemEstoque(
    val produtoNome: String,
    val quantidadeAtual: Int,
    val alerta: Boolean = false
)
