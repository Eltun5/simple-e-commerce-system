package org.ea.backenddevelopertaskforstartup.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ProductRequestDTO(@NotBlank String productName,
                                @Min(0) BigDecimal price) {
}
