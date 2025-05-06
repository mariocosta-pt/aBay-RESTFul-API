package org.example.Controller;

import org.example.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
public class ProductManagmentController {

    public static Set<String> productIds = new HashSet<>(Arrays.asList("p1", "p2", "p3"));
    static List<String> categories = Arrays.asList("Electronics", "Books", "Clothing");

    // 1. Criar produto
    @PostMapping("/product/create")
    public ResponseEntity<?> createProduct(@RequestBody Map<String, String> payload) {
        String name = payload.get("name");
        String category = payload.get("category");

        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields");
        }

        if (!categories.contains(category)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid category reference");
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 2. Remover produto
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) {
        if (productIds.contains(id)) {
            productIds.remove(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    // 3. Atualizar produto
    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") String id, @RequestBody Map<String, String> payload) {
        if (productIds.contains(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    // 4. Listar categorias
    @GetMapping("/category/all")
    public ResponseEntity<?> getCategories(@RequestParam(value = "active", required = false) Boolean active) {
        if (Boolean.TRUE.equals(active)) {
            return ResponseEntity.ok(Collections.singletonList("Electronics")); // Simula apenas uma categoria ativa
        }

        return ResponseEntity.ok(categories);
    }
    @PostMapping("/product/search")
    public ResponseEntity<?> searchProducts(@RequestBody Map<String, String> filters) {
        String name = filters.getOrDefault("name", "").trim().toLowerCase();
        String description = filters.getOrDefault("description", "").trim().toLowerCase();
        String category = filters.getOrDefault("category", "").trim();
        String active = filters.getOrDefault("active", "").trim();
        String price = filters.getOrDefault("price", "").trim();

        List<Product> allProducts = Arrays.asList(
                new Product("Headphones", "Fones de ouvido", new BigDecimal("49.99"), "true", "Eletrônicos"),
                new Product("Livro A", "Um ótimo livro", new BigDecimal("14.90"), "true", "Livros"),
                new Product("TV", "Smart TV 4K", new BigDecimal("899.00"), "true", "Eletrônicos"),
                new Product("Cadeira", "Confortável para escritório", new BigDecimal("120.00"), "true", "Móveis")
        );


        // Simula filtro
        List<Product> filtered = new ArrayList<>();
        for (Product p : allProducts) {
            boolean matches = true;

            if (!name.isEmpty() && !p.getName().toLowerCase().contains(name)) {
                matches = false;
            }

            if (!description.isEmpty() && !p.getDescription().toLowerCase().contains(description)) {
                matches = false;
            }

            // Nota: category, active, price não são parte de Product atualmente
            // Poderias estender a classe Product para isso se quiseres suporte real

            if (matches) {
                filtered.add(p);
            }
        }

        return ResponseEntity.ok(filtered);
    }
    // 5. Obter produto por ID
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") String id) {
        if (productIds.contains(id)) {
            return ResponseEntity.ok("Produto existente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }




}