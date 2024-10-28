import javax.persistence.*
@Entity
data class Categoria(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id Long = 0,

    val nome: String,

    @OneToMany(mappedBy = "categoria". cascade = [CascadeType.ALL])
    val produtos: List<Produto> = listOf()

)