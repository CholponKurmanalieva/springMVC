package com.example.demospringmvc.mapper;

import com.example.demospringmvc.model.dto.DescriptionDTO;
import com.example.demospringmvc.model.entity.Description;
import org.mapstruct.Mapper;

/**
 * @author Cholpon Kurmanalieva
 */

@Mapper(config = MapstructAutoWire.class)
public interface DescriptionMapper extends BaseMapper<Description, DescriptionDTO> {
}