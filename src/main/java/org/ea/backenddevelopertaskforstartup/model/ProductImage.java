package org.ea.backenddevelopertaskforstartup.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id", nullable = false)
    private String publicId;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
}
