package com.FinalProject.ChrisCosmetic.service.mapper;

import com.FinalProject.ChrisCosmetic.dto.ProductDTO;
import com.FinalProject.ChrisCosmetic.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
}
