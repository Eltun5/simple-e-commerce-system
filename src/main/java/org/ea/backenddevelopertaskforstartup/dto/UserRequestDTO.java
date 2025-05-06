package org.ea.backenddevelopertaskforstartup.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(@NotBlank String fullName,
                             @Size(min = 8) String password,
                             @Email String email) {
}
