package monitoramento.inventario.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.EqualsAndHashCode

@Entity
@Table(name = "categoria")
@Data
@AllArgsConstructor
@EqualsAndHashCode
data class Categoria(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var nome: String,

    @OneToMany(mappedBy = "categoria", cascade = [CascadeType.ALL])
    var produtos: List<Produto> = listOf()
)
