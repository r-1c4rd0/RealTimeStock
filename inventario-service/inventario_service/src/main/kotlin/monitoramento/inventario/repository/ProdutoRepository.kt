package monitoramento.inventario.repository;



import monitoramento.inventario.model.Produto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
@Repository
interface ProdutoRepository : JpaRepository<Produto, Long>