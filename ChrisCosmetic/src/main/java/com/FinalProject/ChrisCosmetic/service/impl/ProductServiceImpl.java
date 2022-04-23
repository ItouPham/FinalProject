package com.FinalProject.ChrisCosmetic.service.impl;

import com.FinalProject.ChrisCosmetic.dto.ProductDTO;
import com.FinalProject.ChrisCosmetic.entity.Account;
import com.FinalProject.ChrisCosmetic.entity.Product;
import com.FinalProject.ChrisCosmetic.repository.ProductRepository;
import com.FinalProject.ChrisCosmetic.service.ProductService;
import com.FinalProject.ChrisCosmetic.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Page<Product> findAllProduct(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1,10);
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findProductBySubCategoryID(Long id) {
        return productRepository.findProductsBySubCategoryId(id);
    }

    @Override
    public List<Product> findProductsByCategoryID(Long id) {
        return productRepository.findProductsByCategoryID(id);
    }


    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }
}
