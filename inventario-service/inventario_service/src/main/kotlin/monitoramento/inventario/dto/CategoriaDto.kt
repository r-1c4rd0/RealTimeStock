package monitoramento.inventario.dto

import ProdutoDto
import lombok.Data

@Data
data class CategoriaDto(
    val id: Long = 0,
    val nome: String,
    val produtos: List<ProdutoDto> = listOf()
)