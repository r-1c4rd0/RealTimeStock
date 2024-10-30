import monitoramento.inventario.repository.ProdutoRepository
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class MonitoramentoEstoqueService(private val produtoRepository: ProdutoRepository) {

    private val logger = LoggerFactory.getLogger(MonitoramentoEstoqueService::class.java)

    // Executa a cada 1 minuto (pode ser ajustado conforme a necessidade)
    @Scheduled(fixedRate = 60000)
    fun verificarEstoque() {
        val produtos = produtoRepository.findAll()
        produtos.forEach { produto ->
            if (produto.quantidadeEmEstoque < produto.quantidadeMinimaEstoque) {
                logger.warn("Alerta: O produto ${produto.nome} está com estoque baixo! Quantidade atual: ${produto.quantidadeEmEstoque}")
                // Aqui podemos implementar mais ações, como enviar um e-mail ou notificação.
            }
        }
    }
}