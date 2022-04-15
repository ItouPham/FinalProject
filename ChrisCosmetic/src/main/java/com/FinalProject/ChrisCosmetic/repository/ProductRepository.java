package com.FinalProject.ChrisCosmetic.repository;

import com.FinalProject.ChrisCosmetic.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
