package org.ea.backenddevelopertaskforstartup.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.ea.backenddevelopertaskforstartup.dto.ProductDTO;
import org.ea.backenddevelopertaskforstartup.dto.ProductRequestDTO;
import org.ea.backenddevelopertaskforstartup.model.Product;
import org.ea.backenddevelopertaskforstartup.model.ProductImage;
import org.ea.backenddevelopertaskforstartup.repository.ProductImageRepository;
import org.ea.backenddevelopertaskforstartup.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final Cloudinary cloudinary;

    private final ProductRepository repository;

    private final ProductImageRepository imageRepository;

    public ProductService(Cloudinary cloudinary, ProductRepository repository, ProductImageRepository imageRepository) {
        this.cloudinary = cloudinary;
        this.repository = repository;
        this.imageRepository = imageRepository;
    }

    public ProductDTO createProduct(ProductRequestDTO productRequestDTO, List<MultipartFile> images) throws IOException {
        Product product = Product.builder()
                .productName(productRequestDTO.productName())
                .price(productRequestDTO.price())
                .build();

        List<ProductImage> productImages = new ArrayList<>();
        for (MultipartFile file : images) {
            ProductImage image = upload(file);
            productImages.add(image);
        }
        product.setImages(productImages);
        return ProductDTO.from(repository.save(product));
    }

    public ProductDTO addImage(Long id, MultipartFile file) throws IOException {
        Product product = repository.findById(id).
                orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        ProductImage image = upload(file);
        product.getImages().add(image);
        return ProductDTO.from(repository.save(product));
    }

    public ProductDTO removeImage(Long productId, Long imageId) throws IOException {
        Product product = repository.findById(productId).
                orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        ProductImage image = imageRepository.getReferenceById(imageId);

        cloudinary.uploader().destroy(image.getPublicId(), ObjectUtils.emptyMap());

        product.getImages().remove(image);
        imageRepository.delete(image);

        return ProductDTO.from(repository.save(product));
    }

    public List<ProductDTO> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(ProductDTO::from)
                .toList();
    }

    public ProductDTO getProductById(Long id) {
        return ProductDTO.from(repository.findById(id).
                orElseThrow(() -> new RuntimeException("Product not found with id: " + id)));
    }

    public ProductDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        Product product = repository.findById(id).
                orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setProductName(productRequestDTO.productName());
        product.setPrice(productRequestDTO.price());
        return ProductDTO.from(repository.save(product));
    }

    public void deleteProduct(Long id) {
        Product product = repository.findById(id).
                orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        repository.delete(product);
    }

    private ProductImage upload(MultipartFile file) throws IOException {
        var upload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String url = upload.get("secure_url").toString();
        String publicId = upload.get("public_id").toString();

        ProductImage image = ProductImage.builder()
                .imageUrl(url)
                .publicId(publicId)
                .build();

        return imageRepository.save(image);
    }
}
