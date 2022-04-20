package com.FinalProject.ChrisCosmetic.service;

import com.FinalProject.ChrisCosmetic.dto.ProductDTO;
import com.FinalProject.ChrisCosmetic.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);

    List<Product> findAllProduct();

    List<Product> findProductBySubCategoryID(Long id);

    List<Product> findProductsByCategoryID(Long id);

    void save(Product product);

    void delete(Long id);
}
