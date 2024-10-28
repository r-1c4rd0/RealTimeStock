package monitoramento.inventario.service;

import com.inventario.dto.ProdutoDto
import com.inventario.model.Produto
import com.inventario.repository.ProdutoRepository
import org.springframework.stereotype.Service

@Service
class ProdutoService(private val produtoRepository: ProdutoRepository) {

    fun listarTodos(): List<ProdutoDto> =
            produtoRepository.findAll().map { toDto(it) }

    fun criarProduto(produtoDto: ProdutoDto): ProdutoDto {
        val produto = fromDto(produtoDto)
        produtoRepository.save(produto)
        return toDto(produto)
    }

    fun atualizarProduto(id: Long, produtoDto: ProdutoDto): ProdutoDto {
        val produto = produtoRepository.findById(id).orElseThrow { RuntimeException("Produto não encontrado") }
        produto.apply {
            nome = produtoDto.nome
            preco = produtoDto.preco
            quantidadeEmEstoque = produtoDto.quantidadeEmEstoque
        }
        produtoRepository.save(produto)
        return toDto(produto)
    }

    fun deletarProduto(id: Long) = produtoRepository.deleteById(id)

    private fun toDto(produto: Produto):