package org.ea.backenddevelopertaskforstartup.repository;

import org.ea.backenddevelopertaskforstartup.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
