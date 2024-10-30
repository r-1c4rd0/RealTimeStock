package monitoramento.inventario.service;

import Produto
import ProdutoDto
import monitoramento.inventario.repository.CategoriaRepository
import monitoramento.inventario.repository.ProdutoRepository
import org.springframework.stereotype.Service

@Service
class ProdutoService(
    private val produtoRepository: ProdutoRepository,
    private val categoriaRepository: CategoriaRepository
) {

    fun listarTodos(): List<ProdutoDto> =
            produtoRepository.findAll().map { toDto(it) }

    fun criarProduto(produtoDto: ProdutoDto): ProdutoDto {
        val produto = fromDto(produtoDto)
        produtoRepository.save(produto)
        return toDto(produto)
    }

    fun atualizarProduto(id: Long, produtoDto: ProdutoDto): ProdutoDto {
        val produto = produtoRepository.findById(id).orElseThrow { RuntimeException("Produto não encontrado") }

        val categoria = categoriaRepository.findByNome(produtoDto.categoriaNome)
            ?: throw RuntimeException("Categoria não encontrada")
        val produtoAtualizado = produto.copy(
            nome = produtoDto.nome,
            sku = produtoDto.sku,
            preco = produtoDto.preco,
            quantidadeEmEstoque = produtoDto.quantidadeEmEstoque,
            //categoria = categoria
        )

        produtoRepository.save(produtoAtualizado)
        return toDto(produtoAtualizado)
    }

    fun deletarProduto(id: Long) = produtoRepository.deleteById(id)

    private fun toDto(produto: Produto): ProdutoDto {
        return ProdutoDto(
            id = produto.id,
            nome = produto.nome,
            sku = produto.sku,
            preco = produto.preco,
            quantidadeEmEstoque = produto.quantidadeEmEstoque,
            quantidadeMinimaEstoque = produto.quantidadeMinimaEstoque,
            categoriaNome = produto.categoria.nome
        )
    }

    private fun fromDto(produtoDto: ProdutoDto): Produto {
        val categoria = categoriaRepository.findByNome(produtoDto.categoriaNome)
            ?: throw RuntimeException("Categoria não encontrada")
        return Produto(
            id = produtoDto.id ?: 0L,
            nome = produtoDto.nome,
            sku = produtoDto.sku,
            preco = produtoDto.preco,
            quantidadeEmEstoque = produtoDto.quantidadeEmEstoque,
            quantidadeMinimaEstoque = produtoDto.quantidadeMinimaEstoque,
            categoria = categoria
        )
    }
}