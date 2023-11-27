package com.example.hw_spring_store.service;

import com.example.hw_spring_store.dto.ProductBodyDto;
import com.example.hw_spring_store.dto.ProductData;
import com.example.hw_spring_store.entities.Brand;
import com.example.hw_spring_store.entities.ProductsEntity;
import com.example.hw_spring_store.entities.SubSection;
import com.example.hw_spring_store.exceptions.Resp;
import com.example.hw_spring_store.repository.ProductsRepository;
import com.example.hw_spring_store.specifications.ProductSpecifications;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {
  private final ProductsRepository productsRepository;
  private final BrandService brandService;
  private final SectionService sectionService;

  public Page<ProductsEntity> getList(
    String name,
    String category,
    String brand,
    Double priceMin,
    Double priceMax,
    int offset,
    int limit
  ) {
    Specification<ProductsEntity> spec;

    spec = ProductSpecifications.nameLike(name);

    if (brand != null && !brand.isEmpty()) {
      spec = spec.and(ProductSpecifications.hasBrand(brand));
    }

    if (category != null && !category.isEmpty()) {
      spec = spec.and(ProductSpecifications.hasCategory(category));
    }

    spec = spec.and(ProductSpecifications.priceInRange(priceMin, priceMax));

    PageRequest pageable = PageRequest.of(offset, limit);
    return productsRepository.findAll(spec, pageable);
  }
  public Optional<ProductsEntity> getProductById(Long id) {
    return productsRepository.findById(id);
  }

  public ResponseEntity<?> updateProduct(Long id, ProductBodyDto productBodyDto) {
    Optional<ProductsEntity> productsEntity = getProductById(id);

    if (productsEntity.isEmpty()) {
      return new ResponseEntity<>(new Resp(HttpStatus.BAD_REQUEST.value(), "Ошибка данных"), HttpStatus.BAD_REQUEST);
    }


    ProductsEntity product = productsEntity.get();
    product.setId(id);
    product.setName(productBodyDto.getName());
    product.setDescription(productBodyDto.getDescription());
    product.setPrice(productBodyDto.getPrice());
    product.setImagePath(productBodyDto.getImagePath());

    ProductData productData = getProductData(productBodyDto.getSubSectionId(), productBodyDto.getBrandId());

    product.setBrand(productData.getBrand());
    product.setSubSection(productData.getSubSection());
    product.setActive(productBodyDto.isActive());

    LocalDateTime now = LocalDateTime.now();

    product.setUpdatedAt(now);

    productsRepository.save(product);
    return new ResponseEntity<>(new Resp(HttpStatus.CREATED.value(), "Продукт успешно изменен"), HttpStatus.CREATED);
  }

  public ResponseEntity<?> createProduct(ProductBodyDto productBodyDto) {
    ProductsEntity product = new ProductsEntity();

    product.setName(productBodyDto.getName());
    product.setDescription(productBodyDto.getDescription());
    product.setPrice(productBodyDto.getPrice());
    product.setImagePath(productBodyDto.getImagePath());

    ProductData productData = getProductData(productBodyDto.getSubSectionId(), productBodyDto.getBrandId());

    product.setBrand(productData.getBrand());
    product.setSubSection(productData.getSubSection());

    product.setActive(true);

    LocalDateTime now = LocalDateTime.now();

    product.setCreatedAt(now);
    product.setUpdatedAt(now);

    productsRepository.save(product);
    return new ResponseEntity<>(new Resp(HttpStatus.CREATED.value(), "Продукт успешно создан"), HttpStatus.CREATED);
  }

  private ProductData getProductData(Long subSectionId, Long brandId) {
    Optional<Brand> brand = brandService.getBrandById(brandId);
    Optional<SubSection> subSection = sectionService.getSubSectionById(subSectionId);

    if (brand.isEmpty() || subSection.isEmpty()) {
      return new ProductData();
    }

    ProductData productData = new ProductData();
    productData.setBrand(brand.get());
    productData.setSubSection(subSection.get());

    return productData;
  }
}
