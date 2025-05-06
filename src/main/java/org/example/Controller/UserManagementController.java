package org.example.Controller;

import org.example.Repository.UserRepository;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
public class UserManagementController {

    @Autowired
    private UserRepository userRepository;


    @PutMapping("/admin/update")
    public ResponseEntity<?> adminUpdate(@RequestBody User user) {
        if (user.getId() == null || !userRepository.existsById(user.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilizador não encontrado.");
        }

        User existingUser = userRepository.findById(user.getId()).get();

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());

        userRepository.save(existingUser);

        return ResponseEntity.ok("Utilizador atualizado com sucesso.");
    }

    @PostMapping("/user/create")
    public ResponseEntity<?> userCreate(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já está registado.");
        }
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/user/update")
    public ResponseEntity<?> userUpdate(@RequestBody User user) {
        if (!userRepository.existsById(user.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilizador não encontrado.");
        }
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }



    @DeleteMapping("/user/remove")
    public ResponseEntity<?> removeUser(
            @RequestHeader("Role") String role,
            @RequestParam("id") Long id) {

        // VERIFICA PRIMEIRO A ROLE
        if ("CLIENT".equalsIgnoreCase(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Clientes não podem remover utilizadores.");
        }

        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilizador não encontrado.");
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok("Utilizador removido.");
    }


}
