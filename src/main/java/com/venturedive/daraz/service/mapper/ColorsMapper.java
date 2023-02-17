package com.venturedive.daraz.service.mapper;

import com.venturedive.daraz.domain.Cars;
import com.venturedive.daraz.domain.Colors;
import com.venturedive.daraz.service.dto.CarsDTO;
import com.venturedive.daraz.service.dto.ColorsDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Colors} and its DTO {@link ColorsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ColorsMapper extends EntityMapper<ColorsDTO, Colors> {
    @Mapping(target = "cars", source = "cars", qualifiedByName = "carsIdSet")
    ColorsDTO toDto(Colors s);

    @Mapping(target = "removeCar", ignore = true)
    Colors toEntity(ColorsDTO colorsDTO);

    @Named("carsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CarsDTO toDtoCarsId(Cars cars);

    @Named("carsIdSet")
    default Set<CarsDTO> toDtoCarsIdSet(Set<Cars> cars) {
        return cars.stream().map(this::toDtoCarsId).collect(Collectors.toSet());
    }
}
