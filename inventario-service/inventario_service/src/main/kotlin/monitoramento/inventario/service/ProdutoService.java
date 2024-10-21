package monitoramento.inventario.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import monitoramento.inventario.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoDto> listarTodos() {
        return produtoRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public ProdutoDto criarProduto(ProdutoDto produtoDto) {
        Produto produto = fromDto(produtoDto);
        produtoRepository.save(produto);
        return toDto(produto);
    }

    public ProdutoDto atualizarProduto(Long id, ProdutoDto produtoDto) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setNome(produtoDto.getNome());
        produto.setPreco(produtoDto.getPreco());
        produto.setQuantidadeEmEstoque(produtoDto.getQuantidadeEmEstoque());
        produtoRepository.save(produto);
        return toDto(produto);
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    private ProdutoDto toDto(Produto produto) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setSku(produto.getSku());
        dto.setPreco(produto.getPreco());
        dto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque());
        dto.setCategoriaNome(produto.getCategoria().getNome());
        return dto;
    }

    private Produto fromDto(ProdutoDto dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setSku(dto.getSku());
        produto.setPreco(dto.getPreco());
        produto.setQuantidadeEmEstoque(dto.getQuantidadeEmEstoque());
        return produto;
    }
}