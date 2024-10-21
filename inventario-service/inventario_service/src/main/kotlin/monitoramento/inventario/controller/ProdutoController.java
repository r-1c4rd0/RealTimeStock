package monitoramento.inventario.controller;

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
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<ProdutoDto> listarTodos() {
        return produtoService.listarTodos();
    }

    @PostMapping
    public ProdutoDto criarProduto(@RequestBody ProdutoDto produtoDto) {
        return produtoService.criarProduto(produtoDto);
    }

    @PutMapping("/{id}")
    public ProdutoDto atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDto produtoDto) {
        return produtoService.atualizarProduto(id, produtoDto);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }
}