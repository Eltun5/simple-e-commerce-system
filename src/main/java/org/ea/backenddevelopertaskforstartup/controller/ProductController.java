package org.ea.backenddevelopertaskforstartup.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.ea.backenddevelopertaskforstartup.dto.ProductDTO;
import org.ea.backenddevelopertaskforstartup.dto.ProductRequestDTO;
import org.ea.backenddevelopertaskforstartup.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@Tag(name = "Product API", description = "Operations related to product management")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @Operation(
            summary = "Create a new product",
            description = "Creates a new product with optional images"
    )
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(
            @ModelAttribute ProductRequestDTO productRequestDTO,
            @RequestParam(required = false) List<MultipartFile> images) throws IOException {
        return ResponseEntity.ok(service.createProduct(productRequestDTO, images));
    }

    @Operation(
            summary = "Add image to product",
            description = "Adds a new image to an existing product by its ID"
    )
    @PatchMapping("/add-image/{id}")
    public ResponseEntity<ProductDTO> addImage(
            @PathVariable Long id,
            @RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok(service.addImage(id, image));
    }

    @Operation(
            summary = "Remove image from product",
            description = "Removes an image from a product by product ID and image ID"
    )
    @PatchMapping("/remove-image/{productId}{imageId}")
    public ResponseEntity<ProductDTO> removeImage(
            @PathVariable Long productId,
            @PathVariable Long imageId) throws IOException {
        return ResponseEntity.ok(service.removeImage(productId, imageId));
    }

    @Operation(
            summary = "Get all products",
            description = "Returns a list of all available products"
    )
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @Operation(
            summary = "Get product by ID",
            description = "Fetches a specific product by its ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @Operation(
            summary = "Update product",
            description = "Updates product data by ID"
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO requestDTO) {
        return ResponseEntity.ok(service.updateProduct(id, requestDTO));
    }

    @Operation(
            summary = "Delete product",
            description = "Deletes a product by its ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
