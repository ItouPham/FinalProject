package com.FinalProject.ChrisCosmetic.service.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDTO(E entity);

    List<E> toEntities(List<D> dtoList);

    List<D> toDTOs(List<E> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget E entity, D dto);
}
