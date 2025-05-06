package org.ea.backenddevelopertaskforstartup.dto;

import org.ea.backenddevelopertaskforstartup.model.User;

import java.io.Serializable;

public record UserDTO(String fullName, String email) implements Serializable {
    public static UserDTO from(User user){
        return new UserDTO(user.getFullName(), user.getEmail());
    }
}
