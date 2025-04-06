package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserManagementController {

    // ✅ Admin faz POST para atualizar algo (pode ser detalhes do sistema, etc.)
    @PostMapping("/admin/update")
    public ResponseEntity<Void> adminUpdatePost() {
        return ResponseEntity.ok().build(); // Retorna 200 OK
    }

    // ✅ Admin faz PUT para atualização idempotente
    @PutMapping("/admin/update")
    public ResponseEntity<Void> adminUpdatePut() {
        return ResponseEntity.ok().build(); // Retorna 200 OK
    }

    // ✅ Cliente cria utilizador
    @PostMapping("/user/create")
    public ResponseEntity<Void> userCreate() {
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201 Created
    }

    // ✅ Cliente faz update
    @PutMapping("/user/update")
    public ResponseEntity<Void> userUpdatePut() {
        return ResponseEntity.ok().build(); // Retorna 200 OK
    }

    // ✅ Delete com lógica de role no header
    @DeleteMapping("/user/remove")
    public ResponseEntity<Void> removeUser(@RequestHeader("Role") String role) {
        if ("cliente".equalsIgnoreCase(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 se for cliente
        } else {
            return ResponseEntity.ok().build(); // 200 OK se for admin (ou outro role)
        }
    }
}
