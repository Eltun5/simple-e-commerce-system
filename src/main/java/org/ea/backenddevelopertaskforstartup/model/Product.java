package org.ea.backenddevelopertaskforstartup.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", unique = true, nullable = false)
    private String productName;

    @Column(nullable = false)
    private BigDecimal price;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ProductImage> images;
}
