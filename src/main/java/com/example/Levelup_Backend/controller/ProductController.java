package com.example.Levelup_Backend.controller;

import com.example.Levelup_Backend.model.Product;
import com.example.Levelup_Backend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Productos", description = "Operaciones relacionadas con el catálogo de productos")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Listar productos", description = "Obtiene todos los productos disponibles")
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @Operation(summary = "Obtener producto por ID", description = "Busca un producto por su identificador único")
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @Operation(summary = "Crear producto", description = "Agrega un nuevo producto al catálogo")
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @Operation(summary = "Actualizar producto", description = "Modifica un producto existente")
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return productService.saveProduct(product);
    }

    @Operation(summary = "Eliminar producto", description = "Elimina un producto del catálogo por su ID")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
