package monitoramento.inventario.service

import ProdutoDto
import jakarta.persistence.EntityNotFoundException
import monitoramento.inventario.dto.CategoriaDto
import monitoramento.inventario.model.Categoria
import monitoramento.inventario.model.Produto
import monitoramento.inventario.repository.CategoriaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoriaService @Autowired constructor(
    private val categoriaRepository: CategoriaRepository
) {
    fun findAll(): List<CategoriaDto> {
        return categoriaRepository.findAll().map { it.toDto() }
    }

    fun findById(id: Long): CategoriaDto {
        val categoria = categoriaRepository.findById(id).orElseThrow {
            throw EntityNotFoundException("Categoria não encontrada com id: $id")
        }
        return categoria.toDto()
    }

    fun save(categoriaDTO: CategoriaDto): CategoriaDto {
        val categoria = Categoria(
            nome = categoriaDTO.nome,
            produtos = categoriaDTO.produtos.map {
                Produto(
                    nome = it.nome,
                    sku = it.sku,
                    preco = it.preco,
                    quantidadeEmEstoque = it.quantidadeEmEstoque,
                    quantidadeMinimaEstoque = it.quantidadeMinimaEstoque,
                    categoria = Categoria(
                        id = categoriaDTO.id,
                        nome = categoriaDTO.nome
                    )
                )
            }
        )
        return categoriaRepository.save(categoria).toDto()
    }

    fun update(id: Long, categoriaDto: CategoriaDto): CategoriaDto {
        val categoria = categoriaRepository.findById(id).orElseThrow {
            EntityNotFoundException("Categoria não encontrada com id: $id")
        }

        categoria.nome = categoriaDto.nome
        // Atualizar outros campos se necessário

        // Atualizando os produtos, considerando que novos produtos podem ser adicionados
        categoria.produtos = categoriaDto.produtos.map {
            Produto(
                nome = it.nome,
                sku = it.sku,
                preco = it.preco,
                quantidadeEmEstoque = it.quantidadeEmEstoque,
                quantidadeMinimaEstoque = it.quantidadeMinimaEstoque,
                categoria = categoria // Assumindo que você está vinculando o produto à categoria atual
            )
        }

        return categoriaRepository.save(categoria).toDto()
    }

    fun delete(id: Long) {
        val categoria = categoriaRepository.findById(id).orElseThrow {
            throw EntityNotFoundException("Categoria não encontrada com id: $id")
        }
        categoriaRepository.delete(categoria)
    }

    fun Categoria.toDto(): CategoriaDto {
        return CategoriaDto(
            id = this.id,
            nome = this.nome,
            produtos = this.produtos.map {
                ProdutoDto(
                    it.id,
                    it.nome,
                    it.sku,
                    it.preco,
                    it.quantidadeEmEstoque,
                    it.quantidadeMinimaEstoque,
                    this.nome
                )
            }
        )
    }

}