package monitoramento.inventario.repository

import Categoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoriaRepository : JpaRepository<Categoria, Long>{
    fun findByNome(nome: String): Categoria?
}
