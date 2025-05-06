package org.ea.backenddevelopertaskforstartup.dto;

import org.ea.backenddevelopertaskforstartup.model.ProductImage;

import java.io.Serializable;

public record ProductImageDTO(
        Long id,
        String publicId,
        String imageUrl
) {
    public static ProductImageDTO from(ProductImage image) {
        return new ProductImageDTO(
                image.getId(),
                image.getPublicId(),
                image.getImageUrl()
        );
    }
}
