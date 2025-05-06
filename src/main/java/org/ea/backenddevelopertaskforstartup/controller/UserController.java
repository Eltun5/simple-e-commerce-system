package org.ea.backenddevelopertaskforstartup.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.ea.backenddevelopertaskforstartup.dto.UserDTO;
import org.ea.backenddevelopertaskforstartup.dto.UserRequestDTO;
import org.ea.backenddevelopertaskforstartup.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cache/user")
@Tag(name = "User API", description = "Operations for managing users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(
            summary = "Create a new user",
            description = "Creates a new user based on the provided request data"
    )
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserRequestDTO request){
        return ResponseEntity.ok(service.createUser(request));
    }

    @Operation(
            summary = "Get user by email",
            description = "Fetches a user based on the given email address"
    )
    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getByEmail(@PathVariable String email){
        return ResponseEntity.ok(service.getUserByEmail(email));
    }
}
