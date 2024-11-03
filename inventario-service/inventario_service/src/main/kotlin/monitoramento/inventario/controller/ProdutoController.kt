package monitoramento.inventario.controller;

import ProdutoDto
import monitoramento.inventario.service.ProdutoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/produtos")
class ProdutoController(private val produtoService: ProdutoService) {

    @GetMapping
    fun listarTodos(): kotlin.collections.List<ProdutoDto> = produtoService.listarTodos()

    @PostMapping
    fun criarProduto(@RequestBody produtoDto: ProdutoDto): ProdutoDto = produtoService.criarProduto(produtoDto)

    @PutMapping("/{id}")
    fun atualizarProduto(@PathVariable id: Long, @RequestBody produtoDto: ProdutoDto): ProdutoDto =
            produtoService.atualizarProduto(id, produtoDto)

    @DeleteMapping("/{id}")
    fun deletarProduto(@PathVariable id: Long) = produtoService.deletarProduto(id)
}