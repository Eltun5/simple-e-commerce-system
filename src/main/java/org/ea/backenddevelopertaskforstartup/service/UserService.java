package org.ea.backenddevelopertaskforstartup.service;

import org.ea.backenddevelopertaskforstartup.dto.UserDTO;
import org.ea.backenddevelopertaskforstartup.dto.UserRequestDTO;
import org.ea.backenddevelopertaskforstartup.model.User;
import org.ea.backenddevelopertaskforstartup.repository.UserRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;


    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @CachePut(value = "users")
    public UserDTO createUser(UserRequestDTO request) {
        User user = User.builder().
                fullName(request.fullName()).
                password(request.password()).
                email(request.email()).
                build();
        return UserDTO.from(repository.save(user));
    }

    @Cacheable(value = "users")
    public UserDTO getUserByEmail(String email) {
        User user = repository.findUserByEmail(email).
                orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return UserDTO.from(user);
    }
}
