package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
class UserManagementController {


    @PostMapping("/admin/update")
    public ResponseEntity<Void> adminUpdatePost() {

        return ResponseEntity.ok().build(); // Retorna 200 OK
    }


    @PutMapping("/admin/update")
    public ResponseEntity<Void> adminUpdatePut() {

        return ResponseEntity.ok().build(); // Retorna 200 OK
    }


    @PostMapping("/user/create")
    public ResponseEntity<Void> userCreate() {

        return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna 201 Created
    }

    @PutMapping("/user/update")
    public ResponseEntity<Void> userUpdatePut() {

        return ResponseEntity.ok().build(); // Retorna 200 OK
    }

    @DeleteMapping("/user/remove")
    public ResponseEntity<Void> removeUser(@RequestHeader("Role") String role) {
        if ("cliente".equalsIgnoreCase(role)) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Retorna 403 Forbidden
        } else {

            return ResponseEntity.ok().build(); // Retorna 200 OK
        }
    }
}
