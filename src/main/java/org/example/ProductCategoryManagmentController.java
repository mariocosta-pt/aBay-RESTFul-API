package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductCategoryManagmentController {

    private static Set<String> categoryIds = new HashSet<>(Arrays.asList("c1", "c2", "c3"));
    private static List<String> mainCategories = Arrays.asList("Electronics", "Books", "Clothing");

    // 1. Criar Categoria
    @PostMapping("/product/category/create")
    public ResponseEntity<?> createCategory(@RequestBody Map<String, String> payload) {
        String name = payload.get("name");
        String description = payload.get("description");

        if (name == null || name.trim().isEmpty() || description == null || description.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields");
        }

        // Simula sucesso
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 2. Atualizar Categoria
    @PutMapping("/product/category/update")
    public ResponseEntity<?> updateCategory(@RequestBody Map<String, String> payload) {
        String id = payload.get("id");
        String newName = payload.get("name");

        if (id == null || id.trim().isEmpty() || newName == null || newName.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields");
        }

        if (!categoryIds.contains(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }

        // Simula sucesso
        return ResponseEntity.ok().build();
    }

    // 3. Remover Categoria
    @DeleteMapping("/product/category/remove")
    public ResponseEntity<?> deleteCategory(@RequestParam("id") String id) {
        if (!categoryIds.contains(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }

        categoryIds.remove(id);
        return ResponseEntity.ok().build();
    }

    // 4. Criar Subcategoria
    @PostMapping("/product/category/sub/create")
    public ResponseEntity<?> createSubCategory(@RequestBody Map<String, String> payload) {
        String name = payload.get("name");
        String description = payload.get("description");
        String parentCategory = payload.get("parent");

        if (name == null || name.trim().isEmpty() ||
                description == null || description.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields");
        }

        if (!mainCategories.contains(parentCategory)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parent category not found");
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
