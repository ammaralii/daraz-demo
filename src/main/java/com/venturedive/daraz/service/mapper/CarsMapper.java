package com.venturedive.daraz.service.mapper;

import com.venturedive.daraz.domain.Cars;
import com.venturedive.daraz.service.dto.CarsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cars} and its DTO {@link CarsDTO}.
 */
@Mapper(componentModel = "spring")
public interface CarsMapper extends EntityMapper<CarsDTO, Cars> {}
