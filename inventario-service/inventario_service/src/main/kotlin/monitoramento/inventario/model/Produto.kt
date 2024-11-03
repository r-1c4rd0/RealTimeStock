package monitoramento.inventario.model

import jakarta.persistence.*

@Entity(name= "Produto")
data class Produto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var nome: String,
    var sku: String,
    var preco: Double,
    var quantidadeEmEstoque: Int,

    var quantidadeMinimaEstoque: Int,

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    var categoria: Categoria
)