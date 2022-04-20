package com.FinalProject.ChrisCosmetic.repository;

import com.FinalProject.ChrisCosmetic.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM `final-project`.product where sub_category_id = (?1)", nativeQuery = true)
    List<Product> findProductsBySubCategoryId(Long id);

    @Query(value = "SELECT * FROM `final-project`.product p \n" +
            "join `final-project`.sub_category sc on p.sub_category_id = sc.sub_category_id where category_id = (?1)", nativeQuery = true)
    List<Product> findProductsByCategoryID(Long id);
}
