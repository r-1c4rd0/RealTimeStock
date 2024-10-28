package monitoramento.inventario

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class InventarioServiceApplication

fun main(args: Array<String>) {
	runApplication<InventarioServiceApplication>(*args)
}
