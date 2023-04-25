package com.example.demospringmvc.mapper;

import java.util.List;

/**
 * @author Cholpon Kurmanalieva
 */

public interface BaseMapper<ENTITY, DTO> {
    ENTITY toEntity(DTO dto);
    DTO toDto(ENTITY entity);
    List<ENTITY> toEntities(List<DTO> dtos);
    List<DTO> toDtos(List<ENTITY> entities);
}