package com.example.Levelup_Backend.controller;

import com.example.Levelup_Backend.model.User;
import com.example.Levelup_Backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User u = userService.getUserById(id);
        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
        return ResponseEntity.ok(u);
    }

    // ✔ Corrección importante: devuelve 404 si el usuario no existe
    @GetMapping("/email")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        User u = userService.getUserByEmail(email);
        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
        return ResponseEntity.ok(u);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User creado = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestBody User user) {

        user.setId(id);
        User actualizado = userService.saveUser(user);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
