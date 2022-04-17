package com.FinalProject.ChrisCosmetic.service.mapper;

import com.FinalProject.ChrisCosmetic.dto.SubCategoryDTO;
import com.FinalProject.ChrisCosmetic.entity.SubCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubCategoryMapper extends EntityMapper<SubCategoryDTO, SubCategory> {
}
