package org.ea.backenddevelopertaskforstartup.dto;

import org.ea.backenddevelopertaskforstartup.model.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record ProductDTO(
        Long id,
        String productName,
        BigDecimal price,
        List<ProductImageDTO> images
) {
    public static ProductDTO from(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getProductName(),
                product.getPrice(),
                product.getImages().stream()
                        .map(ProductImageDTO::from)
                        .collect(Collectors.toList())
        );
    }
}
