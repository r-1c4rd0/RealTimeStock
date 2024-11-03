package monitoramento.inventario.controller

import monitoramento.inventario.dto.CategoriaDto
import monitoramento.inventario.service.CategoriaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categorias")
class CategoriaController @Autowired constructor(
    private val categoriaService: CategoriaService
) {

    @GetMapping
    fun getAllCategorias(): List<CategoriaDto> {
        return categoriaService.findAll()
    }

    @GetMapping("/{id}")
    fun getCategoriaById(@PathVariable id: Long): ResponseEntity<CategoriaDto> {
        val categoriaDTO = categoriaService.findById(id)
        return ResponseEntity.ok(categoriaDTO)
    }

    @PostMapping
    fun createCategoria(@RequestBody categoriaDTO: CategoriaDto): ResponseEntity<CategoriaDto> {
        val savedCategoria = categoriaService.save(categoriaDTO)
        return ResponseEntity(savedCategoria, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateCategoria(@PathVariable id: Long, @RequestBody categoriaDTO: CategoriaDto): ResponseEntity<CategoriaDto> {
        val updatedCategoria = categoriaService.update(id, categoriaDTO)
        return ResponseEntity.ok(updatedCategoria)
    }

    @DeleteMapping("/{id}")
    fun deleteCategoria(@PathVariable id: Long): ResponseEntity<Void> {
        categoriaService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}