package monitoramento.inventario.controller;

import ProdutoDto
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import monitoramento.inventario.service.ProdutoService;

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