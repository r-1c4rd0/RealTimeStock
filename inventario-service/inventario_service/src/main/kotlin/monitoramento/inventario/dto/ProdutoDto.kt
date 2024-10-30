data class ProdutoDto(
    val id: Long?,
    val nome: String,
    val sku: String,
    val preco: Double,
    val quantidadeEmEstoque: Int,
    val quantidadeMinimaEstoque: Int,
    val categoriaNome: String
)