@Entity
data class Produto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nome: String,

    val sku: String,

    val preco: Double,

    val quantidadeEmEstoque: Int,

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    val categoria: Categorwia
)