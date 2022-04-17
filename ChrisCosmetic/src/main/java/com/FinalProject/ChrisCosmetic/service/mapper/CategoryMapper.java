package com.FinalProject.ChrisCosmetic.service.mapper;

import com.FinalProject.ChrisCosmetic.dto.CategoryDTO;
import com.FinalProject.ChrisCosmetic.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {
}
